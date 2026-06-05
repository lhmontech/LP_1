package org.example.controller;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.App;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import org.example.model.Remedio;
import org.example.service.AlertService;
import org.example.service.FarmaciaService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalcaoController {
    @FXML private TextField fldQtd;
    @FXML private RadioButton rbDipirona;
    @FXML private RadioButton rbParacetamol;
    @FXML private RadioButton rbAmoxicilina;
    @FXML private AnchorPane anchorPane;
    @FXML private TextField fldQtdCli;
    @FXML private Label precoDipirona;
    @FXML private Label precoParacetamol;
    @FXML private Label precoAmoxicilina;
    @FXML private Label qtdDipirona;
    @FXML private Label qtdDiroponaCli;
    @FXML private Label qtdParacetamol;
    @FXML private Label qtdParacetamolCli;
    @FXML private Label qtdAmoxicilina;
    @FXML private Label qtdAmoxicilinaCli;
    @FXML private RadioButton rbDiroponaCli;
    @FXML private RadioButton rbParacetamolCli;
    @FXML private RadioButton rbAmoxicilinaCli;
    @FXML private Label saldoCliente;
    @FXML private ImageView cliente;
    @FXML private ImageView clienteTomando;
    @FXML private Label lblFala;
    @FXML private ImageView balaoFala;

    private ToggleGroup grupoRemedios;
    private ToggleGroup grupoRemediosCli;
    private FarmaciaService farmaciaService = new FarmaciaService();
    private List<String> listaBalcao = new ArrayList<>();
    private final Map<String, Image> imageCache = new HashMap<>();

    List<double[]> slots = List.of(
            new double[]{152, 348, 48, 48},
            new double[]{121, 363, 48, 48},
            new double[]{233, 349, 48, 48},
            new double[]{202, 364, 48, 48},
            new double[]{350, 380, 48, 48},
            new double[]{335, 405, 48, 48},
            new double[]{400, 380, 48, 48},
            new double[]{385, 405, 48, 48},
            new double[]{496, 385, 48, 48},
            new double[]{481, 410, 48, 48},
            new double[]{546, 385, 48, 48},
            new double[]{531, 410, 48, 48},
            new double[]{471, 435, 48, 48},
            new double[]{644, 386, 48, 48},
            new double[]{629, 411, 48, 48},
            new double[]{64, 500, 48, 48},
            new double[]{49, 525, 48, 48},
            new double[]{39, 550, 48, 48},
            new double[]{143, 500, 48, 48},
            new double[]{128, 525, 48, 48},
            new double[]{118, 550, 48, 48},
            new double[]{218, 500, 48, 48},
            new double[]{193, 550, 48, 48},
            new double[]{217, 501, 48, 48},
            new double[]{202, 526, 48, 48},
            new double[]{192, 551, 48, 48},
            new double[]{292, 501, 48, 48},
            new double[]{274, 526, 48, 48},
            new double[]{259, 551, 48, 48},
            new double[]{370, 501, 48, 48},
            new double[]{355, 526, 48, 48},
            new double[]{345, 551, 48, 48},
            new double[]{445, 501, 48, 48},
            new double[]{427, 526, 48, 48},
            new double[]{412, 551, 48, 48},
            new double[]{527, 502, 48, 48},
            new double[]{512, 527, 48, 48},
            new double[]{502, 552, 48, 48},
            new double[]{602, 502, 48, 48},
            new double[]{584, 527, 48, 48},
            new double[]{569, 552, 48, 48},
            new double[]{677, 526, 48, 48},
            new double[]{667, 551, 48, 48},
            new double[]{752, 524, 48, 48},
            new double[]{737, 549, 48, 48}
    );

    @FXML
    public void initialize() {
        getImage("Dipirona");
        getImage("Paracetamol");
        getImage("Amoxicilina");
        grupoRemedios = new ToggleGroup();
        grupoRemediosCli = new ToggleGroup();
        listaBalcao = farmaciaService.montarListaBalcao();
        rbDipirona.setToggleGroup(grupoRemedios);
        rbParacetamol.setToggleGroup(grupoRemedios);
        rbAmoxicilina.setToggleGroup(grupoRemedios);
        rbDiroponaCli.setToggleGroup(grupoRemediosCli);
        rbParacetamolCli.setToggleGroup(grupoRemediosCli);
        rbAmoxicilinaCli.setToggleGroup(grupoRemediosCli);
        carregarRemedios();
        atualizarIndicadores();
        atualizarIndicadoresCli();
    }

    @FXML
    void clicarExpor() {
        String nomeRemedio = pegarRemedioSelecionado();
        if (nomeRemedio != null) {
            int quantidade = pegarQtd();
            boolean sucesso = farmaciaService.exporProduto(nomeRemedio, quantidade);
            if (sucesso) {
                for (int i = 0; i < quantidade; i++) {
                    listaBalcao.add(nomeRemedio);
                }
                carregarRemedios();
                atualizarIndicadores();
            } else {
                AlertService.mostrar(anchorPane, "Operação não realizada. Verifique o estoque.", AlertService.Tipo.ERRO);
            }
        } else {
            AlertService.mostrar(anchorPane, "Selecione um remédio!", AlertService.Tipo.AVISO);
        }
        limparFormulario();
    }

    @FXML
    void clicarOrganizar() {
        listaBalcao = farmaciaService.montarListaBalcao();
        carregarRemedios();
    }

    @FXML
    void clicarRecolher() {
        String nomeRemedio = pegarRemedioSelecionado();
        if (nomeRemedio != null) {
            int quantidade = pegarQtd();
            boolean sucesso = farmaciaService.recolherProduto(nomeRemedio, quantidade);
            if (sucesso) {
                for (int i = 0; i < quantidade; i++) {
                    listaBalcao.remove(nomeRemedio);
                }
                carregarRemedios();
                atualizarIndicadores();
            } else {
                AlertService.mostrar(anchorPane, "Operação não realizada. Verifique o estoque.", AlertService.Tipo.ERRO);
            }
        } else {
            AlertService.mostrar(anchorPane, "Selecione um remédio!", AlertService.Tipo.AVISO);
        }
        limparFormulario();
    }

    @FXML
    void clicarTomar() {
        String nomeRemedio = pegarRemedioSelecionadoCli();
        if (nomeRemedio != null) {
            int quantidade = pegarQtdCli();
            boolean sucesso = farmaciaService.consumirProduto(nomeRemedio, quantidade);
            if (sucesso) {
                animacaoTomar();
                atualizarIndicadoresCli();
            } else {
                AlertService.mostrar(anchorPane, "Operação não realizada. Verifique o estoque.", AlertService.Tipo.ERRO);
            }
        } else {
            AlertService.mostrar(anchorPane, "Selecione um remédio!", AlertService.Tipo.AVISO);
        }
        limparFormulario();
    }

    @FXML
    void clicarComprar() {
        String nomeRemedio = pegarRemedioSelecionadoCli();
        if (nomeRemedio != null) {
            int quantidade = pegarQtdCli();
            boolean sucesso = farmaciaService.comprarProduto(nomeRemedio, quantidade);
            if (sucesso) {
                animacaoBalao("Olá! Eu gostaria de " + quantidade + " " + nomeRemedio + "(s) por favor!");
                for (int i = 0; i < quantidade; i++) {
                    listaBalcao.remove(nomeRemedio);
                }
                carregarRemedios();
                atualizarIndicadoresCli();
            } else {
                AlertService.mostrar(anchorPane, "Operação não realizada. Verifique o estoque.", AlertService.Tipo.ERRO);
            }
        } else {
            AlertService.mostrar(anchorPane, "Selecione um remédio!", AlertService.Tipo.AVISO);
        }
        limparFormulario();
    }

    @FXML
    void clicarDevolver() {
        String nomeRemedio = pegarRemedioSelecionadoCli();
        if (nomeRemedio != null) {
            int quantidade = pegarQtdCli();
            boolean sucesso = farmaciaService.devolverProduto(nomeRemedio, quantidade);
            if (sucesso) {
                animacaoBalao("Eu gostaria de devolver esse(s) " + quantidade + " " + nomeRemedio + "(s).");
                for (int i = 0; i < quantidade; i++) {
                    listaBalcao.add(nomeRemedio);
                }
                carregarRemedios();
                atualizarIndicadoresCli();
            } else {
                AlertService.mostrar(anchorPane, "Operação não realizada. Verifique o estoque.", AlertService.Tipo.ERRO);
            }
        } else {
            AlertService.mostrar(anchorPane, "Selecione um remédio!", AlertService.Tipo.AVISO);
        }
        limparFormulario();
    }

    @FXML
    public void clicarVoltar() {
        try {
            App.setRoot("farmacia");
        } catch (IOException e) {
            System.out.println("Erro ao mudar para a tela da farmácia: " + e);
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

    public String pegarRemedioSelecionadoCli() {
        RadioButton selecionado = (RadioButton) grupoRemediosCli.getSelectedToggle();
        if (selecionado != null) {
            return selecionado.getText();
        }
        return null;
    }

    public int pegarQtdCli() {
        String qtdTexto = fldQtdCli.getText();
        try {
            return Integer.parseInt(qtdTexto);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public Image getImage(String remedio) {
        return imageCache.computeIfAbsent(remedio, key ->
                new Image(getClass().getResourceAsStream("/images/" + key + ".png"))
        );
    }

    private void limparFormulario() {
        fldQtd.clear();
        rbDipirona.setSelected(false);
        rbParacetamol.setSelected(false);
        rbAmoxicilina.setSelected(false);
        fldQtdCli.clear();
        rbDiroponaCli.setSelected(false);
        rbParacetamolCli.setSelected(false);
        rbAmoxicilinaCli.setSelected(false);
    }

    public void carregarRemedios() {
        anchorPane.getChildren().removeIf(n -> n instanceof ImageView && n.getUserData() != null);
        for (int i = 0; i < listaBalcao.size() && i < slots.size(); i++) {
            double[] slot = slots.get(i);
            String remedio = listaBalcao.get(i);

            ImageView iv = new ImageView(getImage(remedio));
            iv.setLayoutX(slot[0]);
            iv.setLayoutY(slot[1]);
            iv.setFitWidth(slot[2]);
            iv.setFitHeight(slot[3]);
            iv.setUserData("remedio");

            anchorPane.getChildren().add(iv);
        }
    }

    private void atualizarIndicadores() {
        Remedio dipirona = farmaciaService.buscarPorNome("Dipirona");
        Remedio paracetamol = farmaciaService.buscarPorNome("Paracetamol");
        Remedio amoxicilina = farmaciaService.buscarPorNome("Amoxicilina");

        precoDipirona.setText("R$ " + dipirona.getPreco());
        qtdDipirona.setText(dipirona.getQuantidade() + "x");
        precoParacetamol.setText("R$ " + paracetamol.getPreco());
        qtdParacetamol.setText(paracetamol.getQuantidade() + "x");
        precoAmoxicilina.setText("R$ " + amoxicilina.getPreco());
        qtdAmoxicilina.setText(amoxicilina.getQuantidade() + "x");
    }

    private void atualizarIndicadoresCli() {
        Map<String, Integer> cesta = farmaciaService.qtdRemediosCesta();

        qtdDiroponaCli.setText(cesta.getOrDefault("Dipirona", 0) + "x");
        qtdParacetamolCli.setText(cesta.getOrDefault("Paracetamol", 0) + "x");
        qtdAmoxicilinaCli.setText(cesta.getOrDefault("Amoxicilina", 0) + "x");

        saldoCliente.setText("R$ " + farmaciaService.saldoCliente());
    }

    private void animacaoTomar() {
        cliente.setVisible(false);
        clienteTomando.setVisible(true);

        PauseTransition pausa = new PauseTransition(Duration.seconds(3));

        pausa.setOnFinished(e -> {
            clienteTomando.setVisible(false);
            cliente.setVisible(true);
        });

        pausa.play();
    }

    private void animacaoBalao(String texto) {
        lblFala.setText(texto);
        balaoFala.setVisible(true);
        lblFala.setVisible(true);

        PauseTransition pausa = new PauseTransition(Duration.seconds(5));

        pausa.setOnFinished(e -> {
            balaoFala.setVisible(false);
            lblFala.setVisible(false);
        });

        pausa.play();
    }
}