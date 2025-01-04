package todolistapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Task {
    private String title;
    private String description;
    private String dueDate;
    private String category;
    private String priority;
    private boolean isComplete;

    // Constructor
    public Task(String title, String description, String dueDate, String category, String priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.category = category;
        this.priority = priority;
        this.isComplete = false;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nDescription: " + description +
               "\nDue Date: " + dueDate + "\nCategory: " + category +
               "\nPriority: " + priority + "\nStatus: " + (isComplete ? "Complete" : "Incomplete");
    }
}

