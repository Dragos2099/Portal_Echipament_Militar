package controllers.Register;

import Register.controllers.Register;
import Register.services.FileSystemService;
import Register.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;



import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RegisterTest extends  ApplicationTest{
    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    private Register controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = "PEM";
        FileSystemService.initApplicationHomeDirIfNeeded();
        UserService.loadUsersFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile();

        controller = new Register();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.role = new ChoiceBox();
        controller.registrationMessage = new Text();

        controller.passwordField.setText(TEST_PASSWORD);
        controller.usernameField.setText(TEST_USER);
    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void testHandleAddUserActionCode() {
        controller.handleRegisterAction();
        assertEquals(1, UserService.getUsers().size());
        assertEquals("Account created successfully!", controller.registrationMessage.getText());
    }

    @Test
    public void testAddSameUserTwice() {
        controller.handleRegisterAction();
        controller.handleRegisterAction();
        assertEquals("Numele " + TEST_USER + " este deja folosit", controller.registrationMessage.getText());
    }


}
