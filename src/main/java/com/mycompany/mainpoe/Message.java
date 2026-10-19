/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainpoe;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


/**
 *
 * @author Student
 */
public class Message {
   
    
    // Variables to store message details
    private final String messageID;
    private final int messageNumber;
    private final String recipient;
    private final String message;
    private String messageHash;

    // Count successfully sent messages
    private static int totalMessagesSent = 0;

    // Track the status of the message
    private boolean sent = false;
    private boolean stored = false;
    private boolean disregarded = false;

    // Constructor
    public Message(String messageID, int messageNumber,
                   String recipient, String message) {

        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
    }

    // The check that the message ID contains 10 digits.
    public boolean checkMessageID() {

        return messageID != null
                && messageID.matches("[0-9]{10}");
    }

    // Check the recipient's cellphone number
    public String checkRecipientCell() {

        if (recipient != null
                && recipient.matches("^\\+27[0-9]{9}$")) {

            return "Cell phone number successfully captured.";

        } else {

            return "Cell phone number is incorrectly formatted "
                    + "or does not contain an international code. "
                    + "Please correct the number and try again.";
        }
    }

    // show shows that the message does not exceed 250 characters.
    public String checkMessageLength() {

        if (message != null && message.length() <= 250) {

            return "Message ready to send.";

        } else {

            int length = (message == null)
                    ? 0 : message.length();

            return "Message exceeds 250 characters by "
                    + Math.max(0, length - 250)
                    + "; please reduce the size.";
        }
    }

    // Check whether all message details are valid
    private boolean isValidMessage() {

        return checkMessageID()
                && recipient != null
                && recipient.matches("^\\+27[0-9]{9}$")
                && message != null
                && !message.trim().isEmpty()
                && message.length() <= 250;
    }

    // Create the message hash
    public String createMessageHash() {

        if (messageID == null || messageID.length() < 2
                || message == null || message.trim().isEmpty()) {

            return "";
        }

        // Get the first two digits of the message ID
        String firstTwoDigits = messageID.substring(0, 2);

        // Split the message into words
        String[] words = message.trim().split("\\s+");

        // This converts the first and last words to uppercase
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        // Combine the values to create the hash
        messageHash = firstTwoDigits + ":"
                + messageNumber + ":"
                + firstWord + lastWord;

        return messageHash;
    }

    // Allow the user to send, disregard or store a message
    public String SentMessage() {

        Scanner input = new Scanner(System.in);

        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message");
        System.out.print("Enter your choice: ");

        String choice = input.nextLine();

        String result;

        switch (choice) {

            case "1":

                if (!isValidMessage()) {

                    result = "Message cannot be sent. "
                            + "Please check the message details.";

                } else if (sent || stored || disregarded) {

                    result = "This message has already been processed.";

                } else {

                    createMessageHash();

                    sent = true;
                    totalMessagesSent++;

                    result = "Message successfully sent.";
                }
                break;

            case "2":

                if (sent || stored || disregarded) {

                    result = "This message has already been processed.";

                } else {

                    disregarded = true;

                    result = "Message disregarded.";
                }
                break;

            case "3":

                if (!isValidMessage()) {

                    result = "Message cannot be stored. "
                            + "Please check the message details.";

                } else if (sent || stored || disregarded) {

                    result = "This message has already been processed.";

                } else {

                    result = storeMessage();
                }
                break;

            default:

                result = "Invalid choice. Please try again.";
                break;
        }

        return result;
    }

    // Store the message in a JSON file
    public String storeMessage() {

        // Validate the message
        if (!isValidMessage()) {

            return "Message cannot be stored. "
                    + "Please check the message details.";
        }

        // Prevent the message from being processed twice
        if (sent || stored || disregarded) {

            return "This message has already been processed.";
        }

        // Generate the message hash
        createMessageHash();

        // Create a Gson object
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        // Create or locate the JSON file
        File file = new File("messages.json");

        // Create an ArrayList for the messages
        ArrayList<Message> messages = new ArrayList<>();

        // Read messages already saved in the file
        if (file.exists() && file.length() > 0) {

            try (FileReader reader = new FileReader(file)) {

                Type messageListType =
                        new TypeToken<ArrayList<Message>>() {
                        }.getType();

                ArrayList<Message> savedMessages =
                        gson.fromJson(reader, messageListType);

                if (savedMessages != null) {

                    messages = savedMessages;
                }

            } catch (IOException | RuntimeException e) {

                return "Error reading JSON file: "
                        + e.getMessage();
            }
        }

        // Add the current message to the list
        messages.add(this);

        //  This saves the list into the JSON file
        try (FileWriter writer = new FileWriter(file)) {

            stored = true;

            gson.toJson(messages, writer);

            return "Message successfully stored.";

        } catch (IOException e) {

            stored = false;

            return "Error storing message: "
                    + e.getMessage();
        }
    }

    // Display the message details
    public String printMessages() {

        if (messageHash == null) {

            createMessageHash();
        }

        String details = "\nMessage Details"
                + "\nMessage ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;

        return details;
    }

    // Return the total number of successfully sent messages
    public int returnTotalMessagess() {

        return totalMessagesSent;
    }
}