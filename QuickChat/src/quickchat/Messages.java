

package quickchat;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
        
public final class Messages {
    private final String messageId;
private static int messageCount = 0;
private final int messageNumber;
private final String recipient;
private final String messageText;
private final String messageHash;

public Messages(String recipient, String messageText) {
this.messageId = generateMessageId();
this.recipient = recipient;
this.messageText = messageText;
this.messageNumber = ++messageCount;
this.messageHash = createMessageHash();
}

private String generateMessageId() {
long number = (long) (Math.random() * 1_000_000_000L);
return String.format("%010d", number);
}

public boolean checkMessageID() {
return messageId.length() == 10;
}

public boolean checkRecipientCell() {
return recipient.matches("^\\+?\\d{10,13}$");
}

public String createMessageHash() {
String[] words = messageText.trim().split("\\s+");
int hash = 7;
for (String word : words) {
hash = hash * 31 + word.hashCode();
}
return Integer.toHexString(hash);
}

public JSONObject toJSON() {
JSONObject json = new JSONObject();
json.put("messageId", messageId);
json.put("recipient", recipient);
json.put("messageText", messageText);
json.put("messageNumber", messageNumber);
json.put("messageHash", messageHash);
return json;
}

public void saveToFile(String filename) throws IOException {
try (FileWriter file = new FileWriter("messages.json")) {
file.write(toJSON().toString(4));
}
}
    
}
