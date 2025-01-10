import java.time.LocalDate;

public class RecurringTask {
    private String title;
    private String description;
    private String recurrenceInterval;
    private String nextDueDate;

    // Updated constructor to accept nextDueDate
    public RecurringTask(String title, String description, String recurrenceInterval, String nextDueDate) {
        this.title = title;
        this.description = description;
        this.recurrenceInterval = recurrenceInterval.toLowerCase();
        this.nextDueDate = nextDueDate; // Initialize with user-provided date
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
        switch (recurrenceInterval) {
            case "daily":
                currentDueDate = currentDueDate.plusDays(1);
                break;
            case "weekly":
                currentDueDate = currentDueDate.plusWeeks(1);
                break;
            case "monthly":
                currentDueDate = currentDueDate.plusMonths(1);
                break;
            default:
                throw new IllegalArgumentException("Invalid recurrence interval: " + recurrenceInterval);
        }
        this.nextDueDate = currentDueDate.toString();
    }

    @Override
    public String toString() {
        return String.format(
                " %s | %s - Recurrence Interval: %s", title, description, recurrenceInterval
        );
    }
}
