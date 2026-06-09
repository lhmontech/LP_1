package org.example.service;

import org.example.DAO.AgendaDAO;
import org.example.DAO.ClienteDAO;
import org.example.DAO.ServicoDAO;
import org.example.model.Agenda;
import org.example.model.AgendaVista;
import org.example.model.Cliente;
import org.example.model.Servico;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CabeleireiroService {
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final ServicoDAO servicoDAO = new ServicoDAO();
    private final AgendaDAO agendaDAO = new AgendaDAO();

    public String cadastrarCliente(Cliente cliente) {
        if (clienteDAO.existeTelefone(cliente.getTelefone())) {
            return "Já existe um cliente cadastrado com esse telefone.";
        }
        clienteDAO.cadastrarCliente(cliente);
        return null;
    }

    public String atualizarCliente(Cliente cliente) {
        if (clienteDAO.existeTelefoneOutroCliente(cliente.getTelefone(), cliente.getId())) {
            return "Já existe outro cliente cadastrado com esse telefone.";
        }
        clienteDAO.atualizarCliente(cliente);
        return null;
    }

    public void excluirCliente(Long id) {
        clienteDAO.excluirCliente(id);
    }

    public List<Cliente> buscarClientes(String nome) {
        if (nome == null || nome.isBlank()) return clienteDAO.listarTodos();
        return clienteDAO.buscarPorNome(nome);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarTodos();
    }

    public String cadastrarServico(Servico servico) {
        if (servicoDAO.existeTipo(servico.getTipo())) {
            return "Já existe um serviço com esse tipo cadastrado.";
        }
        servicoDAO.cadastrarServico(servico);
        return null;
    }

    public String atualizarServico(Servico servico) {
        if (servicoDAO.existeTipoOutroServico(servico.getTipo(), servico.getId())) {
            return "Já existe outro serviço com esse tipo cadastrado.";
        }
        servicoDAO.atualizarServico(servico);
        return null;
    }

    public void excluirServico(Long id) {
        servicoDAO.excluirServico(id);
    }

    public List<Servico> buscarServicos(String tipo) {
        if (tipo == null || tipo.isBlank()) return servicoDAO.listarTodos();
        return servicoDAO.buscarPorTipo(tipo);
    }

    public List<Servico> listarServicos() {
        return servicoDAO.listarTodos();
    }

    public String cadastrarAgendamento(Agenda agenda) {
        if (agenda.getData().isBefore(LocalDate.now())) {
            return "Não é possível agendar em datas passadas.";
        }
        Servico servico = servicoDAO.buscarPorId(agenda.getServicoId());
        LocalTime horarioFim = agenda.getHorario().plusMinutes(servico.getDuracao());
        if (agendaDAO.existeConflito(agenda.getData(), agenda.getHorario(), horarioFim, null)) {
            return "Já existe um agendamento nesse horário.";
        }
        agendaDAO.cadastrarAgendamento(agenda);
        return null;
    }

    public String atualizarAgendamento(Agenda agenda) {
        if (agenda.getData().isBefore(LocalDate.now())) {
            return "Não é possível agendar em datas passadas.";
        }
        Servico servico = servicoDAO.buscarPorId(agenda.getServicoId());
        LocalTime horarioFim = agenda.getHorario().plusMinutes(servico.getDuracao());
        if (agendaDAO.existeConflito(agenda.getData(), agenda.getHorario(), horarioFim, agenda.getId())) {
            return "Já existe um agendamento nesse horário.";
        }
        agendaDAO.atualizarAgendamento(agenda);
        return null;
    }

    public void excluirAgendamento(Long id) {
        agendaDAO.excluirAgendamento(id);
    }

    public List<AgendaVista> listarAgenda() {
        return agendaDAO.listarComDetalhes();
    }

    public List<AgendaVista> filtrarPorData(LocalDate data) {
        return agendaDAO.buscarPorData(data);
    }
}