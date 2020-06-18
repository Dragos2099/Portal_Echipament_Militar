package Cerere.services;


import Cerere.model.Request;

import Register.services.FileSystemService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class CerereService {
    private static List<Request> cereri;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("cerere", "cerere.json");


    public static void loadCereriFromFile() throws IOException {
        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(CerereService.class.getClassLoader().getResource("cerere.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper2 = new ObjectMapper();

        cereri = objectMapper2.readValue(USERS_PATH.toFile(), new TypeReference<List<Request>>() {
        });
    }

    public static void addCereri(String text1, String text2, String text3) throws IOException {


        cereri.add(new Request(text1,text2,text3));
        persistCereri();
    }



    private static void persistCereri() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), cereri);

    }
}