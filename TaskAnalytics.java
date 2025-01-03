import java.util.*;

public class TaskAnalytics {

    // Task class
    static class Task {
        String title;
        boolean isComplete;
        String category;

        public Task(String title, boolean isComplete, String category) {
            this.title = title;
            this.isComplete = isComplete;
            this.category = category;
        }
    }

    private List<Task> taskList;

    public TaskAnalytics() {
        this.taskList = new ArrayList<>();
    }

    /* Method to add a task
    public void addTask(String title, boolean isComplete, String category) {
        taskList.add(new Task(title, isComplete, category));
    } */

    // Analytics method to calculate and display task completion stats
    public void displayAnalytics() {
        int completedTasks = 0;
        int totalTasks = taskList.size();
        Map<String, Integer> categoryCounts = new HashMap<>();

        for (Task task : taskList) {
            if (task.isComplete) {
                completedTasks++;
            }
            categoryCounts.put(task.category, categoryCounts.getOrDefault(task.category, 0) + 1);
        }

        int pendingTasks = totalTasks - completedTasks;
        double completionRate = totalTasks > 0 ? ((double) completedTasks / totalTasks) * 100 : 0;

        System.out.println("\n=== Analytics Dashboard ===");
        System.out.println("Total Tasks: " + totalTasks);
        System.out.println("Completed: " + completedTasks);
        System.out.println("Pending: " + pendingTasks);
        System.out.println("Completion Rate: " + String.format("%.2f", completionRate) + "%");

        System.out.println("\nTask Categories:");
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /*
    public static void main(String[] args) {
        TaskAnalytics manager = new TaskAnalytics();

        // Sample tasks
        manager.addTask("Complete Java Project", false, "Work");
        manager.addTask("Grocery Shopping", true, "Personal");
        manager.addTask("Read Book", false, "Hobby");
        manager.addTask("Workout", true, "Health");

        // Display analytics
        manager.displayAnalytics();
    }
    */
}
