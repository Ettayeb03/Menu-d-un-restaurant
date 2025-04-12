package restaurantmenu;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    private final List<MenuItem> items = new ArrayList<>();

    public void ajouterItem(MenuItem item) {
        items.add(item);
    }

    public double calculerTotal() {
        return items.stream().mapToDouble(MenuItem::getPrix).sum();
    }

    public void afficherDetails() {
        System.out.println("\nVous avez commandé: ");
        items.forEach(MenuItem::afficherDetails);
        System.out.println("Total : " + calculerTotal() + "dh");
    }
}