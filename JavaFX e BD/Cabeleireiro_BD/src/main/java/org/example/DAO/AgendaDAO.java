package org.example.DAO;

import org.example.infrastructure.ConexaoBD;
import org.example.model.Agenda;
import org.example.model.AgendaVista;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AgendaDAO {

    public void cadastrarAgendamento(Agenda agenda) {
        String sql = "INSERT INTO agenda (cliente_id, servico_id, data, horario) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, agenda.getClienteId());
            stmt.setLong(2, agenda.getServicoId());
            stmt.setDate(3, Date.valueOf(agenda.getData()));
            stmt.setTime(4, Time.valueOf(agenda.getHorario()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarAgendamento(Agenda agenda) {
        String sql = "UPDATE agenda SET cliente_id = ?, servico_id = ?, data = ?, horario = ? WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, agenda.getClienteId());
            stmt.setLong(2, agenda.getServicoId());
            stmt.setDate(3, Date.valueOf(agenda.getData()));
            stmt.setTime(4, Time.valueOf(agenda.getHorario()));
            stmt.setLong(5, agenda.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirAgendamento(Long id) {
        String sql = "DELETE FROM agenda WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existeConflito(LocalDate data, LocalTime horarioInicio, LocalTime horarioFim, Long ignorarId) {
        String sql = "SELECT COUNT(*) FROM agenda a JOIN servico s ON s.id = a.servico_id WHERE a.data = ? AND a.id != ? AND a.horario < ? AND ? < a.horario + s.duracao * INTERVAL '1 minute' ";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(data));
            stmt.setLong(2, ignorarId != null ? ignorarId : -1L);
            stmt.setTime(3, Time.valueOf(horarioFim));
            stmt.setTime(4, Time.valueOf(horarioInicio));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<AgendaVista> listarComDetalhes() {
        String sql = "SELECT a.id, a.cliente_id, a.servico_id, c.nome, s.tipo, a.data, a.horario FROM agenda a JOIN cliente_cab c ON c.id = a.cliente_id JOIN servico s ON s.id = a.servico_id ORDER BY a.data, a.horario";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            return mapearResultado(stmt.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<AgendaVista> buscarPorData(LocalDate data) {
        String sql = "SELECT a.id, a.cliente_id, a.servico_id, c.nome, s.tipo, a.data, a.horario FROM agenda a JOIN cliente_cab c ON c.id = a.cliente_id JOIN servico s ON s.id = a.servico_id WHERE a.data = ? ORDER BY a.horario ";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(data));
            return mapearResultado(stmt.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<AgendaVista> mapearResultado(ResultSet rs) throws SQLException {
        List<AgendaVista> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new AgendaVista(
                    rs.getLong("id"),
                    rs.getLong("cliente_id"),
                    rs.getLong("servico_id"),
                    rs.getString("nome"),
                    rs.getString("tipo"),
                    rs.getDate("data").toLocalDate(),
                    rs.getTime("horario").toLocalTime()
            ));
        }
        return lista;
    }
}