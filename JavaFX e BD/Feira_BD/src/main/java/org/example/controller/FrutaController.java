package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import org.example.App;
import org.example.model.Fruta;
import org.example.service.AlertService;
import org.example.service.FeiraService;

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
    @FXML private AnchorPane rootPane;

    private final FeiraService feiraService = new FeiraService();
    private ToggleGroup grupoFrutas;

    @FXML
    public void initialize(){
        grupoFrutas = new ToggleGroup();

        rsBanana.setToggleGroup(grupoFrutas);
        rsMaca.setToggleGroup(grupoFrutas);
        rsLaranja.setToggleGroup(grupoFrutas);
        atualizarInidicadores();
    }

    @FXML
    public void clicarVoltar(){
        try {
            App.setRoot("feira");
        } catch (IOException e) {
            System.out.println("Erro ao mudar para a tela da feira: " + e);
        }
    }

    @FXML
    public void clicarRepor() {
        String tipoFruta = pegarFrutaSelecionada();
        if (tipoFruta != null) {
            int quantidade = pegarQtd();
            feiraService.adicionarFruta(tipoFruta, quantidade);
            limparFormulario();
            atualizarInidicadores();
        } else {
            AlertService.mostrar(rootPane, "Selecione uma fruta!", AlertService.Tipo.AVISO);
        }
    }

    @FXML
    void clicarAtualizar() {
        String tipoFruta = pegarFrutaSelecionada();
        if (tipoFruta != null) {
            double novoValor = pegarNovoValor();
            feiraService.atualizarPreco(tipoFruta, novoValor);
            limparFormulario();
            atualizarInidicadores();
        } else {
            AlertService.mostrar(rootPane, "Selecione uma fruta!", AlertService.Tipo.AVISO);
        }
    }

    @FXML
    void clicarDesconto() {
        String tipoFruta = pegarFrutaSelecionada();
        if (tipoFruta != null) {
            int desconto = pegarDesconto();
            feiraService.aplicarPromocao(tipoFruta, desconto);
            limparFormulario();
            atualizarInidicadores();
        } else {
            AlertService.mostrar(rootPane, "Selecione uma fruta!", AlertService.Tipo.AVISO);
        }
    }

    @FXML
    void clicarReduzir() {
        String tipoFruta = pegarFrutaSelecionada();
        if (tipoFruta != null) {
            int quantidade = pegarQtd();
            feiraService.descartarFruta(tipoFruta, quantidade);
            limparFormulario();
            atualizarInidicadores();
        } else {
            AlertService.mostrar(rootPane, "Selecione uma fruta!", AlertService.Tipo.AVISO);
        }
    }

    public String pegarFrutaSelecionada() {
        RadioButton selecionado = (RadioButton) grupoFrutas.getSelectedToggle();
        if (selecionado != null) {
            String fruta = selecionado.getText();
            return fruta;
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
            throw new RuntimeException("Digite um valor válido!");
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
        Fruta banana = feiraService.buscarPorTipo("Banana");
        Fruta maca = feiraService.buscarPorTipo("Maçã");
        Fruta laranja = feiraService.buscarPorTipo("Laranja");

        lblPrecoB.setText("R$ " + String.valueOf(banana.getValor()));
        lblQtdB.setText(String.valueOf(banana.getQuantidade()));

        lblPrecoM.setText("R$ " + String.valueOf(maca.getValor()));
        lblQtdM.setText(String.valueOf(maca.getQuantidade()));

        lblPrecoL.setText("R$ " + String.valueOf(laranja.getValor()));
        lblQtdL.setText(String.valueOf(laranja.getQuantidade()));
    }
}
