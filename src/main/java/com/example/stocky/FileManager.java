package com.example.stocky;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalField;
import java.util.Date;

public class FileManager {


    private static File userFile = new File("./personalData.json");



    public static JsonArray readUserFile() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(FileManager.userFile.toURI())));
        JsonElement jsonObject = new JsonParser().parse(content);
        return jsonObject.getAsJsonObject().get("myStocks").getAsJsonArray();
    }

    public static boolean saveSessionFile(JsonArray apiJsonStockData) throws IOException {
        LocalDate currentDate = LocalDateTime.now().toLocalDate();
        File sessionFile = new File(currentDate +".json");

        if (!sessionFile.createNewFile())
            return false;

        FileWriter fileWriter = new FileWriter(sessionFile);





        return true;
    }

}
