package restaurantmenu;

public interface MenuItem {

    String getNom();

    String getDescription();

    double getPrix();

    String getCategorie();

    void afficherDetails();
}
