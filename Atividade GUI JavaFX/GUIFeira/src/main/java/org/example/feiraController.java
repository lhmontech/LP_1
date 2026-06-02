package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class feiraController {
    @FXML
    private void clicarBtnVoltar() {
        try {
            App.setRoot("feira");
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
    private void clicarBtnBarraca() {
        try {
            App.setRoot("barraca");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clicarBtnFrutas() {
        try {
            App.setRoot("fruta");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Pessoa pessoa = new Pessoa();

    @FXML
    private Barraca barraca = new Barraca();

    @FXML
    private Fruta fruta = new Fruta();

    @FXML
    private Label texto;

    @FXML
    private void clicarBtnVenderP() {
        texto.setText(pessoa.vender());
    }

    @FXML
    private void clicarBtnFalar() {
        texto.setText(pessoa.falar());
    }

    @FXML
    private void clicarBtnComprar() {
        texto.setText(pessoa.comprar());
    }

    @FXML
    private void clicarBtnArmazenar() {
        texto.setText(barraca.armazenar());
    }

    @FXML
    private void clicarBtnExpor() {
        texto.setText(barraca.expor());
    }

    @FXML
    private void clicarBtnOrganizar() {
        texto.setText(barraca.organizar());
    }

    @FXML
    private void clicarBtnVender() {
        texto.setText(fruta.vender());
    }

    @FXML
    private void clicarBtnEstragar() {
        texto.setText(fruta.estragar());
    }

    @FXML
    private void clicarBtnComer() {
        texto.setText(fruta.comer());
    }
}
