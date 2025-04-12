package restaurantmenu;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    private static int dernierId = 0;

    private final int id;
    private final List<MenuItem> items = new ArrayList<>();

    public Commande() {
        this.id = ++dernierId; // Génération d'un ID auto-incrémenté
    }

    public int getId() {
        return id;
    }

    public void ajouterItem(MenuItem item) {
        items.add(item);
    }

    public boolean retirerItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            return true;
        }
        return false;
    }

    public double calculerTotal() {
        return items.stream().mapToDouble(MenuItem::getPrix).sum();
    }

    public void afficherDetails() {
        System.out.println("\nCommande #" + id);
        items.forEach(MenuItem::afficherDetails);
        System.out.println("Total: " + calculerTotal() + "dh");
    }

    public List<MenuItem> getItems() {
        return new ArrayList<>(items); // Retourne une copie pour l'immutabilité
    }
}