import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String name;
    private int priority;
    private String deadline;
    private int duration;

    public Task(String name, int priority, String deadline, int duration) {
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public int getDuration() {
        return duration;
    }
}

class TimeKeeperScheduler {
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        // Create some sample tasks for testing
        Task task1 = new Task("Task 1", 2, "2023-07-10", 60);
        Task task2 = new Task("Task 2", 1, "2023-07-12", 90);
        tasks.add(task1);
        tasks.add(task2);

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("==== TimeKeeper Scheduler ====");
            System.out.println("1. Add a task");
            System.out.println("2. Edit a task");
            System.out.println("3. Delete a task");
            System.out.println("4. View all tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading an integer

            switch (choice) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    editTask(scanner);
                    break;
                case 3:
                    deleteTask(scanner);
                    break;
                case 4:
                    viewAllTasks();
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter the task name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the task priority: ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character after reading an integer

        System.out.print("Enter the task deadline: ");
        String deadline = scanner.nextLine();

        System.out.print("Enter the task duration (in minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character after reading an integer

        Task task = new Task(name, priority, deadline, duration);
        tasks.add(task);

        System.out.println("Task added successfully!");
    }

    private static void editTask(Scanner scanner) {
        System.out.print("Enter the task name to edit: ");
        String name = scanner.nextLine();

        Task task = findTaskByName(name);
        if (task == null) {
            System.out.println("Task not found!");
            return;
        }

        System.out.print("Enter the new task name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter the new task priority: ");
        int newPriority = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character after reading an integer

        System.out.print("Enter the new task deadline: ");
        String newDeadline = scanner.nextLine();

        System.out.print("Enter the new task duration (in minutes): ");
        int newDuration = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character after reading an integer

        task = new Task(newName, newPriority, newDeadline, newDuration);
        tasks.remove(findTaskByName(name));
        tasks.add(task);

        System.out.println("Task edited successfully!");
    }

    private static void deleteTask(Scanner scanner) {
        System.out.print("Enter the task name to delete: ");
        String name = scanner.nextLine();

        Task task = findTaskByName(name);
        if (task == null) {
            System.out.println("Task not found!");
            return;
        }

        tasks.remove(task);
        System.out.println("Task deleted successfully!");
    }

    private static Task findTaskByName(String name) {
        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(name)) {
                return task;
            }
        }
        return null;
    }

    private static void viewAllTasks() {
        System.out.println("==== All Tasks ====");
        for (Task task : tasks) {
            System.out.println("Name: " + task.getName());
            System.out.println("Priority: " + task.getPriority());
            System.out.println("Deadline: " + task.getDeadline());
            System.out.println("Duration: " + task.getDuration() + " minutes");
            System.out.println("-------------------");
        }
    }
}
