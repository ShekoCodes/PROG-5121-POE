package com.mycompany.message;

/**
 *
 * @author User Nkahosheko Kopa
 */

import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Message 
{
    // These variables store the individual details of each message object and the are encapsulated.
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String status;
    private int messageNumber;
    
    // The ArrayLists are used to categorise messages according their status throughout the program and it stores all the  messages successfully sent, messages saved for later and th,e discarded messages. 
    public static ArrayList <Message> sentMessages = new ArrayList<>();
    public static ArrayList <Message> storedMessages = new ArrayList<>();
    public static ArrayList <Message> disregardedMessages = new ArrayList<>();
    
    // The hashMap stores messages using the message ID as the key.
    public static HashMap<String, Message> messageMap = new HashMap<>();

    // This is a constructor for creating a Message object.
    // This constructor initializes the recipient cellphone number, message number and message text
    // This constructor also automatically generates a unique message ID, generates the message hash and stores the message object inside the HashMap
    
    public Message(String recipient,int messageNumber,String messageText) 
    {
        this.recipient = recipient;
        this.messageNumber = messageNumber;
        this.messageText = messageText;
        
     // Auto-generate ID
        this.messageID = generateMessageID();

     // Auto-generate hash
        this.messageHash = createMessageHash( this.messageID, this.messageNumber, this.messageText);

     // Store inside HashMap
        messageMap.put(this.messageID, this);
    }

    // This method is supposed to automatically generate a random 10-digit message ID.
    // The Random class is also used to create a unique numerical identifier for every message.
   
    public String generateMessageID()
            
    {
        Random random = new Random();

        long number = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        
        return String.valueOf(number);
    }
    
    // This method will then validate the message ID.
    
    public boolean checkMessageID(String id)
    {
        return id!= null && id.length()<=10;
    }
    
    // This method validates the recipient's cellphone number.
    
    public boolean checkRecipientCell(String number)
    {
      return number != null && number.matches("\\+[0-9]{10,11}");  
    }
    
    public boolean checkMessageLength()
    { 
        return messageText != null && messageText.length() <= 250;
    }
    
    // This method will create a unique message hash.
    // The hash is generated using the first two digits of the message ID, the message number, the first word of the message and the last word of the message
 
    public static String createMessageHash(String id, int messageNumber, String message)
    { 
        String [] words = message.trim().split("\\s+");
        
        String firstWord = words[0].toUpperCase();

        String lastWord = words[words.length - 1].toUpperCase();

        return id.substring(0, 2)+":"+messageNumber+":"+firstWord+lastWord;

    }
    
    // This method will then determine what happens to the message based on the user's menu selection.
    // It also updates the message status accordingly.
    
    public String sentMessage(int choice)
    {
        switch (choice)
        {
           case 1:

                status = "Sent";

                sentMessages.add(this);

                return "Message successfully sent.";

            case 2:

                status = "Disregarded";

                disregardedMessages.add(this);

                return "Press 0 to delete the message.";

            case 3:

                status = "Stored";

                storedMessages.add(this);

                storeMessage();

                return "Message successfully stored.";

            default:

                return "Invalid option.";  
        }
    }
    
    // This method loops through all the sent messages and displays their information.
    // returns a formatted string containing the message ID, message hash recipient, message text and the message status.
    
    public static String printMessage()
    {
       String output = "";

        for (Message msg : sentMessages) 
        {
            output += "\nMessage ID: " + msg.messageID;
            output += "\nMessage Hash: " + msg.messageHash;
            output += "\nRecipient: " + msg.recipient;
            output += "\nMessage: " + msg.messageText;
            output += "\nStatus: " + msg.status;
        }    
         return output;
    }

    // This method returns the total number of messages successfully sent.
    // The size() method is used to count the number of Message objects stored inside the sentMessages ArrayList.
    
    public static int returnTotalMessages()
    {
             return sentMessages.size();
            }

    public static void clearMessages()         
{
    sentMessages.clear();
    storedMessages.clear();
    disregardedMessages.clear();
    messageMap.clear();
}
    
    // This method stores message information inside a JSON file called messages.json.
    // Trough research I also learnt what a JSONObject is and what a FileWriter is. It is used to structure message details into key value pairs.
    // A JSONArray is used to hold the JSON object before writing it into the file.
    // FileWriter is used to physically write the JSON data into the file.

    public void storeMessage()
    {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("MessageID", messageID);
        jsonObject.put("MessageHash", createMessageHash(messageID, 1, messageText));
        jsonObject.put("Recipient", recipient);
        jsonObject.put("Message", messageText);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);

        try (FileWriter file = new FileWriter("messages.json", true)) 
        {

            file.write(jsonArray.toString());
            file.write(System.lineSeparator());
        } 
        catch (IOException e) 
        {

            System.out.println("Error writing to JSON file.");
        }
    }
    
    // For the getter methods, returns the message ID, MassageHash, recipient, messagetext and th,e status
    // Th,e getter methods were used to provide controlled access to private variables from outside the class.
    public String getMessageID()
    {

        return messageID;
    }

    public String getMessageHash()
    {

        return messageHash;
    }

    public String getRecipient()
    {

        return recipient;
    }

    public String getMessageText()
    {

        return messageText;
    }

    public String getStatus()
    {
        return status;
    }
}    
 
