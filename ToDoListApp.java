import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {
    private static List<Task> tasks = new ArrayList<>();
    private static TaskStorage taskStorage = new TaskStorage();
    private static TaskManager taskManager;

    public static void main(String[] args) {
        tasks = taskStorage.loadTasks(); // Load tasks from CSV
        taskManager = new TaskManager(tasks);
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n—— To-Do List Menu ——");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Complete");
            System.out.println("4. Delete Task");
            System.out.println("5. Sort Tasks");
            System.out.println("6. Search Tasks");
            System.out.println("7. Save and Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n—— Add a New Task ——");
                    addTask(scanner);
                    break;
                case 2:
                    System.out.println("\n—— View All Tasks ——");
                    viewTasks();
                    break;
                case 3:
                    System.out.println("\n—— Mark Task as Complete ——");
                    markTaskComplete(scanner);
                    break;
                case 4:
                    System.out.println("\n—— Delete a Task ——");
                    deleteTask(scanner);
                    break;
                case 5:
                    System.out.println("\n—— Sort Tasks——");    //still not working
                    sortTasks(scanner);
                    break;
                case 6:
                    System.out.println("\n—— Search Tasks——");
                    searchTasks(scanner);
                    break;
                case 7:
                    taskStorage.saveTasks(tasks); // Save tasks to CSV before exiting
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();
        System.out.print("Enter task category (Homework, Personal, Work): ");
        String category = scanner.nextLine();
        System.out.print("Enter priority level (Low, Medium, High): ");
        String priority = scanner.nextLine();

        tasks.add(new Task(title, description, dueDate, category, priority, false));
        System.out.println("\nTask \"" + title + "\" added successfully!");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\nTask " + (i + 1) + tasks.get(i));
            }
        }
    }

    private static void markTaskComplete(Scanner scanner) {
        viewTasks();
        System.out.print("Enter the task number to mark as complete: ");
        int taskNumber = scanner.nextInt();

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).setComplete(true);
            System.out.println("\nTask marked as complete!");
        } else {
            System.out.println("\nInvalid task number.");
        }
    }

    private static void deleteTask(Scanner scanner) {
        viewTasks();
        System.out.print("\nEnter the task number to delete: ");
        int taskNumber = scanner.nextInt();

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.remove(taskNumber - 1);
            System.out.println("\nTask deleted successfully!");
        } else {
            System.out.println("\nInvalid task number.");
        }
    }  
    /*
    private static void sortTasks(Scanner scanner) { //gobackto this later
        System.out.println("Sort by:");
        System.out.println("1. Due Date (Ascending)");
        System.out.println("2. Due Date (Descending)");
        System.out.println("3. Priority (High to Low)");
        System.out.println("4. Priority (Low to High)");
        System.out.print("Enter your choice: ");
        int sortChoice = scanner.nextInt();

        switch (sortChoice) {
            case 1:
                taskManager.sortTasksByDueDateAscending();
                break;
            case 2:
                taskManager.sortTasksByDueDateDescending();
                break;
            case 3:
                taskManager.sortTasksByPriorityHighToLow();
                break;
            case 4:
                taskManager.sortTasksByPriorityLowToHigh();
                break;
            default:
                System.out.println("\nInvalid sorting option.");
        }
    }
*/
    private static void searchTasks(Scanner scanner) {
        System.out.println("\nSearch by:");
        System.out.println("1. Title");
        System.out.println("2. Category");
        System.out.print("Enter your choice: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine();

        switch (searchChoice) {
            case 1:
                System.out.print("Enter the task title: ");
                String title = scanner.nextLine();
                taskManager.searchTasksByTitle(title);
                break;
            case 2:
                System.out.print("Enter the task category: ");
                String category = scanner.nextLine();
                taskManager.searchTasksByCategory(category);
                break;
            default:
                System.out.println("\nInvalid choice.");
        }
    }
}

/*
PROGRESS

Completed & integrated:
- Storage system
- Data load state
- Task creation
- Task deletion
- Task searching


Completed:
- Task Sorting
- Recurring tasks
- Extra feature (Data analytics)
- Task dependencies
- Task management

Pending:
- Edit task
- Extra feature(GUI)
- Extra feature (Email notif)
 
NOTES
- clear the output after a command is done?
- Task sorting still not working. add from descending ascending, high-low. have to convert to integer

*/
