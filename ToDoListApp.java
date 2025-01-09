import java.util.*;

public class ToDoListApp {
    private static List<Task> tasks = new ArrayList<>();
    private static List<RecurringTask> recurringTasks = new ArrayList<>();
    private static RecurringTaskStorage recurringTaskStorage = new RecurringTaskStorage();
    private static TaskStorage taskStorage = new TaskStorage();
    private static ViewTasks viewTasks;
    private static SearchSortTasks searchSortTasks;
    private static TaskEditor taskEditor;
    private static RecurringTask recurringTask;
    private static ManageTasks manageTasks;
    private static TaskAnalytics taskAnalytics;
    

    public static void main(String[] args) {
        tasks = taskStorage.loadTasks(); // Load regular tasks from CSV
        recurringTasks = recurringTaskStorage.loadRecurringTasks(); // Load recurring tasks from CSV
        searchSortTasks = new SearchSortTasks(tasks);
        taskEditor = new TaskEditor(tasks);
        manageTasks = new ManageTasks(tasks, recurringTasks, recurringTaskStorage);
        taskAnalytics = new TaskAnalytics(tasks);
        viewTasks = new ViewTasks(tasks, recurringTasks);


        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            try {
                System.out.println("\n╔═════════════════════════════╗");
                System.out.println("║    ☆ To-Do List Menu ☆      ║");
                System.out.println("╠═════════════════════════════╣");
                System.out.println("║  1. Add Task                ║");
                System.out.println("║  2. View Tasks              ║");
                System.out.println("║  3. Mark Task as Complete   ║");
                System.out.println("║  4. Mark Task as Incomplete ║"); // New option
                System.out.println("║  5. Delete Task             ║");
                System.out.println("║  6. Sort Tasks              ║");
                System.out.println("║  7. Search Tasks            ║");
                System.out.println("║  8. Edit Task               ║");
                System.out.println("║  9. Show Analytics          ║");
                System.out.println("║  10. Save and Exit          ║");
                System.out.println("╠═════════════════════════════╣");
                System.out.println("╚═════════════════════════════╝");
                System.out.print("☆ Enter your choice: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println();

                switch (choice) {
                    case 1:
                        manageTasks.addTaskMenu(scanner);
                        break;
                    case 2:
                        viewTasks.displayTasks();
                        break;
                    case 3:
                        manageTasks.markTaskComplete(scanner);
                        break;
                    case 4:
                        manageTasks.markTaskIncomplete(scanner); // New case for marking incomplete
                        break;
                    case 5:
                        manageTasks.deleteTaskMenu(scanner);
                        break;
                    case 6:
                        searchSortTasks.SortTasks(scanner);
                        break;
                    case 7:
                        searchSortTasks.SearchTasks(scanner);
                        break;
                    case 8:
                        taskEditor.editTask(scanner);
                        break;
                    case 9:
                        taskAnalytics.displayAnalytics();
                        break;
                    case 10:
                        taskStorage.saveTasks(tasks); // Save regular tasks to CSV
                        recurringTaskStorage.saveRecurringTasks(recurringTasks); // Save recurring tasks to CSV
                        System.out.println("Tasks saved successfully. Exiting program.");
                        return; // Terminate the program
                    default:
                        System.out.println("\nInvalid choice. Please enter a number between 1 and 9.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError: Please enter a valid number.");
                scanner.nextLine(); 
            }
        }
    }

}
