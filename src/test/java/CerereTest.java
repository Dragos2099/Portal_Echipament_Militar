import Cerere.controllers.Cerere;
import Cerere.services.CerereService;
import Register.controllers.Register;
import Register.services.FileSystemService;
import Register.services.UserService;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CerereTest extends ApplicationTest {

    public static final String TEST_ECHIPAMENT = "testEchipament";
    public static final String TEST_BUCATI = "testBucati";
    public static final String TEST_URGENT = "Da";

    private Cerere controller;

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

        controller = new Cerere();
        controller.Echipament = new TextField();
        controller.Bucati = new PasswordField();
        controller.Urgent = new ChoiceBox();
        controller.Mesaj = new Text();

        controller.Echipament.setText(TEST_ECHIPAMENT);
        controller.Bucati.setText(TEST_BUCATI);
        controller.Urgent.setValue("Da");
    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void testHandleAddCerereActionCode() {
        controller.Creare();
        assertNotNull(CerereService.getCereri());
        assertEquals(1, CerereService.getCereri().size());
        assertEquals("Cerere creata !", controller.Mesaj.getText());
    }

    @Test
    public void testCreare() {
        controller.Creare();
        controller.Creare();
        assertEquals("Cererea exista deja!", controller.Mesaj.getText());
    }

    @Test
    public void testIncomplet1() {
        controller.Echipament.setText("");
        controller.Bucati.setText("");
        controller.Urgent.setValue("");
        controller.Creare();
        assertEquals("Completati toate campurile !", controller.Mesaj.getText());
    }

    @Test
    public void testIncomplet2() {
        controller.Echipament.setText("ceva");
        controller.Bucati.setText("");
        controller.Urgent.setValue("");
        controller.Creare();
        assertEquals("Completati toate campurile !", controller.Mesaj.getText());
    }

    @Test
    public void testIncomplet3() {
        controller.Echipament.setText("");
        controller.Bucati.setText("ceva");
        controller.Urgent.setValue("");
        controller.Creare();
        assertEquals("Completati toate campurile !", controller.Mesaj.getText());
    }

    @Test
    public void testIncomplet4() {
        controller.Echipament.setText("");
        controller.Bucati.setText("");
        controller.Urgent.setValue("Da");
        controller.Creare();
        assertEquals("Completati toate campurile !", controller.Mesaj.getText());
    }

    @Test
    public void testIncomplet5() {
        controller.Echipament.setText("ceva");
        controller.Bucati.setText("ceva");
        controller.Urgent.setValue("");
        controller.Creare();
        assertEquals("Completati toate campurile !", controller.Mesaj.getText());
    }

    @Test
    public void testIncomplet6() {
        controller.Echipament.setText("");
        controller.Bucati.setText("ceva");
        controller.Urgent.setValue("Da");
        controller.Creare();
        assertEquals("Completati toate campurile !", controller.Mesaj.getText());
    }

    @Test
    public void testIncomplet7() {
        controller.Echipament.setText("ceva");
        controller.Bucati.setText("");
        controller.Urgent.setValue("Da");
        controller.Creare();
        assertEquals("Completati toate campurile !", controller.Mesaj.getText());
    }

    @Test
    public void testAddOneCerere() throws Exception {
        CerereService.loadCereriFromFile();
        CerereService.addCereri("test", "testPass", "test1user");
        assertNotNull(CerereService.cereri);
        assertEquals(1, CerereService.cereri.size());
    }

    @Test (expected = NullPointerException.class)
    public void testAnulare() throws Exception {
        ActionEvent actionEvent = null;
        controller.Anulare(actionEvent);
    }

}
