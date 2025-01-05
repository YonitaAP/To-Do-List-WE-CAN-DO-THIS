
import java.util.List;
import java.util.Scanner;

public class RecurringTask {
    private List<Task> tasks;

    public RecurringTask(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addRecurringTask(Scanner scanner) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        System.out.print("Enter due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();
        while (!dueDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.print("Invalid format. Enter due date (YYYY-MM-DD): ");
            dueDate = scanner.nextLine();
        }

        System.out.print("Enter task category (Homework, Personal, Work): ");
        String category = scanner.nextLine();

        System.out.print("Enter priority level (Low, Medium, High): ");
        String priority = scanner.nextLine();
        while (!priority.equalsIgnoreCase("Low") &&
                !priority.equalsIgnoreCase("Medium") &&
                !priority.equalsIgnoreCase("High")) {
            System.out.print("Invalid priority. Enter 'Low', 'Medium', or 'High': ");
            priority = scanner.nextLine();
        }

        System.out.print("Enter recurrence interval (daily, weekly, monthly): ");
        String interval = scanner.nextLine().toLowerCase();
        while (!interval.equals("daily") &&
                !interval.equals("weekly") &&
                !interval.equals("monthly")) {
            System.out.print("Invalid interval. Enter 'daily', 'weekly', or 'monthly': ");
            interval = scanner.nextLine().toLowerCase();
        }

        Task recurringTask = new Task(title, description, dueDate, category, priority, false);
        tasks.add(recurringTask);
        System.out.println("\nRecurring Task \"" + title + "\" added successfully!");

        // Add logic to manage automatic recurrence generation?
    }
}
