package restaurantmenu.core.model;

import restaurantmenu.core.interfaces.MenuItem;
import java.util.List;

public class MenuEnfant extends Pack {
    private static final double PRIX_FIXE = 50.0;
    private final String jouetInclus;

    public MenuEnfant(String nom, String description, List<MenuItem> items, String jouetInclus) {
        super(nom, description, items);
        this.jouetInclus = jouetInclus;
    }

    @Override
    public double getPrix() {
        // Le prix fixe prime sur le calcul normal
        return PRIX_FIXE;
    }

    @Override
    public void afficherDetails() {
        System.out.println("=== MENU ENFANT ===");
        System.out.println(getNom() + " - " + getDescription());
        System.out.println("Prix spécial enfant: " + getPrix() + " DH");
        System.out.println("Jouet inclus: " + jouetInclus);
        System.out.println("\nContenu du menu:");

        getItems().forEach(item ->
                System.out.println(" - " + item.getNom() + " (" + item.getCategorie() + ")"));

        System.out.println("----------------------------");
    }

    @Override
    public String getCategorie() {
        return "Menu Enfant";
    }

    public String getJouetInclus() {
        return jouetInclus;
    }
}