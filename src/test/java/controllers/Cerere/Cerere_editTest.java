package controllers.Cerere;

import Cerere.controllers.afisare.TableCereri;
import Cerere.controllers.editare.TableCereriEditare;
import Cerere.model.Request;
import Cerere.services.CerereService;
import Register.services.FileSystemService;
import Register.services.UserService;
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

public class Cerere_editTest extends ApplicationTest {
    private ObservableList<Request> elem;

    private TableCereriEditare controller;

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

        controller = new TableCereriEditare();
        controller.Echipament = new TableColumn<>();
        controller.Bucati = new TableColumn<>();
        controller.Urgent = new TableColumn<>();
        controller.TableView= new javafx.scene.control.TableView<>();

        elem= FXCollections.observableArrayList(CerereService.getCereri());

    }



    @Test
    public void testPeople() {
        controller.Salvare();
        assertNotNull(controller.getCereri());

    }


    @Test
    public void testPersist() {
        controller.Salvare();
        assertEquals(0, controller.getCereri().size());

    }

}
