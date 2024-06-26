package Primera_Entrega.managers;

import Primera_Entrega.entities.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;

import java.util.List;

public class GestorProducto {

    public void create(String nombre, Integer stock, int precio) {
        EntityManager manager = null;
        EntityTransaction transaction = null;
        try {
            manager = Manager.get();
            transaction = manager.getTransaction();
            transaction.begin();
            Producto producto = new Producto(nombre, precio, stock);
            manager.persist(producto);
            transaction.commit();
        } catch (PersistenceException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error al crear el producto: " + e.getMessage());
        } finally {
            Manager.closeEntity(manager);
        }
    }

    public Producto readOne(Integer id) {
        EntityManager manager = null;
        Producto producto = null;
        try {
            manager = Manager.get();
            producto = manager.find(Producto.class, id);
            if (producto == null) {
                System.out.println("Producto no encontrado con el ID: " + id);
            }
        } catch (PersistenceException e) {
            System.out.println("Error al leer el producto: " + e.getMessage());
        } finally {
            Manager.closeEntity(manager);
        }
        return producto;
    }

    public List<Producto> readAll() {
        EntityManager manager = null;
        List<Producto> productos = null;
        try {
            manager = Manager.get();
            productos = manager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
        } catch (PersistenceException e) {
            System.out.println("Error al obtener todos los productos: " + e.getMessage());
        } finally {
            Manager.closeEntity(manager);
        }
        return productos;
    }

    public void update(Integer id, String nombre, Integer stock, int precio) {
        EntityManager manager = null;
        EntityTransaction transaction = null;
        try {
            manager = Manager.get();
            transaction = manager.getTransaction();
            transaction.begin();
            Producto producto = manager.find(Producto.class, id);
            if (producto != null) {
                producto.setNombre(nombre);
                producto.setStock(stock);
                producto.setPrecio(precio);
                transaction.commit();
            } else {
                System.out.println("Producto no encontrado con el ID: " + id);
                transaction.rollback();
            }
        } catch (PersistenceException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        } finally {
            Manager.closeEntity(manager);
        }
    }

    public void delete(Integer id) {
        EntityManager manager = null;
        EntityTransaction transaction = null;
        try {
            manager = Manager.get();
            transaction = manager.getTransaction();
            transaction.begin();
            Producto producto = manager.find(Producto.class, id);
            if (producto != null) {
                manager.remove(producto);
                transaction.commit();
            } else {
                System.out.println("Producto no encontrado con el ID: " + id);
                transaction.rollback();
            }
        } catch (PersistenceException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        } finally {
            Manager.closeEntity(manager);
        }
    }
}
