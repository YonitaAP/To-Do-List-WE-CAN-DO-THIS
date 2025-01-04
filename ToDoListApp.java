/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package todolistapp;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoListApp {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n—— To-Do List Menu ——");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Complete");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n—— Add a New Task ——");
                    addTask(scanner);
                    break;
                case 2:
                    System.out.println("\n—— View All Tasks ——");
                    viewTasks();
                    break;
                case 3:
                    System.out.println("\n—— Mark Task as Complete ——");
                    markTaskComplete(scanner);
                    break;
                case 4:
                    System.out.println("\n—— Delete a Task ——");
                    deleteTask(scanner);
                    break;
                case 5:
                    System.out.println("\n—— Exiting Program ——");
                    break;
                default:
                    System.out.println("\n—— Invalid Choice, Please Try Again ——");
            }
        } while (choice != 5);
        scanner.close();
    }

    // Add Task
    private static void addTask(Scanner scanner) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();
        System.out.print("Enter task category (Homework, Personal, Work): ");
        String category = scanner.nextLine();
        System.out.print("Enter priority level (Low, Medium, High): ");
        String priority = scanner.nextLine();

        tasks.add(new Task(title, description, dueDate, category, priority));
        System.out.println("Task \"" + title + "\" added successfully!");
    }

    // View Tasks
    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("Task " + (i + 1) + ":");
                System.out.println(tasks.get(i));
                System.out.println("--------------------");
            }
        }
    }

    // Mark Task as Complete
    private static void markTaskComplete(Scanner scanner) {
        viewTasks();
        System.out.print("Enter the task number to mark as complete: ");
        int taskNumber = scanner.nextInt();

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).setComplete(true);
            System.out.println("Task \"" + tasks.get(taskNumber - 1).getTitle() + "\" marked as complete!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    // Delete Task
    private static void deleteTask(Scanner scanner) {
        viewTasks();
        System.out.print("Enter the task number to delete: ");
        int taskNumber = scanner.nextInt();

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task removedTask = tasks.remove(taskNumber - 1);
            System.out.println("Task \"" + removedTask.getTitle() + "\" deleted successfully!");
        } else {
            System.out.println("Invalid task number.");
        }
    }
}


