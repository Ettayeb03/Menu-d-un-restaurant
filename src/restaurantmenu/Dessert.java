package restaurantmenu;

public class Dessert extends AbstractMenuItem {
    public Dessert(String nom, String description, double prix) {
        super(nom, description, prix, Categorie.DESSERT.getLibelle());
    }
}