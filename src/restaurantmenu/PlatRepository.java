package restaurantmenu;

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
        if (index >= 0 && index < plats.size()) {
            return plats.get(index);
        }
        return null;
    }

    @Override
    public List<Plat> getAll() {
        return plats;
    }
}
