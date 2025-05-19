package restaurantmenu.data.repositories;

import restaurantmenu.core.interfaces.MenuRepository;
import restaurantmenu.core.model.Plat;
import java.util.ArrayList;
import java.util.List;

public class PlatRepository implements MenuRepository<Plat> {
    private final List<Plat> plats = new ArrayList<>();

    @Override
    public void ajouter(Plat plat) {
        plats.add(plat);
    }

    @Override
    public Plat get(Integer index) {
        return (index >= 0 && index < plats.size()) ? plats.get(index) : null;
    }

    @Override
    public List<Plat> getAll() {
        return new ArrayList<>(plats);
    }
}