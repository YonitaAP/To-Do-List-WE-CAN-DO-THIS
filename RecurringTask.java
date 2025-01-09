import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RecurringTask {
    private String title;
    private String description;
    private String recurrenceInterval;
    private String nextDueDate;

    public RecurringTask(String title, String description, String recurrenceInterval) {
        this.title = title;
        this.description = description;
        this.recurrenceInterval = recurrenceInterval.toLowerCase();
        this.nextDueDate = calculateNextDueDate(LocalDate.now()); // Set initial due date
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRecurrenceInterval() {
        return recurrenceInterval;
    }

    public String getNextDueDate() {
        return nextDueDate;
    }

    public void updateNextDueDate() {
        LocalDate currentDueDate = LocalDate.parse(nextDueDate);
        this.nextDueDate = calculateNextDueDate(currentDueDate);
        System.out.println("Updated next due date for recurring task: " + nextDueDate);
    }


    private String calculateNextDueDate(LocalDate startDate) {
        switch (recurrenceInterval) {
            case "daily":
                return startDate.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            case "weekly":
                return startDate.plusWeeks(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            case "monthly":
                return startDate.plusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            default:
                throw new IllegalArgumentException("Invalid recurrence interval: " + recurrenceInterval);
        }
    }

    @Override
    public String toString() {
        return String.format(" %s | %s - Recurrence Interval: %s", title, description, recurrenceInterval);
    }
}
