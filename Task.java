
import java.util.*;

public class Task {
    
    private String title;
    private String description;
    private String dueDate;
    private String category;
    private String priority;
    private boolean isComplete;
    private List<String> dependencies; 

    public Task(String title, String description, String dueDate, String category, String priority, boolean isComplete, List<String> dependencies) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.category = category;
        this.priority = priority;
        this.isComplete = isComplete;
        this.dependencies = dependencies;

    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public boolean isComplete() { return isComplete; }
    public void setComplete(boolean isComplete) { this.isComplete = isComplete; }
    
    public List<String> getDependencies() { return dependencies; }

    public void setDependencies(List<String> dependencies) { this.dependencies = dependencies; }

    public int getDueDateAsInteger() {
        try {
            return Integer.parseInt(dueDate.replace("-", ""));
        } catch (NumberFormatException e) {
            System.err.println("Invalid date format: " + dueDate);
            return Integer.MAX_VALUE;  // Return a large value in case of invalid date
        }
    }

   @Override
    public String toString() {
        return String.format(
            "[%s] %s | %s - Due: %s - Category: %s - Priority: %s - Dependencies: %s",
            isComplete ? "Complete" : "Incomplete", 
            title, 
            description, 
            dueDate, 
            category, 
            priority, 
            dependencies.isEmpty() ? "None" : String.join(", ", dependencies));
    }

}
