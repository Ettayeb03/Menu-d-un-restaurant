package restaurantmenu.services;

import restaurantmenu.Commande;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandeService {
    private static final List<Commande> commandes = new ArrayList<>();

    public static Commande creerCommande() {
        Commande nouvelleCommande = new Commande();
        commandes.add(nouvelleCommande);
        return nouvelleCommande;
    }

    public static Optional<Commande> getCommande(int id) {
        return commandes.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    public static boolean supprimerCommande(int id) {
        return commandes.removeIf(c -> c.getId() == id);
    }

    public static List<Commande> getToutesCommandes() {
        return new ArrayList<>(commandes);
    }
}