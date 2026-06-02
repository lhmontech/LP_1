package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.model.Fruta;
import org.example.service.FrutaService;

import java.io.IOException;

public class FeiraController {

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
}
