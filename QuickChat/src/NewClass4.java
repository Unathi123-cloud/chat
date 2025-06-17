import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Message {
    public String sender;
    public String recipient;
    public String message;
    public String flag;
    public String id;
    public String hash;

    public Message(String sender, String recipient, String message, String flag, String id) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.flag = flag;
        this.id = id;
        this.hash = generateHash();
    }

    private String generateHash() {
        try {
            String data = sender + recipient + message + flag + id;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
