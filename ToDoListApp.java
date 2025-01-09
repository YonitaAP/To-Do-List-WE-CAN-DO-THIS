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
    private static Map<Integer, Integer> dependencies = new HashMap<>();
    

    public static void main(String[] args) {
        tasks = taskStorage.loadTasks(); // Load regular tasks from CSV
        recurringTasks = recurringTaskStorage.loadRecurringTasks(); // Load recurring tasks from CSV
        searchSortTasks = new SearchSortTasks(tasks);
        taskEditor = new TaskEditor(tasks);
        manageTasks = new ManageTasks(tasks, recurringTasks, recurringTaskStorage);
        taskAnalytics = new TaskAnalytics(tasks);
        viewTasks = new ViewTasks(tasks, recurringTasks);
        
// Initialize viewTasks

        Scanner scanner = new Scanner(System.in);
        int choice;

        while(true) {
            try {
               System.out.println("\n╔═══════════════════════════╗");
               System.out.println("║   ☆ To-Do List Menu ☆     ║");
               System.out.println("╠═══════════════════════════╣");
               System.out.println("║  1. Add Task              ║");
               System.out.println("║  2. View Tasks            ║");
               System.out.println("║  3. Mark Task as Complete ║");
               System.out.println("║  4. Delete Task           ║");
               System.out.println("║  5. Sort Tasks            ║");
               System.out.println("║  6. Search Tasks          ║");
               System.out.println("║  7. Edit Task             ║");
               System.out.println("║  8. Show Analytics        ║");
               System.out.println("║  9. Save and Exit         ║");
               System.out.println("╠═══════════════════════════╣");
               System.out.println("╚═══════════════════════════╝");
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
                        manageTasks.deleteTaskMenu(scanner);
                        break;
                    case 5:
                        searchSortTasks.SortTasks(scanner);
                        break;
                    case 6:
                        searchSortTasks.SearchTasks(scanner);
                        break;
                    case 7:
                        taskEditor.editTask(scanner);
                        break;
                    case 8:
                        taskAnalytics.displayAnalytics();
                        break;
                    case 9:
                        taskStorage.saveTasks(tasks); // Save regular tasks to CSV
                        recurringTaskStorage.saveRecurringTasks(recurringTasks); // Save recurring tasks to CSV
                        System.out.println("Exiting program.");
                        break;
                    default:
                        System.out.println("\nInvalid choice. Please enter a number between 1 and 9.");
                }
            } catch (InputMismatchException e) {
            System.out.println("\nError: Please enter a valid number.");
            scanner.nextLine(); // Clear the invalid input
            }
        } 

    }
}

/*
PROGRESS

//Completed & run:
- Storage system (1/2)
- Data load state (1/2)
- Task creation (1)
- Task deletion  (1/2)
- Task searching (1/2)
- Mark as complete (1/2)
- Task Sorting (1/2)
- Edit task (1)
- Extra feature (Data analytics) (1)
- Recurring tasks (1)
- 

TO-DO
- Task dependencies (2)
- Extra feature (Email notif) (1)
- GUI (2)


 
NOTES

yonita:
- add task dependencies in edit task


cita:
- option to cancel
- search task by description
- do i need next due date csv? test if the recurring task will automatically add after recurrence completed.
- email notif?
*/
