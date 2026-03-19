package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
    private void clicarBtnPrateleira() {
        try {
            App.setRoot("prateleira");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clicarBtnRemedios() {
        try {
            App.setRoot("prateleira");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Pessoa pessoa = new Pessoa();

    @FXML
    private Prateleira prateleira = new Prateleira();

    @FXML
    private Label texto;

    @FXML
    private void clicarBtnAndar() {
        texto.setText(pessoa.andar());
    }

    @FXML
    private void clicarBtnFalar() {
        texto.setText(pessoa.falar());
    }

    @FXML
    private void clicarBtnGritar() {
        texto.setText(pessoa.gritar());
    }

    @FXML
    private void clicarBtnArmazenar() {
        texto.setText(prateleira.armazenar());
    }

    @FXML
    private void clicarBtnExpor() {
        texto.setText(prateleira.expor());
    }

    @FXML
    private void clicarBtnOrganizar() {
        texto.setText(prateleira.organizar());
    }
}
