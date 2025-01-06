import java.util.List;
import java.util.Scanner;

public class ManageTasks {
    private List<Task> tasks;

    public ManageTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Scanner scanner) {
        String title, description, dueDate, category, priority;

        System.out.print("Enter task title: ");
        title = scanner.nextLine();
        System.out.print("Enter task description: ");
        description = scanner.nextLine();

        // Validate due date input
        while (true) {
            System.out.print("Enter due date (YYYY-MM-DD): ");
            dueDate = scanner.nextLine();
            if (dueDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                break; // Valid date
            } else {
                System.out.println("Invalid due date format. Please use YYYY-MM-DD.");
            }
        }

        System.out.print("Enter task category (Homework, Personal, Work): ");
        category = scanner.nextLine();

        // Validate priority input
        while (true) {
            System.out.print("Enter priority level (Low, Medium, High): ");
            priority = scanner.nextLine();
            if (priority.equalsIgnoreCase("Low") || priority.equalsIgnoreCase("Medium") || priority.equalsIgnoreCase("High")) {
                break;
            } else {
                System.out.println("Invalid priority. Please enter 'Low', 'Medium', or 'High'.");
            }
        }

        tasks.add(new Task(title, description, dueDate, category, priority, false));
        System.out.println("\nTask \"" + title + "\" added successfully!");
    }

    public void markTaskComplete(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks available to mark as complete.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\nTask " + (i + 1) + tasks.get(i));
        }

        System.out.print("Enter the task number to mark as complete: ");
        int taskNumber = scanner.nextInt();

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).setComplete(true);
            System.out.println("\nTask marked as complete!");
        } else {
            System.out.println("\nInvalid task number.");
        }
    }

    public void deleteTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks available to delete.");
            return;
        }

        System.out.println("\n—— View All Tasks ——");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\nTask " + (i + 1) + ": " + tasks.get(i));
        }

        System.out.print("\nEnter the task number to delete: ");
        int taskNumber = scanner.nextInt();

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.remove(taskNumber - 1);
            System.out.println("\nTask deleted successfully!");
        } else {
            System.out.println("\nInvalid task number.");
        }
    }
}
