package restaurantmenu;

import restaurantmenu.services.CommandeService;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private final PlatRepository platRepository = new PlatRepository();
    private final BoissonRepository boissonRepository = new BoissonRepository();
    private final DessertRepository dessertRepository = new DessertRepository();
    private final Scanner scanner = new Scanner(System.in);

    public void ajouterPlat(Plat plat) {
        platRepository.ajouter(plat);
    }

    public void ajouterBoisson(Boisson boisson) {
        boissonRepository.ajouter(boisson);
    }

    public void ajouterDessert(Dessert dessert) {
        dessertRepository.ajouter(dessert);
    }

    public void choisirItems() {
        while (true) {
            afficherMenuPrincipal();
            int choix = lireChoix(3);

            switch (choix) {
                case 1 -> creerNouvelleCommande();
                case 2 -> gererCommandes();
                case 3 -> {
                    System.out.println("Merci d'avoir visité notre restaurant !");
                    return;
                }
            }
        }
    }

    private void creerNouvelleCommande() {
        Commande commande = CommandeService.creerCommande();
        System.out.println("\nNOUVELLE COMMANDE #" + commande.getId());

        while (true) {
            System.out.println("\nQue souhaitez-vous faire ?");
            System.out.println("1. Ajouter des articles");
            System.out.println("2. Finaliser la commande");
            System.out.println("3. Annuler la commande");
            System.out.print("Choix: ");

            int choix = lireChoix(3);

            switch (choix) {
                case 1 -> ajouterArticlesMenu(commande);
                case 2 -> {
                    commande.afficherDetails();
                    System.out.println("Commande finalisée !");
                    return;
                }
                case 3 -> {
                    CommandeService.supprimerCommande(commande.getId());
                    System.out.println("Commande annulée !");
                    return;
                }
            }
        }
    }

    private void ajouterArticlesMenu(Commande commande) {
        System.out.println("\nQue voulez-vous commander ? 1.Plats, 2.Boissons, 3.Desserts");
        int categorie = lireChoix(3);

        switch (categorie) {
            case 1 -> processCategory(platRepository.getAll(), "plats", commande);
            case 2 -> processCategory(boissonRepository.getAll(), "boissons", commande);
            case 3 -> processCategory(dessertRepository.getAll(), "desserts", commande);
        }
    }

    private <T extends MenuItem> void processCategory(List<T> items, String categoryName, Commande commande) {
        afficherMenu(items, categoryName);
        System.out.printf("\nSélectionnez vos %s (numéros séparés par des espaces): ", categoryName);
        String[] choix = scanner.nextLine().split(" ");

        for (String num : choix) {
            try {
                int index = Integer.parseInt(num.trim()) - 1;
                if (index >= 0 && index < items.size()) {
                    commande.ajouterItem(items.get(index));
                    System.out.println("-> " + items.get(index).getNom() + " ajouté");
                } else {
                    System.out.println("Numéro invalide: " + num);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide: " + num);
            }
        }
    }

    public void gererCommandes() {
        while (true) {
            System.out.println("\nGESTION DES COMMANDES");
            System.out.println("1. Modifier une commande");
            System.out.println("2. Supprimer une commande");
            System.out.println("3. Voir toutes les commandes");
            System.out.println("4. Retour");
            System.out.print("Choix: ");

            int choix = lireChoix(4);

            switch (choix) {
                case 1 -> modifierCommande();
                case 2 -> supprimerCommande();
                case 3 -> afficherCommandes();
                case 4 -> { return; }
            }
        }
    }

    private void modifierCommande() {
        System.out.print("Entrez l'ID de la commande à modifier: ");
        int id = Integer.parseInt(scanner.nextLine());

        Optional<Commande> optCommande = CommandeService.getCommande(id);

        if (optCommande.isEmpty()) {
            System.out.println("Commande introuvable!");
            return;
        }

        Commande commande = optCommande.get();
        System.out.println("\nMODIFICATION COMMANDE #" + id);

        while (true) {
            commande.afficherDetails();
            System.out.println("\nOptions:");
            System.out.println("1. Ajouter un article");
            System.out.println("2. Retirer un article");
            System.out.println("3. Terminer");
            System.out.print("Choix: ");

            int choix = lireChoix(3);

            switch (choix) {
                case 1 -> ajouterArticle(commande);
                case 2 -> retirerArticle(commande);
                case 3 -> { return; }
            }
        }
    }

    private void ajouterArticle(Commande commande) {
        System.out.println("\nAJOUT D'ARTICLE");
        System.out.println("1. Plats");
        System.out.println("2. Boissons");
        System.out.println("3. Desserts");
        System.out.print("Choix: ");

        int choix = lireChoix(3);

        List<? extends MenuItem> items = switch (choix) {
            case 1 -> platRepository.getAll();
            case 2 -> boissonRepository.getAll();
            case 3 -> dessertRepository.getAll();
            default -> List.of();
        };

        afficherMenu(items, "disponibles");
        System.out.print("Numéro de l'article à ajouter: ");
        int index = lireChoix(items.size()) - 1;

        commande.ajouterItem(items.get(index));
        System.out.println("Article ajouté !");
    }

    private void retirerArticle(Commande commande) {
        if (commande.getItems().isEmpty()) {
            System.out.println("La commande est vide !");
            return;
        }

        System.out.println("\nRETIRER UN ARTICLE");
        List<MenuItem> items = commande.getItems();

        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%d. %s - %.2f dh\n",
                    i + 1,
                    items.get(i).getNom(),
                    items.get(i).getPrix());
        }

        System.out.print("Numéro de l'article à retirer: ");
        int index = lireChoix(items.size()) - 1;

        if (commande.retirerItem(index)) {
            System.out.println("Article retiré !");
        } else {
            System.out.println("Erreur lors de la suppression");
        }
    }

    private void supprimerCommande() {
        System.out.print("\nSUPPRESSION - Entrez l'ID de la commande: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (CommandeService.supprimerCommande(id)) {
            System.out.println("Commande #" + id + " supprimée !");
        } else {
            System.out.println("Commande introuvable !");
        }
    }

    private void afficherCommandes() {
        System.out.println("\nLISTE DES COMMANDES");
        List<Commande> commandes = CommandeService.getToutesCommandes();

        if (commandes.isEmpty()) {
            System.out.println("Aucune commande enregistrée");
            return;
        }

        for (Commande c : commandes) {
            c.afficherDetails();
        }
    }

    private int lireChoix(int max) {
        while (true) {
            try {
                int choix = Integer.parseInt(scanner.nextLine());
                if (choix >= 1 && choix <= max) return choix;
                System.out.printf("Veuillez entrer un nombre entre %d et %d: ", 1, max);
            } catch (NumberFormatException e) {
                System.out.print("Entrée invalide. Veuillez réessayer: ");
            }
        }
    }

    private <T extends MenuItem> void afficherMenu(List<T> items, String titre) {
        System.out.printf("Menu des %s:\n", titre);
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            System.out.printf("%d. %s - %.2f dh\n", i + 1, item.getNom(), item.getPrix());
        }
    }

    private void afficherMenuPrincipal() {
        System.out.println("\nMENU PRINCIPAL");
        System.out.println("1. Créer une commande");
        System.out.println("2. Gérer les commandes");
        System.out.println("3. Quitter");
        System.out.print("Votre choix: ");
    }
}