import java.util.*;


public class TaskEditor {
    private List<Task> tasks;
    private Map<Integer, Integer> dependencies;


    public TaskEditor(List<Task> tasks) {
        this.tasks = tasks;
        this.dependencies = new HashMap<>();
    }

    public void editTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks available to edit.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }

        System.out.print("\nEnter the task number to edit: ");
        int taskNum = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (taskNum <= 0 || taskNum > tasks.size()) {
            System.out.println("\nInvalid task number.");
            return;
        }

        Task task = tasks.get(taskNum - 1);

        System.out.println();
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║   ★ What would you like to edit? ★  ║");
        System.out.println("╠═════════════════════════════════════╣");
        System.out.println("║  1. Title                           ║");
        System.out.println("║  2. Description                     ║");
        System.out.println("║  3. Due Date                        ║");
        System.out.println("║  4. Category                        ║");
        System.out.println("║  5. Priority                        ║");
        System.out.println("║  6. Add Dependency                  ║");
        System.out.println("║  7. Cancel                          ║");
        System.out.println("╚═════════════════════════════════════╝");
        System.out.print("☆ Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter new title: ");
                task.setTitle(scanner.nextLine());
                break;
            case 2:
                System.out.print("Enter new description: ");
                task.setDescription(scanner.nextLine());
                break;
            case 3:
                System.out.print("Enter new due date (YYYY-MM-DD): ");
                String newDueDate = scanner.nextLine();
                while (!newDueDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    System.out.print("Invalid format. Enter new due date (YYYY-MM-DD): ");
                    newDueDate = scanner.nextLine();
                }
                task.setDueDate(newDueDate);
                break;
            case 4:
                System.out.print("Enter new category: ");
                task.setCategory(scanner.nextLine());
                break;
            case 5:
                System.out.print("Enter new priority (Low, Medium, High): ");
                String newPriority = scanner.nextLine();
                while (!newPriority.equalsIgnoreCase("Low") &&
                        !newPriority.equalsIgnoreCase("Medium") &&
                        !newPriority.equalsIgnoreCase("High")) {
                    System.out.print("Invalid priority. Enter 'Low', 'Medium', or 'High': ");
                    newPriority = scanner.nextLine();
                }
                task.setPriority(newPriority);
                break;
            case 6:
                System.out.print("\nEnter the task number to depend on: ");
                int dependsOnNum = scanner.nextInt();
                if (addDependency(taskNum - 1, dependsOnNum - 1)) {
                    System.out.println("Dependency added successfully!");
                }
                break;
            case 7:
                System.out.println("Edit canceled.");
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("\nTask updated successfully!");
    }
    
    public boolean addDependency(int taskId, int dependsOnId) {
        if (taskId < 0 || taskId >= tasks.size() || dependsOnId < 0 || dependsOnId >= tasks.size()) {
            System.out.println("Invalid task ID(s) provided!");
            return false;
        }
        if (hasCycle(taskId, dependsOnId)) {
            System.out.println("Error: Adding this dependency creates a cycle. Dependency not added.");
            return false;
        }
        dependencies.put(taskId, dependsOnId);
        System.out.println("Task \"" + tasks.get(taskId).getTitle() + "\" now depends on \"" + tasks.get(dependsOnId).getTitle() + "\".");
        return true;
    }
    
    private boolean hasCycle(int taskId, int dependsOnId) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> stack = new HashSet<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry : dependencies.entrySet()) {
            graph.computeIfAbsent(entry.getKey(), k -> new ArrayList<>()).add(entry.getValue());
        }
        graph.computeIfAbsent(taskId, k -> new ArrayList<>()).add(dependsOnId);

        return dfs(taskId, graph, visited, stack);
    }

    private boolean dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited, Set<Integer> stack) {
        if (stack.contains(node)) return true;
        if (visited.contains(node)) return false;

        visited.add(node);
        stack.add(node);

        for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (dfs(neighbor, graph, visited, stack)) return true;
        }

        stack.remove(node);
        return false;
    }
    
}
