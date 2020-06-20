import Cerere.controllers.Cerere;
import Cerere.services.CerereService;
import Oferta.controllers.Offer;
import Oferta.model.Oferta;
import Oferta.services.OfertaService;
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

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OfertaTest extends ApplicationTest {

    public static final String TEST_ECHIPAMENT = "testEchipament";
    public static final String TEST_BUCATI = "testBucati";
    public static final String TEST_PRET = "testPret";

    private Offer controller;

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

        controller = new Offer();
        controller.Echipament = new TextField();
        controller.Bucati = new TextField();
        controller.Pret = new TextField();
        controller.Date= new DatePicker();
        controller.Mesaj = new Text();

        controller.Echipament.setText(TEST_ECHIPAMENT);
        controller.Bucati.setText(TEST_BUCATI);
        controller.Pret.setText(TEST_PRET);
        controller.Date.setValue(LocalDate.of(2020,12,12));
    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void testHandleAddOfertaActionCode() {
        controller.Creare();
        assertNotNull(OfertaService.getOferte());
        assertEquals(1, OfertaService.getOferte().size());
        assertEquals("Oferta creata !", controller.Mesaj.getText());
    }

    @Test
    public void testCreare() {
        controller.Creare();
        controller.Creare();
        assertEquals("Oferta exista deja!", controller.Mesaj.getText());
    }

    @Test
    public void testIncomplet1() {
        controller.Echipament.setText("");
        controller.Bucati.setText("");
        controller.Pret.setText("");
        controller.Date.setValue(null);
        controller.Creare();
        assertEquals("Completati toate campurile !", controller.Mesaj.getText());
    }



    @Test
    public void testAddOneOffer() throws Exception {
        OfertaService.loadOferteFromFile();
        OfertaService.addOferte("test1", "test2", "test3","test4","test5");
        assertNotNull(OfertaService.oferte);
        assertEquals(1, OfertaService.oferte.size());
    }

    @Test (expected = NullPointerException.class)
    public void testAnulare() throws Exception {
        ActionEvent actionEvent = null;
        controller.Anulare(actionEvent);
    }


}
