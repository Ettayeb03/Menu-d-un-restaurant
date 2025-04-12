package restaurantmenu;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final PlatRepository platRepository = new PlatRepository();
    private final BoissonRepository boissonRepository = new BoissonRepository();
    private final DessertRepository dessertRepository = new DessertRepository();

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
        Scanner scanner = new Scanner(System.in);
        Commande commande = new Commande();

        while (true) {
            afficherMenuPrincipal();
            int choix = lireChoix(scanner, 1, 2);

            if (choix == 1) {
                traiterCommande(scanner, commande);
            } else {
                System.out.println("Merci d'avoir visité notre restaurant !");
                return;
            }
        }
    }

    private void traiterCommande(Scanner scanner, Commande commande) {
        System.out.println("Que voulez-vous commander ? 1.Plats, 2.Boissons, 3.Desserts");
        int categorie = lireChoix(scanner, 1, 3);

        switch (categorie) {
            case 1 -> processCategory(scanner, platRepository.getAll(), "plats", commande);
            case 2 -> processCategory(scanner, boissonRepository.getAll(), "boissons", commande);
            case 3 -> processCategory(scanner, dessertRepository.getAll(), "desserts", commande);
        }

        commande.afficherDetails();
    }

    private <T extends MenuItem> void processCategory(Scanner scanner, List<T> items, String categoryName, Commande commande) {
        afficherMenu(items, categoryName);
        System.out.printf("\nSélectionnez vos %s (numéros séparés par des espaces): ", categoryName);
        String[] choix = scanner.nextLine().split(" ");

        for (String num : choix) {
            try {
                int index = Integer.parseInt(num.trim()) - 1;
                if (index >= 0 && index < items.size()) {
                    commande.ajouterItem(items.get(index));
                } else {
                    System.out.println("Numéro invalide: " + num);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide: " + num);
            }
        }
    }

    private int lireChoix(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int choix = Integer.parseInt(scanner.nextLine());
                if (choix >= min && choix <= max) return choix;
                System.out.printf("Veuillez entrer un nombre entre %d et %d: ", min, max);
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
        System.out.println("\nQue souhaitez-vous ?");
        System.out.println("1. Commander");
        System.out.println("2. Quitter");
        System.out.print("Votre choix: ");
    }
}