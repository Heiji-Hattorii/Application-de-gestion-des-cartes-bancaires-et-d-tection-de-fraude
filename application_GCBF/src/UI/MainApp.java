package UI;

import Entity.*;
import Service.ClientService;
import Service.CarteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        CarteService carteService = new CarteService();
        Scanner sc = new Scanner(System.in);
        int choixPrincipal;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Gestion des clients");
            System.out.println("2. Gestion des cartes");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choixPrincipal = sc.nextInt();
            sc.nextLine();

            switch (choixPrincipal) {
                case 1 -> menuClients(sc, clientService);
                case 2 -> menuCartes(sc, carteService);
                case 0 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide !");
            }

        } while (choixPrincipal != 0);

        sc.close();
    }

    // ================= MENU CLIENTS =================
    private static void menuClients(Scanner sc, ClientService clientService) {
        int choix;
        do {
            System.out.println("\n===== MENU CLIENT =====");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Modifier un client");
            System.out.println("3. Supprimer un client");
            System.out.println("4. Afficher tous les clients");
            System.out.println("5. Rechercher un client par ID");
            System.out.println("6. Rechercher un client par email");
            System.out.println("7. Rechercher un client par téléphone");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1 -> {
                    System.out.print("ID : ");
                    String id = sc.nextLine();
                    System.out.print("Nom : ");
                    String nom = sc.nextLine();
                    System.out.print("Email : ");
                    String email = sc.nextLine();
                    System.out.print("Téléphone : ");
                    String tel = sc.nextLine();

                    Client client = new Client(id, nom, email, tel);
                    if (clientService.ajouterClient(client))
                        System.out.println("Client ajouté avec succès !");
                    else
                        System.out.println("Erreur lors de l'ajout du client.");
                }

                case 2 -> {
                    System.out.print("ID du client à modifier : ");
                    String id = sc.nextLine();
                    Optional<Client> clientOpt = clientService.rechercherClientParId(id);

                    if (clientOpt.isPresent()) {
                        System.out.print("Nouveau nom : ");
                        String nom = sc.nextLine();
                        System.out.print("Nouvel email : ");
                        String email = sc.nextLine();
                        System.out.print("Nouveau téléphone : ");
                        String tel = sc.nextLine();

                        Client client = new Client(id, nom, email, tel);
                        if (clientService.modifierClient(client))
                            System.out.println("Client modifié avec succès !");
                        else
                            System.out.println("Échec de la modification.");
                    } else {
                        System.out.println("Client introuvable !");
                    }
                }

                case 3 -> {
                    System.out.print("ID du client à supprimer : ");
                    String id = sc.nextLine();
                    if (clientService.supprimerClient(id))
                        System.out.println("Client supprimé !");
                    else
                        System.out.println("Suppression échouée !");
                }

                case 4 -> {
                    List<Client> clients = clientService.listerClients();
                    if (clients.isEmpty())
                        System.out.println("Aucun client trouvé !");
                    else
                        clients.forEach(System.out::println);
                }

                case 5 -> {
                    System.out.print("ID du client : ");
                    String id = sc.nextLine();
                    Optional<Client> clientOpt = clientService.rechercherClientParId(id);
                    clientOpt.ifPresentOrElse(System.out::println, () -> System.out.println("Client introuvable !"));
                }

                case 6 -> {
                    System.out.print("Email du client : ");
                    String email = sc.nextLine();
                    Optional<Client> clientOpt = clientService.rechercherClientParEmail(email);
                    clientOpt.ifPresentOrElse(System.out::println, () -> System.out.println("Client introuvable !"));
                }

                case 7 -> {
                    System.out.print("Téléphone du client : ");
                    String tel = sc.nextLine();
                    Optional<Client> clientOpt = clientService.rechercherClientParTelephone(tel);
                    clientOpt.ifPresentOrElse(System.out::println, () -> System.out.println("Client introuvable !"));
                }

                case 0 -> System.out.println("Retour au menu principal");
                default -> System.out.println("Choix invalide !");
            }

        } while (choix != 0);
    }

    // ================= MENU CARTES =================
    private static void menuCartes(Scanner sc, CarteService carteService) {
        int choix;
        do {
            System.out.println("\n===== MENU CARTE =====");
            System.out.println("1. Ajouter une carte");
            System.out.println("2. Modifier le statut d'une carte");
            System.out.println("3. Supprimer une carte");
            System.out.println("4. Lister toutes les cartes");
            System.out.println("5. Rechercher les cartes d'un client");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1 -> {
                    System.out.print("ID carte : ");
                    String id = sc.nextLine();
                    System.out.print("Numéro : ");
                    String numero = sc.nextLine();
                    System.out.print("Date expiration (yyyy-mm-dd) : ");
                    LocalDate dateExp = LocalDate.parse(sc.nextLine());
                    System.out.print("ID client : ");
                    String idClient = sc.nextLine();
                    System.out.print("Type (CarteDebit, CarteCredit, CartePrepayee) : ");
                    String type = sc.nextLine();

                    Carte carte = switch (type) {
                        case "CarteDebit" -> new CarteDebit(id, numero, dateExp, StatutCarte.ACTIVE, idClient, 1000);
                        case "CarteCredit" -> new CarteCredit(id, numero, dateExp, StatutCarte.ACTIVE, idClient, 5000, 1.5);
                        case "CartePrepayee" -> new CartePrepayee(id, numero, dateExp, StatutCarte.ACTIVE, idClient, 2000);
                        default -> null;
                    };

                    if (carte != null && carteService.ajouterCarte(carte))
                        System.out.println("Carte ajoutée !");
                    else
                        System.out.println("Erreur lors de l'ajout !");
                }

                case 2 -> {
                    System.out.print("ID carte : ");
                    String idCarte = sc.nextLine();
                    System.out.print("Nouveau statut (ACTIVE, SUSPENDUE, BLOQUEE) : ");
                    StatutCarte statut = StatutCarte.valueOf(sc.nextLine());
                    if (carteService.modifierStatutCarte(idCarte, statut))
                        System.out.println("Statut modifié !");
                    else
                        System.out.println("Erreur lors de la modification !");
                }

                case 3 -> {
                    System.out.print("ID carte : ");
                    String idCarte = sc.nextLine();
                    if (carteService.supprimerCarte(idCarte))
                        System.out.println("Carte supprimée !");
                    else
                        System.out.println("Erreur lors de la suppression !");
                }

                case 4 -> {
                    List<Carte> cartes = carteService.listerCartes();
                    cartes.forEach(System.out::println);
                }

                case 5 -> {
                    System.out.print("ID client : ");
                    String idClient = sc.nextLine();
                    List<Carte> cartes = carteService.rechercherCartesParClient(idClient);
                    cartes.forEach(System.out::println);
                }

                case 0 -> System.out.println("Retour au menu principal");
                default -> System.out.println("Choix invalide !");
            }

        } while (choix != 0);
    }
}
