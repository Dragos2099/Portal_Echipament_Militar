import Cerere.controllers.afisare.TableCereri;
import Cerere.model.Request;
import Cerere.services.CerereService;
import Oferta.controllers.afisare.TableOferte;
import Oferta.model.Oferta;
import Oferta.services.OfertaService;
import Register.services.FileSystemService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Oferta_tableTest extends ApplicationTest {

    private ObservableList<Oferta> elem;
    private TableOferte controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = "PEM";
        FileSystemService.initApplicationHomeDirIfNeeded();
        OfertaService.loadOferteFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        OfertaService.loadOferteFromFile();

        controller = new TableOferte();
        controller.Echipament = new TableColumn<>();
        controller.Bucati = new TableColumn<>();
        controller.Data = new TableColumn<>();
        controller.Pret = new TableColumn<>();
        controller.Stare = new TableColumn<>();
        controller.TableView= new javafx.scene.control.TableView<>();

        elem=FXCollections.observableArrayList(OfertaService.getOferte());

    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void testPeople() throws IOException {
        controller.getPeople();
        assertNotNull(controller.getOferte());

        assertEquals(elem, controller.getOferte());

    }


}
