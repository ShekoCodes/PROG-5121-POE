/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.message;

import java.util.Scanner;

/**
 *
 * @author User Nkahosheko Kopa
 */


public class Main 
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Login app = new Login();
        
        //The output the user will see
        System.out.println("Welcome to the Quick Chat App!");
        
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
            return ;
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
     
        
        // Only continues if login is successful.
        
        if (app.loginUser( inputUsername, inputPassword))
        {

            System.out.println("\nWelcome to QuickChat.");

            // Asks how many messages are to be sent
            System.out.print("\nHow many messages would you like to send?");

            int totalMessages = scanner.nextInt();
            scanner.nextLine();

            int option = 0;

            // Main manue

            while (option != 3)
            { 
                System.out.println("\nQUICK CHAT MENU \n1. Send Messages \n2. Show Sent Messages \n3. Quit \n4. Show Total Messages");
               
                // Prompting the user for input
                System.out.print("Choose an option:");
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option)
                {
                    // Send messages
                    case 1:

                        for (int i = 1; i <= totalMessages; i++)
                        {
                           System.out.println("\nMESSAGE " + i );

                           System.out.print("\nEnter recipient number:");
                           String recipient = scanner.nextLine();

                           System.out.print("Enter message:");

                           String text = scanner.nextLine();
                           
                            if (text.length() > 250)
                            {
                                int exceeded = text.length() - 250;

                                System.out.println("Message exceeds 250 characters by "+ exceeded + "; please reduce the size.");
                                continue;
                            }
                             else
                            {
                                System.out.println("Message ready to send.");
                            }

                                    
                           // Create the Message object
                           Message message = new Message(recipient, i, text);

                           // Validate th,e recipient
                           if (!message.checkRecipientCell(recipient))
                                 
                            {
                                System.out.println( "\nCell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                                continue;
                            }

                            if (!message.checkMessageID( message.getMessageID()))
                                    
                            {
                                System.out.println( "\nMessage ID invalid.");
                                continue;
                            }

                            // Displaying the ID to the user
                            System.out.println("\nMessage ID:" + message.getMessageID());

                            // Display the hash to the user
                            System.out.println("Message Hash: " + message.getMessageHash());

                            System.out.println( "\nChoose an option: \n1. Send Message \n2. Disregard Message \n3. Store Message");
                            int choice = scanner.nextInt();
                            scanner.nextLine();

                            // Display results to the user
                            System.out.println("\n"+ message.sentMessage(choice));
                        }

                        break;

                    // Display send messages

                    case 2:

                        System.out.println( Message.printMessage());

                        break;

                    case 3:

                        System.out.println("\nThank you for using QuickChat.");

                        break;
                        
                    case 4:

                        System.out.println( "\nTotal messages sent: " + Message.returnTotalMessages() );

                        break;
             
                    // Invalid option

                    default:

                        System.out.println("\nInvalid option selected.");
                }
            }
        }

        scanner.close();       
    }    
}
