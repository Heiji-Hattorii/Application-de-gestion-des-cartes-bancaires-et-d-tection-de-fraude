package DAO;

import Entity.OperationCarte;
import Entity.TypeOperation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OperationCarteDAO {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/banque_db",
                "root",
                "heiji123"
        );
    }

    public boolean ajouterOperation(OperationCarte op) {
        String sql = "INSERT INTO operationcarte (id, date, montant, type, lieu, idCarte) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, op.id());
            ps.setTimestamp(2, Timestamp.valueOf(op.date()));
            ps.setDouble(3, op.montant());
            ps.setString(4, op.type().name());
            ps.setString(5, op.lieu());
            ps.setString(6, op.idCarte());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'opération : " + e.getMessage());
            return false;
        }
    }

    public Optional<OperationCarte> rechercherParId(String id) {
        String sql = "SELECT * FROM operationcarte WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new OperationCarte(
                        rs.getString("id"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getDouble("montant"),
                        TypeOperation.valueOf(rs.getString("type")),
                        rs.getString("lieu"),
                        rs.getString("idCarte")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de l'opération : " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<OperationCarte> listerOperations() {
        List<OperationCarte> liste = new ArrayList<>();
        String sql = "SELECT * FROM operationcarte";
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                liste.add(new OperationCarte(
                        rs.getString("id"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getDouble("montant"),
                        TypeOperation.valueOf(rs.getString("type")),
                        rs.getString("lieu"),
                        rs.getString("idCarte")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des opérations : " + e.getMessage());
        }
        return liste;
    }

    public boolean supprimerOperation(String id) {
        String sql = "DELETE FROM operationcarte WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'opération : " + e.getMessage());
            return false;
        }
    }
}
