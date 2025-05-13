package Model.DAO;

import Model.ConnexioBD;
import Model.Constructors.Class;
import Model.Interfaces.CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements CRUD<Class> {
    public void inserir(Class c) {
        String sql = "INSERT INTO classes (id_class, nom) VALUES (?, ?)";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, c.getId());
            stmt.setString(2, c.getNom());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Class> llistar() {
        List<Class> llista = new ArrayList<>();
        String sql = "SELECT * FROM classes";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                llista.add(new Class(rs.getInt("id_class"), rs.getString("nom")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return llista;
    }

    public Class obtenirPerId(int id) {
        String sql = "SELECT * FROM classes WHERE id_class = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Class(rs.getInt("id_class"), rs.getString("nom"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void actualitzar(Class c) {
        String sql = "UPDATE classes SET nom = ? WHERE id_class = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNom());
            stmt.setInt(2, c.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM classes WHERE id_class = ?";

        try (Connection conn = ConnexioBD.getConnexio();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
