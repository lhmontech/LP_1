package org.example.DAO;

import org.example.infrastructure.ConexaoBD;
import org.example.model.Fruta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FrutaDAO {
    public void alterarQtdFruta(Long frutaId, int quantidade){
        String sql = "UPDATE fruta SET quantidade = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setLong(2, frutaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar quantidade de frutas no banco: " + e);
        }
    }

    public void alterarPrecoFruta(Long frutaId, double preco){
        String sql = "UPDATE fruta SET preco = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, preco);
            stmt.setLong(2, frutaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar valor da fruta no banco: " + e);
        }
    }

}
