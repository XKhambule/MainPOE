/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainpoe;

/**
 *
 * @author Student
 */
class Login {

    // Variables
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhoneNumber;

    // Constructor
    public Login() {
    }

    // Constructor with registration details
    public Login(String firstName, String lastName, String username,
                 String password, String cellPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    // Check if the username is valid
    public boolean checkUserName(String username) {

        return username.contains("_") && username.length() <= 5;
    }

    // Check if the password meets the complexity requirements
    public boolean checkPasswordComplexity(String password) {

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        if (password.length() >= 8) {

            for (int i = 0; i < password.length(); i++) {

                char character = password.charAt(i);

                if (Character.isUpperCase(character)) {
                    hasCapital = true;
                }

                if (Character.isDigit(character)) {
                    hasNumber = true;
                }

                if (!Character.isLetterOrDigit(character)) {
                    hasSpecialCharacter = true;
                }
            }
        }

        return password.length() >= 8
                && hasCapital
                && hasNumber
                && hasSpecialCharacter;
    }

    // Check if the cellphone number is valid
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {

        String regex = "^\\+27[0-9]{9}$";

        return cellPhoneNumber.matches(regex);
    }

    // Register the user
    public String registerUser(String firstName, String lastName,
                                String username, String password,
                                String cellPhoneNumber) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number and a special character.";
        }

        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;

        return "User has been registered successfully.";
    }

    // Check login details
    public boolean loginUser(String enteredUsername, String enteredPassword) {

        return enteredUsername.equals(this.username)
                && enteredPassword.equals(this.password);
    }

    // Return login status message
    public String returnLoginStatus(boolean loginSuccessful) {

        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    boolean returnLoginStatus(boolean loggedIn, String firstName, String lastName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}