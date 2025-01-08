
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecurringTaskStorage {
    private static final String FILE_NAME = "recurring_tasks.csv";

    public void saveRecurringTasks(List<RecurringTask> recurringTasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (RecurringTask task : recurringTasks) {
                String line = String.format("%s,%s,%s",
                        task.getTitle(), task.getDescription(), task.getRecurrenceInterval());
                writer.write(line);
                writer.newLine();
            }
            System.out.println("\nRecurring tasks saved successfully!");
        } catch (IOException e) {
            System.err.println("\nError saving recurring tasks: " + e.getMessage());
        }
    }

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
                String[] parts = line.split(",", 3); // Split into 3 parts
                RecurringTask task = new RecurringTask(parts[0], parts[1], parts[2]);
                recurringTasks.add(task);
            }
        } catch (IOException e) {
            System.err.println("Error loading recurring tasks: " + e.getMessage());
        }

        return recurringTasks;
    }
}
