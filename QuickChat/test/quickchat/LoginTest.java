/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package quickchat;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RC_Student_lab
 */
public class LoginTest {
    
    public LoginTest() {
    }
    Login login = new Login();
    
    @Test
    public void testCheckUserNameTrue() {
        assertTrue(login.checkUserName("mash_"));
    }

      @Test
    public void testCheckUserNameFalse() {
        assertFalse(login.checkUserName("lebogang"));
    }

    
    @Test
    public void testCheckPasswordComplexityTrue () {
        assertTrue(login.checkPasswordComplexity("@Lebogang_12"));
    }
    
     @Test
    public void testCheckPasswordComplexityFalse () {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testChecksCellPhoneNumberTrue() {
        assertTrue(login.checksCellPhoneNumber("+27674393492"));
    }
    
    @Test
    public void testChecksCellPhoneNumberFalse() {
        assertFalse(login.checkPasswordComplexity("0735494632"));
    }


    @Test
    public void testRegisterUser() {
        String result = login.registerUser("mash_", "@Lebogang_12", "+27674393492");
        assertEquals("User is successfully registered",result);
    }

    @Test
    public void testLoginUsername() {
    }

    @Test
    public void testReturnLoginStatus() {
    }
}
