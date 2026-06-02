package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class barbeariaController {
    @FXML
    private void clicarBtnVoltar() {
        try {
            App.setRoot("barbearia");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clicarBtnPessoa() {
        try {
            App.setRoot("pessoa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clicarBtnCadeira() {
        try {
            App.setRoot("cadeira");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clicarBtnCabelo() {
        try {
            App.setRoot("cabelo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Pessoa pessoa = new Pessoa();

    @FXML
    private Cadeira cadeira = new Cadeira();

    @FXML
    private Cabelo cabelo = new Cabelo();

    @FXML
    private Label texto;

    @FXML
    private void clicarBtnPintarp() {
        texto.setText(pessoa.pintar());
    }

    @FXML
    private void clicarBtnFalar() {
        texto.setText(pessoa.falar());
    }

    @FXML
    private void clicarBtnCortarp() {
        texto.setText(pessoa.cortar());
    }

    @FXML
    private void clicarBtnRegular() {
        texto.setText(cadeira.regular());
    }

    @FXML
    private void clicarBtnSentar() {
        texto.setText(cadeira.sentar());
    }

    @FXML
    private void clicarBtnGirar() {
        texto.setText(cadeira.girar());
    }

    @FXML
    private void clicarBtnCortar() {
        texto.setText(cabelo.cortar());
    }

    @FXML
    private void clicarBtnLavar() {
        texto.setText(cabelo.lavar());
    }

    @FXML
    private void clicarBtnPintar() {
        texto.setText(cabelo.pintar());
    }
}
