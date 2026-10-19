/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mainpoe;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class MainPOE {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("CHAT APPLICATION");
            
            // Create Login object
            Login login = new Login();
            Message message= new message();
            
            // Registration
            System.out.println("\n========== USER REGISTRATION ==========");
            
            System.out.print("Enter First Name: ");
            String firstName = input.nextLine();
            
            System.out.print("Enter Last Name: ");
            String lastName = input.nextLine();
            
            System.out.print("Enter Username: ");
            String username = input.nextLine();
            
            System.out.print("Enter Password: ");
            String password = input.nextLine();
            
            System.out.print("Enter South African Cell Number (+27...): ");
            String cellPhone = input.nextLine();
            
            // Register user
            String registrationResult = login.registerUser(
                    firstName,
                    lastName,
                    username,
                    password,
                    cellPhone
            );
            
            System.out.println("\n" + registrationResult);
            
            // Only continue if registration was successful
            if (registrationResult.equals("User has been registered successfully.")) {
                
                System.out.println("\n========== LOGIN ==========");
                
                System.out.print("Enter Username: ");
                String loginUsername = input.nextLine();
                
                System.out.print("Enter Password: ");
                String loginPassword = input.nextLine();
                
                boolean loggedIn = login.loginUser(loginUsername, loginPassword);
                
                System.out.println(
                        login.returnLoginStatus(
                                loggedIn,
                                firstName,
                                lastName
                        )
                );
                
            } else {
                
                System.out.println("\nRegistration failed.");
                System.out.println("Restart the application and try again.");
                
            }
        }
    }

    private static class message extends Message {

        public message() {
        }
    }
}
