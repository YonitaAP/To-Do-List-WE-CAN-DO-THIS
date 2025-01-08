
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
    private static TaskAnalytics taskAnalytics;
    

    public static void main(String[] args) {
        tasks = taskStorage.loadTasks(); // Load tasks from CSV
        searchSortTasks = new SearchSortTasks(tasks);
        taskEditor = new TaskEditor(tasks);
        recurringTask = new RecurringTask(tasks);
        manageTasks = new ManageTasks(tasks);
        taskAnalytics = new TaskAnalytics(tasks);

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
            System.out.println("9. Show Analytics");
            System.out.println("10. Save and Exit");
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
                    searchSortTasks.SortTasks(scanner);
                    break;
                case 6:
                    System.out.println("\n—— Search Tasks ——");
                    searchSortTasks.SearchTasks(scanner);
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
                    taskAnalytics.displayAnalytics();
                    break;
                case 10:
                    taskStorage.saveTasks(tasks); // Save tasks to CSV before exiting
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        } while (choice != 10);

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
}


/*
PROGRESS

Completed & run:
- Storage system (1/2)
- Data load state (1/2)
- Task creation (1)
- Task deletion  (1/2)
- Task searching (1/2)
- Mark as complete (1/2)
- Task Sorting (1/2)
- Edit task (1)
- Extra feature (Data analytics) (1)



TO-DO
- Recurring tasks - runs, but doesn't fulfill function
- Task dependencies (2)
- Extra feature (Email notif) (1)


 
NOTES (ignore aja wkwk)
- Mark task as incomplete
- Go back to view all tasks after task is changed?
- clear the output after a command is done
- move add recurring task to sub of add task?
- add task dependencies in edit task
- recurring task not rly working as intended
- add options to cancel in: add task, mask task as complete, delete task, add recurring tasks
- comparator nono
*/
