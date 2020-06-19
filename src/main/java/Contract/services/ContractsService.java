package Contract.services;

import Contract.exceptions.ContractAlreadyExistsException;
import Contract.exceptions.CouldNotWriteContractException;
import Contract.model.Contracts;

import Register.services.FileSystemService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class ContractsService {

    private static List<Contracts> contracts;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("contract", "contracts.json");


    public static void loadContractsFromFile() throws IOException {
        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(ContractsService.class.getClassLoader().getResource("contracts.json")), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper2 = new ObjectMapper();

        contracts = objectMapper2.readValue(USERS_PATH.toFile(), new TypeReference<List<Contracts>>() {
        });
    }

    public static void addContracts(String da, String text, String text1, String text2, String text3, String text4) throws ContractAlreadyExistsException {
        checkContractDoesNotAlreadyExist(da,text,text1,text2,text3,text4);
        Contracts c2=new Contracts(da,text,text1,text2,text3,text4);
        contracts.add(c2);
        persistContracts();
    }

    private static void persistContracts(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), contracts);
        } catch (IOException e) {
            throw new CouldNotWriteContractException();
        }
    }


    private static void checkContractDoesNotAlreadyExist(String a,String b,String c1,String d,String e,String f) throws ContractAlreadyExistsException {

        for (Contracts c : contracts) {
            if ((Objects.equals(a, c.getData())) && (Objects.equals(b, c.getBuc())) && (Objects.equals(c1, c.getTotal())) && (Objects.equals(d, c.getEchipamente())) && (Objects.equals(e, c.getNr_f())) && (Objects.equals(f, c.getNr_bm())))
                throw new ContractAlreadyExistsException();
        }
    }
}
