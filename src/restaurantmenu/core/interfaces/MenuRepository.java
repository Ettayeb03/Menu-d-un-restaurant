package restaurantmenu.core.interfaces;

import java.util.List;

public interface MenuRepository<T extends MenuItem> {
    void ajouter(T item);
    T get(Integer index);
    List<T> getAll();
}