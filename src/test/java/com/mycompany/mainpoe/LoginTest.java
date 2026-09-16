/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mainpoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    public LoginTest() {
    }


    Login obj = new Login();

    // Test valid username
    @Test
    public void TestValidUsername() {

        String username = "kyl_1";

        boolean result = obj.checkUserName(username);

        assertTrue(result);
    }

    // Test invalid username
    @Test
    public void TestInvalidUsername() {

        String username = "kyle!!!!!!!";

        boolean result = obj.checkUserName(username);

        assertFalse(result);
    }

    // Test valid password
    @Test
    public void TestValidPassword() {

        String password = "Ch&&sec@ke99!";

        boolean result = obj.checkPasswordComplexity(password);

        assertTrue(result);
    }

    // Test invalid password
    @Test
    public void TestInvalidPassword() {

        String password = "password";

        boolean result = obj.checkPasswordComplexity(password);

        assertFalse(result);
    }

    // Test valid cellphone number
    @Test
    public void TestValidCellPhoneNumber() {

        String cellPhoneNumber = "+27838968976";

        boolean result = obj.checkCellPhoneNumber(cellPhoneNumber);

        assertTrue(result);
    }

    // Test invalid cellphone number
    @Test
    public void TestInvalidCellPhoneNumber() {

        String cellPhoneNumber = "08966553";

        boolean result = obj.checkCellPhoneNumber(cellPhoneNumber);

        assertFalse(result);
    }

    // Test successful login
    @Test
    public void TestSuccessfulLogin() {

        Login user = new Login(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        boolean result = user.loginUser(
                "kyl_1",
                "Ch&&sec@ke99!"
        );

        assertTrue(result);
    }

    // Test unsuccessful login
    @Test
    public void TestUnsuccessfulLogin() {

        Login user = new Login(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        boolean result = user.loginUser(
                "wrong_user",
                "wrong_password"
        );

        assertFalse(result);
    }
}