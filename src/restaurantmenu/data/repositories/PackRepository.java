package restaurantmenu.data.repositories;

import restaurantmenu.core.interfaces.MenuRepository;
import restaurantmenu.core.model.Pack;
import java.util.ArrayList;
import java.util.List;

public class PackRepository implements MenuRepository<Pack> {
    private final List<Pack> packs = new ArrayList<>();

    @Override
    public void ajouter(Pack pack) {
        packs.add(pack);
    }

    @Override
    public Pack get(Integer index) {
        return (index >= 0 && index < packs.size()) ? packs.get(index) : null;
    }

    @Override
    public List<Pack> getAll() {
        return new ArrayList<>(packs);
    }
}