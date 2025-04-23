package restaurantmenu;

import java.util.ArrayList;
import java.util.List;

public class PackRepository {
    private final List<Pack> packs = new ArrayList<>();

    public void ajouter(Pack pack) {
        packs.add(pack);
    }

    public Pack get(int index) {
        if (index >= 0 && index < packs.size()) {
            return packs.get(index);
        }
        return null;
    }

    public List<Pack> getAll() {
        return new ArrayList<>(packs);
    }
}