package restaurantmenu;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<Plat> plats;       // Liste des plats
    private List<Boisson> boissons; // Liste des boissons
    private List<Dessert> desserts; // Liste des desserts

    public Menu() {
        plats = new ArrayList<>();
        boissons = new ArrayList<>();
        desserts = new ArrayList<>();
    }

    // Ajouter un plat
    public void ajouterPlat(Plat plat) {
        plats.add(plat);
    }

    // Ajouter une boisson
    public void ajouterBoisson(Boisson boisson) {
        boissons.add(boisson);
    }

    // Ajouter un dessert
    public void ajouterDessert(Dessert dessert) {
        desserts.add(dessert);
    }

    // Afficher le menu des plats
    public void afficherPlats() {
        System.out.println("Menu des plats: ");
        for (int i = 0; i < plats.size(); i++) {
            Plat plat = plats.get(i);
            System.out.println((i + 1) + ". " + plat.getNom() + " - " + plat.getPrix() + "dh");
        }
    }

    // Afficher le menu des boissons
    public void afficherBoissons() {
        System.out.println("Menu des boissons: ");
        for (int i = 0; i < boissons.size(); i++) {
            Boisson boisson = boissons.get(i);
            System.out.println((i + 1) + ". " + boisson.getNom() + " - " + boisson.getPrix() + "dh");
        }
    }

    // Afficher le menu des desserts
    public void afficherDesserts() {
        System.out.println("Menu des desserts: ");
        for (int i = 0; i < desserts.size(); i++) {
            Dessert dessert = desserts.get(i);
            System.out.println((i + 1) + ". " + dessert.getNom() + " - " + dessert.getPrix() + "dh");
        }
    }

    // Afficher le menu principal avec l'option de quitter
    public void afficherMenuPrincipal() {
        System.out.println("Que souhaitez-vous ? ");
        System.out.println("1. Commander");
        System.out.println("2. Quitter");
    }

    // Permettre au client de choisir des plats, des boissons ou des desserts
    public void choisirItems() {
        Scanner scanner = new Scanner(System.in);
        List<MenuItem> commande = new ArrayList<>();

        while (true) {
            afficherMenuPrincipal();
            int choixMenuPrincipal = scanner.nextInt();
            scanner.nextLine();

            if (choixMenuPrincipal == 1) {
                // Demander le choix entre plats, boissons ou desserts
                System.out.println("Que voulez-vous commander ? 1.Plats, 2.Boissons, 3.Desserts");
                int categorieChoisie = scanner.nextInt();
                scanner.nextLine(); // Consommer la ligne vide

                // Afficher et choisir en fonction de la catégorie
                if (categorieChoisie == 1) {
                    afficherPlats();
                    System.out.println("\nSélectionnez vos plats (saisir les numéros séparés par des espaces): ");
                    String choixPlats = scanner.nextLine();
                    ajouterChoixPlats(choixPlats, commande);
                } else if (categorieChoisie == 2) {
                    afficherBoissons();
                    System.out.println("\nSélectionnez vos boissons (saisir les numéros séparés par des espaces): ");
                    String choixBoissons = scanner.nextLine();
                    ajouterChoixBoissons(choixBoissons, commande);
                } else if (categorieChoisie == 3) {
                    afficherDesserts();
                    System.out.println("\nSélectionnez vos desserts (saisir les numéros séparés par des espaces): ");
                    String choixDesserts = scanner.nextLine();
                    ajouterChoixDesserts(choixDesserts, commande);
                } else {
                    System.out.println("Choix invalide.");
                    continue;
                }

                // Afficher la commande finale
                afficherCommande(commande);

            } else if (choixMenuPrincipal == 2) {
                // L'utilisateur a choisi de quitter
                System.out.println("Merci d'avoir visité notre restaurant !");
                break; // Sortir de la boucle et quitter
            } else {
                System.out.println("Choix invalide. Veuillez entrer 1 pour commander ou 2 pour quitter.");
            }
        }
    }

    // Ajouter les desserts choisis à la commande
    private void ajouterChoixDesserts(String choix, List<MenuItem> commande) {
        String[] numChoix = choix.split(" ");
        for (String num : numChoix) {
            try {
                int index = Integer.parseInt(num.trim()) - 1;
                if (index >= 0 && index < desserts.size()) {
                    commande.add(desserts.get(index));
                } else {
                    System.out.println("Numéro invalide : " + num);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide : " + num);
            }
        }
    }

    // Ajouter les plats choisis à la commande
    private void ajouterChoixPlats(String choix, List<MenuItem> commande) {
        String[] numChoix = choix.split(" ");
        for (String num : numChoix) {
            try {
                int index = Integer.parseInt(num.trim()) - 1;
                if (index >= 0 && index < plats.size()) {
                    commande.add(plats.get(index));
                } else {
                    System.out.println("Numéro invalide: " + num);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide: " + num);
            }
        }
    }

    // Ajouter les boissons choisies à la commande
    private void ajouterChoixBoissons(String choix, List<MenuItem> commande) {
        String[] numChoix = choix.split(" ");
        for (String num : numChoix) {
            try {
                int index = Integer.parseInt(num.trim()) - 1;
                if (index >= 0 && index < boissons.size()) {
                    commande.add(boissons.get(index));
                } else {
                    System.out.println("Numéro invalide : " + num);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide : " + num);
            }
        }
    }

    // Afficher la commande
    private void afficherCommande(List<MenuItem> commande) {
        System.out.println("\nVous avez commandé: ");
        double total = 0;
        for (MenuItem item : commande) {
            item.afficherDetails();
            total += item.getPrix();
            System.out.println("------------------------");
        }
        System.out.println("Total : " + total + "dh");
    }
}
