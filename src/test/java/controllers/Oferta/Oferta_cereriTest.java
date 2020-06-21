package controllers.Oferta;

import Cerere.controllers.afisare.TableCereri;
import Cerere.model.Request;
import Cerere.services.CerereService;
import Oferta.controllers.TableCereriOferta;
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

public class Oferta_cereriTest extends ApplicationTest {
    private ObservableList<Request> elem;

    private TableCereri controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = "PEM";
        FileSystemService.initApplicationHomeDirIfNeeded();
        CerereService.loadCereriFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        CerereService.loadCereriFromFile();

        controller = new TableCereri();
        controller.Echipament = new TableColumn<>();
        controller.Bucati = new TableColumn<>();
        controller.Urgent = new TableColumn<>();
        controller.TableView= new javafx.scene.control.TableView<>();

        elem= FXCollections.observableArrayList(CerereService.getCereri());

    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void testPeople() throws IOException {
        controller.getPeople();
        assertNotNull(controller.getCereri());

        assertEquals(elem, controller.getCereri());

    }


}
