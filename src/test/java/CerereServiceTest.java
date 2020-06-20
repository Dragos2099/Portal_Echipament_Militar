import Cerere.controllers.Cerere;
import Cerere.exceptions.CerereAlreadyExistsException;
import Cerere.model.Request;
import Cerere.services.CerereService;
import Register.services.FileSystemService;
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

public class CerereServiceTest {
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
        CerereService.loadCereriFromFile();
        assertTrue(Files.exists(CerereService.USERS_PATH));
    }

    @Test
    public void testLoadCereresFromFile() throws Exception {
        CerereService.loadCereriFromFile();
        assertNotNull(CerereService.cereri);
        Assert.assertEquals(0, CerereService.cereri.size());
    }
    @Test
    public void testAddOneCerere() throws Exception {
        CerereService.loadCereriFromFile();
        CerereService.addCereri("test", "testPass", "test1user");
        assertNotNull(CerereService.cereri);
        assertEquals(1, CerereService.cereri.size());
    }
    @Test
    public void testAddTwoCereres() throws Exception {
        CerereService.loadCereriFromFile();
        CerereService.addCereri("test1", "testPass1", "test2user");
        CerereService.addCereri("test2", "testPass2", "test2user");
        assertNotNull(CerereService.cereri);
        assertEquals(2, CerereService.cereri.size());
    }
    @Test(expected = CerereAlreadyExistsException.class)
    public void testAddCerereAlreadyExists() throws Exception {
        CerereService.loadCereriFromFile();
        CerereService.addCereri("test1", "testPass1", "test2user");
        assertNotNull(CerereService.cereri);
        CerereService.checkCerereDoesNotAlreadyExist("test1","testPass1","test2user");
    }
    @Test
    public void testAddOneCerereIsPersisted() throws Exception {
        CerereService.loadCereriFromFile();
        CerereService.addCereri("test1", "testPass1", "test2user");
        List<Request> Cereres = new ObjectMapper().readValue(CerereService.USERS_PATH.toFile(), new TypeReference<List<Request>>() {
        });
        assertNotNull(Cereres);
        assertEquals(1, Cereres.size());
    }
    @Test
    public void testAddTwoCereresArePersisted() throws Exception {
        CerereService.loadCereriFromFile();
        CerereService.addCereri("test1", "testPass1", "test2user");
        CerereService.addCereri("test2", "testPass2", "test2user");
        List<Request> Cereres = new ObjectMapper().readValue(CerereService.USERS_PATH.toFile(), new TypeReference<List<Request>>() {
        });
        assertNotNull(Cereres);
        assertEquals(2,Cereres.size());
    }
}
