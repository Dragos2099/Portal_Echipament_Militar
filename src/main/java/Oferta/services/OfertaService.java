package Oferta.services;

import Cerere.model.Request;
import Oferta.exceptions.CouldNotWriteOfertaException;
import Oferta.exceptions.OfertaAlreadyExistsException;
import Oferta.model.Oferta;

import Register.services.FileSystemService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class OfertaService {
    public static List<Oferta> oferte;
    public static final Path USERS_PATH = FileSystemService.getPathToFile("oferte", "oferte.json");


    public static void loadOferteFromFile() throws IOException {
        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(OfertaService.class.getClassLoader().getResource("oferte.json")), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper2 = new ObjectMapper();

        oferte = objectMapper2.readValue(USERS_PATH.toFile(), new TypeReference<List<Oferta>>() {
        });
    }

    public static void addOferte(String text1, String text2, String text3,String text4,String text5) throws OfertaAlreadyExistsException {
        checkOfertaDoesNotAlreadyExist(text1,text2,text3,text4,text5);

        oferte.add(new Oferta(text1,text2,text3,text4,text5));
        persistoferte();
    }

    public static void checkOfertaDoesNotAlreadyExist(String text1, String text2, String text3,String text4,String text5) throws OfertaAlreadyExistsException {
        for (Oferta r : oferte) {
            if ((Objects.equals(text1, r.getEchipament())) && (Objects.equals(text2, r.getBucati())) && (Objects.equals(text3, r.getPret())) && (Objects.equals(text4, r.getData())) && (Objects.equals(text5, r.getStare())))
                throw new OfertaAlreadyExistsException();
        }

    }

    private static void persistoferte() {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), oferte);
        } catch (IOException e) {
            throw new CouldNotWriteOfertaException();
        }
    }


    public static List<Oferta> getOferte() {
        return oferte;
    }
}
