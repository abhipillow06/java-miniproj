package com.example.miniproj;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryCLI {
    private ArrayList<Item> inventory = new ArrayList<>();
    private Scanner scanner;

    public InventoryCLI() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item Quantity");
            System.out.println("3. Remove Item");
            System.out.println("4. View Inventory");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addItem();
                    break;
                case 2:
                    updateItem();
                    break;
                case 3:
                    removeItem();
                    break;
                case 4:
                    viewInventory();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addItem() {
        try {
            System.out.print("Enter Item ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            for (Item i : inventory) {
                if (i.getId() == id) {
                    System.out.println("ID already exists.");
                    return;
                }
            }

            System.out.print("Enter Item Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Item item = new Item(id, name, quantity);
            inventory.add(item);
            System.out.println("Item added successfully!");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); // Clear the buffer
        }
    }

    private void updateItem() {
        System.out.print("Enter Item ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Item item = findItemById(id);
        if (item != null) {
            System.out.print("Enter new quantity: ");
            int newQty = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            item.setQuantity(newQty);
            System.out.println("Item quantity updated.");
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    private void removeItem() {
        System.out.print("Enter Item ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Item item = findItemById(id);
        if (item != null) {
            inventory.remove(item);
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    private void viewInventory() {
        if (inventory.isEmpty()) {
            System.out.println("No items in inventory.");
            return;
        }
        System.out.println("\nInventory List:");
        System.out.printf("%-10s%-20s%-10s\n", "ID", "Name", "Quantity");
        for (Item item : inventory) {
            System.out.printf("%-10d%-20s%-10d\n", item.getId(), item.getName(), item.getQuantity());
        }
    }

    private Item findItemById(int id) {
        for (Item item : inventory) {
            if (item.getId() == id) return item;
        }
        return null;
    }

    public static void main(String[] args) {
        InventoryCLI cli = new InventoryCLI();
        cli.start();
    }
}

class Item {
    private int id;
    private String name;
    private int quantity;

    public Item(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
