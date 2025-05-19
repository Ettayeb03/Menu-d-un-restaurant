package restaurantmenu.core.model;
import restaurantmenu.core.interfaces.MenuItem;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

public class Commande {
    private final int id;
    private final List<MenuItem> items = new ArrayList<>();

    public Commande(int id) {
        this.id = id;
    }

    public void ajouterItem(MenuItem item) {
        items.add(Objects.requireNonNull(item));
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
        System.out.println("\n=== COMMANDE #" + id + " ===");
        items.forEach(item ->
                System.out.printf("- %s: %.2f DH\n", item.getNom(), item.getPrix()));
        System.out.printf("TOTAL: %.2f DH\n", calculerTotal());
    }

    public int getId() { return id; }
    public List<MenuItem> getItems() { return new ArrayList<>(items); }
}