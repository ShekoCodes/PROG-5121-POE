package com.mycompany.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author User Nkahosheko Kopa
 */

public class MessageTest 
{
    @Test
    public void testMessageLengthSuccess ()      
    {
        Message message = new Message("+27718693002",0,"Hi Mike, can you join us for dinner tonight?");
        assertTrue(message.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure()
    {
        String longMessage = "a".repeat(260);
        Message message = new Message("+27718693002",0,longMessage);
        assertTrue(message.checkMessageLength());
    }

    @Test
    public void testRecipientCellSuccess()
    {
        Message message = new Message("+27718693002",0,"Hello");

        assertTrue( message.checkRecipientCell("+27718693002"));
    }

    @Test
    public void testRecipientCellFailure()
    {
        Message message = new Message("08575975889",0,"Hello");

        assertTrue( message.checkRecipientCell("08575975889"));
    }

    @Test
    public void testCreateMessageHash()
    {
        String hash = Message.createMessageHash("0012345678",0,"Hi thanks");

        assertEquals("00:0:HITHANKS",hash);
    }

    @Test
    public void testMessageIDGenerated()
    {
        Message message = new Message("+27718693002",0,"Hello");

        assertNotNull( message.getMessageID());

        assertEquals(10,message.getMessageID().length());
    }

    @Test
    public void testSendMessage()
    {
        Message.clearMessages();

        Message message = new Message("+27718693002", 0,"Hello");

        String result = message.sentMessage(1);

        assertEquals("Message successfully sent.",result);
    }

    @Test
    public void testDisregardMessage()
    {
        Message.clearMessages();

        Message message = new Message("+27718693002", 0, "Hello");

        String result = message.sentMessage(2);

        assertEquals("Press 0 to delete the message.", result);
    }

    @Test
    public void testStoreMessage()
    {
        Message.clearMessages();

        Message message = new Message("+27718693002",0,"Hello");

        String result = message.sentMessage(3);
        assertEquals("Message successfully stored.",result);
    }

    @Test
    public void testReturnTotalMessages()
    {
        Message.clearMessages();

        Message message1 = new Message("+27718693002",0, "Hello");

        message1.sentMessage(1);

        Message message2 =
                new Message("+27718693002", 1, "Hi again");

        message2.sentMessage(1);

        assertEquals( 2, Message.returnTotalMessages());
    }

    
}
