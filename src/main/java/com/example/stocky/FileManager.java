package com.example.stocky;

import com.google.gson.*;

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


    private static final File userFile = new File("./personalData.json");



    public static JsonArray readUserFile() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(FileManager.userFile.toURI())));
        JsonElement jsonObject = new JsonParser().parse(content);
        return jsonObject.getAsJsonObject().get("myStocks").getAsJsonArray();
    }




    public static String saveSessionFile(JsonArray apiJsonStockData) throws IOException {

        LocalDate currentDate = LocalDateTime.now().toLocalDate();
        File sessionFile = new File("data/" + currentDate + ".json");

        FileWriter fileWriter = new FileWriter(sessionFile);
        fileWriter.write("{\n \"StockData\" : [\n");


        for(int i = 0; i < apiJsonStockData.size(); i++){

            JsonElement el = apiJsonStockData.get(i);
            fileWriter.write(
            "\t\t{\n\t\t\t\"Ticker\" : "+'\"'+el.getAsJsonObject().get("Ticker").getAsString()+"\","
                +"\n\t\t\t\"Currentprice\" : "+el.getAsJsonObject().get("Currentprice").getAsDouble()
                +"\n\t\t}"
            );

            if ((i != apiJsonStockData.size() - 1))
                fileWriter.write(",\n");
            else
                fileWriter.write('\n');
        }

        fileWriter.write("\t]\n}");
        fileWriter.close();

        return "data/" + currentDate.toString() + ".json";
    }

}
