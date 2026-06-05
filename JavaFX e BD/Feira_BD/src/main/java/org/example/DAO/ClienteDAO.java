package org.example.DAO;

import org.example.infrastructure.ConexaoBD;
import org.example.model.Cliente;
import org.example.model.Fruta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public void alterarQtdFruta(Long frutaId, int quantidade){
        String sql = "UPDATE sacola SET quantidade = ? WHERE fruta_id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setLong(2, frutaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar quantidade de frutas no banco: " + e);
        }
    }

    public void alterarValorPago(Long frutaId, double valor){
        String sql = "UPDATE sacola SET valor_pago = ? WHERE fruta_id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setLong(2, frutaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar valor pago no banco: " + e);
        }
    }

    public void alterarSaldo(Long clienteId, double novoValor){
        String sql = "UPDATE cliente SET saldo = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, novoValor);
            stmt.setLong(2, clienteId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar saldo do cliente no banco: " + e);
        }
    }

    public Fruta buscarFruta(Long FrutaId){
        String sql = "SELECT s.fruta_id, f.tipo, s.valor_pago, s.quantidade FROM sacola s JOIN fruta f ON f.id = s.fruta_id WHERE s.fruta_id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, FrutaId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Fruta(
                        rs.getLong("fruta_id"),
                        rs.getString("tipo"),
                        rs.getDouble("valor_pago"),
                        rs.getInt("quantidade"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cliente buscarCliente(){
        String sql = "SELECT id, saldo FROM cliente ORDER BY id LIMIT 1";

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

    public void comprarNovaFruta(Long clienteId, Long frutaId, int quantidade, double valor){
        String sql = "INSERT INTO sacola (fruta_id, cliente_id, quantidade, valor_pago) VALUES (?,?,?,?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, frutaId);
            stmt.setLong(2, clienteId);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, valor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro no banco ao adicionar nova fruta: " + e);
        }
    }

    public List<Fruta> frutasSacola(){
        String sql = "SELECT s.fruta_id, s.quantidade, f.tipo, s.valor_pago FROM sacola s JOIN fruta f ON f.id = s.fruta_id ORDER BY s.fruta_id";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            List<Fruta> frutas = new ArrayList<>();
            while (rs.next()) {
                frutas.add( new Fruta(
                        rs.getLong("fruta_id"),
                        rs.getString("tipo"),
                        rs.getDouble("valor_pago"),
                        rs.getInt("quantidade")
                ));
            }
            return frutas;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
