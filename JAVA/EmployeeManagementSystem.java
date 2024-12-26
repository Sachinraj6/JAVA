import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int age;
    private String department;

    public Employee(int id, String name, int age, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Department: " + department;
    }
}

class EmployeeManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Employee> employees;

    public EmployeeManager() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(int id) {
        employees.removeIf(employee -> employee.getId() == id);
    }

    public void updateEmployee(int id, int age, String department) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setAge(age);
                employee.setDepartment(department);
                break;
            }
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static EmployeeManager loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (EmployeeManager) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new EmployeeManager();
        }
    }
}

public class EmployeeManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static EmployeeManager employeeManager = EmployeeManager.loadFromFile("employees.dat");

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    removeEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    displayEmployees();
                    break;
                case 5:
                    exit = true;
                    employeeManager.saveToFile("employees.dat");
                    System.out.println("Employee data saved to file.");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        System.out.println("Thank you for using the employee management system.");
    }

    private static void printMenu() {
        System.out.println("\nEmployee Management System Menu:");
        System.out.println("1. Add new employee");
        System.out.println("2. Remove employee");
        System.out.println("3. Update employee");
        System.out.println("4. Display employees");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addEmployee() {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter employee department: ");
        String department = scanner.nextLine();

        employeeManager.addEmployee(new Employee(id, name, age, department));
        System.out.println("Employee added: " + name);
    }

    private static void removeEmployee() {
        System.out.print("Enter employee ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        employeeManager.removeEmployee(id);
        System.out.println("Employee removed.");
    }

    private static void updateEmployee() {
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new department: ");
        String department = scanner.nextLine();

        employeeManager.updateEmployee(id, age, department);
        System.out.println("Employee updated.");
    }

    private static void displayEmployees() {
        List<Employee> employees = employeeManager.getEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees available.");
        } else {
            System.out.println("Employees:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }
}