import java.util.*;

class User {
    int id;
    String name;
    String email;

    User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

public class UserManagement {
    static List<User> users = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int idCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- User Management ---");
            System.out.println("1. Create User");
            System.out.println("2. View Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1 -> createUser();
                case 2 -> viewUsers();
                case 3 -> updateUser();
                case 4 -> deleteUser();
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid option!");
            }
        }
    }

    static void createUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        users.add(new User(idCounter++, name, email));
        System.out.println("User created successfully!");
    }

    static void viewUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users) {
                System.out.println("ID: " + user.id + ", Name: " + user.name + ", Email: " + user.email);
            }
        }
    }

    static void updateUser() {
        System.out.print("Enter user ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (User user : users) {
            if (user.id == id) {
                System.out.print("Enter new name: ");
                user.name = scanner.nextLine();
                System.out.print("Enter new email: ");
                user.email = scanner.nextLine();
                System.out.println("User updated successfully!");
                return;
            }
        }
        System.out.println("User not found!");
    }

    static void deleteUser() {
        System.out.print("Enter user ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().id == id) {
                iterator.remove();
                System.out.println("User deleted successfully!");
                return;
            }
        }
        System.out.println("User not found!");
    }
}

