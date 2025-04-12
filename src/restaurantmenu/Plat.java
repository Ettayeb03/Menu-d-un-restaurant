package restaurantmenu;

public class Plat extends AbstractMenuItem {
    public Plat(String nom, String description, double prix) {
        super(nom, description, prix, Categorie.PLAT.getLibelle());
    }
}