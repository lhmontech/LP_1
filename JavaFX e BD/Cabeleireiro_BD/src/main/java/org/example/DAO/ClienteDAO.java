package org.example.DAO;

import org.example.infrastructure.ConexaoBD;
import org.example.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente_cab (nome, genero, telefone) VALUES (?, ?::genero, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, String.valueOf(cliente.getGenero()));
            stmt.setString(3, cliente.getTelefone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente_cab SET nome = ?, genero = ?::genero, telefone = ? WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, String.valueOf(cliente.getGenero()));
            stmt.setString(3, cliente.getTelefone());
            stmt.setLong(4, cliente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirCliente(Long id) {
        String sql = "DELETE FROM cliente_cab WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existeTelefone(String telefone) {
        String sql = "SELECT COUNT(*) FROM cliente_cab WHERE telefone = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, telefone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existeTelefoneOutroCliente(String telefone, Long idIgnorar) {
        String sql = "SELECT COUNT(*) FROM cliente_cab WHERE telefone = ? AND id != ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, telefone);
            stmt.setLong(2, idIgnorar);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> buscarPorNome(String nome) {
        String sql = "SELECT * FROM cliente_cab WHERE nome ILIKE ? ORDER BY nome";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            return mapearResultado(stmt.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Cliente> listarTodos() {
        String sql = "SELECT * FROM cliente_cab ORDER BY nome";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            return mapearResultado(stmt.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Cliente> mapearResultado(ResultSet rs) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            clientes.add(new Cliente(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getString("genero").charAt(0),
                    rs.getString("telefone")
            ));
        }
        return clientes;
    }
}