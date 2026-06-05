package org.example.DAO;

import org.example.infrastructure.ConexaoBD;
import org.example.model.Remedio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemedioDAO {
    public void alterarQtdRemedio(Long remedioId, int quantidade) {
        String sql = "UPDATE remedio SET quantidade = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setLong(2, remedioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar quantidade de remédios no banco: " + e);
        }
    }

    public void alterarPrecoRemedio(Long remedioId, double preco) {
        String sql = "UPDATE remedio SET preco = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, preco);
            stmt.setLong(2, remedioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar preço do remédio no banco: " + e);
        }
    }

    public Remedio buscarPorNome(String nomeRemedio) {
        String sql = "SELECT * FROM remedio WHERE nome = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeRemedio);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Remedio(
                        rs.getLong("id"),
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