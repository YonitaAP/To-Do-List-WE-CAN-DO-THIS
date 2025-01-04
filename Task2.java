public class Task {
    // Attributes of a Task
        String title;
        String description;
        String dueDate;
        String priority;

        // Constructor to initialize a Task
        public Task(String title, String description, String dueDate, String priority) {
            this.title = title;
            this.description = description;
            this.dueDate = dueDate;
            this.priority = priority;
    }

    // Override toString method to display Task details
    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Due Date: " + dueDate + "\n" +
                "Priority: " + priority + "\n";
    }
}

