
import java.util.*;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    //on progress, still error
   /* public void sortTasksByDueDateAscending() {
        tasks.sort(Comparator.comparingInt(Task::getDueDateAsInteger));
        System.out.println("\nTasks sorted by due date (ascending).");
    }

    public void sortTasksByDueDateDescending() {
        tasks.sort((task1, task2) -> Integer.compare(task2.getDueDateAsInteger(), task1.getDueDateAsInteger()));
        System.out.println("\nTasks sorted by due date (descending).");
    }

    public void sortTasksByPriorityHighToLow() {
        tasks.sort((task1, task2) -> Integer.compare(mapPriorityToValue(task2.getPriority()), mapPriorityToValue(task1.getPriority())));
        System.out.println("\nTasks sorted by priority (high to low).");
    }

    public void sortTasksByPriorityLowToHigh() {
        tasks.sort((task1, task2) -> Integer.compare(mapPriorityToValue(task1.getPriority()), mapPriorityToValue(task2.getPriority())));
        System.out.println("\nTasks sorted by priority (low to high).");
    }
*/
    // Map priority to numeric value for comparison
    private int mapPriorityToValue(String priority) {
        switch (priority.toLowerCase()) {
            case "high": return 4;
            case "medium": return 3;
            case "low": return 2;
            default: return 1;  // If invalid, place it at the end
        }
    }

    
    public void searchTasksByTitle(String title) {  //linear search O(n) time complexity
        boolean found = false;
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                System.out.println("\nFound Task: " + task);
                found = true;
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
}
