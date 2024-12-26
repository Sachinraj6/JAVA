import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String roomType;
    private boolean isBooked;

    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isBooked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookRoom() {
        if (!isBooked) {
            isBooked = true;
            System.out.println("Room " + roomNumber + " booked successfully.");
        } else {
            System.out.println("Room " + roomNumber + " is already booked.");
        }
    }

    public void checkOut() {
        if (isBooked) {
            isBooked = false;
            System.out.println("Checked out from room " + roomNumber + " successfully.");
        } else {
            System.out.println("Room " + roomNumber + " is not booked.");
        }
    }
}

class Customer {
    private String name;
    private String contactNumber;
    private Room room;

    public Customer(String name, String contactNumber, Room room) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Contact Number: " + contactNumber + ", Room Number: " + room.getRoomNumber();
    }
}

class Hotel {
    private ArrayList<Room> rooms;
    private ArrayList<Customer> customers;

    public Hotel() {
        rooms = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public void addRoom(int roomNumber, String roomType) {
        rooms.add(new Room(roomNumber, roomType));
        System.out.println("Room " + roomNumber + " added successfully.");
    }

    public void removeRoom(int roomNumber) {
        Room roomToRemove = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                roomToRemove = room;
                break;
            }
        }
        if (roomToRemove != null) {
            rooms.remove(roomToRemove);
            System.out.println("Room " + roomNumber + " removed successfully.");
        } else {
            System.out.println("Room " + roomNumber + " not found.");
        }
    }

    public void displayRooms() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms available in the hotel.");
        } else {
            System.out.println("Rooms available in the hotel:");
            for (Room room : rooms) {
                System.out.println("Room Number: " + room.getRoomNumber() + ", Room Type: " + room.getRoomType() + ", Booked: " + (room.isBooked() ? "Yes" : "No"));
            }
        }
    }

    public void bookRoom(int roomNumber, String name, String contactNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && !room.isBooked()) {
                room.bookRoom();
                customers.add(new Customer(name, contactNumber, room));
                return;
            }
        }
        System.out.println("Room " + roomNumber + " is either not available or already booked.");
    }

    public void checkOutRoom(int roomNumber) {
        Customer customerToRemove = null;
        for (Customer customer : customers) {
            if (customer.getRoom().getRoomNumber() == roomNumber) {
                customer.getRoom().checkOut();
                customerToRemove = customer;
                break;
            }
        }
        if (customerToRemove != null) {
            customers.remove(customerToRemove);
        } else {
            System.out.println("Room " + roomNumber + " is not booked.");
        }
    }

    public void displayCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers in the hotel.");
        } else {
            System.out.println("Customers in the hotel:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }
}

public class HotelBookingSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Hotel hotel = new Hotel();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    removeRoom();
                    break;
                case 3:
                    displayRooms();
                    break;
                case 4:
                    bookRoom();
                    break;
                case 5:
                    checkOutRoom();
                    break;
                case 6:
                    displayCustomers();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        System.out.println("Thank you for using the hotel booking system.");
    }

    private static void printMenu() {
        System.out.println("\nHotel Booking System Menu:");
        System.out.println("1. Add room");
        System.out.println("2. Remove room");
        System.out.println("3. Display rooms");
        System.out.println("4. Book room");
        System.out.println("5. Check out room");
        System.out.println("6. Display customers");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addRoom() {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter room type: ");
        String roomType = scanner.nextLine();
        hotel.addRoom(roomNumber, roomType);
    }

    private static void removeRoom() {
        System.out.print("Enter room number to remove: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        hotel.removeRoom(roomNumber);
    }

    private static void displayRooms() {
        hotel.displayRooms();
    }

    private static void bookRoom() {
        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine();
        hotel.bookRoom(roomNumber, name, contactNumber);
    }

    private static void checkOutRoom() {
        System.out.print("Enter room number to check out: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        hotel.checkOutRoom(roomNumber);
    }

    private static void displayCustomers() {
        hotel.displayCustomers();
    }
}