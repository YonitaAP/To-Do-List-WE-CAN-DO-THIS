import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SearchSortTasks {
    private List<Task> tasks;

    public SearchSortTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Methods to handle sorting directly
    public void SortTasks(Scanner scanner) {
        System.out.println("Sort by:");
        System.out.println("1. Due Date (Ascending)");
        System.out.println("2. Due Date (Descending)");
        System.out.println("3. Priority (High to Low)");
        System.out.println("4. Priority (Low to High)");
        System.out.print("Enter your choice: ");
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
        System.out.println("\nSearch by:");
        System.out.println("1. Title");
        System.out.println("2. Category");
        System.out.print("Enter your choice: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (searchChoice) {
            case 1:
                System.out.print("Enter the task title: ");
                String title = scanner.nextLine();
                searchTasksByTitle(title);
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
        System.out.println("\nTasks sorted by due date (ascending):");
        printTasks();
    }

    public void sortTasksByDueDateDescending() {
        tasks.sort((task1, task2) -> Integer.compare(task2.getDueDateAsInteger(), task1.getDueDateAsInteger()));
        System.out.println("\nTasks sorted by due date (descending):");
        printTasks();
    }

    public void sortTasksByPriorityHighToLow() {
        tasks.sort((task1, task2) -> Integer.compare(mapPriorityToValue(task2.getPriority()), mapPriorityToValue(task1.getPriority())));
        System.out.println("\nTasks sorted by priority (high to low):");
        printTasks();
    }

    public void sortTasksByPriorityLowToHigh() {
        tasks.sort((task1, task2) -> Integer.compare(mapPriorityToValue(task1.getPriority()), mapPriorityToValue(task2.getPriority())));
        System.out.println("\nTasks sorted by priority (low to high):");
        printTasks();
    }

    // Searching methods
    public void searchTasksByTitle(String title) {
        boolean found = false;

        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                if (!found) {
                    System.out.println("\nFound Task(s):");
                    found = true;
                }
                System.out.println(task);
            }
        }

        if (!found) {
            System.out.println("\nNo task found with the title: " + title);
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
