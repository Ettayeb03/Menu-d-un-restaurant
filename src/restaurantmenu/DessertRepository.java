package restaurantmenu ;

import java.util.ArrayList;
import java.util.List;

public class DessertRepository implements MenuRepository<Dessert> {
    private final List<Dessert> desserts = new ArrayList<>();

    @Override
    public void ajouter(Dessert dessert) {
        desserts.add(dessert);
    }

    @Override
    public Dessert get(Integer index) {
        if (index >= 0 && index < desserts.size()) {
            return desserts.get(index);
        }
        return null;
    }

    @Override
    public List<Dessert> getAll() {
        return desserts;
    }
}
