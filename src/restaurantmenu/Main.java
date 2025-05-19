package restaurantmenu;

import restaurantmenu.core.interfaces.MenuItem;
import restaurantmenu.core.model.*;
import restaurantmenu.ui.RestaurantGUI;
import restaurantmenu.data.repositories.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialisation des repositories
        PlatRepository platRepository = new PlatRepository();
        BoissonRepository boissonRepository = new BoissonRepository();
        DessertRepository dessertRepository = new DessertRepository();
        PackRepository packRepository = new PackRepository();

        // Ajout des items
        initialiserItems(platRepository, boissonRepository, dessertRepository);

        // Création des packs
        if (!initialiserPacks(platRepository, boissonRepository, dessertRepository, packRepository)) {
            JOptionPane.showMessageDialog(null,
                    "Erreur lors de l'initialisation des packs. Certains éléments sont manquants.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Lancement de l'interface
        SwingUtilities.invokeLater(() -> {
            RestaurantGUI gui = new RestaurantGUI(
                    platRepository,
                    boissonRepository,
                    dessertRepository,
                    packRepository
            );
            gui.setLocationRelativeTo(null); // Centrer la fenêtre
            gui.setVisible(true);
        });
    }

    private static void initialiserItems(PlatRepository platRepo,
                                         BoissonRepository boissonRepo,
                                         DessertRepository dessertRepo) {
        // Ajout de plats
        // Initialisation des plats avec catégories spécifiques
        platRepo.ajouter(new Plat("Pizza Margherita", 30.0,
                "Pizza classique avec sauce tomate et mozzarella", "Pizza"));
        platRepo.ajouter(new Plat("Pizza Viande Hachée", 40.0,
                "Pizza classique avec sauce tomate et viande hachée", "Pizza"));
        platRepo.ajouter(new Plat("Pizza Poulet", 40.0,
                "Pizza classique avec sauce tomate et escalope de poulet", "Pizza"));
        platRepo.ajouter(new Plat("Pizza Special", 50.0,
                "Pizza spécial de notre restaurant", "Pizza"));

        platRepo.ajouter(new Plat("Tacos Viande Hachée", 35.0,
                "Tacos avec sauce, viande hachée et frites", "Tacos"));
        platRepo.ajouter(new Plat("Tacos Poulet", 35.0,
                "Tacos avec sauce, escalope de poulet et frites", "Tacos"));
        platRepo.ajouter(new Plat("Tacos Special", 50.0,
                "Tacos spécial de notre restaurant", "Tacos"));

        platRepo.ajouter(new Plat("Spaghetti Carbonara", 30.0,
                "Spaghetti crémeux à base d'œufs et de lardons", "Pâtes"));
        platRepo.ajouter(new Plat("Burger Classique", 35.0,
                "Burger avec viande de bœuf, laitue, tomate et fromage", "Burger"));
        platRepo.ajouter(new Plat("Salade César", 40.0,
                "Salade avec laitue, poulet grillé, croûtons et sauce César", "Salade"));
        platRepo.ajouter(new Plat("Grilled Salmon", 60.0,
                "Saumon grillé avec légumes et riz", "Poisson"));

        // Ajout de boissons
        boissonRepo.ajouter(new Boisson("Coca-Cola", "Boisson gazeuse", 15));
        boissonRepo.ajouter(new Boisson("Fanta", "Boisson gazeuse", 15));
        boissonRepo.ajouter(new Boisson("Hawai", "Boisson gazeuse", 15));
        boissonRepo.ajouter(new Boisson("Sprite", "Boisson gazeuse", 15));
        boissonRepo.ajouter(new Boisson("Oulmes", "Eau gazeuse", 15));
        boissonRepo.ajouter(new Boisson("Jus d'orange", "Jus naturel d'orange", 20));
        boissonRepo.ajouter(new Boisson("Jus de fraise", "Jus naturel de fraise", 25));
        boissonRepo.ajouter(new Boisson("Jus d'ananas", "Jus naturel d'ananas", 30));
        boissonRepo.ajouter(new Boisson("Jus panaché", "Jus de fruits mixés", 35));
        boissonRepo.ajouter(new Boisson("Eau minérale", "Eau pure et fraîche", 10));
        boissonRepo.ajouter(new Boisson("Thé Marocain", "Thé vert à la menthe", 15));
        boissonRepo.ajouter(new Boisson("Café noir", "Café noir corsé", 20));
        boissonRepo.ajouter(new Boisson("Café crème", "Café au lait doux", 20));
        boissonRepo.ajouter(new Boisson("Chocolat chaud", "Boisson chaude au chocolat", 20));

        // Ajout de desserts
        dessertRepo.ajouter(new Dessert("Tiramisu", "Dessert italien avec mascarpone, café et cacao", 20));
        dessertRepo.ajouter(new Dessert("Crème brûlée", "Crème vanillée caramélisée", 25));
        dessertRepo.ajouter(new Dessert("Profiteroles", "Choux garnis de crème et nappés de chocolat", 20));
        dessertRepo.ajouter(new Dessert("Cheesecake", "Gâteau crémeux au fromage", 30));
        dessertRepo.ajouter(new Dessert("Mousse au chocolat", "Mousse légère au chocolat noir", 25));
    }

    private static boolean initialiserPacks(PlatRepository platRepo,
                                            BoissonRepository boissonRepo,
                                            DessertRepository dessertRepo,
                                            PackRepository packRepo) {
        try {
            // Pack personnalisé
            List<MenuItem> itemsPerso = creerListePack(
                    platRepo.get(2),   // Pizza Poulet
                    boissonRepo.get(7), // Jus d'ananas
                    dessertRepo.get(3)   // Cheesecake
            );
            packRepo.ajouter(new PackNormal("Mon Pack", "Pack personnalisé", itemsPerso));

            // Pack Étudiant
            List<MenuItem> packEtudiant = creerListePack(
                    platRepo.get(4),   // Tacos Viande Hachée
                    boissonRepo.get(9), // Eau minérale
                    dessertRepo.get(4)   // Mousse au chocolat
            );
            packRepo.ajouter(new PackNormal("Pack Étudiant", "Idéal pour une pause rapide", packEtudiant));

            // Pack Formule Midi
            List<MenuItem> formuleMidi = creerListePack(
                    platRepo.get(8),   // Burger Classique
                    boissonRepo.get(0), // Coca-Cola
                    dessertRepo.get(0)   // Tiramisu
            );
            packRepo.ajouter(new PackNormal("Formule Midi", "Un déjeuner complet", formuleMidi));

            // Pack Gourmet
            List<MenuItem> packGourmet = creerListePack(
                    platRepo.get(10),  // Grilled Salmon
                    boissonRepo.get(8), // Jus panaché
                    dessertRepo.get(1)  // Crème brûlée
            );
            packRepo.ajouter(new PackNormal("Pack Gourmet", "Pour les fins gourmets", packGourmet));

            // Menu Enfant
            List<MenuItem> itemsKids = creerListePack(
                    platRepo.get(8),   // Burger Classique
                    boissonRepo.get(5), // Jus d'orange
                    dessertRepo.get(2)   // Profiteroles
            );
            packRepo.ajouter(new MenuEnfant("Menu Enfant", "Menu pour les petits", itemsKids, "Jouet surprise"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static List<MenuItem> creerListePack(MenuItem... items) {
        List<MenuItem> liste = new ArrayList<>();
        for (MenuItem item : items) {
            if (item == null) {
                throw new IllegalArgumentException("Un élément du pack est null");
            }
            liste.add(item);
        }
        return liste;
    }
}