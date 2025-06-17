import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageAppTest {

    @BeforeEach
    public void setup() {
        MessageApp.sentMessages.clear();
        MessageApp.populateTestData();
    }

    @Test
    public void testLongestMessage() {
        Message longest = MessageApp.sentMessages.stream().max((a, b) -> a.message.length() - b.message.length()).orElse(null);
        assertNotNull(longest);
        assertEquals("It is dinner time !", longest.message);
    }

    @Test
    public void testSearchById() {
        MessageApp.searchMessageById("M001");
        assertTrue(MessageApp.messageIDs.contains("M001"));
    }

    @Test
    public void testDeleteByHash() {
        String hashToDelete = MessageApp.sentMessages.get(0).hash;
        MessageApp.deleteMessageByHash(hashToDelete);
        assertFalse(MessageApp.messageHashes.contains(hashToDelete));
    }
}

