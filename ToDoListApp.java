import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {
    private static List<Task> tasks = new ArrayList<>();
    private static TaskStorage taskStorage = new TaskStorage();
    private static SearchSortTasks searchSortTasks;
    private static TaskEditor taskEditor;
    private static RecurringTask recurringTask;
    private static ManageTasks manageTasks;

    public static void main(String[] args) {
        tasks = taskStorage.loadTasks(); // Load tasks from CSV
        searchSortTasks = new SearchSortTasks(tasks);
        taskEditor = new TaskEditor(tasks);
        recurringTask = new RecurringTask(tasks);
        manageTasks = new ManageTasks(tasks);
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
            System.out.println("7. Edit Task");
            System.out.println("8. Add Recurring Task");
            System.out.println("9. Save and Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n—— Add a New Task ——");
                    manageTasks.addTask(scanner);
                    break;
                case 2:
                    System.out.println("\n—— View All Tasks ——");
                    viewTasks();
                    break;
                case 3:
                    System.out.println("\n—— Mark Task as Complete ——");
                    manageTasks.markTaskComplete(scanner);
                    break;
                case 4:
                    System.out.println("\n—— Delete a Task ——");
                    manageTasks.deleteTask(scanner);
                    break;
                case 5:
                    System.out.println("\n—— Sort Tasks ——");
                    sortTasks(scanner);
                    break;
                case 6:
                    System.out.println("\n—— Search Tasks ——");
                    searchTasks(scanner);
                    break;
                case 7:
                    System.out.println("\n—— Edit Task ——");
                    taskEditor.editTask(scanner);
                    break;
                case 8:
                    System.out.println("\n—— Add Recurring Task ——");
                    recurringTask.addRecurringTask(scanner);
                    break;
                case 9:
                    taskStorage.saveTasks(tasks); // Save tasks to CSV before exiting
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        } while (choice != 9);

        scanner.close();
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\nTask " + (i + 1) + " " + tasks.get(i));
            }
        }
    }

    private static void sortTasks(Scanner scanner) {
        System.out.println("Sort by:");
        System.out.println("1. Due Date (Ascending)");
        System.out.println("2. Due Date (Descending)");
        System.out.println("3. Priority (High to Low)");
        System.out.println("4. Priority (Low to High)");
        System.out.print("Enter your choice: ");
        int sortChoice = scanner.nextInt();

        switch (sortChoice) {
            case 1:
                searchSortTasks.sortTasksByDueDateAscending();
                break;
            case 2:
                searchSortTasks.sortTasksByDueDateDescending();
                break;
            case 3:
                searchSortTasks.sortTasksByPriorityHighToLow();
                break;
            case 4:
                searchSortTasks.sortTasksByPriorityLowToHigh();
                break;
            default:
                System.out.println("\nInvalid sorting option.");
        }
    }

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
                searchSortTasks.searchTasksByTitle(title);
                break;
            case 2:
                System.out.print("Enter the task category: ");
                String category = scanner.nextLine();
                searchSortTasks.searchTasksByCategory(category);
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
- Task Sorting
- Edit task
- Recurring tasks


Completed:
- Task dependencies
- Extra feature (Data analytics)

Pending:
- Extra feature(GUI)
- Extra feature (Email notif)
 
NOTES
- Go back to view all tasks after task is changed?
- clear the output after a command is done
- back to menu option if accidentally clicked
- move add recurring task to sub of add task
- add task dependencies in edit task
*/
