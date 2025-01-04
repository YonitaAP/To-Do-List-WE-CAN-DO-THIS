import java.util.ArrayList; // Enables use of ArrayList for dynamic lists
import java.util.Collections; // Provides the sorting utility
import java.util.Comparator; // Specify how objects should be compared

public class TaskManager {
    public static void main(String[] args) {
        // Create an ArrayList to store Task objects
        ArrayList<Task> tasks = new ArrayList<Task>();

        // Create task objects with proper arguments
        Task task1 = new Task("Buy groceries", "Get milk and eggs from the store", "2025-01-10", "high");
        Task task2 = new Task("Complete project", "Finish the final report", "2025-01-05", "medium");
        Task task3 = new Task("Doctor appointment", "Annual checkup with Dr. Smith", "2025-01-15", "low");

        // Add tasks to the ArrayList
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        // Print tasks before sorting
        System.out.println("TASKS BEFORE SORTING:\n");
        for (Task task : tasks) {
            System.out.println(task);
        }

        // Sort Tasks by Due Date Ascending
        sortByDueDate(tasks);
        System.out.println("\nTASKS AFTER SORTING BY DUE DATE (ASCENDING):\n");
        for (Task task : tasks) {
            System.out.println(task);
        }

        // Sort Tasks by Due Date Descending
        sortByDueDateDescending(tasks);
        System.out.println("\nTASKS AFTER SORTING BY DUE DATE (DESCENDING):\n");
        for (Task task : tasks) {
            System.out.println(task);
        }

        // Sort Tasks by Priority (High to Low)
        sortByPriority(tasks);
        System.out.println("\nTASKS AFTER SORTING BY PRIORITY (HIGH TO LOW):\n");
        for (Task task : tasks) {
            System.out.println(task);
        }

        // Sort Tasks by Priority (Low to High)
        sortByPriorityLowToHigh(tasks);
        System.out.println("\nTASKS AFTER SORTING BY PRIORITY (LOW TO HIGH):\n");
        for (Task task : tasks) {
            System.out.println(task);
        }

        // Search for tasks by keyword
        System.out.println("\nSEARCH RESULTS FOR 'project':\n");
        ArrayList<Task> searchResults = searchTasks(tasks, "project");
        for (Task task : searchResults) {
            System.out.println(task);
        }

        System.out.println("\nSEARCH RESULTS FOR 'doctor':\n");
        searchResults = searchTasks(tasks, "doctor");
        for (Task task : searchResults) {
            System.out.println(task);
        }

        System.out.println("\nSEARCH RESULTS FOR 'groceries':\n");
        searchResults = searchTasks(tasks, "groceries");
        for (Task task : searchResults) {
            System.out.println(task);
        }
    }

    // Sort By Due Date Ascending
    public static void sortByDueDate(ArrayList<Task> tasks) {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.dueDate.compareTo(t2.dueDate); // Ascending order
            }
        });
    }

    // Sort By Due Date Descending
    public static void sortByDueDateDescending(ArrayList<Task> tasks) {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t2.dueDate.compareTo(t1.dueDate); // Descending order
            }
        });
    }

    // Sort By Priority (High to Low)
    public static void sortByPriority(ArrayList<Task> tasks) {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                int priority1 = getPriorityValue(t1.priority);
                int priority2 = getPriorityValue(t2.priority);
                return Integer.compare(priority1, priority2); // High to Low
            }

            private int getPriorityValue(String priority) {
                switch (priority.toLowerCase()) {
                    case "high":
                        return 1;
                    case "medium":
                        return 2;
                    case "low":
                        return 3;
                    default:
                        return 4;
                }
            }
        });
    }

    // Sort By Priority (Low to High)
    public static void sortByPriorityLowToHigh(ArrayList<Task> tasks) {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                int priority1 = getPriorityValue(t1.priority);
                int priority2 = getPriorityValue(t2.priority);
                return Integer.compare(priority2, priority1); // Low to High
            }

            private int getPriorityValue(String priority) {
                switch (priority.toLowerCase()) {
                    case "high":
                        return 1;
                    case "medium":
                        return 2;
                    case "low":
                        return 3;
                    default:
                        return 4;
                }
            }
        });
    }

    // Search Tasks by Keyword
    public static ArrayList<Task> searchTasks(ArrayList<Task> tasks, String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.title.toLowerCase().contains(keyword.toLowerCase())
                    || task.description.toLowerCase().contains(keyword.toLowerCase())) {
                results.add(task);
            }
        }
        return results;
    }
}
