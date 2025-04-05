package restaurantmenu;

public class Plat implements MenuItem {
    private String nom;
    private String description;
    private double prix;
    private String categorie;

    public Plat(String nom, String description, double prix) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.categorie = "Plat principal";
    }

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
        return categorie;
    }

    @Override
    public void afficherDetails() {
        System.out.println(getNom());
        System.out.println("-" + getDescription() + "-");
        System.out.println("Prix: " + getPrix() + "dh");
        System.out.println("Catégorie: " + getCategorie());
    }
}
