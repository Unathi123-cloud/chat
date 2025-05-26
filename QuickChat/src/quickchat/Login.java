/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quickchat;

/**
 *
 * @author RC_Student_lab
 */
public class Login {
    
     public boolean checkUserName (String username) {

        if (username.contains ("_") && username.length() <=5) {
            return true;
        }else{
           return false;
        }
    }

    public boolean checkPasswordComplexity(String password) {
   
        String capital = ".*[A-Z].*"; //Checks capital letters
        String small = ".*[a-z].*"; //Checks small letters
        String special = ".*[!@#$%^&*(),.?\":{}|<>].*"; //Checks special characters
        String digits = ".*\\d.*"; //Checks digit characters
   
        if (password.length() >=8 && password.matches(capital) && password.matches(small) && password.matches(digits) && password.matches(special)) {
             return true;
        }
         else {
            return false;
        }
    }
        public boolean checksCellPhoneNumber (String phone) {
   
            String saCode = "+27";
            String firstThreeChart = phone.substring(0, 3); //Gets characters from index 0 to 2 (inclusive)
   
            int fourthDigit =Character.getNumericValue(phone.charAt(3)); //Gets and converts the fourth digit
            if  (phone.length() <= 12 &&  firstThreeChart.equals(saCode) && fourthDigit >= 6 && fourthDigit <=8) {
             return true;
       
            }else{
                return false;
            }
       
    }

    public String registerUser (String username, String password, String phone) {
        boolean validatePhone = checksCellPhoneNumber (phone);
        boolean validateUsername = checkUserName (username);
        boolean validatePassword = checkPasswordComplexity (password);
   
        if (validatePhone ==true && validateUsername ==true && validatePassword ==true) {
           return "User is successfully registered";
        }else {
          return "user registration failed!!!!!";
       }
    }
   
   
   
    public boolean loginUsername(String username, String password) {
        boolean validateUserName = checkUserName (username);
        boolean  validatePassword = checkPasswordComplexity (password);
       
        if (validateUserName ==true && validatePassword ==true) {
            return true;
            }else{
                 return false;
        }

    }
   
    public String returnLoginStatus(String username, String password) {
        boolean validUsername = checkUserName (username);
        boolean validPassword = checkPasswordComplexity (password);
       
        if (validUsername ==true && validPassword ==true) {
            return "A successful login";
        }else{
            return "A failed login";
        }
    }
    
}
