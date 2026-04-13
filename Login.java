package com.mycompany.login;
/**
 * @author User Nkahosheko Kopa
 */

import java.util.Scanner;

public class Login {
    
    //Making fields public, also declaring the variables and is part of encapsulation.
    
    public String name;
    public String surname;
    public String username;
    public String password;
    public String cellnumber;
   

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Login app = new Login();
        
        //The output the user will see
        System.out.println("Welcome to the Chat App!");
        
        //Prompting the user for their personal details such as name and surname in order to make use of at the welcome message.
        System.out.println("\nEnter your name:");
        app.name = scanner.next();
        
        System.out.println("\nEnter your surname:");
        app.surname = scanner.next();
        
        /** User registration will display 
         * Giving users a set of instructions to follow in order to have a successful registration.
         * Prompting the user for username, password and cell number input.
        */
        
        System.out.println("\nUSER REGISTRATION");
        System.out.println("You have to create an account and by that you have to adhere to the instructions/conditions precisely");
        
        System.out.println("\nConsider the following conditions to form a username:\n - Username must contain five (5) characters only  \n - Username must contain an underscore(_) ");
        
        //This part is where the user inputs their username.
        System.out.println("Enter prefered username:");
        app.username = scanner.next();
        
        System.out.println("\nConsider the following conditions to form a password:\n - Password must contain eight (8) characters \n - Password must contain one (1) capital letter \n - Password must contain one (1) number \n - Password must contain one (1) special character [*,&,^,%,$,#,@,!] etc.");
        
        //This part is where the user inputs their password.
        System.out.println("Enter prefered password:");  
        app.password = scanner.next();
        
        System.out.println("\nConsider the following conditions when entering cellphone number:\n - Cellphone number must start with +27 \n - Cellphone number must be a valid length digit");
        
        ////This part is where the user inputs their cell number.
        System.out.println("Enter cellphone number:");   
        app.cellnumber = scanner.next();
        
        // I then call the registration method to display result.
        String result = app.registerUser();
        System.out.println("\n" + result);

        
        // The program should stop if registration fails.
        if (!result.contains("Registration successful.")) 
        {
            return;
        }

        /** Chatapp login will display once the registration still runs.
         * The username and password are saved or stored, I think so. to verify I will have to request the user to enter their username and password again. 
         * Then display the login results for the user to either receive their welcome message or otherwise. 
         */
         
        System.out.println("\nCHATAPP LOGIN ");
        
        // User enters login username.
        System.out.print("\nUsername: ");
        String inputUsername = scanner.next();

        // User enters login pasword.
        System.out.print("\nPassword: ");
        String inputPassword = scanner.next();

        // Display the login outcome.
        System.out.println(app.returnLoginStatus( inputUsername, inputPassword));

        // Always close the scanner once done.
        scanner.close();
        
    }
    
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
    
    /**
     * Overall my program validates all inputs before allowing the user to login.
     * I separated stored data from the user's input for clarity and to under my code a lot more better.
     * I used methods to enforce modular design   
    */
}
