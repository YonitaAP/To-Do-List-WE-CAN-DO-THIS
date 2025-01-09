import java.util.*;

public class ManageTasks {
    private List<Task> tasks; 
    private List<RecurringTask> recurringTasks;
    private RecurringTaskStorage recurringTaskStorage;

    public ManageTasks(List<Task> tasks, List<RecurringTask> recurringTasks, RecurringTaskStorage recurringTaskStorage) {
        this.tasks = tasks;
        this.recurringTasks = recurringTasks;
        this.recurringTaskStorage = recurringTaskStorage;
    }

    public void addTaskMenu(Scanner scanner) {
           System.out.println("╔══════════════════════════╗");
           System.out.println("║       ★ Add Task ★       ║");
           System.out.println("╠══════════════════════════╣");
           System.out.println("║  1. Add Regular Task     ║");
           System.out.println("║  2. Add Recurring Task   ║");
           System.out.println("║  3. Cancel               ║");           
           System.out.println("╚══════════════════════════╝");
           System.out.print("☆ Enter your choice: ");

        int choice = scanner.nextInt();
        System.out.println();

        scanner.nextLine(); 

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

    public void addTask(Scanner scanner) {
        String title, description, dueDate, category, priority;
        List<String> dependencies = new ArrayList<>();
        
        System.out.println("╔══════════════════════════╗");
        System.out.println("║   ★ Task Information ★   ║");
        System.out.println("╚══════════════════════════╝");
        
        System.out.print("Enter task title: ");
        title = scanner.nextLine();
        System.out.print("Enter task description: ");
        description = scanner.nextLine();

        while (true) {
            System.out.print("Enter due date (YYYY-MM-DD): ");
            dueDate = scanner.nextLine();
            if (dueDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                break; 
            } else {
                System.out.println("Invalid due date format. Please use YYYY-MM-DD.");
            }
        }

        System.out.print("Enter task category (Homework, Personal, Work, etc.): ");
        category = scanner.nextLine();

        while (true) {
            System.out.print("Enter priority level (Low, Medium, High): ");
            priority = scanner.nextLine();
            if (priority.equalsIgnoreCase("Low") || priority.equalsIgnoreCase("Medium") || priority.equalsIgnoreCase("High")) {
                break;
            } else {
                System.out.println("Invalid priority. Please enter 'Low', 'Medium', or 'High'.");
            }
        }

        tasks.add(new Task(title, description, dueDate, category, priority, false, dependencies));
        System.out.println("\nTask \"" + title + "\" added successfully!");
    }

    
    public void addRecurringTask(Scanner scanner) {
        String title, description, recurrenceInterval, nextDueDate;

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

        // Validate next due date input
        while (true) {
            System.out.print("Enter first recurrence date (YYYY-MM-DD): ");
            nextDueDate = scanner.nextLine();
            if (nextDueDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                break; // Valid date
            } else {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

        // Create and save the recurring task
        RecurringTask recurringTask = new RecurringTask(title, description, recurrenceInterval, nextDueDate);
        recurringTasks.add(recurringTask);

        // Automatically create a regular task for the first recurrence
        Task regularTask = new Task(
            title, 
            description, 
            recurringTask.getNextDueDate(), 
            recurrenceInterval + " recurrence", 
            "Medium", 
            false, 
            new ArrayList<>() 
        );
        tasks.add(regularTask);

        System.out.println("\nRecurring task \"" + title + "\" added successfully!");
        System.out.println("Regular task created for the first recurrence.");

        recurringTaskStorage.saveRecurringTasks(recurringTasks);
    }


        public void handleRecurringTaskCompletion(RecurringTask recurringTask) {
            // Update  next due date for the recurring task
            recurringTask.updateNextDueDate();

            Task newTask = new Task(
            recurringTask.getTitle(), 
            recurringTask.getDescription(), 
            recurringTask.getNextDueDate(), 
            recurringTask.getRecurrenceInterval() + " recurrence", 
            "Medium", 
            false, 
            new ArrayList<>() 
        );


            tasks.add(newTask);
            System.out.println("\nNew regular task created for the next recurrence: " + newTask.getDueDate());

            recurringTaskStorage.saveRecurringTasks(recurringTasks);
        }


    // Delete task menu
    public void deleteTaskMenu(Scanner scanner) {
        System.out.println("╔═══════════════════════════╗");
        System.out.println("║     ★ Delete Task ★       ║");
        System.out.println("╠═══════════════════════════╣");
        System.out.println("║  1. Delete Single Task    ║");
        System.out.println("║  2. Delete Recurring Task ║");
        System.out.println("║  3. Back to Main Menu     ║"); 
        System.out.println("╚═══════════════════════════╝");
        System.out.print("☆ Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

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
        
        System.out.println("╔════════════════════════════╗");
        System.out.println("║  ★ Mark Task as Complete ★ ║");
        System.out.println("╚════════════════════════════╝");
        
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Task " + (i + 1) + ": " + tasks.get(i));
        }

        System.out.print("Enter the task number to mark as complete (or 0 to cancel): ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (taskNumber == 0) {
                    System.out.println("Returning to the main menu.");
                    return;
        }

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            
            if (task.isComplete()) {
            System.out.println("\nTask \"" + task.getTitle() + "\" is already incomplete.");
            return;
            }

            List<String> dependencies = task.getDependencies();
            if (!dependencies.isEmpty()) {
                for (String dependencyTitle : dependencies) {
                    boolean isDependencyComplete = tasks.stream()
                        .filter(t -> t.getTitle().equalsIgnoreCase(dependencyTitle))
                        .allMatch(Task::isComplete);

                    if (!isDependencyComplete) {
                        System.out.println("\nTask \"" + task.getTitle() + "\" cannot be marked complete. Unresolved dependency: \"" + dependencyTitle + "\".");
                        return;
                    }
                }
            }

            task.setComplete(true);
            System.out.println("\nTask \"" + task.getTitle() + "\" marked as complete!");

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
    
    public void markTaskIncomplete(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks available to mark as incomplete.");
            return;
        }

        System.out.println("╔════════════════════════════╗");
        System.out.println("║    ★ Mark as Incomplete ★  ║");
        System.out.println("╚════════════════════════════╝");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Task " + (i + 1) + ": " + tasks.get(i));
        }

        System.out.print("Enter the task number to mark as incomplete (or 0 to cancel): ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (taskNumber == 0) {
            System.out.println("Returning to the main menu.");
            return;
        }

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);

            if (!task.isComplete()) {
                System.out.println("\nTask \"" + task.getTitle() + "\" is already incomplete.");
                return;
            }

            // Reverse check for dependencies (if needed)
            for (Task dependentTask : tasks) {
                List<String> dependencies = dependentTask.getDependencies();
                if (dependencies.contains(task.getTitle()) && dependentTask.isComplete()) {
                    System.out.println("\nTask \"" + task.getTitle() + "\" cannot be marked incomplete as it is a dependency for \"" + dependentTask.getTitle() + "\".");
                    return;
                }
            }

            task.setComplete(false);
            System.out.println("\nTask \"" + task.getTitle() + "\" marked as incomplete!");

            for (RecurringTask recurringTask : recurringTasks) {
                if (recurringTask.getTitle().equalsIgnoreCase(task.getTitle())) {
                    System.out.println("Note: Marking a recurring task's instance incomplete does not affect the recurrence schedule.");
                    break;
                }
            }
        } else {
            System.out.println("\nInvalid task number.");
        }
    }

}
