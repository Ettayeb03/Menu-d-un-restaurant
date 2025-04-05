package restaurantmenu;

public class Main {
    public static void main(String[] args) {
        // Création du menu
        Menu menu = new Menu();

        // Ajouter des plats
        menu.ajouterPlat(new Plat("Pizza Margherita", "Pizza classique avec sauce tomate et mozzarella", 9.99));
        menu.ajouterPlat(new Plat("Spaghetti Carbonara", "Spaghetti avec sauce crémeuse à base d'œufs et de lardons", 12.50));
        menu.ajouterPlat(new Plat("Burger Classique", "Burger avec viande de bœuf, laitue, tomate et fromage", 8.00));
        menu.ajouterPlat(new Plat("Salade César", "Salade avec laitue, poulet grillé, croûtons et sauce César", 10.50));
        menu.ajouterPlat(new Plat("Grilled Salmon", "Saumon grillé accompagné de légumes et de riz", 15.00));

        // Ajouter des boissons
        menu.ajouterBoisson(new Boisson("Coca-Cola", "Boisson gazeuse", 2.50));
        menu.ajouterBoisson(new Boisson("Jus d'orange", "Jus naturel d'orange frais", 3.00));
        menu.ajouterBoisson(new Boisson("Eau minérale", "Eau pure et fraîche", 1.00));
        menu.ajouterBoisson(new Boisson("Limonade", "Boisson sucrée à base de citron et de sucre", 2.00));
        menu.ajouterBoisson(new Boisson("Thé vert", "Thé vert chaud", 1.50));

        // Ajouter des desserts
        menu.ajouterDessert(new Dessert("Tiramisu", "Dessert italien à base de mascarpone, café et cacao", 4.50));
        menu.ajouterDessert(new Dessert("Crème brûlée", "Crème à base de vanille caramélisée", 5.00));
        menu.ajouterDessert(new Dessert("Profiteroles", "Choux fourrés à la crème avec sauce au chocolat", 4.00));
        menu.ajouterDessert(new Dessert("Cheesecake", "Gâteau au fromage crémeux sur une base de biscuit", 5.50));
        menu.ajouterDessert(new Dessert("Mousse au chocolat", "Mousse légère au chocolat noir", 4.20));

        // Lancer le processus de commande
        menu.choisirItems();
    }
}
