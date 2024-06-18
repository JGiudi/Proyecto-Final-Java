package Primera_Entrega;

import jakarta.persistence.*;

import java.util.List;

public class GestorProducto {
    public void create(String nombre, int precio, int stock) {
        EntityManager manager = GestorGenerico.getEntityManager();
        manager.getTransaction().begin();
        Producto producto = new Producto(nombre, precio, stock);
        manager.persist(producto);
        manager.getTransaction().commit();
        manager.close();
    }
}
