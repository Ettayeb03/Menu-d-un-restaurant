package restaurantmenu.ui;

import restaurantmenu.core.interfaces.MenuItem;
import restaurantmenu.core.model.*;
import restaurantmenu.data.repositories.*;
import javax.swing.*;
import java.awt.*;

public class RestaurantGUI extends JFrame {
    private final PlatRepository platRepository;
    private final BoissonRepository boissonRepository;
    private final DessertRepository dessertRepository;
    private final PackRepository packRepository;

    public RestaurantGUI(PlatRepository platRepo, BoissonRepository boissonRepo,
                         DessertRepository dessertRepo, PackRepository packRepo) {
        this.platRepository = platRepo;
        this.boissonRepository = boissonRepo;
        this.dessertRepository = dessertRepo;
        this.packRepository = packRepo;

        setTitle("Gestionnaire de Menu Restaurant");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        creerInterface();
    }

    private void creerInterface() {
        JTabbedPane onglets = new JTabbedPane();

        // Onglet Plats
        JPanel panelPlats = new JPanel(new GridLayout(0, 3, 10, 10));
        for(Plat plat : platRepository.getAll()) {
            panelPlats.add(creerCarteItem(plat));
        }
        onglets.addTab("Plats", new JScrollPane(panelPlats));

        // Onglet Boissons
        JPanel panelBoissons = new JPanel(new GridLayout(0, 3, 10, 10));
        for(Boisson boisson : boissonRepository.getAll()) {
            panelBoissons.add(creerCarteItem(boisson));
        }
        onglets.addTab("Boissons", new JScrollPane(panelBoissons));

        // Onglet Desserts
        JPanel panelDesserts = new JPanel(new GridLayout(0, 3, 10, 10));
        for(Dessert dessert : dessertRepository.getAll()) {
            panelDesserts.add(creerCarteItem(dessert));
        }
        onglets.addTab("Desserts", new JScrollPane(panelDesserts));

        // Onglet Packs
        JPanel panelPacks = new JPanel(new GridLayout(0, 2, 10, 10));
        for(Pack pack : packRepository.getAll()) {
            panelPacks.add(creerCartePack(pack));
        }
        onglets.addTab("Packs", new JScrollPane(panelPacks));

        add(onglets);
    }

    private JPanel creerCarteItem(MenuItem item) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setPreferredSize(new Dimension(250, 150));

        JLabel nomLabel = new JLabel(item.getNom());
        nomLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nomLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea descArea = new JTextArea(item.getDescription());
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setBackground(panel.getBackground());

        JLabel prixLabel = new JLabel(String.format("%.2f DH", item.getPrix()));
        prixLabel.setForeground(Color.RED);
        prixLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(nomLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(descArea), BorderLayout.CENTER);
        panel.add(prixLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel creerCartePack(Pack pack) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLUE),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setPreferredSize(new Dimension(350, 200));

        JLabel nomLabel = new JLabel(pack.getNom());
        nomLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nomLabel.setForeground(Color.BLUE);

        JTextArea descArea = new JTextArea(pack.getDescription());
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);

        JLabel prixLabel = new JLabel(String.format("Prix total: %.2f DH", pack.getPrix()));
        prixLabel.setFont(new Font("Arial", Font.BOLD, 16));
        prixLabel.setForeground(Color.RED);

        // Liste des items du pack
        JTextArea itemsArea = new JTextArea();
        itemsArea.setEditable(false);
        for(MenuItem item : pack.getItems()) {
            itemsArea.append("• " + item.getNom() + "\n");
        }

        panel.add(nomLabel, BorderLayout.NORTH);
        panel.add(descArea, BorderLayout.CENTER);
        panel.add(new JScrollPane(itemsArea), BorderLayout.SOUTH);
        panel.add(prixLabel, BorderLayout.EAST);

        return panel;
    }
}