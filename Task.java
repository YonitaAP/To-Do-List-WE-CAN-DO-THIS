import java.util.*;

public class Task {
    
    private String title;
    private String description;
    private String dueDate;
    private String category;
    private String priority;
    private boolean isComplete;
    private List<String> dependencies;

    // Constructor
    public Task(String title, String description, String dueDate, String category, String priority, boolean isComplete, List<String> dependencies) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.category = category;
        this.priority = priority;
        this.isComplete = isComplete;
        this.dependencies = dependencies != null ? new ArrayList<>(dependencies) : new ArrayList<>();
    }

    // Getter for title
    public String getTitle() { 
        return title; 
    }
    public void setTitle(String title) { 
        this.title = title; 
    }

    // Getter for description
    public String getDescription() { 
        return description; 
    }
    public void setDescription(String description) {
        this.description = description; 
    }

    // Getter for dueDate
    public String getDueDate() { 
        return dueDate; 
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate; 
    }

    // Getter for category
    public String getCategory() { 
        return category; 
    }
    public void setCategory(String category) { 
        this.category = category; 
    }

    // Getter for priority
    public String getPriority() { 
        return priority; 
    }
    public void setPriority(String priority) {
        this.priority = priority; 
    }

    // Getter for completion status
    public boolean isComplete() { 
        return isComplete; 
    }
    public void setComplete(boolean isComplete) { 
        this.isComplete = isComplete; 
    }

    // Getter for dependencies
    public List<String> getDependencies() {
        // Return a mutable copy to avoid modification issues
        return new ArrayList<>(dependencies);
    }

    // Setter for dependencies
    public void setDependencies(List<String> dependencies) { 
        this.dependencies = dependencies != null ? new ArrayList<>(dependencies) : new ArrayList<>();
    }

    // Add a single dependency
    public void addDependency(String dependency) {
        if (!dependencies.contains(dependency)) {
            dependencies.add(dependency);
        }
    }

    // Remove a single dependency
    public void removeDependency(String dependency) {
        dependencies.remove(dependency);
    }

    // Parse the due date into an integer for comparison
    public int getDueDateAsInteger() {
        try {
            return Integer.parseInt(dueDate.replace("-", ""));
        } catch (NumberFormatException e) {
            System.err.println("Invalid date format: " + dueDate);
            return Integer.MAX_VALUE;  // Return a large value in case of invalid date
        }
    }

    // Override toString for task display
    @Override
    public String toString() {
        return String.format(
            "[%s] %s | %s - Due: %s - Category: %s - Priority: %s %s",
            isComplete ? "Complete" : "Incomplete", 
            title, 
            description, 
            dueDate, 
            category, 
            priority, 
            dependencies.isEmpty() ? "" : "- Dependencies: " + String.join(", ", dependencies));
    }
}
