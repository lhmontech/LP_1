package org.example.DAO;

import org.example.infrastructure.ConexaoBD;
import org.example.model.Fruta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BarracaDAO {
    public void exporNovaFruta(Long frutaId, int quantidade){
        String sql = "INSERT INTO barraca (fruta_id, quantidade) VALUES (?,?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, frutaId);
            stmt.setInt(2, quantidade);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro no banco ao adicionar nova fruta: " + e);
        }
    }

    public void alterarQtdFruta(Long frutaId, int quantidade){
        String sql = "UPDATE barraca SET quantidade = ? WHERE fruta_id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setLong(2, frutaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar quantidade de frutas no banco: " + e);
        }
    }

    public List<Fruta> buscarFrutas(){
        String sql = "SELECT b.fruta_id, b.quantidade, f.tipo FROM barraca b JOIN fruta f ON f.id = b.fruta_id ORDER BY fruta_id";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            List<Fruta> frutas = new ArrayList<>();
            while (rs.next()) {
                frutas.add( new Fruta(
                        rs.getLong("fruta_id"),
                        rs.getString("tipo"),
                        rs.getInt("quantidade")
                ));
            }
            return frutas;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Fruta verificarFruta(Long FrutaId){
        String sql = "SELECT b.fruta_id, b.quantidade, f.tipo FROM barraca b JOIN fruta f ON f.id = b.fruta_id WHERE fruta_id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, FrutaId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Fruta(
                        rs.getLong("fruta_id"),
                        rs.getString("tipo"),
                        rs.getInt("quantidade"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
