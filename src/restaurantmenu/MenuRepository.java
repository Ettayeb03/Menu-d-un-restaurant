package restaurantmenu;

import java.util.List;

public interface MenuRepository<T> {
    void ajouter(T item);
    T get(int index);
    List<T> getAll();
}
