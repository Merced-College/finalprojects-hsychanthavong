import java.util.*;

public class TaskManager {
    //array that stores up to 100 tasks
    private Task[] taskArray = new Task[100];
    private int taskCount = 0; //tracks current number of tasks
    private Stack<Task> undoStack = new Stack<>(); //stack for undo function - stores tasks that can be undone
    private Queue<Task> reminderQueue = new LinkedList<>(); //queue that manages reminders - stores tasks with due date entered
    private HashMap<String, Task> taskMap = new HashMap<>(); //hash map that gives fast lookup of tasks by their name

    //add a task by getting input from user
    public void addTask(Scanner scanner) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();
        System.out.print("Enter priority (1-5): ");
        int priority = Integer.parseInt(scanner.nextLine());

        //creates new task object using user input
        Task newTask = new Task(title, category, dueDate, priority);
        taskArray[taskCount++] = newTask; //store task in array and increment count
        undoStack.push(newTask); //saves task in undo stack
        taskMap.put(title.toLowerCase(), newTask); //add task to hash map
        System.out.println("Task added.");
    }
        
    //view all current tasks in array
    public void displayTasks() {
        if (taskCount == 0) {
            System.out.println("No tasks found.");
            return;
        }
    
        System.out.println("=== Task List ===");
        //for loop iterates through the task array
        for (int i = 0; i < taskCount; i++) {
            if (taskArray[i] != null) {
                System.out.println((i + 1) + ". " + taskArray[i]); //prints task number and task details
            }
        }
    }

    //undo last action either adding or deleting
    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        //gets last task from undo stack
        Task lastTask = undoStack.pop();

        //tries to find the task in the array
        boolean wasFound = false;
        for (int i = 0; i < taskCount; i++) {
            if (taskArray[i] == lastTask) {
                for (int j = i; j < taskCount - 1; j++) { //removes task from array by shifting elements left
                    taskArray[j] = taskArray[j + 1];
                }
                taskArray[--taskCount] = null;
                System.out.println("Undo successful: Removed Task \"" + lastTask.getTitle() + "\".");
                wasFound = true;
                break;
            }
        }

        //checks if task is found in array if it was deleted it restores it
        if (!wasFound && taskCount < taskArray.length) {
            taskArray[taskCount++] = lastTask;
            taskMap.put(lastTask.getTitle().toLowerCase(), lastTask);
            System.out.println("Undo successful: Restored deleted task \"" + lastTask.getTitle() + "\".");
        }
    }

    //delete a task by its title
    public void deleteTaskByTitle(Scanner scanner) {
        String titleToDelete = scanner.nextLine();

    //searches array for matching title
        for (int i = 0; i < taskCount; i++) {
            if (taskArray[i] != null && taskArray[i].getTitle().equalsIgnoreCase(titleToDelete)) {
                undoStack.push(taskArray[i]);
            
            //remove from array by shifting
            for (int j = i; j < taskCount - 1; j++) {
                taskArray[j] = taskArray[j + 1];
                }
                taskArray[--taskCount] = null;
                taskMap.remove(titleToDelete.toLowerCase()); //remove from hashmap 

                System.out.println("Task \"" + titleToDelete + "\" deleted.");
                return;
            }
        }
            System.out.println("Task not found.");
    }
     
    public void checkReminders(String today) {
        reminderQueue.clear(); //clears previous reminders from linkedlist

        //looks for tasks due today
        for (int i = 0; i < taskCount; i++) {
            Task task = taskArray[i];
            if (task != null && task.getDueDate().equals(today)) {
                reminderQueue.offer(task); //adding to back of queue
            }
        }

        //display reminders
        if (reminderQueue.isEmpty()) {
            System.out.println("No tasks due today.");
        } else {
            System.out.println("Reminders for today:");
            while (!reminderQueue.isEmpty()) {
                Task reminder = reminderQueue.poll(); //gets the next reminder task
                System.out.println("- " + reminder.getTitle() + " (Category: " + reminder.getCategory() + ")");
            }
        }
    }
    
    //task lookup using lowercase title as key
    public Task findTaskbyTitle(String title) {
        return taskMap.get(title.toLowerCase());
    }

    //recursive function that calculates weighted priority
    public int calculatePriorityWeight(int priority) {
        if (priority <= 1) return 1;
        return priority * calculatePriorityWeight(priority - 1); //multiplies current priority by the factorial of (priority - 1)
    }
}

