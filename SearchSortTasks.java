import java.util.*;

public class SearchSortTasks {
    private List<Task> tasks;

    public SearchSortTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Methods to handle sorting directly
    public void SortTasks(Scanner scanner) {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║         ★ Sort by ★          ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  1. Due Date (Ascending)     ║");
        System.out.println("║  2. Due Date (Descending)    ║");
        System.out.println("║  3. Priority (High to Low)   ║");
        System.out.println("║  4. Priority (Low to High)   ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("☆ Enter your choice: ");

        int sortChoice = scanner.nextInt();

        switch (sortChoice) {
            case 1:
                sortTasksByDueDateAscending();
                break;
            case 2:
                sortTasksByDueDateDescending();
                break;
            case 3:
                sortTasksByPriorityHighToLow();
                break;
            case 4:
                sortTasksByPriorityLowToHigh();
                break;
            default:
                System.out.println("\nInvalid sorting option.");
        }
    }

    public void SearchTasks(Scanner scanner) {
        System.out.println("╔═══════════════════════════╗");
        System.out.println("║       ★ Search by ★       ║");
        System.out.println("╠═══════════════════════════╣");
        System.out.println("║ 1. Title/Description      ║");
        System.out.println("║ 2. Category               ║");
        System.out.println("╚═══════════════════════════╝");
        System.out.print("☆ Enter your choice: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine(); 


        switch (searchChoice) {
            case 1:
                System.out.print("Enter the keyword (title/description): ");
                String keyword = scanner.nextLine();
                searchTasksByTitleOrDescription(keyword);
                break;
            case 2:
                System.out.print("Enter the task category: ");
                String category = scanner.nextLine();
                searchTasksByCategory(category);
                break;
            default:
                System.out.println("\nInvalid search option.");
        }
    }

    // Sorting methods
    public void sortTasksByDueDateAscending() {
        tasks.sort(Comparator.comparingInt(Task::getDueDateAsInteger));
        System.out.println();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Tasks sorted by due date (ascending)  ║");
        System.out.println("╚════════════════════════════════════════╝");
        printTasks();
    }

    public void sortTasksByDueDateDescending() {
        tasks.sort((task1, task2) -> Integer.compare(task2.getDueDateAsInteger(), task1.getDueDateAsInteger()));
        System.out.println();        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Tasks sorted by due date (descending) ║");
        System.out.println("╚════════════════════════════════════════╝");
        printTasks();
    }

    public void sortTasksByPriorityHighToLow() {
        tasks.sort((task1, task2) -> Integer.compare(mapPriorityToValue(task2.getPriority()), mapPriorityToValue(task1.getPriority())));
        System.out.println();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║ Tasks sorted by priority (high to low) ║");
        System.out.println("╚════════════════════════════════════════╝");
        printTasks();
    }

    public void sortTasksByPriorityLowToHigh() {
        tasks.sort((task1, task2) -> Integer.compare(mapPriorityToValue(task1.getPriority()), mapPriorityToValue(task2.getPriority())));
        System.out.println();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║ Tasks sorted by priority (low to high) ║");
        System.out.println("╚════════════════════════════════════════╝");        
        printTasks();
    }

    // Searching methods
    public void searchTasksByTitleOrDescription(String keyword) {
        boolean found = false;
        String lowerKeyword = keyword.toLowerCase(); // Normalize the keyword for case-insensitive search

        for (Task task : tasks) {
            // Check if the title or description contains the keyword
            if (task.getTitle().toLowerCase().contains(lowerKeyword) || task.getDescription().toLowerCase().contains(lowerKeyword)) {
                if (!found) {
                    System.out.println("\nFound Task(s):");
                    found = true;
                }
                System.out.println(task);
            }
        }

        if (!found) {
            System.out.println("\nNo task found with the keyword: " + keyword);
        }
    }

    public void searchTasksByCategory(String category) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getCategory().equalsIgnoreCase(category)) {
                if (!found) {
                    System.out.println("\nFound Task(s):");
                    found = true;
                }
                System.out.println(task);
            }
        }

        if (!found) {
            System.out.println("\nNo task found in the category: " + category);
        }
    }

    // Utility methods
    private int mapPriorityToValue(String priority) {
        switch (priority.toLowerCase()) {
            case "high":
                return 3;
            case "medium":
                return 2;
            case "low":
                return 1;
            default:
                return Integer.MAX_VALUE;
        }
    }

    private void printTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
