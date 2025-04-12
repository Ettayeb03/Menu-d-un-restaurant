package restaurantmenu;

import java.util.List;

public interface MenuRepository<T> {
    void ajouter(T item);

    T get(Integer index);

    List<T> getAll();
}
