package restaurantmenu;

import java.util.List;

public abstract class Pack implements MenuItem {
    protected String nom;
    protected String description;
    protected double prix;
    protected List<MenuItem> items;

    public Pack(String nom, String description, List<MenuItem> items) {
        this.nom = nom;
        this.description = description;
        this.items = items;
        this.prix = calculerPrixPack();
    }

    protected abstract double calculerPrixPack();

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrix() {
        return prix;
    }

    @Override
    public String getCategorie() {
        return "Pack";
    }

    @Override
    public void afficherDetails() {
        System.out.println(getNom());
        System.out.println("-" + getDescription() + "-");
        System.out.println("Contenu du pack:");
        items.forEach(item -> System.out.println("  • " + item.getNom()));
        System.out.println("Prix total: " + getPrix() + "dh");
        System.out.println("------------------------------------");
    }

    public List<MenuItem> getItems() {
        return items;
    }
}