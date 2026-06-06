package org.example.controller;

import javafx.fxml.FXML;
import org.example.App;

import java.io.IOException;

public class FarmaciaController {

    @FXML
    public void clicarBalcao() {
        try {
            App.setRoot("balcao");
        } catch (IOException e) {
            System.out.println("Erro ao mudar para a tela do balcão: " + e);
            e.printStackTrace();
        }
    }

    @FXML
    public void clicarCliente() {
        try {
            App.setRoot("balcao");
        } catch (IOException e) {
            System.out.println("Erro ao mudar para a tela do cliente: " + e);
            e.printStackTrace();
        }
    }

    @FXML
    public void clicarRemedios() {
        try {
            App.setRoot("remedio");
        } catch (IOException e) {
            System.out.println("Erro ao mudar para a tela de remédios: " + e);
        }
    }
}