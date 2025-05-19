package restaurantmenu.core.enums;

public enum Categorie {
    PLAT("Plat principal"),
    DESSERT("Dessert"),
    BOISSON("Boisson"),
    PACK("Pack spécial");
    public static final String PIZZA = "Pizza";
    public static final String TACOS = "Tacos";
    public static final String PASTA = "Pâtes";

    private final String libelle;

    Categorie(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}