package DAO;

import Entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarteDAO {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/banque_db",
                "root",
                "heiji123"
        );
    }

    public boolean ajouterCarte(Carte carte) {
        String sql = "INSERT INTO carte (id, numero, dateExpiration, statut, typeCarte, idClient) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, carte.id());
            ps.setString(2, carte.numero());
            ps.setDate(3, Date.valueOf(carte.dateExpiration()));
            ps.setString(4, carte.statut().name());
            ps.setString(5, carte.getClass().getSimpleName());
            ps.setString(6, carte.idClient());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Erreur lors de l’ajout de la carte : " + e.getMessage());
            return false;
        }
    }

    public boolean modifierStatutCarte(String idCarte, StatutCarte statut) {
        String sql = "UPDATE carte SET statut = ? WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, statut.name());
            ps.setString(2, idCarte);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du statut : " + e.getMessage());
            return false;
        }
    }

    public boolean supprimerCarte(String idCarte) {
        String sql = "DELETE FROM carte WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idCarte);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la carte : " + e.getMessage());
            return false;
        }
    }

    public List<Carte> listerCartes() {
        List<Carte> cartes = new ArrayList<>();
        String sql = "SELECT * FROM carte";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                cartes.add(resultSetToCarte(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des cartes : " + e.getMessage());
        }
        return cartes;
    }

    public List<Carte> rechercherParClient(String idClient) {
        List<Carte> cartes = new ArrayList<>();
        String sql = "SELECT * FROM carte WHERE idClient = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idClient);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cartes.add(resultSetToCarte(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des cartes : " + e.getMessage());
        }
        return cartes;
    }

    private Carte resultSetToCarte(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String numero = rs.getString("numero");
        LocalDate dateExp = rs.getDate("dateExpiration").toLocalDate();
        StatutCarte statut = StatutCarte.valueOf(rs.getString("statut"));
        String type = rs.getString("typeCarte");
        String idClient = rs.getString("idClient");

        return switch (type) {
            case "CarteDebit" -> new CarteDebit(id, numero, dateExp, statut, idClient, 0);
            case "CarteCredit" -> new CarteCredit(id, numero, dateExp, statut, idClient, 0, 0);
            case "CartePrepayee" -> new CartePrepayee(id, numero, dateExp, statut, idClient, 0);
            default -> throw new IllegalStateException("Type de carte inconnu: " + type);
        };
    }
}
