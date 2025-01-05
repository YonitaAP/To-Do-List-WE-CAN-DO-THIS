import java.util.*;

public class SearchSortTasks {
    private List<Task> tasks;

    public SearchSortTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Debugging: print sorted tasks
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

    private int mapPriorityToValue(String priority) {
        switch (priority.toLowerCase()) {
            case "high": return 3;
            case "medium": return 2;
            case "low": return 1;
            default: return Integer.MAX_VALUE;
        }
    }

    public void searchTasksByTitle(String title) {
        boolean found = false;

        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                if (!found) {  // Print the "Found Task" message only once
                    System.out.println("\nFound Task(s):");
                    found = true;
                }
                System.out.println(task);  // Print the task
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
                System.out.println("\nFound Task: " + task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("\nNo task found in the category: " + category);
        }
    }

    private void printTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
