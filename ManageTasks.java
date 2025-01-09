import java.util.*;

public class ManageTasks {
    private List<Task> tasks; // Regular tasks
    private List<RecurringTask> recurringTasks; // Recurring tasks
    private RecurringTaskStorage recurringTaskStorage;
    private Map<Integer, Integer> dependencies;

    public ManageTasks(List<Task> tasks, List<RecurringTask> recurringTasks, RecurringTaskStorage recurringTaskStorage) {
        this.tasks = tasks;
        this.recurringTasks = recurringTasks;
        this.recurringTaskStorage = recurringTaskStorage;
        this.dependencies = new HashMap<>();;
    }

    // Add task menu
    public void addTaskMenu(Scanner scanner) {
           System.out.println("╔══════════════════════════╗");
           System.out.println("║       ★ Add Task ★       ║");
           System.out.println("╠══════════════════════════╣");
           System.out.println("║  1. Add Regular Task     ║");
           System.out.println("║  2. Add Recurring Task   ║");
           System.out.println("║  3. Back to Main Menu    ║");           
           System.out.println("╚══════════════════════════╝");
           System.out.print("☆ Enter your choice: ");

        int choice = scanner.nextInt();
        System.out.println();

        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addTask(scanner);
                break;
            case 2:
                addRecurringTask(scanner);
                break;
            default:
                System.out.println("\nReturning to main menu.");
        }
    }

    // Add a regular task
    public void addTask(Scanner scanner) {
        String title, description, dueDate, category, priority;
        
        System.out.println("╔══════════════════════════╗");
        System.out.println("║   ★ Task Information ★   ║");
        System.out.println("╚══════════════════════════╝");
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

        System.out.print("Enter task category (Homework, Personal, Work, etc.): ");
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
    
    public void addRecurringTask(Scanner scanner) {
        String title, description, recurrenceInterval;
        
        System.out.println("╔══════════════════════════╗");
        System.out.println("║   ★ Task Information ★   ║");
        System.out.println("╚══════════════════════════╝");

        System.out.print("Enter recurring task title: ");
        title = scanner.nextLine();
        System.out.print("Enter recurring task description: ");
        description = scanner.nextLine();

    // Validate recurrence interval input
        while (true) {
            System.out.print("Enter recurrence interval (daily, weekly, monthly): ");
            recurrenceInterval = scanner.nextLine().toLowerCase();
            if (recurrenceInterval.equals("daily") || recurrenceInterval.equals("weekly") || recurrenceInterval.equals("monthly")) {
                break;
            } else {
                System.out.println("Invalid interval. Please enter 'daily', 'weekly', or 'monthly'.");
            }
        }

    // Create and save the recurring task
        RecurringTask recurringTask = new RecurringTask(title, description, recurrenceInterval);
        recurringTasks.add(recurringTask);

    // Automatically create a regular task for the initial recurrence
        Task regularTask = new Task(title, description, recurringTask.getNextDueDate(), 
                                    recurrenceInterval + " recurrence","Medium", false);
        tasks.add(regularTask);
        System.out.println("\nRecurring task \"" + title + "\" added successfully!");
        System.out.println("\nRegular task created for the first recurrence.");

        recurringTaskStorage.saveRecurringTasks(recurringTasks);
    }

    public void handleRecurringTaskCompletion(RecurringTask recurringTask) {
        // Update the next due date for the recurring task
        recurringTask.updateNextDueDate();

        // Create a new regular task for the next recurrence
        Task newTask = new Task(recurringTask.getTitle(), recurringTask.getDescription(), recurringTask.getNextDueDate(),
        recurringTask.getRecurrenceInterval() + " recurrence", "Medium", false);

        tasks.add(newTask);
        System.out.println("\nNew regular task created for the next recurrence: " + newTask.getDueDate());

        recurringTaskStorage.saveRecurringTasks(recurringTasks);
    }


    // Delete task menu
    public void deleteTaskMenu(Scanner scanner) {
        System.out.println("╔═══════════════════════════╗");
        System.out.println("║     ★ Delete Task ★       ║");
        System.out.println("╠═══════════════════════════╣");
        System.out.println("║  1. Delete Regular Task   ║");
        System.out.println("║  2. Delete Recurring Task ║");
        System.out.println("╚═══════════════════════════╝");
        System.out.print("☆ Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                deleteTask(scanner);
                break;
            case 2:
                removeRecurringTask(scanner);
                break;
            default:
                System.out.println("\nInvalid choice. Returning to main menu.");
        }
    }

    // Delete a regular task
    public void deleteTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks available to delete.");
            return;
        }
            System.out.println();
            System.out.println("╔═══════════════════════════╗");
            System.out.println("║     ★ Regular Tasks ★     ║");
            System.out.println("╚═══════════════════════════╝");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }

        System.out.print("\nEnter the task number to delete: ");
        int taskNumber = scanner.nextInt();

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task removedTask = tasks.remove(taskNumber - 1);
            System.out.println("\nTask \"" + removedTask.getTitle() + "\" deleted successfully!");
        } else {
            System.out.println("\nInvalid task number.");
        }
    }

    // Delete a recurring task
    public void removeRecurringTask(Scanner scanner) {
        if (recurringTasks.isEmpty()) {
            System.out.println("\nNo recurring tasks available to delete.");
            return;
        }

        System.out.println();
        System.out.println("╔═══════════════════════════╗");
        System.out.println("║    ★ Recurring Tasks ★    ║");
        System.out.println("╚═══════════════════════════╝");

        for (int i = 0; i < recurringTasks.size(); i++) {
            System.out.println("\nRecurring Task " + (i + 1) + ": " + recurringTasks.get(i));
        }

        System.out.print("\nEnter the recurring task number to delete: ");
        int taskNumber = scanner.nextInt();

        if (taskNumber > 0 && taskNumber <= recurringTasks.size()) {
            RecurringTask removedTask = recurringTasks.remove(taskNumber - 1);
            System.out.println("\nRecurring task \"" + removedTask.getTitle() + "\" deleted successfully!");

            // Save updated recurring tasks to file
            recurringTaskStorage.saveRecurringTasks(recurringTasks);
        } else {
            System.out.println("\nInvalid recurring task number.");
        }
    }
    
    public void markTaskComplete(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks available to mark as complete.");
            return;
        }

        System.out.println("\n—— View All Regular Tasks ——");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Task " + (i + 1) + ": " + tasks.get(i));
        }

        System.out.print("Enter the task number to mark as complete: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            
            // Check for dependencies
            Integer dependency = dependencies.get(taskNumber - 1); // Adjust index for 0-based IDs
            if (dependency != null) {
            Task dependentTask = tasks.get(dependency);
                if (!dependentTask.isComplete()) {
                    System.out.println("\nWarning: Task \"" + task.getTitle() + "\" cannot be marked as complete because it depends on \"" + dependentTask.getTitle() + "\".");
                    return;
                }
            }

            // Mark task as complete
            task.setComplete(true);
            System.out.println("\nTask marked as complete!");

            // Check if task belongs to a recurring task and handle it
            for (RecurringTask recurringTask : recurringTasks) {
                if (recurringTask.getTitle().equalsIgnoreCase(task.getTitle())) {
                    handleRecurringTaskCompletion(recurringTask);
                    break;
                }
            }
        } else {
            System.out.println("\nInvalid task number.");
        }
    }
    
}
