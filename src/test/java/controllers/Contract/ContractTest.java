package controllers.Contract;

import Cerere.controllers.Cerere;
import Cerere.services.CerereService;
import Contract.controllers.Contract;
import Contract.services.ContractsService;
import Register.services.FileSystemService;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ContractTest extends ApplicationTest {

    public static final String TEST_ECHIPAMENT = "testEchipament";
    public static final String TEST_BUCATI = "testBucati";
    public static final String TEST_TOTAL = "testTotal";
    public static final String TEST_NR_F = "testNRF";
    public static final String TEST_NR_B = "testNRB";


    private Contract controller;

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

        controller = new Contract();
        controller.ec = new TextField();
        controller.buc = new TextField();
        controller.total = new TextField();
        controller.nr_f = new TextField();
        controller.nr_mil = new TextField();
        controller.date = new DatePicker();
        controller.eroare = new Text();

        controller.ec.setText(TEST_ECHIPAMENT);
        controller.buc.setText(TEST_BUCATI);
        controller.total.setText(TEST_TOTAL);
        controller.nr_f.setText(TEST_NR_F);
        controller.nr_mil.setText(TEST_NR_B);

    }



    @Test
    public void testHandleAddContractActionCode() throws IOException {
        controller.date.setValue(LocalDate.of(2020,12,12));
        controller.creeare_contract();
        assertNotNull(ContractsService.getContracts());
        assertEquals(1, ContractsService.getContracts().size());
        assertEquals("Contractul a fost creat !", controller.eroare.getText());
    }

    @Test
    public void testCreare() throws IOException {
        controller.date.setValue(LocalDate.of(2020,12,12));
        controller.creeare_contract();
        controller.creeare_contract();
        assertEquals("Contractul exista deja!", controller.eroare.getText());
    }
/*
    @Test
    public void testIncomplet1() throws IOException {
        controller.ec.setText("");
        controller.buc.setText("");
        controller.total.setText("");
        controller.nr_f.setText("");
        controller.nr_mil.setText("");
        //controller.date.getEditor().clear();
       // datepicker.setValue(null);
        controller.creeare_contract();
        assertEquals("Completati toate campurile !", controller.eroare.getText());
    }
*/
    @Test
    public void testAddOneCerere() throws Exception {
        ContractsService.loadContractsFromFile();
        ContractsService.addContracts("test", "test2", "test3","test4","test5","test5");
        assertNotNull(ContractsService.contracts);
        assertEquals(1, ContractsService.contracts.size());
    }


}
