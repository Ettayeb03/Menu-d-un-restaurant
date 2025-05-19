package restaurantmenu.core.model;

import restaurantmenu.core.interfaces.MenuItem;
import java.util.List;

public abstract class Pack extends AbstractMenuItem {
    protected final List<MenuItem> items;

    public Pack(String nom, String description, List<MenuItem> items) {
        super(nom, description, 0, "PACK"); // Prix calculé dynamiquement
        this.items = items;
    }

    @Override
    public abstract double getPrix();

    public List<MenuItem> getItems() { return items; }

    @Override
    public void afficherDetails() {
        super.afficherDetails();
        System.out.println("Contenu du pack:");
        items.forEach(item ->
                System.out.println(" - " + item.getNom() + " (" + item.getPrix() + " DH)"));
    }
}