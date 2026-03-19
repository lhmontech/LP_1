package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class farmaciaController {
    @FXML
    private void clicarBtnPessoa() {
        try {
            App.setRoot("pessoa");
        } catch (Exception e) {
            System.out.println("Erro ao trocar de tela");
        }
    }
}
