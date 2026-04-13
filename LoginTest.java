package com.mycompany.login ;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author User Nkahosheko Kopa
 */

public class LoginTest 
        
   /**
    * The units in this test will be from the login class.
    * Each of the test basically verifies a specific condition from the rubric.
    */     
{
    Login app = new Login();
    
    /**
     * For the 1st test which is about the username.
     * It is to check if the username method is correctly formatted.
     * To add on the welcome message is supposed to display.
     * Therefore data from the code is collected.
     */
    @Test
    public void testCorrectUsername() /// I need to make the test independent therfore i need to create a new instance.
    { 
        // I then set valid user data to make use of.
        app.name = "Lethabo";
        app.surname = "Kopa";
        app.username = "kyl_1";
        app.password = "Ch&&sec@ke99!";
        app.cellnumber = "+27838968976";
       
        // This is the system output I expect to receive. 
        String anticipated = "Welcome Lethabo, Kopa it is great to see you again.";
        
        // The assertEquals is used to verify from the code the login message.
        assertEquals(anticipated, app.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
    }
    
    /**
     * For the 2nd test which is about the username.
     * It is to check if the username method is correctly formatted should the username br incorrect.
     * To add on the error message is supposed to display.
     * Therefore data from the code is collected.
     */
    @Test
    public void testIncorrectUsername()
    {
        // I therefore have to set the invalid username
        app.username = "kyle!!!!!!!";
        app.password = "password";
        app.cellnumber = "08966553";
        
        // This is the error message that must display.
        String anticipated = "Username is not correctly formatted; please ensure your username contains an underscore and is no more than five characters in length.";

        // This part then validates the failure of the registration.
        assertEquals(anticipated, app.registerUser());
    }
    
    /**
     * For the 3rd test which is about the password.
     * It is to check if the password method is correctly formatted should the password be correct.
     * To add on the password has to meet the  complexity conditions.
     * Then the successful message has to be in the output.
     * Therefore data from the code is collected.
     */
    @Test
    public void testCorrectPassword()
    {
        app.username = "kyl_1";
        app.password = "Ch&&sec@ke99!";
        app.cellnumber = "+27838968976";
        String outcome = app.registerUser();

        // This part then checks that success message exists inside full output
        assertTrue(outcome.contains("Password successfully captured."));
    }
    
    /**
     * For the 4th test which is about the password.
     * It is to check if the password method is correctly formatted should the password be incorrect.
     * To add on the password has to meet the  complexity conditions.
     * Then the error message has to be in the output.
     */
    @Test
    public void testIncorrectPassword()
    {
        app.username = "kyl_1";
        app.password = "password";
        app.cellnumber = "08966553";
        
        // This is the expected output
        String anticipated = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";

        assertEquals(anticipated, app.registerUser());
    }
    
     /**
     * For the 5th test which is about the cellphone number.
     * Its is to check if the cell number method is correctly formatted should the cellphone number be correct.
     * To add on the cell number has to meet the conditions.
     * Then the successful message has to be in the output.
     */
    @Test
    public void testCorrectCellNo()
    {
        app.username = "kyl_1";
        app.password = "Ch&&sec@ke99!";
        app.cellnumber = "+27838968976";
        
        String outcome = app.registerUser();
        assertTrue(outcome.contains("Cell number successfully captured."));
    }
    
     /**
     * For the 6th test which is about the cellphone number.
     * Its is to check if the cell number method is correctly formatted should the cellphone number be incorrect.
     * To add on the cell number has to meet the conditions.
     * Then the error message has to be in the output.
     */
    @Test
    public void testIncorrectCellNo()
    {
        app.username = "kyl_1";
        app.password = "Ch&&sec@ke99!";
        app.cellnumber = "08966553"; // I have to make use of the invalid format
        String anticipated = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";

        assertEquals(anticipated, app.registerUser());
    }
    
     /**
     * For the 7th test which is about the successful login.
     * Its is to check if the login method is correctly formatted should the login be correct.
     * I expect the method to run as true
     */
    @Test
    public void testSuccesfulLogin() 
    { 
        app.username = "lee_1";
        app.password = "Kopa@123";

        assertTrue(app.loginUser("lee_1", "Kopa@123"));
    }
    
    /**
     * For the 8th test which is about the failed login.
     * Its is to check if the login method is correctly formatted should the login be incorrect.
     * I expect the method to run as false
     */
    @Test
    public void testFailedLogin() 
    { 
        app.username = "lee_1";
        app.password = "Kopa@123";

        assertFalse(app.loginUser("wrong", "wrong"));
    }
    
    /**
     * For the 9th test which is about the username.
     * I expect the username method validation to return as true
     */
    @Test
    public void testCorrectBooleanUsername() 
    {
        app.username = "kyl_1";

        assertTrue(app.checkUserName());
    }
    
    /**
    * For the 10th test which is about the username.
    * I expect the username method validation to return as false
    */
    @Test
    public void testIncorrectBooleanUsername() 
    {
        app.username = "kyle!!!!!!!";

        assertFalse(app.checkUserName());
    }

    /**
    * For the 11th test which is about the password.
    * I expect the password method validation to return as true
    */
    @Test
    public void testCorrectBooleanPassword() 
    {
        app.password = "Ch&&sec@ke99!";

        assertTrue(app.checkPasswordComplexity());
    }

    /**
    * For the 12th test which is about the password.
    * I expect the password method validation to return as false
    */
    @Test
    public void testIncorrectBooleanPassword() 
    {
        app.password = "password";

        assertFalse(app.checkPasswordComplexity());
    }

    /**
    * For the 13th test which is about the cell number.
    * I expect the cell number method validation to return as true
    */
    @Test
    public void testCorrectBooleanCellNo() 
    {
        app.cellnumber = "+27838968976";

        assertTrue(app.checkCellPhoneNumber());
    }
    
    /**
    * For the 14th test which is about the cell number.
    * I expect the cell number method validation to return as false.
    */
    @Test
    public void testIncorrectBooleanCellNo()
    {
        app.cellnumber = "08966553";

        assertFalse(app.checkCellPhoneNumber());
    }  
}
