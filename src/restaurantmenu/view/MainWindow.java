package restaurantmenu.view;

import javax.swing.*;
import javax.swing.border.*;
import restaurantmenu.core.interfaces.MenuItem;
import restaurantmenu.core.model.*;
import restaurantmenu.data.services.CommandeService;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MainWindow extends JFrame {
    private final CommandeService commandeService;
    private DefaultListModel<MenuItem> panierModel;
    private DefaultListModel<MenuItem> allMenuItemsModel;
    private JList<MenuItem> menuList;
    private JList<MenuItem> panierList;
    private JLabel totalLabel;
    private double totalCommande = 0.0;

    // Couleurs du thème
    private static final Color PRIMARY_COLOR = new Color(44, 62, 80);
    private static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private static final Color ACCENT_COLOR = new Color(46, 204, 113);
    private static final Color BACKGROUND_COLOR = new Color(250, 250, 250);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BORDER_COLOR = new Color(220, 220, 220);

    // Polices
    private static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 18);
    private static final Font FONT_NORMAL = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 14);

    public MainWindow(CommandeService commandeService) {
        this.commandeService = commandeService;
        initializeUI();
    }

    private void initializeUI() {
        configureMainWindow();
        setupTabbedPane();
    }

    private void configureMainWindow() {
        setTitle("Système de Commande - Restaurant");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_COLOR);
    }

    private void setupTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        styleTabbedPane(tabbedPane);

        tabbedPane.addTab("Menu", createMenuPanel());
        tabbedPane.addTab("Commande", createOrderPanel());

        add(tabbedPane);
    }

    private void styleTabbedPane(JTabbedPane tabbedPane) {
        tabbedPane.setBackground(PRIMARY_COLOR);
        tabbedPane.setForeground(TEXT_COLOR);
        tabbedPane.setFont(FONT_TITLE);
        tabbedPane.setBorder(BorderFactory.createEmptyBorder());
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(createContentBorder());

        // Filtre par catégorie
        JPanel filterPanel = createFilterPanel();
        panel.add(filterPanel, BorderLayout.NORTH);

        // Initialise le modèle complet
        allMenuItemsModel = new DefaultListModel<>();
        loadMenuItems();

        // Crée la JList avec le modèle complet
        menuList = new JList<>(allMenuItemsModel);
        menuList.setCellRenderer(new MenuItemRenderer());
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.setFixedCellHeight(80);

        panel.add(new JScrollPane(menuList), BorderLayout.CENTER);

        // Bouton Ajouter
        JButton addButton = createActionButton("Ajouter au panier", SECONDARY_COLOR);
        addButton.addActionListener(e -> addToCart(menuList));
        panel.add(addButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createFilterPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(BACKGROUND_COLOR);

        JLabel filterLabel = new JLabel("Filtrer par catégorie:");
        filterLabel.setFont(FONT_BOLD);

        String[] categories = {"Tous", "Pizzas", "Tacos", "Burgers", "Salades", "Pâtes", "Grillades", "Spécialités"};
        JComboBox<String> categoryFilter = createStyledComboBox(categories);

        categoryFilter.addActionListener(e -> {
            String selectedCategory = (String)categoryFilter.getSelectedItem();
            filterMenuItems(selectedCategory);
        });

        panel.add(filterLabel);
        panel.add(categoryFilter);
        return panel;
    }

    private void filterMenuItems(String category) {
        DefaultListModel<MenuItem> filteredModel = new DefaultListModel<>();

        for (int i = 0; i < allMenuItemsModel.size(); i++) {
            MenuItem item = allMenuItemsModel.getElementAt(i);
            if ("Tous".equals(category) || item.getCategorie().equals(category)) {
                filteredModel.addElement(item);
            }
        }

        menuList.setModel(filteredModel);
    }

    private JPanel createOrderPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(createContentBorder());

        // Panier
        panierModel = new DefaultListModel<>();
        panierList = createOrderList(panierModel);
        panel.add(new JScrollPane(panierList), BorderLayout.CENTER);

        // Contrôles
        JPanel controlsPanel = createOrderControlsPanel();
        panel.add(controlsPanel, BorderLayout.SOUTH);

        // Total
        JPanel totalPanel = createTotalPanel();
        panel.add(totalPanel, BorderLayout.NORTH);

        return panel;
    }

    private JList<MenuItem> createOrderList(DefaultListModel<MenuItem> model) {
        JList<MenuItem> list = new JList<>(model);
        list.setCellRenderer(new OrderItemRenderer());
        list.setFixedCellHeight(50);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return list;
    }

    private JPanel createOrderControlsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.setBorder(new EmptyBorder(10, 0, 0, 0));
        panel.setBackground(BACKGROUND_COLOR);

        List<OrderControl> controls = Arrays.asList(
                new OrderControl("Retirer", new Color(231, 76, 60), this::removeFromCart),
                new OrderControl("Vider", new Color(149, 165, 166), this::clearCart),
                new OrderControl("Valider", new Color(155, 89, 182), this::submitOrder)
        );

        for (OrderControl control : controls) {
            panel.add(createActionButton(control.text, control.color, control.action));
        }
        return panel;
    }

    private JPanel createTotalPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        totalLabel = new JLabel("Total: 0.00 DH", SwingConstants.RIGHT);
        totalLabel.setFont(FONT_TITLE);
        totalLabel.setForeground(PRIMARY_COLOR);
        totalLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        panel.add(totalLabel, BorderLayout.EAST);
        return panel;
    }

    private void loadMenuItems() {
        MenuItem[] items = {
                new Plat("Pizza 4 Fromages", 45.0, "Pizza avec mozzarella, gorgonzola, parmesan et provolone", "Pizzas"),
                new Plat("Pizza Végétarienne", 42.0, "Pizza avec légumes frais et fromage", "Pizzas"),
                new Plat("Pizza Fruits de Mer", 55.0, "Pizza avec crevettes, moules et calamars", "Pizzas"),
                new Plat("Pizza Royale", 48.0, "Pizza complète avec jambon, champignons et œuf", "Pizzas"),

                new Plat("Tacos Classique", 35.0, "Tacos traditionnel avec viande hachée et sauce fromagère", "Tacos"),
                new Plat("Tacos Mexicain", 38.0, "Tacos épicé avec piments et guacamole", "Tacos"),
                new Plat("Tacos Végétarien", 36.0, "Tacos avec légumes grillés et sauce tahini", "Tacos"),
                new Plat("Tacos Géant", 45.0, "Grand tacos avec triple garniture", "Tacos"),

                new Plat("Spaghetti Bolognaise", 32.0, "Spaghetti avec sauce à la viande maison", "Pâtes"),
                new Plat("Lasagnes", 38.0, "Lasagnes traditionnelles à la bolognaise", "Pâtes"),
                new Plat("Penne Arrabiata", 30.0, "Penne avec sauce tomate piquante", "Pâtes"),
                new Plat("Fettuccine Carbonara", 35.0, "Fettuccine avec sauce crémeuse aux lardons", "Pâtes"),

                new Plat("Burger Spécial", 40.0, "Burger avec double viande et bacon croustillant", "Burgers"),
                new Plat("Burger Végétarien", 36.0, "Burger avec steak de légumes et avocat", "Burgers"),
                new Plat("Burger Poulet", 38.0, "Burger avec filet de poulet pané et sauce BBQ", "Burgers"),
                new Plat("Burger Fish", 42.0, "Burger avec filet de poisson pané et sauce tartare", "Burgers"),

                new Plat("Salade Niçoise", 38.0, "Salade avec thon, œufs, olives et légumes", "Salades"),
                new Plat("Salade Grecque", 36.0, "Salade avec feta, olives et concombre", "Salades"),
                new Plat("Salade Caesar au Saumon", 45.0, "Salade Caesar revisitée avec saumon fumé", "Salades"),
                new Plat("Salade de Chèvre Chaud", 40.0, "Salade avec toast de chèvre fondu et noix", "Salades"),

                new Plat("Steak Frites", 45.0, "Entrecôte avec frites maison et sauce au choix", "Grillades"),
                new Plat("Côte d'Agneau", 55.0, "Côte d'agneau grillée avec légumes de saison", "Grillades"),
                new Plat("Poulet Rôti", 38.0, "Poulet fermier rôti avec pommes de terre", "Grillades"),
                new Plat("Filet Mignon", 50.0, "Filet mignon de porc avec sauce aux champignons", "Grillades"),

                new Plat("Plateau de Fruits de Mer", 65.0, "Assortiment de fruits de mer frais", "Spécialités"),
                new Plat("Risotto aux Champignons", 40.0, "Risotto crémeux aux champignons sauvages", "Spécialités"),
                new Plat("Cassoulet", 42.0, "Cassoulet traditionnel aux haricots blancs", "Spécialités"),
                new Plat("Bouillabaisse", 58.0, "Soupe de poissons méditerranéenne", "Spécialités")        };

        for (MenuItem item : items) {
            allMenuItemsModel.addElement(item);
        }
    }

    private void addToCart(JList<MenuItem> menuList) {
        MenuItem selected = menuList.getSelectedValue();
        if (selected != null) {
            panierModel.addElement(selected);
            totalCommande += selected.getPrix();
            updateTotalDisplay();
            showMessage(selected.getNom() + " ajouté au panier", "Succès");
        } else {
            showMessage("Veuillez sélectionner un article", "Avertissement");
        }
    }

    private void removeFromCart() {
        int selectedIndex = panierList.getSelectedIndex();
        if (selectedIndex != -1) {
            MenuItem removedItem = panierModel.getElementAt(selectedIndex);
            totalCommande -= removedItem.getPrix();
            panierModel.remove(selectedIndex);
            updateTotalDisplay();
        } else {
            showMessage("Veuillez sélectionner un article à retirer", "Avertissement");
        }
    }

    private void clearCart() {
        panierModel.clear();
        totalCommande = 0.0;
        updateTotalDisplay();
    }

    private void submitOrder() {
        if (panierModel.isEmpty()) {
            showMessage("Le panier est vide", "Erreur");
            return;
        }

        int option = JOptionPane.showConfirmDialog(this,
                "Confirmer la commande pour " + String.format("%.2f DH", totalCommande) + "?",
                "Validation de commande", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            Commande commande = commandeService.creerCommande();
            for (int i = 0; i < panierModel.size(); i++) {
                commandeService.ajouterItem(commande, panierModel.get(i));
            }
            commandeService.enregistrerCommande(commande);
            generateInvoice(commande);
            clearCart();
        }
    }

    private void generateInvoice(Commande commande) {
        StringBuilder invoice = new StringBuilder();
        invoice.append("=== FACTURE ===\n");
        invoice.append("Commande #").append(commande.getId()).append("\n\n");
        invoice.append("Articles commandés:\n");

        for (int i = 0; i < panierModel.size(); i++) {
            MenuItem item = panierModel.getElementAt(i);
            invoice.append(String.format("- %s: %.2f DH\n", item.getNom(), item.getPrix()));
        }

        invoice.append("\nTOTAL: ").append(String.format("%.2f DH", totalCommande));
        invoice.append("\n\nMerci pour votre commande !");

        JTextArea textArea = new JTextArea(invoice.toString());
        textArea.setEditable(false);
        textArea.setFont(FONT_NORMAL);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 500));

        JOptionPane.showMessageDialog(this, scrollPane, "Facture", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateTotalDisplay() {
        totalLabel.setText(String.format("Total: %.2f DH", totalCommande));
    }

    private void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    // Classes internes pour le rendu
    private static class MenuItemRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof MenuItem) {
                MenuItem item = (MenuItem) value;
                setText(createItemText(item, isSelected));
                styleItem(this, index, isSelected);
            }
            return this;
        }

        private String createItemText(MenuItem item, boolean isSelected) {
            String nameColor = isSelected ? "white" : String.format("#%06x", PRIMARY_COLOR.getRGB() & 0x00FFFFFF);
            String priceColor = isSelected ? "white" : String.format("#%06x", SECONDARY_COLOR.getRGB() & 0x00FFFFFF);

            return String.format("<html><div style='padding:5px;'>" +
                            "<b style='color:%s;font-size:14px;'>%s</b><br>" +
                            "<span style='color:#555;font-size:12px;'>%s</span><br>" +
                            "<span style='color:%s;font-weight:bold;'>%.2f DH</span>" +
                            "</div></html>",
                    nameColor, item.getNom(), item.getDescription(), priceColor, item.getPrix());
        }

        private void styleItem(JLabel label, int index, boolean isSelected) {
            if (isSelected) {
                label.setBackground(SECONDARY_COLOR);
                label.setForeground(Color.WHITE);
            } else {
                label.setBackground(index % 2 == 0 ? Color.WHITE : new Color(240, 240, 240));
                label.setForeground(Color.BLACK);
            }
        }
    }

    private static class OrderItemRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof MenuItem) {
                MenuItem item = (MenuItem) value;
                setText(String.format("<html><div style='padding:3px;'>" +
                                "<b>%s</b> - <span style='color:#%06x;'>%.2f DH</span>" +
                                "</div></html>",
                        item.getNom(), SECONDARY_COLOR.getRGB() & 0x00FFFFFF, item.getPrix()));

                setBorder(new EmptyBorder(5, 10, 5, 10));
            }
            return this;
        }
    }

    private static class OrderControl {
        String text;
        Color color;
        Runnable action;

        OrderControl(String text, Color color, Runnable action) {
            this.text = text;
            this.color = color;
            this.action = action;
        }
    }

    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(FONT_NORMAL);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(new CompoundBorder(
                new LineBorder(BORDER_COLOR, 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        return comboBox;
    }

    private JButton createActionButton(String text, Color bgColor) {
        return createActionButton(text, bgColor, null);
    }

    private JButton createActionButton(String text, Color bgColor, Runnable action) {
        JButton button = new JButton(text);
        button.setFont(FONT_BOLD);
        button.setBackground(bgColor);
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorder(new CompoundBorder(
                new LineBorder(bgColor.darker(), 1),
                new EmptyBorder(10, 15, 10, 15)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (action != null) {
            button.addActionListener(e -> action.run());
        }
        return button;
    }

    private Border createContentBorder() {
        return new EmptyBorder(15, 15, 15, 15);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new MainWindow(new CommandeService()).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}