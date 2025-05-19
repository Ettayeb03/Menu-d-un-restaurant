package restaurantmenu.core.interfaces;

public interface MenuItem {
    String getNom();
    double getPrix();
    String getDescription();
    String getCategorie();

    default void afficherDetails() {
        System.out.println(getNom() + " (" + getCategorie() + ")");
        System.out.println("- " + getDescription());
        System.out.println("Prix: " + getPrix() + " DH");
    }
}