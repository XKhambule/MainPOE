/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainpoe;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Message {
   
    private final String messageID;
    private final int messageNumber;
    private final String recipient;
    private final String message;
    private String messageHash;
    
    //constructor that runs everytime a new parcel is created
     public Message(String messageID, int messageNumber,
                   String recipient, String message) {

        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
     }
        // Check that the message ID is no more than 10 characters
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
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

    // Check the message length
    public String checkMessageLength() {

        if (message != null && message.length() <= 250) {

            return "Message ready to send.";

        } else {

            int length = (message == null) ? 0 : message.length();

            return "Message exceeds 250 characters by "
                    + Math.max(0, length - 250)
                    + "; please reduce the size.";
        }
    }
        // Create the message hash
    public String createMessageHash() {

        // Check that the message ID and message are valid
        if (messageID == null || messageID.length() < 2
                || message == null || message.trim().isEmpty()) {
            return "";
        }

        // Get the first two characters of the message ID
        String firstTwoDigits = messageID.substring(0, 2);

        // Split the message into words
        String[] words = message.trim().split("\\s+");

        // Get the first and last words and convert them to uppercase
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        // Combine the details to create the message hash
        messageHash = firstTwoDigits + ":"
                + messageNumber + ":"
                + firstWord + lastWord;

        return messageHash;
    }
        // Allow the user to send, disregard or store the message
    public String SentMessage() {

        Scanner input = new Scanner(System.in);

         String result;

        switch (choice) {

            case "1":
                if (!checkMessageID()
                        || !recipient.matches("^\\+27[0-9]{9}$")
                        || message == null
                        || message.length() > 250) {

                    result = "Message cannot be sent. "
                            + "Please check the message details.";

                } else {

                    totalMessagesSent++;
                    result = "Message successfully sent.";
                }
                break;

            case "2":
                result = "Press 0 to delete the message.";
                break;

            case "3":
                result = "Message successfully stored.";
                break;

            default:
                result = "Invalid choice. Please try again.";
                break;
        }

        return result;
    }

    // Display all the details of this message
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

