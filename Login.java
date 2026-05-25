/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.message;

/**
 *
 * @author User Nkahosheko Kopa
 */

public class Login {
    
    //Making fields public, also declaring the variables and is part of encapsulation.
    
    public String name;
    public String surname;
    public String username;
    public String password;
    public String cellnumber;
   
    
    public boolean checkUserName()
    {  
        /**
         * This method basically validates the username 
         * It is restricted to certain conditions such as:
         * ~ Should be 5 characters max
         * ~ Should contain an underscore
         * ~ Should not be empty
         */
        return username != null && username.length()<= 5 && username.contains("_"); 
    }
    
    public boolean checkPasswordComplexity()
    {
        /**
         * This method basically validates the password complexity.
         * It is restricted to certain conditions such as:
         * ~ Should be at least 8 characters
         * ~ Should contain an uppercase letter
         * ~ Should contain a special character
         * ~ Should contain a number
         * ~ Should not be empty
         */
        
        boolean caps = false;
        boolean num = false;
        boolean special= false;
        
        // The password should fail immediately should the password be too short or empty or rather null.
         if (password == null || password.length() < 8) 
        {
            return false;
        }

        // The program should loop through each character in password.
        for (char character : password.toCharArray()) 
        {

            if (Character.isUpperCase(character)) 
            {
                caps = true;
            } 
            else if (Character.isDigit(character)) 
            {
                num = true;
            } 
            else if (!Character.isLetterOrDigit(character)) 
            {
                special = true;
            }
        }
        
        // The program should return true only if all conditions and requirements are met.
        return caps && num && special ;
    }    
         
    public boolean checkCellPhoneNumber()
    {
        /**
         * This method validates the cellphone number.
         * It is restricted to certain conditions which are:
         * ~ Should start with +27 and must contain 10-11 digits
         * ~ Should not be empty (null)
         */
        return cellnumber != null && cellnumber.matches("\\+[0-9]{10,11}");
    }
    
    public String registerUser()
    {
        /**
        * This method handles user registration
        * It also validates all the user's inputs and returns appropriate messages
        */
        String message = "";
        
        // The username validation
        if (!checkUserName())
       {            
          return "Username is not correctly formatted; please ensure your username contains an underscore and is no more than five characters in length.";
       }
        else 
        {
            message += "Username successfully captured.\n";
        }
        
        // The password complexity validation
        if (!checkPasswordComplexity())
        {
          return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character."; 
        }
        else 
        {
           message += "Password successfully captured.\n";
        }
    
        // The cellphone number validation
        if (!checkCellPhoneNumber())
        {
            return "Cell number is incorrectly formatted or does not contain internatonal code;  please correct the number and try again.";
        }
        else
        {
            message += "Cell phone number successfully added.\n";
        }

        // Then the final registration success message as a return.
       message += "Registration successful.";
       
       return message;
    }   
    
    public boolean loginUser( String inputUsername, String inputPassword) 
    {
        // This method basically checks if the login credentials match the stored values.
        return username != null && username.equals(inputUsername) && password.equals(inputPassword);
    }
    
    public String returnLoginStatus( String inputUsername, String inputPassword)          
    {
        // Lastly this methods returns the result message.
        if (loginUser(inputUsername, inputPassword))
        { 
            return "Welcome " + name + ", " + surname + " it is great to see you again.";
        }
        return "Username or password is incorrect, please try again.";
    }
}
