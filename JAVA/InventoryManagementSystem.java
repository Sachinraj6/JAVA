import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int quantity;
    private double price;

    public Item(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Quantity: " + quantity + ", Price: $" + price;
    }
}

class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(int id) {
        items.removeIf(item -> item.getId() == id);
    }

    public void updateItem(int id, int quantity, double price) {
        for (Item item : items) {
            if (item.getId() == id) {
                item.setQuantity(quantity);
                item.setPrice(price);
                break;
            }
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Inventory loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Inventory) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Inventory();
        }
    }
}

public class InventoryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Inventory inventory = Inventory.loadFromFile("inventory.dat");

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    updateItem();
                    break;
                case 4:
                    displayItems();
                    break;
                case 5:
                    generateReport();
                    break;
                case 6:
                    exit = true;
                    inventory.saveToFile("inventory.dat");
                    System.out.println("Inventory saved to file.");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        System.out.println("Thank you for using the inventory management system.");
    }

    private static void printMenu() {
        System.out.println("\nInventory Management System Menu:");
        System.out.println("1. Add new item");
        System.out.println("2. Remove item");
        System.out.println("3. Update item");
        System.out.println("4. Display items");
        System.out.println("5. Generate inventory report");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addItem() {
        System.out.print("Enter item ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        inventory.addItem(new Item(id, name, quantity, price));
        System.out.println("Item added: " + name);
    }

    private static void removeItem() {
        System.out.print("Enter item ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        inventory.removeItem(id);
        System.out.println("Item removed.");
    }

    private static void updateItem() {
        System.out.print("Enter item ID to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        inventory.updateItem(id, quantity, price);
        System.out.println("Item updated.");
    }

    private static void displayItems() {
        List<Item> items = inventory.getItems();
        if (items.isEmpty()) {
            System.out.println("No items in the inventory.");
        } else {
            System.out.println("Inventory items:");
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }

    private static void generateReport() {
        List<Item> items = inventory.getItems();
        if (items.isEmpty()) {
            System.out.println("No items in the inventory to generate report.");
        } else {
            double totalValue = 0;
            System.out.println("Inventory Report:");
            for (Item item : items) {
                System.out.println(item);
                totalValue += item.getQuantity() * item.getPrice();
            }
            System.out.println("Total Inventory Value: $" + totalValue);
        }
    }
}