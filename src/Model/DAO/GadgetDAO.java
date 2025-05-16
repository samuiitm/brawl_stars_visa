package Model.DAO;

import Model.ConnexioBD;
import Model.Constructors.Gadget;
import Model.Interfaces.CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GadgetDAO implements CRUD<Gadget> {
    public void inserir(Gadget g) {
        String sql = "INSERT INTO gadgets (id_gadget, nom, descripcio, id_brawler) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, g.getId());
            stmt.setString(2, g.getNom());
            stmt.setString(3, g.getDescripcio());
            stmt.setInt(4, g.getIdBrawler());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Gadget> llistar() {
        List<Gadget> llista = new ArrayList<>();
        String sql = "SELECT * FROM gadgets";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                llista.add(new Gadget(rs.getInt("id_gadget"), rs.getString("nom"),
                        rs.getString("descripcio"), rs.getInt("id_brawler")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return llista;
    }

    public Gadget obtenirPerId(int id) {
        String sql = "SELECT * FROM gadgets WHERE id_gadget = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Gadget(rs.getInt("id_gadget"), rs.getString("nom"),
                            rs.getString("descripcio"), rs.getInt("id_brawler"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void actualitzar(Gadget g) {
        String sql = "UPDATE gadgets SET nom = ?, descripcio = ?, id_brawler = ? WHERE id_gadget = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, g.getNom());
            stmt.setString(2, g.getDescripcio());
            stmt.setInt(3, g.getIdBrawler());
            stmt.setInt(4, g.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM gadgets WHERE id_gadget = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Gadget> llistarPerBrawler(int idBrawler) {
        List<Gadget> llista = new ArrayList<>();
        String sql = "SELECT * FROM gadgets WHERE id_brawler = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idBrawler);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    llista.add(new Gadget(rs.getInt("id_gadget"), rs.getString("nom"),
                            rs.getString("descripcio"), rs.getInt("id_brawler")));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return llista;
    }

}
