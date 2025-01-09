import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecurringTaskStorage {
    private static final String FILE_NAME = "recurring_tasks.csv";

    // Save recurring tasks to the CSV file
    public void saveRecurringTasks(List<RecurringTask> recurringTasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (RecurringTask task : recurringTasks) {
                String line = String.format("%s,%s,%s,%s",
                        task.getTitle(),
                        task.getDescription(),
                        task.getRecurrenceInterval(),
                        task.getNextDueDate());
                writer.write(line);
                writer.newLine();
            }
            System.out.println("\nRecurring tasks saved successfully!");
        } catch (IOException e) {
            System.err.println("\nError saving recurring tasks: " + e.getMessage());
        }
    }

    // Load recurring tasks from the CSV file
    public List<RecurringTask> loadRecurringTasks() {
        List<RecurringTask> recurringTasks = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No existing recurring tasks file found.");
            return recurringTasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 4); // Split into 4 parts: title, description, interval, nextDueDate
                if (parts.length == 4) {
                    RecurringTask task = new RecurringTask(parts[0], parts[1], parts[2]);
                    task.updateNextDueDate(); // Update to align with the stored nextDueDate
                    recurringTasks.add(task);
                }
            }
            System.out.println("Recurring tasks loaded successfully!");
        } catch (IOException e) {
            System.err.println("Error loading recurring tasks: " + e.getMessage());
        }

        return recurringTasks;
    }
}
