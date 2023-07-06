import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

class User {
    private String username;
    private String password;
    private String email;
    private boolean isActive;
    private String resetToken;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isActive = false;
        this.resetToken = null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
}

class UserAuth {
    private static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        // User registration and login flow
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        boolean isAuthenticated = false;

        while (isRunning) {
            if (!isAuthenticated) {
                System.out.println("==== TimeKeeper Scheduler ====");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character after reading an integer

                switch (choice) {
                    case 1:
                        registerUser(scanner);
                        break;
                    case 2:
                        isAuthenticated = loginUser(scanner);
                        break;
                    case 3:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } else {
                // User is authenticated, perform tasks management or other actions
                // Add code for managing tasks or other functionalities here
                isAuthenticated = false; // Simulating logout by resetting authentication
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        // Check if the username is already taken
        if (isUsernameTaken(username)) {
            System.out.println("Username is already taken. Please choose a different username.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        // Create a new user object
        User user = new User(username, password, email);
        users.add(user);

        System.out.println("Registration successful!");
    }

    private static boolean loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Find the user by username
        User user = findUserByUsername(username);

        // Check if the user exists, is active, and the password matches
        if (user != null && user.isActive() && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }

    private static User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    private static boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    // Password reset functionality
    private static void sendPasswordResetEmail(User user) {
        String token = generatePasswordResetToken();

        // Store the token in the user's account
        user.setResetToken(token);

        // Send password reset email
        String emailBody = "Click the following link to reset your password: http://yourwebsite.com/reset-password?token=" + token;
        sendEmail(user.getEmail(), "Password Reset", emailBody);

        System.out.println("Password reset email sent successfully!");
    }

    private static String generatePasswordResetToken() {
        // Generate a unique password reset token (e.g., using UUID.randomUUID())
        // You can also set an expiration time for the token
        return UUID.randomUUID().toString();
    }

    private static void sendEmail(String recipientEmail, String subject, String body) {
        // Code for sending email using your preferred email sending mechanism
    }
}
