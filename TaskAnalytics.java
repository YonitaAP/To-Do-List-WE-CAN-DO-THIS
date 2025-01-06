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
            if (task.isComplete()) {
                completedTasks++;
            }
            // Normalize category to lowercase for case-insensitive grouping
            String normalizedCategory = task.getCategory().toLowerCase();
            categoryCounts.put(normalizedCategory, categoryCounts.getOrDefault(normalizedCategory, 0) + 1);
        }

        int pendingTasks = totalTasks - completedTasks;
        double completionRate = totalTasks > 0 ? ((double) completedTasks / totalTasks) * 100 : 0;

        System.out.println("\n—— Analytics Dashboard ——");
        System.out.println("Total Tasks: " + totalTasks);
        System.out.println("Completed: " + completedTasks);
        System.out.println("Pending: " + pendingTasks);
        System.out.println("Completion Rate: " + String.format("%.2f", completionRate) + "%");

        // Format Task Categories in a single line
        System.out.print("Task Categories: ");
        List<String> formattedCategories = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            // Capitalize the first letter of each category for display
            String formattedCategory = capitalize(entry.getKey()) + ": " + entry.getValue();
            formattedCategories.add(formattedCategory);
        }
        System.out.println(String.join(", ", formattedCategories));
    }

    // Utility method to capitalize the first letter of a word
    private String capitalize(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
