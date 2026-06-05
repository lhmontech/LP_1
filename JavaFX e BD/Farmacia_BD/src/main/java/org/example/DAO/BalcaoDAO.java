package org.example.DAO;

import org.example.infrastructure.ConexaoBD;
import org.example.model.Remedio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BalcaoDAO {
    public void exporNovoRemedio(Long remedioId, int quantidade) {
        String sql = "INSERT INTO balcao (remedio_id, quantidade) VALUES (?,?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, remedioId);
            stmt.setInt(2, quantidade);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro no banco ao adicionar novo remédio: " + e);
        }
    }

    public void alterarQtdRemedio(Long remedioId, int quantidade) {
        String sql = "UPDATE balcao SET quantidade = ? WHERE remedio_id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setLong(2, remedioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar quantidade de remédios no banco: " + e);
        }
    }

    public List<Remedio> buscarRemedios() {
        String sql = "SELECT b.remedio_id, b.quantidade, r.nome, r.preco FROM balcao b JOIN remedio r ON r.id = b.remedio_id ORDER BY b.remedio_id";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            List<Remedio> remedios = new ArrayList<>();
            while (rs.next()) {
                remedios.add(new Remedio(
                        rs.getLong("remedio_id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                ));
            }
            return remedios;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Remedio verificarRemedio(Long remedioId) {
        String sql = "SELECT b.remedio_id, b.quantidade, r.nome, r.preco FROM balcao b JOIN remedio r ON r.id = b.remedio_id WHERE b.remedio_id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, remedioId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Remedio(
                        rs.getLong("remedio_id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                );
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}