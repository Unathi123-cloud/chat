/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package quickchat;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author RC_Student_lab
 */
public class MessagesTest {
    
   @Test
public void testMessageIdLength() {
    Messages msg = new Messages("+1234567890", "Hello!");
    assertTrue("Message ID should be 10 digits long", msg.checkMessageID());
}

@Test
public void testValidRecipientNumber() {
    Messages msg = new Messages("+12345678901", "Test");
    assertTrue("Valid recipient number should pass regex check", msg.checkRecipientCell());
}

@Test
public void testInvalidRecipientNumber() {
    Messages msg = new Messages("abc123", "Invalid");
    assertFalse("Non-numeric recipient should fail regex check", msg.checkRecipientCell());
}

    @Test
    public void testMessageHashConsistency() {
        Messages msg1 = new Messages("+1234567890", "Same message");
        Messages msg2 = new Messages("+1234567890", "Same message");
        assertEquals(msg1.createMessageHash(), msg2.createMessageHash(), "Hash should be consistent for same text");
    }

    @Test
    public void testToJSONStructure() {
        Messages msg = new Messages("+1234567890", "Testing JSON");
        JSONObject json = msg.toJSON();
        assertNotNull(json.get("messageId"));
        assertEquals("Testing JSON", json.get("messageText"));
        assertEquals("+1234567890", json.get("recipient"));
        assertNotNull(json.get("messageHash"));
        assertTrue(json.has("messageNumber"));
    }

    @Test
public void testSaveToFileCreatesFile() throws IOException {
    Messages msg = new Messages("+1234567890", "Save this to file");
    String filename = "test_message.json";
    msg.saveToFile(filename);

    File file = new File("messages.json"); // Still hardcoded in your class
    assertTrue("File should be created", file.exists());

    // Optionally delete the file after test
    file.delete();
}
}
