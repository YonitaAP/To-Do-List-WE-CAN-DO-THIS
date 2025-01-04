import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Task {
    String title, description, dueDate, recurrenceInterval;
    boolean isComplete;

    Task(String title, String description, String dueDate, String recurrenceInterval) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.recurrenceInterval = recurrenceInterval;
        this.isComplete = false;
    }

    @Override
    public String toString() {
        return String.format("[ %s ] %s - Due: %s", isComplete ? "Complete" : "Incomplete", title, dueDate);
    }
}

class TaskManager {
    List<Task> taskList = new ArrayList<>();
    Map<Integer, Integer> dependencies = new HashMap<>();

    void addTask(String title, String description, String dueDate, String recurrenceInterval) {
        taskList.add(new Task(title, description, dueDate, recurrenceInterval));
        System.out.println("Task \"" + title + "\" added successfully!");
    }

    void addRecurringTasks() {
        List<Task> newTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.recurrenceInterval.equals("daily")) {
                newTasks.add(new Task(task.title, task.description, incrementDate(task.dueDate, 1), "daily"));
            } else if (task.recurrenceInterval.equals("weekly")) {
                newTasks.add(new Task(task.title, task.description, incrementDate(task.dueDate, 7), "weekly"));
            } else if (task.recurrenceInterval.equals("monthly")) {
                newTasks.add(new Task(task.title, task.description, incrementDate(task.dueDate, 30), "monthly"));
            }
        }
        taskList.addAll(newTasks);
        System.out.println("Recurring tasks have been generated!");
    }

    void addDependency(int taskId, int dependsOnId) {
        if (taskId < 0 || taskId >= taskList.size() || dependsOnId < 0 || dependsOnId >= taskList.size()) {
            System.out.println("Invalid task ID(s) provided!");
            return;
        }
        if (hasCycle(taskId, dependsOnId)) {
            System.out.println("Error: Adding this dependency creates a cycle. Dependency not added.");
        } else {
            dependencies.put(taskId, dependsOnId);
            System.out.println("Task \"" + taskList.get(taskId).title + "\" now depends on \"" + taskList.get(dependsOnId).title + "\".");
        }
    }

    void markTaskAsComplete(int taskId) {
        if (taskId < 0 || taskId >= taskList.size()) {
            System.out.println("Invalid task ID!");
            return;
        }
        Integer dependency = dependencies.get(taskId);
        if (dependency == null || taskList.get(dependency).isComplete) {
            taskList.get(taskId).isComplete = true;
            System.out.println("Task \"" + taskList.get(taskId).title + "\" marked as complete!");
        } else {
            System.out.println("Warning: Task \"" + taskList.get(taskId).title + "\" cannot be marked as complete because it depends on \"" + taskList.get(dependency).title + "\".");
        }
    }

    private boolean hasCycle(int taskId, int dependsOnId) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> stack = new HashSet<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for (Map.Entry<Integer, Integer> entry : dependencies.entrySet()) {
            graph.computeIfAbsent(entry.getKey(), k -> new ArrayList<>()).add(entry.getValue());
        }
        graph.computeIfAbsent(taskId, k -> new ArrayList<>()).add(dependsOnId);

        return dfs(taskId, graph, visited, stack);
    }

    private boolean dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited, Set<Integer> stack) {
        if (stack.contains(node)) return true;
        if (visited.contains(node)) return false;

        visited.add(node);
        stack.add(node);

        for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (dfs(neighbor, graph, visited, stack)) return true;
        }

        stack.remove(node);
        return false;
    }

    private String incrementDate(String date, int days) {
        return LocalDate.parse(date).plusDays(days).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    void printTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + ": " + taskList.get(i));
        }
    }
}

public class RecurringTasksTest {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        manager.addTask("Finish Assignment", "Complete the Java programming assignment", "2024-01-05", "none");
        manager.addTask("Weekly Report", "Submit weekly progress", "2024-01-06", "weekly");
        manager.addTask("Prepare Presentation", "Get slides ready", "2024-01-07", "none");

        manager.addRecurringTasks();

        manager.addDependency(2, 0);
        manager.markTaskAsComplete(2);
        manager.markTaskAsComplete(0);
        manager.markTaskAsComplete(2);

        manager.addDependency(1, 2);
        manager.addDependency(2, 1);

        manager.printTasks();
    }
}
