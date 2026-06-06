package org.example.DAO;

import org.example.infrastructure.ConexaoBD;
import org.example.model.Cliente;
import org.example.model.Remedio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public void alterarQtdRemedio(Long remedioId, int quantidade) {
        String sql = "UPDATE cesta SET quantidade = ? WHERE remedio_id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setLong(2, remedioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar quantidade de remédios no banco: " + e);
        }
    }

    public void alterarValorPago(Long remedioId, double valor) {
        String sql = "UPDATE cesta SET valor_pago = ? WHERE remedio_id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setLong(2, remedioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar valor pago no banco: " + e);
        }
    }

    public void alterarSaldo(Long clienteId, double novoValor) {
        String sql = "UPDATE cliente_farma SET saldo = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, novoValor);
            stmt.setLong(2, clienteId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar saldo do cliente no banco: " + e);
        }
    }

    public Remedio buscarRemedio(Long remedioId) {
        String sql = "SELECT c.remedio_id, r.nome, c.valor_pago, c.quantidade FROM cesta c JOIN remedio r ON r.id = c.remedio_id WHERE c.remedio_id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, remedioId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Remedio(
                        rs.getLong("remedio_id"),
                        rs.getString("nome"),
                        rs.getDouble("valor_pago"),
                        rs.getInt("quantidade")
                );
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cliente buscarCliente() {
        String sql = "SELECT id, saldo FROM cliente_farma ORDER BY id LIMIT 1";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                        rs.getLong("id"),
                        rs.getDouble("saldo")
                );
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void comprarNovoRemedio(Long clienteId, Long remedioId, int quantidade, double valor) {
        String sql = "INSERT INTO cesta (remedio_id, cliente_id, quantidade, valor_pago) VALUES (?,?,?,?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, remedioId);
            stmt.setLong(2, clienteId);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, valor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro no banco ao adicionar novo remédio: " + e);
        }
    }

    public List<Remedio> remediosCesta() {
        String sql = "SELECT c.remedio_id, c.quantidade, r.nome, c.valor_pago FROM cesta c JOIN remedio r ON r.id = c.remedio_id ORDER BY c.remedio_id";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            List<Remedio> remedios = new ArrayList<>();
            while (rs.next()) {
                remedios.add(new Remedio(
                        rs.getLong("remedio_id"),
                        rs.getString("nome"),
                        rs.getDouble("valor_pago"),
                        rs.getInt("quantidade")
                ));
            }
            return remedios;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}