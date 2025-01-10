import java.util.List;
import java.util.Scanner;

public class TaskEditor {
    private List<Task> tasks;

    public TaskEditor(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void editTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks available to edit.");
            return;
        }

        // Display all tasks with dependencies
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task);
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
        System.out.println("║  6. Add dependencies                ║");
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
                setTaskDependencies(scanner, task);
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

    private void setTaskDependencies(Scanner scanner, Task task) {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║             ★ Manage Task Dependencies ★          ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.println(" Current dependencies: " + task.getDependencies());
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║  1. Add Dependency                                ║");
        System.out.println("║  2. Remove Dependency                             ║");
        System.out.println("║  3. Cancel                                        ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.print("☆ Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter the task number it depends on: ");
                int dependsOnTaskNum = scanner.nextInt();
                scanner.nextLine();

                if (dependsOnTaskNum <= 0 || dependsOnTaskNum > tasks.size()) {
                    System.out.println("\nInvalid task number.");
                } else if (task == tasks.get(dependsOnTaskNum - 1)) {
                    System.out.println("\nA task cannot depend on itself.");
                } else {
                    Task dependentTask = tasks.get(dependsOnTaskNum - 1);

                    if (dependentTask.getDependencies().contains(task.getTitle())) {
                        System.out.println("\nCircular dependency detected! Task cannot depend on each other.");
                    } else if (hasCircularDependency(task, dependentTask)) {
                        System.out.println("\nCircular dependency detected through multi-level dependencies!");
                    } else {
                        task.addDependency(dependentTask.getTitle());
                        System.out.println("Dependency added successfully!");
                    }
                }
                break;
            case 2:
                System.out.println("Current dependencies: " + task.getDependencies());
                System.out.print("Enter the dependency to remove: ");
                String dependencyToRemove = scanner.nextLine();

                task.removeDependency(dependencyToRemove);
                System.out.println("Dependency removed successfully!");
                break;
            case 3:
                System.out.println("Canceling dependency edit.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    private boolean hasCircularDependency(Task currentTask, Task targetTask) {
        if (currentTask == targetTask) {
            return true; // Direct circular dependency
        }

        for (String dependencyTitle : targetTask.getDependencies()) {
            Task dependencyTask = tasks.stream()
                    .filter(t -> t.getTitle().equals(dependencyTitle))
                    .findFirst()
                    .orElse(null);
            if (dependencyTask != null && hasCircularDependency(currentTask, dependencyTask)) {
                return true; // Circular dependency found
            }
        }

        return false;
    }


}
