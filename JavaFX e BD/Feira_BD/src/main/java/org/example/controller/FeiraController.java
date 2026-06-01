package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;

import java.io.IOException;

public class FeiraController {
    @FXML private TextField fldDesconto;
    @FXML private TextField fldPreco;
    @FXML private TextField fldQtd;
    @FXML private Label lblPreco;
    @FXML private Label lblQtd;

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
    void clicarAdicionar(ActionEvent event) {

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

}
