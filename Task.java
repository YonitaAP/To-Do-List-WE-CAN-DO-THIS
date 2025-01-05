
public class Task {
    private String title;
    private String description;
    private String dueDate;
    private String category;
    private String priority;
    private boolean isComplete;

    public Task(String title, String description, String dueDate, String category, String priority, boolean isComplete) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.category = category;
        this.priority = priority;
        this.isComplete = isComplete;
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
    

    @Override
    public String toString() {
        return String.format("\n Title: %-20s \n Description: %s \n Due Date: %s \n Category: %s \n Priority: %s \n Complete: %b",
                title, description, dueDate, category, priority, isComplete);
    }
}
