package restaurantmenu;

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
        System.out.println("------------------------------------");
    }
}