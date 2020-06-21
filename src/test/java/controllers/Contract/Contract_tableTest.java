package controllers.Contract;

import Contract.controllers.Contract;
import Contract.controllers.afisare.TableContracte;
import Contract.model.Contracts;
import Contract.services.ContractsService;
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

public class Contract_tableTest extends ApplicationTest {
    private ObservableList<Contracts> elem;


    private TableContracte controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = "PEM";
        FileSystemService.initApplicationHomeDirIfNeeded();
        ContractsService.loadContractsFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        ContractsService.loadContractsFromFile();

        controller = new TableContracte();
        controller.Echipamente= new TableColumn<>();
        controller.Buc = new TableColumn<>();
        controller.Total = new TableColumn<>();
        controller.Nr_bm = new TableColumn<>();
        controller.Nr_f= new TableColumn<>();
        controller.Data= new TableColumn<>();
        controller.TableView= new javafx.scene.control.TableView<>();

        elem= FXCollections.observableArrayList(ContractsService.getContracts());

    }



    @Test
    public void testPeople() throws IOException {
        controller.getPeople();
        assertNotNull(controller.getContracts());

        assertEquals(elem, controller.getContracts());

    }


}
