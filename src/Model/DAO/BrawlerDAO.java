package Model.DAO;

import Model.ConnexioBD;
import Model.Constructors.Brawler;
import Model.Interfaces.CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrawlerDAO implements CRUD<Brawler> {
    public void inserir(Brawler b) {
        String sql = "INSERT INTO brawlers (id_brawler, nom, descripcio, id_rarity, id_class) VALUES (?, ?, ?, ?, ?)" +
                     " ON DUPLICATE KEY UPDATE nom = VALUES(nom), descripcio = VALUES(descripcio), id_rarity = VALUES(id_rarity), id_class = VALUES(id_class)";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, b.getId());
            stmt.setString(2, b.getNom());
            stmt.setString(3, b.getDescripcio());
            stmt.setInt(4, b.getIdRarity());
            stmt.setInt(5, b.getIdClasse());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Brawler> llistar() {
        List<Brawler> llista = new ArrayList<>();
        String sql = "SELECT * FROM brawlers";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                llista.add(new Brawler(rs.getInt("id_brawler"), rs.getString("nom"),
                        rs.getString("descripcio"), rs.getInt("id_rarity"), rs.getInt("id_class")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return llista;
    }

    public Brawler obtenirPerId(int id) {
        String sql = "SELECT * FROM brawlers WHERE id_brawler = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Brawler(rs.getInt("id_brawler"), rs.getString("nom"),
                            rs.getString("descripcio"), rs.getInt("id_rarity"), rs.getInt("id_class"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void actualitzar(Brawler b) {
        String sql = "UPDATE brawlers SET nom = ?, descripcio = ?, id_rarity = ?, id_class = ? WHERE id_brawler = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, b.getNom());
            stmt.setString(2, b.getDescripcio());
            stmt.setInt(3, b.getIdRarity());
            stmt.setInt(4, b.getIdClasse());
            stmt.setInt(5, b.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM brawlers WHERE id_brawler = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
