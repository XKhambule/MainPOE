/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mainpoe;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author Student
 */
public class MainPOE {
    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {

            System.out.println("CHAT APPLICATION");

            // Create the Login object
            Login login = new Login();

            // PART 1: USER REGISTRATION
            System.out.println(
                    "\n========== USER REGISTRATION =========="
            );

            System.out.print("Enter First Name: ");
            String firstName = input.nextLine();

            System.out.print("Enter Last Name: ");
            String lastName = input.nextLine();

            System.out.print("Enter Username: ");
            String username = input.nextLine();

            System.out.print("Enter Password: ");
            String password = input.nextLine();

            System.out.print(
                    "Enter South African Cell Number (+27...): "
            );
            String cellPhone = input.nextLine();

            // Register the user
            String registrationResult = login.registerUser(
                    firstName,
                    lastName,
                    username,
                    password,
                    cellPhone
            );

            System.out.println("\n" + registrationResult);

            // Only continue if registration succeeds
            if (registrationResult.equals(
                    "User has been registered successfully.")) {

                // PART 1: USER LOGIN
                System.out.println(
                        "\n========== LOGIN =========="
                );

                System.out.print("Enter Username: ");
                String loginUsername = input.nextLine();

                System.out.print("Enter Password: ");
                String loginPassword = input.nextLine();

                boolean loggedIn = login.loginUser(
                        loginUsername,
                        loginPassword
                );

                System.out.println(
                        login.returnLoginStatus(
                                loggedIn,
                                firstName,
                                lastName
                        )
                );

                // PART 2: QUICKCHAT
                // Only allow access after successful login
                if (loggedIn) {

                    System.out.println(
                            "\nWelcome to QuickChat."
                    );

                    // Ask how many messages the user wants
                    System.out.print(
                            "How many messages would you like to enter? "
                    );

                    int maximumMessages;

                    try {
                        maximumMessages = Integer.parseInt(
                                input.nextLine()
                        );

                    } catch (NumberFormatException e) {

                        System.out.println(
                                "Invalid number of messages."
                        );
                        return;
                    }

                    if (maximumMessages <= 0) {

                        System.out.println(
                                "Please enter a number greater than zero."
                        );
                        return;
                    }

                    // Count messages created in this session
                    int messageCount = 0;

                    // Count messages successfully sent
                    int totalSent = 0;

                    // Create Random object for message IDs
                    Random random = new Random();

                    boolean running = true;

                    // Keep showing the menu until user quits
                    while (running) {

                        System.out.println(
                                "\n========== QUICKCHAT MENU =========="
                        );

                        System.out.println("1. Send Messages");
                        System.out.println(
                                "2. Show recently sent messages"
                        );
                        System.out.println("3. Quit");

                        System.out.print("Enter your choice: ");
                        String choice = input.nextLine();

                        switch (choice) {

                            case "1":

                                if (messageCount >= maximumMessages) {

                                    System.out.println(
                                            "You have reached your message limit."
                                    );
                                    break;
                                }

                                // Generate a random 10-digit message ID
                                String messageID = "";

                                for (int i = 0; i < 10; i++) {

                                    messageID += random.nextInt(10);
                                }

                                // Get recipient
                                System.out.print(
                                        "Enter recipient cellphone number (+27...): "
                                );
                                String recipient = input.nextLine();

                                // Get message text
                                System.out.print(
                                        "Enter your message: "
                                );
                                String messageText = input.nextLine();

                                // Create Message object
                                Message message = new Message(
                                        messageID,
                                        messageCount,
                                        recipient,
                                        messageText
                                );

                                // Validate the message
                                if (!message.checkMessageID()) {

                                    System.out.println(
                                            "Invalid message ID."
                                    );
                                    break;
                                }

                                String recipientResult =
                                        message.checkRecipientCell();

                                System.out.println(recipientResult);

                                if (!recipientResult.equals(
                                        "Cell phone number successfully captured.")) {

                                    break;
                                }

                                String lengthResult =
                                        message.checkMessageLength();

                                System.out.println(lengthResult);

                                if (!lengthResult.equals(
                                        "Message ready to send.")) {

                                    break;
                                }

                                if (messageText.trim().isEmpty()) {

                                    System.out.println(
                                            "Message cannot be empty."
                                    );
                                    break;
                                }

                                // Create and display message hash
                                System.out.println(
                                        "Message Hash: "
                                        + message.createMessageHash()
                                );

                                // Display message details
                                System.out.println(
                                        message.printMessages()
                                );

                                // Send, disregard or store the message
                                String messageResult =
                                        message.SentMessage();

                                System.out.println(messageResult);

                                // Increase count only when sent
                                if (messageResult.equals(
                                        "Message successfully sent.")) {

                                    totalSent++;
                                }

                                // Count processed messages
                                if (messageResult.equals(
                                        "Message successfully sent.")
                                        || messageResult.equals(
                                                "Message successfully stored.")
                                        || messageResult.equals(
                                                "Message disregarded.")) {

                                    messageCount++;
                                }

                                break;

                            case "2":

                                System.out.println("Coming Soon.");
                                break;

                            case "3":

                                System.out.println(
                                        "\nTotal messages sent: "
                                        + totalSent
                                );

                                System.out.println(
                                        "Thank you for using QuickChat."
                                );

                                running = false;
                                break;

                            default:

                                System.out.println(
                                        "Invalid choice. Please try again."
                                );
                                break;
                        }
                    }

                } else {

                    System.out.println(
                            "Login failed. Access to QuickChat denied."
                    );
                }

            } else {

                System.out.println("\nRegistration failed.");
                System.out.println(
                        "Restart the application and try again."
                );
            }
        }
    }
}

    

    
