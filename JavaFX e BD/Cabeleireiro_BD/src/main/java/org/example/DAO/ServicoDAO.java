package org.example.DAO;

import org.example.infrastructure.ConexaoBD;
import org.example.model.Servico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    public void cadastrarServico(Servico servico) {
        String sql = "INSERT INTO servico (tipo, preco, duracao) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servico.getTipo());
            stmt.setDouble(2, servico.getPreco());
            stmt.setInt(3, servico.getDuracao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarServico(Servico servico) {
        String sql = "UPDATE servico SET tipo = ?, preco = ?, duracao = ? WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servico.getTipo());
            stmt.setDouble(2, servico.getPreco());
            stmt.setInt(3, servico.getDuracao());
            stmt.setLong(4, servico.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirServico(Long id) {
        String sql = "DELETE FROM servico WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existeTipo(String tipo) {
        String sql = "SELECT COUNT(*) FROM servico WHERE LOWER(tipo) = LOWER(?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existeTipoOutroServico(String tipo, Long idIgnorar) {
        String sql = "SELECT COUNT(*) FROM servico WHERE LOWER(tipo) = LOWER(?) AND id != ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            stmt.setLong(2, idIgnorar);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Servico buscarPorId(Long id) {
        String sql = "SELECT * FROM servico WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Servico(
                        rs.getLong("id"),
                        rs.getString("tipo"),
                        rs.getDouble("preco"),
                        rs.getInt("duracao")
                );
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Servico> buscarPorTipo(String tipo) {
        String sql = "SELECT * FROM servico WHERE tipo ILIKE ? ORDER BY tipo";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + tipo + "%");
            return mapearResultado(stmt.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Servico> listarTodos() {
        String sql = "SELECT * FROM servico ORDER BY tipo";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            return mapearResultado(stmt.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Servico> mapearResultado(ResultSet rs) throws SQLException {
        List<Servico> servicos = new ArrayList<>();
        while (rs.next()) {
            servicos.add(new Servico(
                    rs.getLong("id"),
                    rs.getString("tipo"),
                    rs.getDouble("preco"),
                    rs.getInt("duracao")
            ));
        }
        return servicos;
    }
}