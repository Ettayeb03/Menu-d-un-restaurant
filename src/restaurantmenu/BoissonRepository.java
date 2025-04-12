package restaurantmenu;

import java.util.ArrayList;
import java.util.List;

public class BoissonRepository implements MenuRepository<Boisson> {
    private final List<Boisson> boissons = new ArrayList<>();

    @Override
    public void ajouter(Boisson boisson) {
        boissons.add(boisson);
    }

    @Override
    public Boisson get(Integer index) {
        if (index >= 0 && index < boissons.size()) {
            return boissons.get(index);
        }
        return null;
    }

    @Override
    public List<Boisson> getAll() {
        return boissons;
    }
}
