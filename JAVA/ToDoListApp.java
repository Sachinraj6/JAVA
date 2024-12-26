import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        isCompleted = true;
    }
}

class ToDoList {
    private ArrayList<Task> tasks;

    public ToDoList() {
        tasks = new ArrayList<>();
    }

    public void addTask(String description) {
        tasks.add(new Task(description));
        System.out.println("Task added: " + description);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            System.out.println("Task removed: " + removedTask.getDescription());
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println((i + 1) + ". " + task.getDescription() + " [" + (task.isCompleted() ? "Completed" : "Not Completed") + "]");
            }
        }
    }

    public void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsCompleted();
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Invalid task index.");
        }
    }
}

public class ToDoListApp {
    private static Scanner scanner = new Scanner(System.in);
    private static ToDoList toDoList = new ToDoList();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    addNewTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    displayTasks();
                    break;
                case 4:
                    markTaskAsCompleted();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        System.out.println("Thank you for using the To-Do List app.");
    }

    private static void printMenu() {
        System.out.println("\nTo-Do List Menu:");
        System.out.println("1. Add new task");
        System.out.println("2. Remove task");
        System.out.println("3. Display tasks");
        System.out.println("4. Mark task as completed");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addNewTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        toDoList.addTask(description);
    }

    private static void removeTask() {
        System.out.print("Enter task index to remove: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        toDoList.removeTask(index);
    }

    private static void displayTasks() {
        toDoList.displayTasks();
    }

    private static void markTaskAsCompleted() {
        System.out.print("Enter task index to mark as completed: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        toDoList.markTaskAsCompleted(index);
    }
}