package controllers.Oferta;

import Cerere.controllers.editare.TableCereriEditare;
import Cerere.model.Request;
import Cerere.services.CerereService;
import Oferta.controllers.afisare.TableOferte;
import Oferta.controllers.editare.TableOferteEditare;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Oferta_editTest extends ApplicationTest {
    private ObservableList<Request> elem;

    private TableOferteEditare controller;

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

        controller = new TableOferteEditare();
        controller.Echipament = new TableColumn<>();
        controller.Bucati = new TableColumn<>();
        controller.Data = new TableColumn<>();
        controller.Pret = new TableColumn<>();
        controller.Stare = new TableColumn<>();
        controller.TableView= new javafx.scene.control.TableView<>();

        elem= FXCollections.observableArrayList(CerereService.getCereri());

    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void testPeople() {
        controller.Salvare();
        assertNotNull(controller.getOferte());

    }


    @Test
    public void testPersist() {
        controller.Salvare();
        assertEquals(0, controller.getOferte().size());

    }
}
