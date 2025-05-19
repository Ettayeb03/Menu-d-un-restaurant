package restaurantmenu.core.model;

import restaurantmenu.core.interfaces.MenuItem;

public class Plat implements MenuItem {
    private String nom;
    private double prix;
    private String description;
    private String categorie;

    public Plat(String nom, double prix, String description, String categorie) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.categorie = categorie;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public double getPrix() {
        return prix;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getCategorie() {
        return categorie;
    }
}