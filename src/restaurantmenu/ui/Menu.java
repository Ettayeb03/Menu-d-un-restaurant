package restaurantmenu.ui;

import restaurantmenu.core.interfaces.MenuItem;
import restaurantmenu.core.model.*;
import restaurantmenu.data.repositories.*;
import restaurantmenu.data.services.CommandeService;
import java.util.*;

public class Menu {
    private final PlatRepository platRepo;
    private final BoissonRepository boissonRepo;
    private final DessertRepository dessertRepo;
    private final PackRepository packRepo;
    private final CommandeService commandeService;
    private final Scanner scanner;

    public Menu(PlatRepository platRepo,
                BoissonRepository boissonRepo,
                DessertRepository dessertRepo,
                PackRepository packRepo) {
        this.platRepo = platRepo;
        this.boissonRepo = boissonRepo;
        this.dessertRepo = dessertRepo;
        this.packRepo = packRepo;
        this.commandeService = new CommandeService();
        this.scanner = new Scanner(System.in);
    }

    public void choisirItems() {
        while (true) {
            afficherMenuPrincipal();
            int choix = lireChoix(1, 4);

            switch (choix) {
                case 1 -> creerNouvelleCommande();
                case 2 -> modifierCommande();
                case 3 -> afficherCommandes();
                case 4 -> {
                    System.out.println("Merci pour votre visite !");
                    return;
                }
            }
        }
    }

    private void creerNouvelleCommande() {
        Commande commande = commandeService.creerCommande();
        System.out.println("\n=== NOUVELLE COMMANDE #" + commande.getId() + " ===");

        while (true) {
            System.out.println("\nOptions :");
            System.out.println("1. Ajouter des articles");
            System.out.println("2. Finaliser la commande");
            System.out.println("3. Annuler");
            System.out.print("Votre choix : ");

            int choix = lireChoix(1, 3);

            switch (choix) {
                case 1 -> ajouterArticles(commande);
                case 2 -> finaliserCommande(commande);
                case 3 -> {
                    commandeService.supprimerCommande(commande.getId());
                    System.out.println("Commande annulée !");
                    return;
                }
            }
        }
    }

    private void ajouterArticles(Commande commande) {
        System.out.println("\nCatégories disponibles :");
        System.out.println("1. Plats");
        System.out.println("2. Boissons");
        System.out.println("3. Desserts");
        System.out.println("4. Packs spéciaux");
        System.out.print("Votre choix : ");

        int categorie = lireChoix(1, 4);

        switch (categorie) {
            case 1 -> afficherEtAjouter(platRepo.getAll(), "plats", commande);
            case 2 -> afficherEtAjouter(boissonRepo.getAll(), "boissons", commande);
            case 3 -> afficherEtAjouter(dessertRepo.getAll(), "desserts", commande);
            case 4 -> afficherEtAjouter(packRepo.getAll(), "packs", commande);
        }
    }

    private void afficherEtAjouter(List<? extends MenuItem> items, String categorie, Commande commande) {
        System.out.println("\n=== " + categorie.toUpperCase() + " ===");
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            System.out.printf("%d. %s - %.2f DH\n", i + 1, item.getNom(), item.getPrix());
        }

        System.out.print("\nSélection (numéro) : ");
        int choix = lireChoix(1, items.size()) - 1;
        commande.ajouterItem(items.get(choix));
        System.out.println(items.get(choix).getNom() + " ajouté à la commande !");
    }

    private void finaliserCommande(Commande commande) {
        commande.afficherDetails();
        System.out.print("\nConfirmer la commande (O/N) ? ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("O")) {
            commandeService.enregistrerCommande(commande);
            System.out.println("Commande confirmée !");
        } else {
            commandeService.supprimerCommande(commande.getId());
            System.out.println("Commande annulée");
        }
    }

    private void modifierCommande() {
        List<Commande> commandes = commandeService.getToutesCommandes();
        if (commandes.isEmpty()) {
            System.out.println("\nAucune commande à modifier");
            return;
        }

        System.out.println("\nCommandes disponibles :");
        commandes.forEach(c -> System.out.println("#" + c.getId()));

        System.out.print("\nID de la commande à modifier : ");
        int id = Integer.parseInt(scanner.nextLine());

        Optional<Commande> commandeOpt = commandeService.getCommande(id);
        if (commandeOpt.isEmpty()) {
            System.out.println("Commande introuvable !");
            return;
        }

        Commande commande = commandeOpt.get();
        System.out.println("\nModification de la commande #" + id);

        while (true) {
            commande.afficherDetails();
            System.out.println("\nOptions :");
            System.out.println("1. Ajouter un article");
            System.out.println("2. Retirer un article");
            System.out.println("3. Terminer");
            System.out.print("Votre choix : ");

            int choix = lireChoix(1, 3);

            switch (choix) {
                case 1 -> ajouterArticles(commande);
                case 2 -> retirerArticle(commande);
                case 3 -> { return; }
            }
        }
    }

    private void retirerArticle(Commande commande) {
        List<MenuItem> items = commande.getItems();
        if (items.isEmpty()) {
            System.out.println("\nLa commande est vide !");
            return;
        }

        System.out.println("\nArticles dans la commande :");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, items.get(i).getNom());
        }

        System.out.print("\nNuméro de l'article à retirer : ");
        int index = lireChoix(1, items.size()) - 1;
        if (commande.retirerItem(index)) {
            System.out.println("Article retiré !");
        }
    }

    private void afficherCommandes() {
        List<Commande> commandes = commandeService.getToutesCommandes();
        if (commandes.isEmpty()) {
            System.out.println("\nAucune commande enregistrée");
            return;
        }

        System.out.println("\n=== COMMANDES ENREGISTRÉES ===");
        commandes.forEach(Commande::afficherDetails);
    }

    private void afficherMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Nouvelle commande");
        System.out.println("2. Modifier une commande");
        System.out.println("3. Voir toutes les commandes");
        System.out.println("4. Quitter");
        System.out.print("Votre choix : ");
    }

    private int lireChoix(int min, int max) {
        while (true) {
            try {
                int choix = scanner.nextInt();
                scanner.nextLine(); // Nettoyer le buffer
                if (choix >= min && choix <= max) {
                    return choix;
                }
                System.out.printf("Choix invalide. Entrez un nombre entre %d et %d : ", min, max);
            } catch (InputMismatchException e) {
                System.out.print("Entrée invalide. Veuillez entrer un nombre : ");
                scanner.nextLine(); // Nettoyer le buffer
            }
        }
    }
}