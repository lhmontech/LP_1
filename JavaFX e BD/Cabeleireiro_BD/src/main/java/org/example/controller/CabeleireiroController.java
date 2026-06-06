package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CabeleireiroController {

    @FXML
    private TableColumn<?, ?> clCheckAg;

    @FXML
    private TableColumn<?, ?> clCheckCli;

    @FXML
    private TableColumn<?, ?> clCheckSer;

    @FXML
    private TableColumn<?, ?> clCliente;

    @FXML
    private TableColumn<?, ?> clData;

    @FXML
    private TableColumn<?, ?> clDuracao;

    @FXML
    private TableColumn<?, ?> clGenero;

    @FXML
    private TableColumn<?, ?> clHorario;

    @FXML
    private TableColumn<?, ?> clNome;

    @FXML
    private TableColumn<?, ?> clPreco;

    @FXML
    private TableColumn<?, ?> clServicoAg;

    @FXML
    private TableColumn<?, ?> clTelefone;

    @FXML
    private TableColumn<?, ?> clTipo;

    @FXML
    private Button clicarEditarAg;

    @FXML
    private TableView<?> clientesTable;

    @FXML
    private DatePicker dateBusca;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<?> servicosTable;

    @FXML
    private Spinner<?> spDuracao;

    @FXML
    private Tab tabAgenda;

    @FXML
    private Tab tabClientes;

    @FXML
    private Tab tabServicos;

    @FXML
    private TextField txtBuscaNome;

    @FXML
    private TextField txtBuscaServico;

    @FXML
    private TextField txtClienteAg;

    @FXML
    private TextField txtHorario;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtServicoAg;

    @FXML
    private TextField txtTipo;

    @FXML
    void cbGenero(ActionEvent event) {

    }

    @FXML
    void clicarAgendar(ActionEvent event) {

    }

    @FXML
    void clicarBuscarCli(ActionEvent event) {

    }

    @FXML
    void clicarBuscarSer(ActionEvent event) {

    }

    @FXML
    void clicarCadastrarCli(ActionEvent event) {

    }

    @FXML
    void clicarCadastrarSer(ActionEvent event) {

    }

    @FXML
    void clicarEditarCli(ActionEvent event) {

    }

    @FXML
    void clicarExcluirAg(ActionEvent event) {

    }

    @FXML
    void clicarExcluirCli(ActionEvent event) {

    }

    @FXML
    void clicarFiltrar(ActionEvent event) {

    }

    @FXML
    void txtNomeCli(ActionEvent event) {

    }

    @FXML
    void txtTelefone(ActionEvent event) {

    }

}

