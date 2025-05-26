/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quickchat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */
public class QuickChat {

    /**
     * @param args the command line arguments
     */
    
     private static boolean isLoggedIn = false;
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Messages> sentMessages = new ArrayList<>(); 
    
    public static void main(String[] args) {
        //login
        String firstname =  JOptionPane.showInputDialog(null,"Enter First Name:" ,  "Registraion" , JOptionPane.QUESTION_MESSAGE);
       String lastname =  JOptionPane.showInputDialog(null,"Enter Last Name" ,  "Registraion" , JOptionPane.QUESTION_MESSAGE);
       String username =  JOptionPane.showInputDialog(null,  "Enter Username" ,  "Registraion" , JOptionPane.QUESTION_MESSAGE);
       String password =  JOptionPane.showInputDialog(null, "Enter Password" ,  "Registraion" , JOptionPane.QUESTION_MESSAGE);
       String phone =  JOptionPane.showInputDialog(null,"Enter Phone" ,  "Registraion" , JOptionPane.QUESTION_MESSAGE);
       
     
       /* JOptionPane.showInputDialog("Enter Last Name");
        lastname = input.nextLine();
        JOptionPane.showInputDialog("Enter Username");
        username = input.nextLine();
       JOptionPane.showInputDialog("Enter Password");
        password = input.nextLine();
        JOptionPane.showInputDialog("Enter Phone Number (starting with South African international code (+27) :");
        phone = input.nextLine();
        */
       
        Login login = new Login(); //create and instantiate Login object
       
        boolean validatePhone = login.checksCellPhoneNumber(phone);
        boolean validateUsername =login.checkUserName(username);
        boolean validatePassword = login.checkPasswordComplexity(password);
       
        //checks and validates username
        if (validateUsername ==true) {
            JOptionPane.showMessageDialog(null ,"username succefully captured");
        }else{
            JOptionPane.showMessageDialog(null ,"Username is not correctly formatted, please ensure that your username contains an undersore and is no more than five characters in length", "Error", JOptionPane.ERROR_MESSAGE );
        }
       
        //checks and validates password
        if  (validatePassword ==true) {
            JOptionPane.showMessageDialog(null,"Password successfully captured.");
        }else{
            JOptionPane.showMessageDialog(null,"Password is not correctly formatted, please ensure contains at least eight characters, a capital and small letter, a number and a specialcharater", "Error", JOptionPane.ERROR_MESSAGE);
        }
       
        //checks and validates phone number
        if (validatePhone ==true) {
            JOptionPane.showMessageDialog(null,"Cellphone number successfully added");
        }else{
            JOptionPane.showMessageDialog(null,"Cellphone number incorrectly formatted or does not contain international code");
        }
         
        if (validateUsername ==true && validatePassword ==true && validatePhone ==true) {
            JOptionPane.showMessageDialog(null,"You have successfully registered");
           
           
            String loginUsername = JOptionPane.showInputDialog(null, "Enter Username" ,  "Login" , JOptionPane.QUESTION_MESSAGE);
            String loginPassword = JOptionPane.showInputDialog(null, "Enter Password" ,  "Login" , JOptionPane.QUESTION_MESSAGE);
           
         
             
           
             
              if (loginUsername.equals(username) && loginPassword.equals(password)) {
                  JOptionPane.showMessageDialog(null,"Welcome " +firstname +  "," + lastname + "it is great to see you again.");
              }else{
                 JOptionPane.showMessageDialog(null ,"Login failed!, wrong username or password","Error", JOptionPane.ERROR_MESSAGE);
               }
              }else{
            JOptionPane.showMessageDialog( null, "Failed to register");
            main(null);
        }
        
        //message
          JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
        login();

        if (isLoggedIn) {
            boolean running = true;
            while (running) {
                String[] options = {"Send Messages", "Show Recently Sent Messages", "Quit"};
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Choose an option:",
                        "QuickChat Menu",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                switch (choice) {
                    case 0:
                        sendMessages();
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Coming Soon.");
                        break;
                    case 2:
                    case JOptionPane.CLOSED_OPTION:
                        running = false;
                        JOptionPane.showMessageDialog(null, "Goodbye!");
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static void login() {
        String username = JOptionPane.showInputDialog(null, "Enter username:");
        String password = JOptionPane.showInputDialog(null, "Enter password:");

        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            isLoggedIn = true;
            JOptionPane.showMessageDialog(null, "Login successful!");
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Username or password cannot be empty.");
        }
    }

    private static void sendMessages() {
        String input = JOptionPane.showInputDialog(null, "How many messages would you like to send?");
        if (input == null) return;

        int messageCount;
        try {
            messageCount = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number.");
            return;
        }

        for (int i = 0; i < messageCount; i++) {
            String recipient = JOptionPane.showInputDialog(null, "Enter recipient phone number (+countrycodexxx...):");
            if (recipient == null) return;

            String text = JOptionPane.showInputDialog(null, "Enter your message:");
            if (text == null) return;

            Messages msg = new Messages(recipient, text);

            if (!msg.checkRecipientCell()) {
                JOptionPane.showMessageDialog(null, "Invalid recipient format. Skipping.");
                continue;
            }

            sentMessages.add(msg);

            try {
                msg.saveToFile("messages.json");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving message: " + e.getMessage());
            }

            JOptionPane.showMessageDialog(null, "Message sent with ID: " + msg.toJSON().getString("messageId"));
        }
        
    }
    
}
