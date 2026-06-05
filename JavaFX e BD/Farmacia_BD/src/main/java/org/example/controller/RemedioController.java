package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import org.example.App;
import org.example.model.Remedio;
import org.example.service.AlertService;
import org.example.service.FarmaciaService;

import java.io.IOException;

public class RemedioController {
    @FXML private TextField fldDesconto;
    @FXML private TextField fldPreco;
    @FXML private TextField fldQtd;
    @FXML private Label lblPrecoD;
    @FXML private Label lblQtdD;
    @FXML private Label lblPrecoP;
    @FXML private Label lblQtdP;
    @FXML private Label lblPrecoA;
    @FXML private Label lblQtdA;
    @FXML private RadioButton rsDipirona;
    @FXML private RadioButton rsParacetamol;
    @FXML private RadioButton rsAmoxicilina;
    @FXML private AnchorPane rootPane;

    private final FarmaciaService farmaciaService = new FarmaciaService();
    private ToggleGroup grupoRemedios;

    @FXML
    public void initialize() {
        grupoRemedios = new ToggleGroup();
        rsDipirona.setToggleGroup(grupoRemedios);
        rsParacetamol.setToggleGroup(grupoRemedios);
        rsAmoxicilina.setToggleGroup(grupoRemedios);
        atualizarIndicadores();
    }

    @FXML
    public void clicarVoltar() {
        try {
            App.setRoot("farmacia");
        } catch (IOException e) {
            System.out.println("Erro ao mudar para a tela da farmácia: " + e);
        }
    }

    @FXML
    public void clicarRepor() {
        String nomeRemedio = pegarRemedioSelecionado();
        if (nomeRemedio != null) {
            int quantidade = pegarQtd();
            farmaciaService.adicionarRemedio(nomeRemedio, quantidade);
            limparFormulario();
            atualizarIndicadores();
        } else {
            AlertService.mostrar(rootPane, "Selecione um remédio!", AlertService.Tipo.AVISO);
        }
    }

    @FXML
    void clicarAtualizar() {
        String nomeRemedio = pegarRemedioSelecionado();
        if (nomeRemedio != null) {
            double novoValor = pegarNovoValor();
            farmaciaService.atualizarPreco(nomeRemedio, novoValor);
            limparFormulario();
            atualizarIndicadores();
        } else {
            AlertService.mostrar(rootPane, "Selecione um remédio!", AlertService.Tipo.AVISO);
        }
    }

    @FXML
    void clicarDesconto() {
        String nomeRemedio = pegarRemedioSelecionado();
        if (nomeRemedio != null) {
            int desconto = pegarDesconto();
            farmaciaService.aplicarPromocao(nomeRemedio, desconto);
            limparFormulario();
            atualizarIndicadores();
        } else {
            AlertService.mostrar(rootPane, "Selecione um remédio!", AlertService.Tipo.AVISO);
        }
    }

    @FXML
    void clicarReduzir() {
        String nomeRemedio = pegarRemedioSelecionado();
        if (nomeRemedio != null) {
            int quantidade = pegarQtd();
            farmaciaService.descartarRemedio(nomeRemedio, quantidade);
            limparFormulario();
            atualizarIndicadores();
        } else {
            AlertService.mostrar(rootPane, "Selecione um remédio!", AlertService.Tipo.AVISO);
        }
    }

    public String pegarRemedioSelecionado() {
        RadioButton selecionado = (RadioButton) grupoRemedios.getSelectedToggle();
        if (selecionado != null) {
            return selecionado.getText();
        }
        return null;
    }

    public int pegarQtd() {
        String qtdTexto = fldQtd.getText();
        try {
            return Integer.parseInt(qtdTexto);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public int pegarDesconto() {
        String qtdTexto = fldDesconto.getText();
        try {
            return Integer.parseInt(qtdTexto);
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
        rsDipirona.setSelected(false);
        rsParacetamol.setSelected(false);
        rsAmoxicilina.setSelected(false);
    }

    private void atualizarIndicadores() {
        Remedio dipirona = farmaciaService.buscarPorNome("Dipirona");
        Remedio paracetamol = farmaciaService.buscarPorNome("Paracetamol");
        Remedio amoxicilina = farmaciaService.buscarPorNome("Amoxicilina");

        lblPrecoD.setText("R$ " + dipirona.getPreco());
        lblQtdD.setText(String.valueOf(dipirona.getQuantidade()));

        lblPrecoP.setText("R$ " + paracetamol.getPreco());
        lblQtdP.setText(String.valueOf(paracetamol.getQuantidade()));

        lblPrecoA.setText("R$ " + amoxicilina.getPreco());
        lblQtdA.setText(String.valueOf(amoxicilina.getQuantidade()));
    }
}