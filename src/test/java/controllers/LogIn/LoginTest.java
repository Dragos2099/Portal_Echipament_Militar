package controllers.LogIn;

import Log_in.controllers.Login;
import Register.controllers.Register;
import Register.controllers.RegisterAdm;
import Register.exceptions.UsernameAlreadyExistsException;
import Register.services.FileSystemService;
import Register.services.UserService;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class LoginTest extends ApplicationTest {
    public static final String TEST_USER = "dragos";
    public static final String TEST_PASSWORD = "dragos";
    private Login controller;
    private Register controller_reg;

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

        controller_reg = new Register();
        controller_reg.usernameField = new TextField();
        controller_reg.passwordField = new PasswordField();
        controller_reg.role = new ChoiceBox();
        controller_reg.registrationMessage = new Text();

        controller_reg.passwordField.setText(TEST_PASSWORD);
        controller_reg.usernameField.setText(TEST_USER);


        controller = new Login();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.loginMessage = new Text();

        controller.passwordField.setText(TEST_PASSWORD);
        controller.usernameField.setText(TEST_USER);
    }


    @Test
    public void testPressButton() throws IOException {
        controller.passwordField.setText("a");
        controller.usernameField.setText("a");
        controller.pressButton();
        assertEquals("Contul nu exista !", controller.loginMessage.getText());
    }

    @Test
    public void testPressButton2() throws IOException {
        controller.passwordField.setText("");
        controller.usernameField.setText(TEST_USER);
        controller.pressButton();
        assertEquals("Introduceti parola !", controller.loginMessage.getText());
    }

    @Test
    public void testPressButton3() throws IOException {
        controller.passwordField.setText(TEST_PASSWORD);
        controller.usernameField.setText("");
        controller.pressButton();
        assertEquals("Introduceti un e-mail valid ! ", controller.loginMessage.getText());
    }

    @Test(expected = NullPointerException.class)
    public void testPressButton4() throws IOException, UsernameAlreadyExistsException {
        // final Path USERS_PATH = FileSystemService.getPathToFile("utilizatori", "users.json");
        // Files.delete(USERS_PATH);

        UserService.addUser(TEST_USER,TEST_PASSWORD,"Administrator");


        controller.pressButton();
        assertEquals("", controller.loginMessage.getText());
        // Files.delete(USERS_PATH);
    }



}
