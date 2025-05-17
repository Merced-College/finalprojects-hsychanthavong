import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in); //scanner for user input
        TaskManager manager = new TaskManager(); //creates task manager instance

        boolean running = true; //flag to control program loop

        //displays options on a menu to user
        while (running) {
            System.out.println("\n=== Task Manager ===");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Undo");
            System.out.println("4. Delete Task");
            System.out.println("5. Check Reminders");
            System.out.println("6. Find Task");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine(); //read the users input

            switch (choice) {
                //adds new task
                case "1":
                    manager.addTask(scanner);
                    break;
                case "2":
                //shows all current tasks stored in array
                    manager.displayTasks();
                    break;
                case "3":
                //undo last delete or add
                    manager.undo();
                    break;
                case "4":
                //delete task by entering title
                    manager.deleteTaskByTitle(scanner);
                    break;
                case "5":
                //checks reminders for today
                    System.out.print("Enter today's date (YYYY-MM-DD): ");
                    String today = scanner.nextLine();
                    manager.checkReminders(today);
                    break;
                case "6":
                //find task by title and displays details
                    System.out.print("Enter Task to find: ");
                    String title = scanner.nextLine();
                    Task found = manager.findTaskbyTitle(title);
                    if (found != null) {
                        System.out.println("Found: " + found);
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case "7":
                //exits program
                    running = false;
                    System.out.println("Exiting Task Manager.");
                    break;
                default:
                //handles invalid inputs
                    System.out.println("Invalid option.");
            }
        }

        scanner.close(); //closes scanner
        System.out.println("See you later!"); //goodbye message

    }
}