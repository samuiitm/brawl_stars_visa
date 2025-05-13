package Model.DAO;

import Model.ConnexioBD;
import Model.Constructors.Starpower;
import Model.Interfaces.CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StarpowerDAO implements CRUD<Starpower> {
    public void inserir(Starpower s) {
        String sql = "INSERT INTO starpowers (id_starpower, nom, descripcio, id_brawler) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, s.getId());
            stmt.setString(2, s.getNom());
            stmt.setString(3, s.getDescripcio());
            stmt.setInt(4, s.getIdBrawler());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Starpower> llistar() {
        List<Starpower> llista = new ArrayList<>();
        String sql = "SELECT * FROM starpowers";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                llista.add(new Starpower(rs.getInt("id_starpower"), rs.getString("nom"),
                        rs.getString("descripcio"), rs.getInt("id_brawler")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return llista;
    }

    public Starpower obtenirPerId(int id) {
        String sql = "SELECT * FROM starpowers WHERE id_starpower = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Starpower(rs.getInt("id_starpower"), rs.getString("nom"),
                            rs.getString("descripcio"), rs.getInt("id_brawler"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void actualitzar(Starpower s) {
        String sql = "UPDATE starpowers SET nom = ?, descripcio = ?, id_brawler = ? WHERE id_starpower = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, s.getNom());
            stmt.setString(2, s.getDescripcio());
            stmt.setInt(3, s.getIdBrawler());
            stmt.setInt(4, s.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM starpowers WHERE id_starpower = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}