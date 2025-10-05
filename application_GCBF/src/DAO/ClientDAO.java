package DAO;

import Entity.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAO {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/banque_db",
                "root",
                "heiji123");
    }

    public boolean ajouterClient(Client client) {
        String sql = "INSERT INTO clients (id, name, email, Telephone) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, client.id());
            ps.setString(2, client.name());
            ps.setString(3, client.email());
            ps.setString(4, client.Telephone());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Erreur lors de l’ajout du client : " + e.getMessage());
            return false;
        }
    }

    public boolean modifierClient(Client client) {
        String sql = "UPDATE clients SET name = ?, email = ?, Telephone = ? WHERE id = ?";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, client.name());
            ps.setString(2, client.email());
            ps.setString(3, client.Telephone());
            ps.setString(4, client.id());
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour : " + e.getMessage());
            return false;
        }
    }

    public boolean supprimerClient(String id) {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression : " + e.getMessage());
            return false;
        }
    }

    public List<Client> listerClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";

        try (Connection con = getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                clients.add(new Client(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("Telephone")));
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des clients : " + e.getMessage());
        }
        return clients;
    }

    public Optional<Client> rechercherClientParEmail(String email) {
        String sql = "SELECT * FROM client WHERE email = ?";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Client client = new Client(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("Telephone"));
                return Optional.of(client);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du client par email : " + e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Client> rechercherClientParTelephone(String telephone) {
        String sql = "SELECT * FROM client WHERE Telephone = ?";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, telephone);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Client client = new Client(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("Telephone"));
                return Optional.of(client);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du client par téléphone : " + e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Client> rechercherClientParId(String id) {
        String sql = "SELECT * FROM clients WHERE id = ?";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Client client = new Client(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("Telephone"));
                return Optional.of(client);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du client : " + e.getMessage());
        }
        return Optional.empty();
    }
}
