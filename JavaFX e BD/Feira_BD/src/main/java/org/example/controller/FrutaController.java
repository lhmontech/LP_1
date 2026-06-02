package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.model.Fruta;
import org.example.service.FrutaService;

import java.io.IOException;

public class FrutaController {
    @FXML private TextField fldDesconto;
    @FXML private TextField fldPreco;
    @FXML private TextField fldQtd;
    @FXML private Label lblPrecoB;
    @FXML private Label lblQtdB;
    @FXML private Label lblPrecoM;
    @FXML private Label lblQtdM;
    @FXML private Label lblPrecoL;
    @FXML private Label lblQtdL;
    @FXML private RadioButton rsBanana;
    @FXML private RadioButton rsMaca;
    @FXML private RadioButton rsLaranja;

    private final FrutaService frutaService = new FrutaService();

    @FXML
    public void initialize(){
        atualizarInidicadores();
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
    public void clicarAdicionar() {
        String tipoFruta = pegarFrutaSelecionada();
        int quantidade = pegarQtd();
        frutaService.adicionarFruta(tipoFruta, quantidade);
        limparFormulario();
        atualizarInidicadores();
    }

    @FXML
    void clicarAtualizar() {
        String tipoFruta = pegarFrutaSelecionada();
        double novoValor = pegarNovoValor();
        frutaService.atualizarPreco(tipoFruta, novoValor);
        limparFormulario();
        atualizarInidicadores();
    }

    @FXML
    void clicarDesconto() {
        String tipoFruta = pegarFrutaSelecionada();
        int desconto = pegarDesconto();
        frutaService.aplicarPromocao(tipoFruta, desconto);
        limparFormulario();
        atualizarInidicadores();
    }

    @FXML
    void clicarExcluir() {
        String tipoFruta = pegarFrutaSelecionada();
        int quantidade = pegarQtd();
        frutaService.descartarFruta(tipoFruta, quantidade);
        limparFormulario();
        atualizarInidicadores();
    }

    public String pegarFrutaSelecionada() {
        if (rsBanana.isSelected()) {
            return rsBanana.getText();
        }
        if (rsLaranja.isSelected()) {
            return rsLaranja.getText();
        }
        if (rsMaca.isSelected()) {
            return rsMaca.getText();
        }
        return null;
    }

    public int pegarQtd() {
        String qtdTexto = fldQtd.getText();
        try {
            int qtdNumero = Integer.parseInt(qtdTexto);
            return qtdNumero;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public int pegarDesconto() {
        String qtdTexto = fldDesconto.getText();
        try {
            int qtdNumero = Integer.parseInt(qtdTexto);
            return qtdNumero;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public double pegarNovoValor() {
        String texto = fldPreco.getText();

        try {
            texto = texto.replace(",", ".");
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Digite um valor válido");
        }
    }

    private void limparFormulario() {
        fldQtd.clear();
        fldPreco.clear();
        fldDesconto.clear();
        rsBanana.setSelected(false);
        rsLaranja.setSelected(false);
        rsMaca.setSelected(false);
    }

    private void atualizarInidicadores() {
        Fruta banana = frutaService.buscarPorTipo("Banana");
        Fruta maca = frutaService.buscarPorTipo("Maçã");
        Fruta laranja = frutaService.buscarPorTipo("Laranja");


        lblPrecoB.setText(String.valueOf(banana.getValor()));
        lblQtdB.setText(String.valueOf(banana.getQuantidade()));

        lblPrecoM.setText(String.valueOf(maca.getValor()));
        lblQtdM.setText(String.valueOf(maca.getQuantidade()));

        lblPrecoL.setText(String.valueOf(laranja.getValor()));
        lblQtdL.setText(String.valueOf(laranja.getQuantidade()));
    }
}
