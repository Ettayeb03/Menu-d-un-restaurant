package restaurantmenu.data.repositories;

import restaurantmenu.core.interfaces.MenuRepository;
import restaurantmenu.core.model.Boisson;
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
        return (index >= 0 && index < boissons.size()) ? boissons.get(index) : null;
    }

    @Override
    public List<Boisson> getAll() {
        return new ArrayList<>(boissons);
    }
}