import Contract.controllers.Contract;
import Contract.exceptions.ContractAlreadyExistsException;
import Contract.model.Contracts;
import Contract.services.ContractsService;
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

public class ContractsServiceTest {
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
        ContractsService.loadContractsFromFile();
        assertTrue(Files.exists(ContractsService.USERS_PATH));
    }

    @Test
    public void testLoadContractsFromFile() throws Exception {
        ContractsService.loadContractsFromFile();
        assertNotNull(ContractsService.contracts);
        Assert.assertEquals(0, ContractsService.contracts.size());
    }

    @Test
    public void testAddOneContract() throws Exception {
        ContractsService.loadContractsFromFile();
        ContractsService.addContracts("test", "testPass", "test1user","test1","Test1","11/11/1111");
        assertNotNull(ContractsService.contracts);
        assertEquals(1, ContractsService.contracts.size());
    }
    @Test
    public void testAddTwoContracts() throws Exception {
        ContractsService.loadContractsFromFile();
        ContractsService.addContracts("test1", "testPass1", "test1user","test1","Test1","11/11/1111");
        ContractsService.addContracts("test2", "testPass2", "test2user","test1","Test1","11/11/1111");
        assertNotNull(ContractsService.contracts);
        assertEquals(2, ContractsService.contracts.size());
    }
    @Test(expected = ContractAlreadyExistsException.class)
    public void testAddContractAlreadyExists() throws Exception {
        ContractsService.loadContractsFromFile();
        ContractsService.addContracts("test1", "testPass1", "test1user","test1","Test1","11/11/1111");
        assertNotNull(ContractsService.contracts);
        ContractsService.checkContractDoesNotAlreadyExist("test1","testPass1","test1user","test1","Test1","11/11/1111");
    }
    @Test
    public void testAddOneContractIsPersisted() throws Exception {
        ContractsService.loadContractsFromFile();
        ContractsService.addContracts("test1", "testPass1", "test1user","test1","Test1","11/11/1111");
        List<Contracts> contracts= new ObjectMapper().readValue(ContractsService.USERS_PATH.toFile(), new TypeReference<List<Contracts>>() {
        });
        assertNotNull(ContractsService.contracts);
        assertEquals(1, contracts.size());
    }
    @Test
    public void testAddTwoContractsArePersisted() throws Exception {
        ContractsService.loadContractsFromFile();
        ContractsService.addContracts("test1", "testPass1", "test1user","test1","Test1","11/11/1111");
        ContractsService.addContracts("test2", "testPass2", "test2user","test1","Test1","11/11/1111");
        List<Contracts> contracts= new ObjectMapper().readValue(ContractsService.USERS_PATH.toFile(), new TypeReference<List<Contracts>>() {
        });
        assertNotNull(ContractsService.contracts);
        assertEquals(2, contracts.size());
    }

}
