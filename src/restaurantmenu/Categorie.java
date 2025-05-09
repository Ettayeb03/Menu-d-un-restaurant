package restaurantmenu;

public enum Categorie { //pour standardiser les catégories.
    PLAT("Plat principal"),
    BOISSON("Boisson"),
    DESSERT("Dessert");

    private final String libelle;

    Categorie(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}