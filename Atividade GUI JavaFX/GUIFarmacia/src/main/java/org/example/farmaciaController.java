package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class farmaciaController {
    @FXML
    private void clicarBtnPessoa() {
        try {
            App.setRoot("pessoa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Pessoa pessoa = new Pessoa();

    @FXML
    private void clicarBtnAndar() {
        pessoa.andar();
    }

    @FXML
    private Label texto;

    @FXML
    private void clicarBtnFalar() {
        texto.setText(pessoa.falar());
    }

    @FXML
    private void clicarBtnGritar() {
        texto.setText(pessoa.gritar());
    }
}
