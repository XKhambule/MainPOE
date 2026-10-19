/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.mainpoe.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class TestMessage {
        
    // Test 1: Valid message ID
    @Test
    public void testValidMessageID() {

        Message message = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Hi tonight"
        );

        assertTrue(message.checkMessageID());
    }

    // Test 2: Invalid message ID
    @Test
    public void testInvalidMessageID() {

        Message message = new Message(
                "12345",
                0,
                "+27718693002",
                "Hi tonight"
        );

        assertFalse(message.checkMessageID());
    }

    // Test 3: Valid recipient cellphone number
    @Test
    public void testValidRecipientCell() {

        Message message = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Hi tonight"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    // Test 4: Invalid recipient cellphone number
    @Test
    public void testInvalidRecipientCell() {

        Message message = new Message(
                "0012345678",
                0,
                "0718693002",
                "Hi tonight"
        );

        assertEquals(
                "Cell phone number is incorrectly formatted "
                + "or does not contain an international code. "
                + "Please correct the number and try again.",
                message.checkRecipientCell()
        );
    }

    // Test 5: Valid message length
    @Test
    public void testValidMessageLength() {

        Message message = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Hi tonight"
        );

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength()
        );
    }

    // Test 6: Message exceeds 250 characters
    @Test
    public void testInvalidMessageLength() {

        String longMessage = "A".repeat(251);

        Message message = new Message(
                "0012345678",
                0,
                "+27718693002",
                longMessage
        );

        assertEquals(
                "Message exceeds 250 characters by 1; "
                + "please reduce the size.",
                message.checkMessageLength()
        );
    }

    // Test 7: Message contains exactly 250 characters
    @Test
    public void testMessageExactly250Characters() {

        String messageText = "A".repeat(250);

        Message message = new Message(
                "0012345678",
                0,
                "+27718693002",
                messageText
        );

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength()
        );
    }

    // Test 8: Correct message hash
    @Test
    public void testCreateMessageHash() {

        Message message = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Hi tonight"
        );

        assertEquals(
                "00:0:HITONIGHT",
                message.createMessageHash()
        );
    }

    // Test 9: Hash with a different message number
    @Test
    public void testCreateMessageHashNumberTwo() {

        Message message = new Message(
                "1234567890",
                2,
                "+27718693002",
                "Hello how are you today"
        );

        assertEquals(
                "12:2:HELLOTODAY",
                message.createMessageHash()
        );
    }

    // Test 10: Display message details
    @Test
    public void testPrintMessages() {

        Message message = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Hi tonight"
        );

        String result = message.printMessages();

        assertTrue(result.contains("0012345678"));
        assertTrue(result.contains("00:0:HITONIGHT"));
        assertTrue(result.contains("+27718693002"));
        assertTrue(result.contains("Hi tonight"));
    }

    // Test 11: Check the total messages sent
    @Test
    public void testTotalMessagesSent() {

        Message message = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Hi tonight"
        );

        assertTrue(message.returnTotalMessagess() >= 0);
    }
}

