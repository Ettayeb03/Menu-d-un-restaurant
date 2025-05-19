package restaurantmenu;

import restaurantmenu.data.services.CommandeService;
import restaurantmenu.view.MainWindow;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // Initialisez vos services existants
        CommandeService commandeService = new CommandeService();

        // Lancez l'interface Swing
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow(commandeService);
            window.setVisible(true);
        });
    }
}