import Register.exceptions.UsernameAlreadyExistsException;
import Register.model.User;
import Register.services.Criptare;
import Register.services.FileSystemService;
import Register.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class UserServiceTest {
    @BeforeClass
    public static void setupClass() {
        FileSystemService.APPLICATION_FOLDER = "PEM";
        FileSystemService.initApplicationHomeDirIfNeeded();
    }

    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
    }

    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception {
        UserService.loadUsersFromFile();
        assertTrue(Files.exists(UserService.USERS_PATH));
    }

    @Test
    public void testLoadUsersFromFile() throws Exception {
        UserService.loadUsersFromFile();
        assertNotNull(UserService.users);
        Assert.assertEquals(0, UserService.users.size());
    }
    @Test
    public void testAddOneUser() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test", "testPass", "test1user");
        assertNotNull(UserService.users);
        assertEquals(1, UserService.users.size());
    }
    @Test
    public void testAddTwoUsers() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "test2users");
        UserService.addUser("test2", "testPass2", "test2users");
        assertNotNull(UserService.users);
        assertEquals(2, UserService.users.size());
    }
    @Test
    public void testAddTenUsers() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "test2users");
        UserService.addUser("test2", "testPass2", "test2users");
        UserService.addUser("test3", "testPass3", "test2users");
        UserService.addUser("test4", "testPass4", "test2users");
        UserService.addUser("test5", "testPass5", "test2users");
        UserService.addUser("test6", "testPass6", "test2users");
        UserService.addUser("test7", "testPass7", "test2users");
        UserService.addUser("test8", "testPass8", "test2users");
        UserService.addUser("test9", "testPass9", "test2users");
        UserService.addUser("test10", "testPass10", "test2users");
        assertNotNull(UserService.users);
        assertEquals(10, UserService.users.size());
    }
    @Test(expected = UsernameAlreadyExistsException.class)
    public void testAddUserAlreadyExists() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "test1");
        assertNotNull(UserService.users);
        UserService.checkUserDoesNotAlreadyExist("test1");
    }

    @Test
    public void testAddOneUserIsPersisted() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test", "testPass", "test1user");
        List<User> users = new ObjectMapper().readValue(UserService.USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
        assertNotNull(users);
        assertEquals(1, users.size());
    }
    @Test
    public void testAddTwoUsersArePersisted() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "test2users");
        UserService.addUser("test2", "testPass2", "test2users");
        List<User> users = new ObjectMapper().readValue(UserService.USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
        assertNotNull(users);
        assertEquals(2, users.size());
    }
    @Test
    public void testAddTenUsersArePersisted() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "test2users");
        UserService.addUser("test2", "testPass2", "test2users");
        UserService.addUser("test3", "testPass3", "test2users");
        UserService.addUser("test4", "testPass4", "test2users");
        UserService.addUser("test5", "testPass5", "test2users");
        UserService.addUser("test6", "testPass6", "test2users");
        UserService.addUser("test7", "testPass7", "test2users");
        UserService.addUser("test8", "testPass8", "test2users");
        UserService.addUser("test9", "testPass9", "test2users");
        UserService.addUser("test10", "testPass10", "test2users");
        List<User> users = new ObjectMapper().readValue(UserService.USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
        assertNotNull(users);
        assertEquals(10, users.size());
    }
    @Test
    public void testPasswordEncrypt() {
        assertNotEquals("testPass1", Criptare.encrypt("username1", "testPass1"));
    }

}
