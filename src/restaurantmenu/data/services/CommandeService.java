package restaurantmenu.data.services;

import java.util.Optional;
import restaurantmenu.core.interfaces.MenuItem;
import restaurantmenu.core.model.Commande;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CommandeService {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);
    private final List<Commande> commandes = new ArrayList<>();

    public void ajouterItem(Commande commande, MenuItem item) {
        // Dans une vraie implémentation, vous voudrez probablement spécifier à quelle commande ajouter l'item
        System.out.println("Item ajouté : " + item.getNom());
        item.afficherDetails();
    }

    public Commande creerCommande() {
        Commande commande = new Commande(idGenerator.incrementAndGet());
        commandes.add(commande);
        return commande;
    }

    public boolean supprimerCommande(int id) {
        return commandes.removeIf(c -> c.getId() == id);
    }

    public Optional<Commande> getCommande(int id) {
        return commandes.stream().filter(c -> c.getId() == id).findFirst();
    }

    public List<Commande> getToutesCommandes() {
        return new ArrayList<>(commandes);
    }

    public void enregistrerCommande(Commande commande) {
        System.out.println("Commande #" + commande.getId() + " enregistrée");
    }
}