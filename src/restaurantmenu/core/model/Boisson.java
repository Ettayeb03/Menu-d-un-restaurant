package restaurantmenu.core.model;

import restaurantmenu.core.enums.Categorie;

public class Boisson extends AbstractMenuItem {
    private boolean estAlcoolisee;

    public Boisson(String nom, String description, double prix) {
        super(nom, description, prix, Categorie.BOISSON.getLibelle());
    }

    // Méthode spécifique
    public boolean estAlcoolisee() {
        return estAlcoolisee;
    }
}