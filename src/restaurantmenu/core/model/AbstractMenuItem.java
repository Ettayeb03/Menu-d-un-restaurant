package restaurantmenu.core.model;

import restaurantmenu.core.interfaces.MenuItem;
import restaurantmenu.core.enums.Categorie;

public abstract class AbstractMenuItem implements MenuItem {
    protected String nom;
    protected String description;
    protected double prix;
    protected String categorie;

    public AbstractMenuItem(String nom, String description, double prix, String categorie) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
    }

    @Override public String getNom() { return nom; }
    @Override public String getDescription() { return description; }
    @Override public double getPrix() { return prix; }
    @Override public String getCategorie() { return categorie; }

    public void afficherDetails() {
        System.out.println(getNom() + " (" + getCategorie() + ")");
        System.out.println("- " + getDescription());
        System.out.println("Prix: " + getPrix() + " DH");
        System.out.println("----------------------------");
    }
}