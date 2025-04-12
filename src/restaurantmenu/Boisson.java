package restaurantmenu;

public class Boisson extends AbstractMenuItem {
    public Boisson(String nom, String description, double prix) {
        super(nom, description, prix, Categorie.BOISSON.getLibelle());
    }
}