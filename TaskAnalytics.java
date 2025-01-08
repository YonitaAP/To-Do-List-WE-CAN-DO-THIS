import java.util.*;

public class TaskAnalytics {
    private List<Task> tasks;

    public TaskAnalytics(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void displayAnalytics() {
        int completedTasks = 0;
        int totalTasks = tasks.size();
        Map<String, Integer> categoryCounts = new HashMap<>();

        for (Task task : tasks) {
            if (task.isComplete()) completedTasks++;
            String normalizedCategory = task.getCategory().toLowerCase();
            categoryCounts.put(normalizedCategory, categoryCounts.getOrDefault(normalizedCategory, 0) + 1);
        }

        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║    ★ Analytics Dashboard ★    ║");
        System.out.println("╚═══════════════════════════════╝");
        System.out.println("Total Tasks: " + totalTasks);
        System.out.println("Completed: " + completedTasks);
        System.out.println("Pending: " + (totalTasks - completedTasks));
        System.out.println("Completion Rate: " + (totalTasks > 0 ? String.format("%.2f", (double) completedTasks / totalTasks * 100) : "0") + "%");

        // Display Categories on separate lines
        System.out.println("Task Categories:");
        categoryCounts.forEach((category, count) -> 
            System.out.println("  - " + capitalize(category) + ": " + count)
        );
    }


    // Utility method to capitalize the first letter of a word
    private String capitalize(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
