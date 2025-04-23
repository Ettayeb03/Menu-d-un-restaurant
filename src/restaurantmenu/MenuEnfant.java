package restaurantmenu;

import java.util.List;

public class MenuEnfant extends Pack {
    private static final double PRIX_FIXE = 50.0; // Prix spécial pour enfants
    private String jouetInclus;

    public MenuEnfant(String nom, String description, List<MenuItem> items, String jouetInclus) {
        super(nom, description, items);
        this.jouetInclus = jouetInclus;
    }

    @Override
    protected double calculerPrixPack() {
        return PRIX_FIXE;
    }

    @Override
    public void afficherDetails() {
        super.afficherDetails();
    }

    @Override
    public String getCategorie() {
        return "Menu Enfant";
    }
}