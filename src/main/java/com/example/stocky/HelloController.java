package com.example.stocky;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        ScrapingAPI.getJSONCard("gme");
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}