package restaurantmenu.core.model;

import restaurantmenu.core.interfaces.MenuItem;
import java.util.List;

public class PackNormal extends Pack {
    private static final double REMISE = 0.9; // 10% de réduction
    private static final String CATEGORIE = "Pack Normal";

    public PackNormal(String nom, String description, List<MenuItem> items) {
        super(nom, description, items);
    }

    @Override
    public double getPrix() {
        double total = getItems().stream()
                .mapToDouble(MenuItem::getPrix)
                .sum();
        return arrondirPrix(total * REMISE);
    }

    @Override
    public void afficherDetails() {
        System.out.println("=== " + CATEGORIE.toUpperCase() + " ===");
        System.out.println(getNom() + " - " + getDescription());
        System.out.printf("Prix total avec remise: %.2f DH\n", getPrix());
        System.out.println("\nContenu du pack:");

        getItems().forEach(item -> {
            System.out.printf(" - %s (%s): %.2f DH\n",
                    item.getNom(),
                    item.getCategorie(),
                    item.getPrix());
        });

        System.out.println("----------------------------");
        System.out.printf("Économie: %.2f DH\n", calculerEconomie());
        System.out.println("----------------------------");
    }

    @Override
    public String getCategorie() {
        return CATEGORIE;
    }

    private double calculerEconomie() {
        double totalOriginal = getItems().stream()
                .mapToDouble(MenuItem::getPrix)
                .sum();
        return totalOriginal - getPrix();
    }

    private double arrondirPrix(double prix) {
        return Math.round(prix * 100.0) / 100.0;
    }
}