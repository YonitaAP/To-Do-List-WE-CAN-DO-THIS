
import java.io.*;
import java.util.*;

public class TaskStorage {
    private static final String FILE_NAME = "tasks.csv";

    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                String line = String.format("%s,%s,%s,%s,%s,%b",
                        task.getTitle(), task.getDescription(), task.getDueDate(),
                        task.getCategory(), task.getPriority(), task.isComplete());
                writer.write(line);
                writer.newLine();
            }
            System.out.println("\nTasks saved successfully!");
        } catch (IOException e) {
            System.err.println("\nError saving tasks: " + e.getMessage());
        }
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No existing task file found. Enter new.");
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 6); // Split into 6 parts
                Task task = new Task(parts[0], parts[1], parts[2], parts[3], parts[4], Boolean.parseBoolean(parts[5]));
                tasks.add(task);
            }
            System.out.println("Tasks loaded successfully!");
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }
}
