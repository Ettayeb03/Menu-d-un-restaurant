package restaurantmenu;

public interface MenuItem {
    String getNom();               // Méthode pour obtenir le nom de l'élément
    String getDescription();       // Méthode pour obtenir la description
    double getPrix();              // Méthode pour obtenir le prix
    String getCategorie();         // Méthode pour obtenir la catégorie
    void afficherDetails();        // Méthode pour afficher les détails de l'élément
}
