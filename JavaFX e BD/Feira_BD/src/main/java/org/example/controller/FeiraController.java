package org.example.controller;

import javafx.fxml.FXML;
import org.example.App;

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
            App.setRoot("barraca");
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
