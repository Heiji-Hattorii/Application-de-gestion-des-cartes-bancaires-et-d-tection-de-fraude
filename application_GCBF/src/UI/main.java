package UI;

import Entity.Client;
import Service.ClientService;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        Scanner sc = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n===== MENU CLIENT =====");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Modifier un client");
            System.out.println("3. Supprimer un client");
            System.out.println("4. Afficher tous les clients");
            System.out.println("5. Rechercher un client par ID");
            System.out.println("0. Quitter");
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
                    System.out.print("ID du client à rechercher : ");
                    String id = sc.nextLine();
                    Optional<Client> clientOpt = clientService.rechercherClientParId(id);
                    clientOpt.ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Client introuvable !")
                    );
                }

                case 0 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);

        sc.close();
    }
}
