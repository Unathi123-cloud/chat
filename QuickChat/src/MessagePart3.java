import java.util.*;

public class MessageApp {
    static ArrayList<Message> sentMessages = new ArrayList<>();
    static ArrayList<Message> disregardedMessages = new ArrayList<>();
    static ArrayList<Message> storedMessages = new ArrayList<>();
    static ArrayList<String> messageHashes = new ArrayList<>();
    static ArrayList<String> messageIDs = new ArrayList<>();

    public static void main(String[] args) {
        populateTestData();
        displayMenu();
    }

    public static void populateTestData() {
        // Message 1
        Message m1 = new Message("System", "+27834557896", "Did you get the cake?", "Sent", "M001");
        sentMessages.add(m1);

        // Message 2 - corrupted, skipped

        // Message 3
        Message m3 = new Message("System", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored", "M002");
        storedMessages.add(m3);

        Message m3b = new Message("System", "+27834484567", "Yohoooo, I am at your gate.", "Disregard", "M003");
        disregardedMessages.add(m3b);

        // Message 4
        Message m4 = new Message("Developer", "0838884567", "It is dinner time !", "Sent", "M004");
        sentMessages.add(m4);

        // Message 5
        Message m5 = new Message("System", "+27838884567", "Ok, I am leaving without you.", "Stored", "M365");
        storedMessages.add(m5);

        // Populate hash and ID arrays
        for (Message m : sentMessages) {
            messageHashes.add(m.hash);
            messageIDs.add(m.id);
        }
        for (Message m : storedMessages) {
            messageHashes.add(m.hash);
            messageIDs.add(m.id);
        }
        for (Message m : disregardedMessages) {
            messageHashes.add(m.hash);
            messageIDs.add(m.id);
        }
    }

    public static void displayMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Message App ---");
            System.out.println("1. Display sender and recipient of sent messages");
            System.out.println("2. Display the longest sent message");
            System.out.println("3. Search for a message by ID");
            System.out.println("4. Search messages by recipient");
            System.out.println("5. Delete message by hash");
            System.out.println("6. Display sent message report");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> displaySendersAndRecipients();
                case 2 -> displayLongestSentMessage();
                case 3 -> {
                    System.out.print("Enter Message ID: ");
                    String id = sc.nextLine();
                    searchMessageById(id);
                }
                case 4 -> {
                    System.out.print("Enter recipient number: ");
                    String recipient = sc.nextLine();
                    searchMessagesByRecipient(recipient);
                }
                case 5 -> {
                    System.out.print("Enter hash to delete: ");
                    String hash = sc.nextLine();
                    deleteMessageByHash(hash);
                }
                case 6 -> displayFullSentReport();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public static void displaySendersAndRecipients() {
        for (Message m : sentMessages) {
            System.out.println("Sender: " + m.sender + " -> Recipient: " + m.recipient);
        }
    }

    public static void displayLongestSentMessage() {
        Message longest = sentMessages.stream().max(Comparator.comparingInt(m -> m.message.length())).orElse(null);
        if (longest != null) {
            System.out.println("Longest Message: " + longest.message);
        }
    }

    public static void searchMessageById(String id) {
        for (Message m : sentMessages) {
            if (m.id.equals(id)) {
                System.out.println("Recipient: " + m.recipient);
                System.out.println("Message: " + m.message);
                return;
            }
        }
        System.out.println("Message ID not found in sent messages.");
    }

    public static void searchMessagesByRecipient(String recipient) {
        for (Message m : sentMessages) {
            if (m.recipient.equals(recipient)) {
                System.out.println("ID: " + m.id + " -> " + m.message);
            }
        }
    }

    public static void deleteMessageByHash(String hash) {
        Iterator<Message> iterator = sentMessages.iterator();
        while (iterator.hasNext()) {
            Message m = iterator.next();
            if (m.hash.equals(hash)) {
                iterator.remove();
                messageHashes.remove(hash);
                messageIDs.remove(m.id);
                System.out.println("Message deleted.");
                return;
            }
        }
        System.out.println("Hash not found in sent messages.");
    }

    public static void displayFullSentReport() {
        for (Message m : sentMessages) {
            System.out.println("ID: " + m.id);
            System.out.println("Sender: " + m.sender);
            System.out.println("Recipient: " + m.recipient);
            System.out.println("Message: " + m.message);
            System.out.println("Hash: " + m.hash);
            System.out.println("----------------------");
        }
    }
}

