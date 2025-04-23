package restaurantmenu;

import java.util.List;

public class PackNormal extends Pack {
    private static final double REMISE = 0.9; // 10% de réduction

    public PackNormal(String nom, String description, List<MenuItem> items) {
        super(nom, description, items);
    }

    @Override
    protected double calculerPrixPack() {
        double total = items.stream().mapToDouble(MenuItem::getPrix).sum();
        return total * REMISE; // Application de la remise
    }

    @Override
    public String getCategorie() {
        return "Pack Normal";
    }
}