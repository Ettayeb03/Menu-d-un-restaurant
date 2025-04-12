package restaurantmenu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();

        menu.ajouterPlat(new Plat("Pizza Margherita", "Pizza classique avec sauce tomate et mozzarella", 30));
        menu.ajouterPlat(new Plat("Pizza Viande Hachée", "Pizza classique avec sauce tomate et viande hachée", 40));
        menu.ajouterPlat(new Plat("Pizza Poulet", "Pizza classique avec sauce tomate et escalope de poulet", 40));
        menu.ajouterPlat(new Plat("Pizza Special", "Pizza special de notre réstaurant", 50));
        menu.ajouterPlat(new Plat("Tacos Viande Hachée", "Tacos avec sauce, viande hachée et frites", 35));
        menu.ajouterPlat(new Plat("Tacos Poulet", "Tacos avec sauce, escalope de poulet et frites", 35));
        menu.ajouterPlat(new Plat("Tacos Special", "Tacos special de notre réstaurant", 50));
        menu.ajouterPlat(new Plat("Spaghetti Carbonara", "Spaghetti avec sauce crémeuse à base d'œufs et de lardons", 30));
        menu.ajouterPlat(new Plat("Burger Classique", "Burger avec viande de bœuf, laitue, tomate et fromage", 35));
        menu.ajouterPlat(new Plat("Salade César", "Salade avec laitue, poulet grillé, croûtons et sauce César", 40));
        menu.ajouterPlat(new Plat("Grilled Salmon", "Saumon grillé accompagné de légumes et de riz", 60));

        menu.ajouterBoisson(new Boisson("Coca-Cola", "Boisson gazeuse", 15));
        menu.ajouterBoisson(new Boisson("Fanta", "Boisson gazeuse", 15));
        menu.ajouterBoisson(new Boisson("Hawai", "Boisson gazeuse", 15));
        menu.ajouterBoisson(new Boisson("Sprite", "Boisson gazeuse", 15));
        menu.ajouterBoisson(new Boisson("Oulmes", "Boisson gazeuse", 15));
        menu.ajouterBoisson(new Boisson("Jus d'orange", "Jus naturel d'orange frais", 20));
        menu.ajouterBoisson(new Boisson("Jus de fraise", "Jus naturel de fraise frais", 25));
        menu.ajouterBoisson(new Boisson("Jus d'ananas", "Jus naturel d'ananas frais", 30));
        menu.ajouterBoisson(new Boisson("Jus panaché", "Jus naturel des fruits frais", 35));
        menu.ajouterBoisson(new Boisson("Eau minérale", "Eau pure et fraîche", 10));
        menu.ajouterBoisson(new Boisson("Thé Marocain", "Thé vert chaud", 15));
        menu.ajouterBoisson(new Boisson("Café noire", "Café noire", 20));
        menu.ajouterBoisson(new Boisson("Café créme", "Café au lait", 20));
        menu.ajouterBoisson(new Boisson("Chocolat chaud", "Lait au chocolat", 20));

        menu.ajouterDessert(new Dessert("Tiramisu", "Dessert italien à base de mascarpone, café et cacao", 20));
        menu.ajouterDessert(new Dessert("Crème brûlée", "Crème à base de vanille caramélisée", 25));
        menu.ajouterDessert(new Dessert("Profiteroles", "Choux fourrés à la crème avec sauce au chocolat", 20));
        menu.ajouterDessert(new Dessert("Cheesecake", "Gâteau au fromage crémeux sur une base de biscuit", 30));
        menu.ajouterDessert(new Dessert("Mousse au chocolat", "Mousse légère au chocolat noir", 25));

        menu.choisirItems();
    }
}
