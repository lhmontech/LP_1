package org.example.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.example.model.Agenda;
import org.example.model.AgendaVista;
import org.example.model.Cliente;
import org.example.model.Servico;
import org.example.service.CabeleireiroService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class CabeleireiroController {

    @FXML private TextField txtNomeCli;
    @FXML private TextField txtTelefone;
    @FXML private ComboBox<String> cbGenero;
    @FXML private TextField txtBuscaNome;
    @FXML private TableView<Cliente> clientesTable;
    @FXML private TableColumn<Cliente, String> clNome;
    @FXML private TableColumn<Cliente, Character> clGenero;
    @FXML private TableColumn<Cliente, String> clTelefone;
    @FXML private TableColumn<Cliente, ?> clCheckCli;

    @FXML private TextField txtTipo;
    @FXML private TextField txtPreco;
    @FXML private Spinner<Integer> spDuracao;
    @FXML private TextField txtBuscaServico;
    @FXML private TableView<Servico> servicosTable;
    @FXML private TableColumn<Servico, String> clTipo;
    @FXML private TableColumn<Servico, Double> clPreco;
    @FXML private TableColumn<Servico, Integer> clDuracao;
    @FXML private TableColumn<Servico, ?> clCheckSer;

    @FXML private ComboBox<Cliente> cbClienteAg;
    @FXML private ComboBox<Servico> cbServicoAg;
    @FXML private DatePicker datePicker;
    @FXML private TextField txtHorario;
    @FXML private DatePicker dateBusca;
    @FXML private TableView<AgendaVista> agendaTable;
    @FXML private TableColumn<AgendaVista, LocalDate> clData;
    @FXML private TableColumn<AgendaVista, LocalTime> clHorario;
    @FXML private TableColumn<AgendaVista, String> clCliente;
    @FXML private TableColumn<AgendaVista, String> clServicoAg;
    @FXML private TableColumn<AgendaVista, ?> clCheckAg;

    @FXML private Tab tabClientes;
    @FXML private Tab tabServicos;
    @FXML private Tab tabAgenda;

    private final CabeleireiroService service = new CabeleireiroService();

    private Long idEditandoCli = null;
    private Long idEditandoSer = null;
    private Long idEditandoAg = null;

    @FXML
    public void initialize() {
        cbGenero.setItems(FXCollections.observableArrayList("M", "F"));

        spDuracao.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 480, 30, 5));

        clNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        clTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        clientesTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        clTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        clPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        clDuracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));
        servicosTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        clData.setCellValueFactory(new PropertyValueFactory<>("data"));
        clHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        clCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        clServicoAg.setCellValueFactory(new PropertyValueFactory<>("tipoServico"));
        agendaTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        cbClienteAg.setConverter(new StringConverter<>() {
            @Override
            public String toString(Cliente c) { return c != null ? c.getNome() : ""; }
            @Override
            public Cliente fromString(String s) { return null; }
        });

        cbServicoAg.setConverter(new StringConverter<>() {
            @Override
            public String toString(Servico s) { return s != null ? s.getTipo() + " — R$ " + s.getPreco() : ""; }
            @Override
            public Servico fromString(String s) { return null; }
        });

        carregarTabelaClientes();
        carregarTabelaServicos();
        carregarTabelaAgenda();
        carregarComboBoxesAgenda();
    }

    // ==================== CLIENTES ====================

    @FXML
    void clicarCadastrarCli(ActionEvent event) {
        String nome = txtNomeCli.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String generoStr = cbGenero.getValue();

        if (nome.isBlank() || telefone.isBlank() || generoStr == null) {
            mostrarAviso("Preencha todos os campos.");
            return;
        }

        char genero = generoStr.charAt(0);

        if (idEditandoCli == null) {
            String erro = service.cadastrarCliente(new Cliente(null, nome, genero, telefone));
            if (erro != null) { mostrarAviso(erro); return; }
            mostrarInfo("Cliente cadastrado com sucesso!");
        } else {
            String erro = service.atualizarCliente(new Cliente(idEditandoCli, nome, genero, telefone));
            if (erro != null) { mostrarAviso(erro); return; }
            mostrarInfo("Cliente atualizado com sucesso!");
        }

        limparFormCli();
        carregarTabelaClientes();
        carregarComboBoxesAgenda();
    }

    @FXML
    void clicarBuscarCli(ActionEvent event) {
        clientesTable.setItems(FXCollections.observableArrayList(
                service.buscarClientes(txtBuscaNome.getText().trim())
        ));
    }

    @FXML
    void clicarEditarCli(ActionEvent event) {
        Cliente selecionado = clientesTable.getSelectionModel().getSelectedItem();
        if (selecionado == null) { mostrarAviso("Selecione um cliente na tabela."); return; }
        idEditandoCli = selecionado.getId();
        txtNomeCli.setText(selecionado.getNome());
        txtTelefone.setText(selecionado.getTelefone());
        cbGenero.setValue(String.valueOf(selecionado.getGenero()));
    }

    @FXML
    void clicarExcluirCli(ActionEvent event) {
        Cliente selecionado = clientesTable.getSelectionModel().getSelectedItem();
        if (selecionado == null) { mostrarAviso("Selecione um cliente na tabela."); return; }
        if (confirmar("Deseja excluir o cliente \"" + selecionado.getNome() + "\"?")) {
            service.excluirCliente(selecionado.getId());
            carregarTabelaClientes();
            carregarComboBoxesAgenda();
            carregarTabelaAgenda();
        }
    }

    // ==================== SERVIÇOS ====================

    @FXML
    void clicarCadastrarSer(ActionEvent event) {
        String tipo = txtTipo.getText().trim();
        String precoStr = txtPreco.getText().trim().replace(",", ".");

        if (tipo.isBlank() || precoStr.isBlank()) {
            mostrarAviso("Preencha todos os campos.");
            return;
        }

        double preco;
        try {
            preco = Double.parseDouble(precoStr);
        } catch (NumberFormatException e) {
            mostrarAviso("Digite um preço válido.");
            return;
        }

        if (preco < 0) { mostrarAviso("O preço deve ser maior que R$ 0,00."); return; }

        int duracao = spDuracao.getValue();

        if (idEditandoSer == null) {
            String erro = service.cadastrarServico(new Servico(null, tipo, preco, duracao));
            if (erro != null) { mostrarAviso(erro); return; }
            mostrarInfo("Serviço cadastrado com sucesso!");
        } else {
            String erro = service.atualizarServico(new Servico(idEditandoSer, tipo, preco, duracao));
            if (erro != null) { mostrarAviso(erro); return; }
            mostrarInfo("Serviço atualizado com sucesso!");
        }

        limparFormSer();
        carregarTabelaServicos();
        carregarComboBoxesAgenda();
    }

    @FXML
    void clicarBuscarSer(ActionEvent event) {
        servicosTable.setItems(FXCollections.observableArrayList(
                service.buscarServicos(txtBuscaServico.getText().trim())
        ));
    }

    @FXML
    void clicarEditarSer(ActionEvent event) {
        Servico selecionado = servicosTable.getSelectionModel().getSelectedItem();
        if (selecionado == null) { mostrarAviso("Selecione um serviço na tabela."); return; }
        idEditandoSer = selecionado.getId();
        txtTipo.setText(selecionado.getTipo());
        txtPreco.setText(String.valueOf(selecionado.getPreco()));
        spDuracao.getValueFactory().setValue(selecionado.getDuracao());
    }

    @FXML
    void clicarExcluirSer(ActionEvent event) {
        Servico selecionado = servicosTable.getSelectionModel().getSelectedItem();
        if (selecionado == null) { mostrarAviso("Selecione um serviço na tabela."); return; }
        if (confirmar("Deseja excluir o serviço \"" + selecionado.getTipo() + "\"?")) {
            service.excluirServico(selecionado.getId());
            carregarTabelaServicos();
            carregarComboBoxesAgenda();
            carregarTabelaAgenda();
        }
    }

    // ==================== AGENDA ====================

    @FXML
    void clicarAgendar(ActionEvent event) {
        Cliente clienteSel = cbClienteAg.getValue();
        Servico servicoSel = cbServicoAg.getValue();
        LocalDate data = datePicker.getValue();
        String horarioStr = txtHorario.getText().trim();

        if (clienteSel == null || servicoSel == null || data == null || horarioStr.isBlank()) {
            mostrarAviso("Preencha todos os campos.");
            return;
        }

        LocalTime horario;
        try {
            horario = LocalTime.parse(horarioStr);
        } catch (DateTimeParseException e) {
            mostrarAviso("Formato de horário inválido. Use HH:mm.");
            return;
        }

        if (idEditandoAg == null) {
            String erro = service.cadastrarAgendamento(new Agenda(null, clienteSel.getId(), servicoSel.getId(), data, horario));
            if (erro != null) { mostrarAviso(erro); return; }
            mostrarInfo("Agendamento realizado com sucesso!");
        } else {
            String erro = service.atualizarAgendamento(new Agenda(idEditandoAg, clienteSel.getId(), servicoSel.getId(), data, horario));
            if (erro != null) { mostrarAviso(erro); return; }
            mostrarInfo("Agendamento atualizado com sucesso!");
        }

        limparFormAgenda();
        carregarTabelaAgenda();
    }

    @FXML
    void clicarEditarAg(ActionEvent event) {
        AgendaVista selecionado = agendaTable.getSelectionModel().getSelectedItem();
        if (selecionado == null) { mostrarAviso("Selecione um agendamento na tabela."); return; }
        idEditandoAg = selecionado.getId();

        cbClienteAg.getItems().stream()
                .filter(c -> c.getId().equals(selecionado.getClienteId()))
                .findFirst()
                .ifPresent(cbClienteAg::setValue);

        cbServicoAg.getItems().stream()
                .filter(s -> s.getId().equals(selecionado.getServicoId()))
                .findFirst()
                .ifPresent(cbServicoAg::setValue);

        datePicker.setValue(selecionado.getData());
        txtHorario.setText(selecionado.getHorario().toString());
    }

    @FXML
    void clicarExcluirAg(ActionEvent event) {
        AgendaVista selecionado = agendaTable.getSelectionModel().getSelectedItem();
        if (selecionado == null) { mostrarAviso("Selecione um agendamento na tabela."); return; }
        if (confirmar("Deseja excluir o agendamento de \"" + selecionado.getNomeCliente() + "\"?")) {
            service.excluirAgendamento(selecionado.getId());
            carregarTabelaAgenda();
        }
    }

    @FXML
    void clicarFiltrar(ActionEvent event) {
        LocalDate data = dateBusca.getValue();
        if (data == null) { carregarTabelaAgenda(); return; }
        agendaTable.setItems(FXCollections.observableArrayList(service.filtrarPorData(data)));
    }

    @FXML void cbGenero(ActionEvent event) {}
    @FXML void txtNomeCli(ActionEvent event) {}
    @FXML void txtTelefone(ActionEvent event) {}

    // ==================== AUXILIARES ====================

    private void carregarTabelaClientes() {
        clientesTable.setItems(FXCollections.observableArrayList(service.listarClientes()));
    }

    private void carregarTabelaServicos() {
        servicosTable.setItems(FXCollections.observableArrayList(service.listarServicos()));
    }

    private void carregarTabelaAgenda() {
        agendaTable.setItems(FXCollections.observableArrayList(service.listarAgenda()));
    }

    private void carregarComboBoxesAgenda() {
        cbClienteAg.setItems(FXCollections.observableArrayList(service.listarClientes()));
        cbServicoAg.setItems(FXCollections.observableArrayList(service.listarServicos()));
    }

    private void limparFormCli() {
        txtNomeCli.clear();
        txtTelefone.clear();
        cbGenero.setValue(null);
        idEditandoCli = null;
    }

    private void limparFormSer() {
        txtTipo.clear();
        txtPreco.clear();
        spDuracao.getValueFactory().setValue(30);
        idEditandoSer = null;
    }

    private void limparFormAgenda() {
        cbClienteAg.setValue(null);
        cbServicoAg.setValue(null);
        datePicker.setValue(null);
        txtHorario.clear();
        idEditandoAg = null;
    }

    private void mostrarAviso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarInfo(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private boolean confirmar(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }
}