package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.service.FrutaService;

import java.io.IOException;

public class FeiraController {
    @FXML private TextField fldDesconto;
    @FXML private TextField fldPreco;
    @FXML private TextField fldQtd;
    @FXML private Label lblPreco;
    @FXML private Label lblQtd;
    @FXML private RadioButton rsBanana;
    @FXML private RadioButton rsMaca;
    @FXML private RadioButton rsLaranja;

    private final FrutaService frutaService = new FrutaService();

    @FXML
    public void clicarBarraca(){
        try {
            App.setRoot("barraca");
        } catch (IOException e) {
            System.out.println("Erro ao mudar para a tela barraca: " + e);
        }
    }

    @FXML
    public void clicarCliente(){
        try {
            App.setRoot("cliente");
        } catch (IOException e) {
            System.out.println("Erro ao mudar para a tela cliente: " + e);
        }
    }

    @FXML
    public void clicarFrutas(){
        try {
            App.setRoot("fruta");
        } catch (IOException e) {
            System.out.println("Erro ao mudar para a tela de frutas: " + e);
        }
    }

    @FXML
    public void clicarVoltar(){
        try {
            App.setRoot("feira");
        } catch (IOException e) {
            System.out.println("Erro ao mudar para a tela de frutas: " + e);
        }
    }

    @FXML
    void clicarAdicionar() {

        frutaService.adicionarFruta();
    }

    @FXML
    void clicarAtualizar(ActionEvent event) {

    }

    @FXML
    void clicarDesconto(ActionEvent event) {

    }

    @FXML
    void clicarExcluir(ActionEvent event) {

    }

    public String pegarFrutaSelecionada( {
        if (rsBanana.isSelected()) {
            return rsBanana.getText();
        }
    })

}
