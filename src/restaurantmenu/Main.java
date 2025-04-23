package restaurantmenu;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();

        // Ajout de plats
        menu.ajouterPlat(new Plat("Pizza Margherita", "Pizza classique avec sauce tomate et mozzarella", 30));
        menu.ajouterPlat(new Plat("Pizza Viande Hachée", "Pizza classique avec sauce tomate et viande hachée", 40));
        menu.ajouterPlat(new Plat("Pizza Poulet", "Pizza classique avec sauce tomate et escalope de poulet", 40));
        menu.ajouterPlat(new Plat("Pizza Special", "Pizza spécial de notre restaurant", 50));
        menu.ajouterPlat(new Plat("Tacos Viande Hachée", "Tacos avec sauce, viande hachée et frites", 35));
        menu.ajouterPlat(new Plat("Tacos Poulet", "Tacos avec sauce, escalope de poulet et frites", 35));
        menu.ajouterPlat(new Plat("Tacos Special", "Tacos spécial de notre restaurant", 50));
        menu.ajouterPlat(new Plat("Spaghetti Carbonara", "Spaghetti crémeux à base d'œufs et de lardons", 30));
        menu.ajouterPlat(new Plat("Burger Classique", "Burger avec viande de bœuf, laitue, tomate et fromage", 35));
        menu.ajouterPlat(new Plat("Salade César", "Salade avec laitue, poulet grillé, croûtons et sauce César", 40));
        menu.ajouterPlat(new Plat("Grilled Salmon", "Saumon grillé avec légumes et riz", 60));

        // Ajout de boissons
        menu.ajouterBoisson(new Boisson("Coca-Cola", "Boisson gazeuse", 15));
        menu.ajouterBoisson(new Boisson("Fanta", "Boisson gazeuse", 15));
        menu.ajouterBoisson(new Boisson("Hawai", "Boisson gazeuse", 15));
        menu.ajouterBoisson(new Boisson("Sprite", "Boisson gazeuse", 15));
        menu.ajouterBoisson(new Boisson("Oulmes", "Eau gazeuse", 15));
        menu.ajouterBoisson(new Boisson("Jus d'orange", "Jus naturel d'orange", 20));
        menu.ajouterBoisson(new Boisson("Jus de fraise", "Jus naturel de fraise", 25));
        menu.ajouterBoisson(new Boisson("Jus d'ananas", "Jus naturel d'ananas", 30));
        menu.ajouterBoisson(new Boisson("Jus panaché", "Jus de fruits mixés", 35));
        menu.ajouterBoisson(new Boisson("Eau minérale", "Eau pure et fraîche", 10));
        menu.ajouterBoisson(new Boisson("Thé Marocain", "Thé vert à la menthe", 15));
        menu.ajouterBoisson(new Boisson("Café noir", "Café noir corsé", 20));
        menu.ajouterBoisson(new Boisson("Café crème", "Café au lait doux", 20));
        menu.ajouterBoisson(new Boisson("Chocolat chaud", "Boisson chaude au chocolat", 20));

        // Ajout de desserts
        menu.ajouterDessert(new Dessert("Tiramisu", "Dessert italien avec mascarpone, café et cacao", 20));
        menu.ajouterDessert(new Dessert("Crème brûlée", "Crème vanillée caramélisée", 25));
        menu.ajouterDessert(new Dessert("Profiteroles", "Choux garnis de crème et nappés de chocolat", 20));
        menu.ajouterDessert(new Dessert("Cheesecake", "Gâteau crémeux au fromage", 30));
        menu.ajouterDessert(new Dessert("Mousse au chocolat", "Mousse légère au chocolat noir", 25));

        // Pack personnalisé
        List<MenuItem> itemsPerso = List.of(
                new Plat("Pizza Poulet", "Pizza avec escalope de poulet", 40),
                new Boisson("Jus d'ananas", "Jus naturel d'ananas", 30),
                new Dessert("Cheesecake", "Gâteau crémeux au fromage", 30)
        );
        menu.ajouterPack(new PackNormal("Mon Pack", "Pack personnalisé", itemsPerso));

        // Pack Étudiant
        List<MenuItem> packEtudiant = List.of(
                new Plat("Tacos Viande Hachée", "Tacos complet", 35),
                new Boisson("Eau minérale", "Eau", 10),
                new Dessert("Mousse au chocolat", "Dessert léger", 25)
        );
        menu.ajouterPack(new PackNormal("Pack Étudiant", "Idéal pour une pause rapide", packEtudiant));

        // Pack Formule Midi
        List<MenuItem> formuleMidi = List.of(
                new Plat("Burger Classique", "Burger savoureux", 35),
                new Boisson("Coca-Cola", "Boisson gazeuse", 15),
                new Dessert("Tiramisu", "Dessert italien", 20)
        );
        menu.ajouterPack(new PackNormal("Formule Midi", "Un déjeuner complet", formuleMidi));

        // Pack Gourmet
        List<MenuItem> packGourmet = List.of(
                new Plat("Grilled Salmon", "Saumon grillé", 60),
                new Boisson("Jus panaché", "Jus frais mixé", 35),
                new Dessert("Crème brûlée", "Dessert raffiné", 25)
        );
        menu.ajouterPack(new PackNormal("Pack Gourmet", "Pour les fins gourmets", packGourmet));

        // Menu Enfant
        List<MenuItem> itemsKids = List.of(
                new Plat("Burger Classique", "Mini Burger", 35),
                new Boisson("Jus d'orange", "Jus frais", 20),
                new Dessert("Profiteroles", "Choux gourmands", 20)
        );
        menu.ajouterPack(new MenuEnfant("Menu Enfant", "Menu pour les petits", itemsKids, "Jouet surprise"));

        // Initialiser les autres packs définis dans la classe Menu
        menu.initialiserPacks();

        // Lancer le menu interactif
        menu.choisirItems();
    }
}
