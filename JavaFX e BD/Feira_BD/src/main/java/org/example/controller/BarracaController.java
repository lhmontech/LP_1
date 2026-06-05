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
import org.example.model.Fruta;
import org.example.service.FeiraService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarracaController {
    @FXML private TextField fldQtd;
    @FXML private RadioButton rbBanana;
    @FXML private RadioButton rbLaranja;
    @FXML private RadioButton rbMaca;
    @FXML private AnchorPane anchorPane;
    @FXML private TextField fldQtdCli;
    @FXML private Label precoBanana;
    @FXML private Label precoMaca;
    @FXML private Label precoLaranja;
    @FXML private Label qtdBanana;
    @FXML private Label qtdBananaCli;
    @FXML private Label qtdLaranja;
    @FXML private Label qtdLaranjaCli;
    @FXML private Label qtdMaca;
    @FXML private Label qtdMacaCli;
    @FXML private RadioButton rbBananaCli;
    @FXML private RadioButton rbLaranjaCli;
    @FXML private RadioButton rbMacaCli;
    @FXML private Label saldoCliente;
    @FXML private ImageView cliente;
    @FXML private ImageView clienteComendo;


    private ToggleGroup grupoFrutas;
    private ToggleGroup grupoFrutasCli;
    private FeiraService feiraService = new FeiraService();
    private List<String> listaBarraca = new ArrayList<>();
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
            new double[]{619, 436, 48, 48},
            new double[]{694, 386, 48, 48},
            new double[]{679, 411, 48, 48},
            new double[]{669, 436, 48, 48},
            new double[]{791, 390, 48, 48},
            new double[]{776, 415, 48, 48},
            new double[]{766, 440, 48, 48},
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
    public void initialize(){
        getImage("Banana");
        getImage("Maçã");
        getImage("Laranja");
        grupoFrutas = new ToggleGroup();
        grupoFrutasCli = new ToggleGroup();
        listaBarraca = feiraService.montarListaBarraca();
        rbBanana.setToggleGroup(grupoFrutas);
        rbMaca.setToggleGroup(grupoFrutas);
        rbLaranja.setToggleGroup(grupoFrutas);
        rbBananaCli.setToggleGroup(grupoFrutasCli);
        rbMacaCli.setToggleGroup(grupoFrutasCli);
        rbLaranjaCli.setToggleGroup(grupoFrutasCli);
        carregarFrutas();
        atualizarInidicadores();
        atualizarInidicadoresCli();
    }

    @FXML
    void clicarExpor() {
        String tipoFruta = pegarFrutaSelecionada();
        if (tipoFruta != null) {
            int quantidade = pegarQtd();
            boolean sucesso = feiraService.exporProduto(tipoFruta, quantidade);
            if (sucesso) {
                for (int i = 0; i < quantidade; i++) {
                    listaBarraca.add(tipoFruta);
                }
                carregarFrutas();
                atualizarInidicadores();
            }
        } else {
            System.out.println("Necessário selecionar alguma fruta!");
        }
        limparFormulario();
    }

    @FXML
    void clicarOrganizar() {
        listaBarraca = feiraService.montarListaBarraca();
        carregarFrutas();
    }

    @FXML
    void clicarRecolher() {
        String tipoFruta = pegarFrutaSelecionada();
        if (tipoFruta != null) {
            int quantidade = pegarQtd();
            boolean sucesso = feiraService.recolherProduto(tipoFruta, quantidade);
            if (sucesso) {
                for (int i = 0; i < quantidade; i++) {
                    listaBarraca.remove(tipoFruta);
                }
                carregarFrutas();
                atualizarInidicadores();
            }
        } else {
            System.out.println("Necessário selecionar alguma fruta");
        }
        limparFormulario();
    }

    @FXML
    void clicarComer() {
        String tipoFruta = pegarFrutaSelecionadaCli();
        if (tipoFruta != null) {
            int quantidade = pegarQtdCli();
            boolean sucesso = feiraService.consumirProduto(tipoFruta, quantidade);
            if (sucesso) {
                animacaoComer();
                atualizarInidicadoresCli();
            }
        } else {
            System.out.println("Necessário selecionar alguma fruta");
        }
        limparFormulario();
    }

    @FXML
    void clicarComprar() {
        String tipoFruta = pegarFrutaSelecionadaCli();
        if (tipoFruta != null) {
            int quantidade = pegarQtdCli();
            boolean sucesso = feiraService.comprarProduto(tipoFruta, quantidade);
            if (sucesso) {
                for (int i = 0; i < quantidade; i++) {
                    listaBarraca.remove(tipoFruta);
                }
                carregarFrutas();
                atualizarInidicadoresCli();
            }
        } else {
            System.out.println("Necessário selecionar alguma fruta");
        }
        limparFormulario();
    }

    @FXML
    void clicarDevolver() {
        String tipoFruta = pegarFrutaSelecionadaCli();
        if (tipoFruta != null) {
            int quantidade = pegarQtdCli();
            boolean sucesso = feiraService.devolverProduto(tipoFruta, quantidade);
            if (sucesso) {
                for (int i = 0; i < quantidade; i++) {
                    listaBarraca.add(tipoFruta);
                }
                carregarFrutas();
                atualizarInidicadoresCli();
            }
        } else {
            System.out.println("Necessário selecionar alguma fruta");
        }
        limparFormulario();
    }

    @FXML
    public void clicarVoltar(){
        try {
            App.setRoot("feira");
        } catch (IOException e) {
            System.out.println("Erro ao mudar para a tela da feira: " + e);
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

    public String pegarFrutaSelecionadaCli() {
        RadioButton selecionado = (RadioButton) grupoFrutasCli.getSelectedToggle();
        if (selecionado != null) {
            String fruta = selecionado.getText();
            return fruta;
        }
        return null;
    }

    public int pegarQtdCli() {
        String qtdTexto = fldQtdCli.getText();
        try {
            int qtdNumero = Integer.parseInt(qtdTexto);
            return qtdNumero;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public Image getImage(String fruta) {
        return imageCache.computeIfAbsent(fruta, key ->
                new Image(getClass().getResourceAsStream("/images/" + key + ".png"))
        );
    }

    private void limparFormulario() {
        fldQtd.clear();
        rbBanana.setSelected(false);
        rbLaranja.setSelected(false);
        rbMaca.setSelected(false);
        fldQtdCli.clear();
        rbBananaCli.setSelected(false);
        rbLaranjaCli.setSelected(false);
        rbMacaCli.setSelected(false);
    }

    public void carregarFrutas() {
        anchorPane.getChildren().removeIf(n -> n instanceof ImageView && n.getUserData() != null);
        for (int i = 0; i < listaBarraca.size() && i < slots.size(); i++) {
            double[] slot = slots.get(i);
            String fruta = listaBarraca.get(i);

            ImageView iv = new ImageView(getImage(fruta));
            iv.setLayoutX(slot[0]);
            iv.setLayoutY(slot[1]);
            iv.setFitWidth(slot[2]);
            iv.setFitHeight(slot[3]);
            iv.setUserData("fruta");

            anchorPane.getChildren().add(iv);
        }
    }

    private void atualizarInidicadores() {
        Fruta banana = feiraService.buscarPorTipo("Banana");
        Fruta maca = feiraService.buscarPorTipo("Maçã");
        Fruta laranja = feiraService.buscarPorTipo("Laranja");

        precoBanana.setText("R$ " + String.valueOf(banana.getValor()));
        qtdBanana.setText(String.valueOf(banana.getQuantidade()) + "x");
        precoMaca.setText("R$ " + String.valueOf(maca.getValor()));
        qtdMaca.setText(String.valueOf(maca.getQuantidade()) + "x");
        precoLaranja.setText("R$ " + String.valueOf(laranja.getValor()));
        qtdLaranja.setText(String.valueOf(laranja.getQuantidade()) + "x");
    }

    private void atualizarInidicadoresCli() {
        Map<String, Integer> sacola = feiraService.qtdFrutasSacola();

        qtdBananaCli.setText(sacola.getOrDefault("Banana", 0) + "x");
        qtdMacaCli.setText(sacola.getOrDefault("Maçã", 0)     + "x");
        qtdLaranjaCli.setText(sacola.getOrDefault("Laranja", 0) + "x");

        saldoCliente.setText("R$ " + feiraService.saldoCliente());
    }

    private void animacaoComer() {
        cliente.setVisible(false);
        clienteComendo.setVisible(true);

        PauseTransition pausa = new PauseTransition(Duration.seconds(3));

        pausa.setOnFinished(e -> {
            clienteComendo.setVisible(false);
            cliente.setVisible(true);
        });

        pausa.play();
    }

}
