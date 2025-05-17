public class Task {
    private String title; //name of task eg homework
    private String category; //category of task eg work
    private String dueDate; //due date of task in YYYY-MM-DD format
    private int priority; //priority level from 1-5

    public Task(String title, String category, String dueDate, int priority) {
        this.title = title; 
        this.category = category; 
        this.dueDate = dueDate; 
        this.priority = priority; 
    }
    //getter methods
    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    //returns all details in a readable format
    public String toString() {
        return "Title: " + title + ", Category: " + category + ", Due: " + dueDate + ", Priority: " + priority;
    }
}