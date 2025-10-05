package DAO;

import Entity.AlerteFraude;
import Entity.NiveauAlerte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlerteDAO {

    // 🔹 Méthode de connexion centralisée comme dans ClientDAO
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/banque_db",
                "root",
                "heiji123"
        );
    }

    // 🔹 Ajouter une alerte
    public boolean ajouterAlerte(AlerteFraude alerte) {
        String sql = "INSERT INTO alertefraude (id, description, niveau, idCarte) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, alerte.id());
            ps.setString(2, alerte.description());
            ps.setString(3, alerte.niveau().name());
            ps.setString(4, alerte.idCarte());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Erreur lors de l’ajout de l’alerte : " + e.getMessage());
            return false;
        }
    }

    // 🔹 Rechercher une alerte par ID
    public Optional<AlerteFraude> rechercherParId(String id) {
        String sql = "SELECT * FROM alertefraude WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                AlerteFraude alerte = new AlerteFraude(
                        rs.getString("id"),
                        rs.getString("description"),
                        NiveauAlerte.valueOf(rs.getString("niveau")),
                        rs.getString("idCarte")
                );
                return Optional.of(alerte);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de l’alerte : " + e.getMessage());
        }
        return Optional.empty();
    }

    // 🔹 Lister toutes les alertes
    public List<AlerteFraude> listerAlertes() {
        List<AlerteFraude> alertes = new ArrayList<>();
        String sql = "SELECT * FROM alertefraude";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                alertes.add(new AlerteFraude(
                        rs.getString("id"),
                        rs.getString("description"),
                        NiveauAlerte.valueOf(rs.getString("niveau")),
                        rs.getString("idCarte")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors du listing des alertes : " + e.getMessage());
        }
        return alertes;
    }

    // 🔹 Supprimer une alerte
    public boolean supprimerAlerte(String id) {
        String sql = "DELETE FROM alertefraude WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression : " + e.getMessage());
            return false;
        }
    }
}
