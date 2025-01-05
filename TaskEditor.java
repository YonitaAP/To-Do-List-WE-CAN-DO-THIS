
import java.util.List;
import java.util.Scanner;

public class TaskEditor {
    private List<Task> tasks;

    public TaskEditor(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void editTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks available to edit.");
            return;
        }

        System.out.println("\n—— View All Tasks ——");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }

        System.out.print("\nEnter the task number to edit: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            System.out.println("\nInvalid task number.");
            return;
        }

        Task task = tasks.get(taskNumber - 1);

        System.out.println("\nWhat would you like to edit?");
        System.out.println("1. Title");
        System.out.println("2. Description");
        System.out.println("3. Due Date");
        System.out.println("4. Category");
        System.out.println("5. Priority");
        System.out.println("6. Cancel");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter new title: ");
                task.setTitle(scanner.nextLine());
                break;
            case 2:
                System.out.print("Enter new description: ");
                task.setDescription(scanner.nextLine());
                break;
            case 3:
                System.out.print("Enter new due date (YYYY-MM-DD): ");
                String newDueDate = scanner.nextLine();
                while (!newDueDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    System.out.print("Invalid format. Enter new due date (YYYY-MM-DD): ");
                    newDueDate = scanner.nextLine();
                }
                task.setDueDate(newDueDate);
                break;
            case 4:
                System.out.print("Enter new category: ");
                task.setCategory(scanner.nextLine());
                break;
            case 5:
                System.out.print("Enter new priority (Low, Medium, High): ");
                String newPriority = scanner.nextLine();
                while (!newPriority.equalsIgnoreCase("Low") &&
                        !newPriority.equalsIgnoreCase("Medium") &&
                        !newPriority.equalsIgnoreCase("High")) {
                    System.out.print("Invalid priority. Enter 'Low', 'Medium', or 'High': ");
                    newPriority = scanner.nextLine();
                }
                task.setPriority(newPriority);
                break;
            case 6:
                System.out.println("Edit canceled.");
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("\nTask updated successfully!");
    }
}
