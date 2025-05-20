package Model.DAO;

import Model.ConnexioBD;
import Model.Constructors.Rarity;
import Model.Interfaces.CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RarityDAO implements CRUD<Rarity> {
    public void inserir(Rarity r) {
        String sql = "INSERT INTO rarities (id_rarity, nom) VALUES (?, ?)" +
                     " ON DUPLICATE KEY UPDATE nom = VALUES(nom)";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, r.getId());
            stmt.setString(2, r.getNom());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Rarity> llistar() {
        List<Rarity> llista = new ArrayList<>();
        String sql = "SELECT * FROM rarities";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                llista.add(new Rarity(rs.getInt("id_rarity"), rs.getString("nom")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return llista;
    }

    public Rarity obtenirPerId(int id) {
        String sql = "SELECT * FROM rarities WHERE id_rarity = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Rarity(rs.getInt("id_rarity"), rs.getString("nom"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void actualitzar(Rarity r) {
        String sql = "UPDATE rarities SET nom = ? WHERE id_rarity = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, r.getNom());
            stmt.setInt(2, r.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM rarities WHERE id_rarity = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}