import Oferta.exceptions.OfertaAlreadyExistsException;
import Oferta.model.Oferta;
import Oferta.services.OfertaService;
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

public class OfertaServiceTest {
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
        OfertaService.loadOferteFromFile();
        assertTrue(Files.exists(OfertaService.USERS_PATH));
    }

    @Test
    public void testLoadOfertasFromFile() throws Exception {
        OfertaService.loadOferteFromFile();
        assertNotNull(OfertaService.oferte);
        Assert.assertEquals(0, OfertaService.oferte.size());
    }
    @Test
    public void testAddOneOferta() throws Exception {
        OfertaService.loadOferteFromFile();
        OfertaService.addOferte("test", "testPass", "test1user","test","Test");
        assertNotNull(OfertaService.oferte);
        assertEquals(1, OfertaService.oferte.size());
    }
    @Test
    public void testAddTwoOfertas() throws Exception {
        OfertaService.loadOferteFromFile();
        OfertaService.addOferte("test1", "testPass1", "test2user","test1","Test1");
        OfertaService.addOferte("test2", "testPass2", "test2user","test1","Test2");
        assertNotNull(OfertaService.oferte);
        assertEquals(2, OfertaService.oferte.size());
    }
    @Test(expected = OfertaAlreadyExistsException.class)
    public void testAddOfertaAlreadyExists() throws Exception {
        OfertaService.loadOferteFromFile();
        OfertaService.addOferte("test1", "testPass1", "test2user","test1","Test1");
        assertNotNull(OfertaService.oferte);
        OfertaService.checkOfertaDoesNotAlreadyExist("test1", "testPass1", "test2user","test1","Test1");
    }
    @Test
    public void testAddOneOfertaIsPersisted() throws Exception {
        OfertaService.loadOferteFromFile();
        OfertaService.addOferte("test1", "testPass1", "test2user","test1","Test1");
        List<Oferta> Ofertas = new ObjectMapper().readValue(OfertaService.USERS_PATH.toFile(), new TypeReference<List<Oferta>>() {
        });
        assertNotNull(Ofertas);
        assertEquals(1, Ofertas.size());
    }
    @Test
    public void testAddTwoOfertasArePersisted() throws Exception {
        OfertaService.loadOferteFromFile();
        OfertaService.addOferte("test1", "testPass1", "test2user","test1","Test1");
        OfertaService.addOferte("test2", "testPass2", "test2user","test1","Test2");
        List<Oferta> Ofertas = new ObjectMapper().readValue(OfertaService.USERS_PATH.toFile(), new TypeReference<List<Oferta>>() {
        });
        assertNotNull(Ofertas);
        assertEquals(2, Ofertas.size());
    }
}
