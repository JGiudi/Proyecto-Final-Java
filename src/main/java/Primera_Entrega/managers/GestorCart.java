package Primera_Entrega.managers;

import Primera_Entrega.entities.Cliente;
import Primera_Entrega.entities.Producto;
import Primera_Entrega.entities.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class GestorCart {

    public void addToCart(int amount, Producto producto, Cliente cliente) {
        if (producto == null || cliente == null) {
            System.out.println("Producto o cliente no válidos");
            return;
        }

        EntityManager manager = null;
        EntityTransaction transaction = null;
        try {
            manager = Manager.get();
            transaction = manager.getTransaction();

            if (!transaction.isActive()) {
                transaction.begin();
            }

            Cart cart = new Cart();
            cart.setAmount(amount);
            cart.setPrice(producto.getPrecio());
            cart.setCliente_id(cliente);
            cart.setProducto_id(producto);
            manager.persist(cart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error al agregar al carrito: " + e.getMessage());
        } finally {
            Manager.closeEntity(manager);
        }
    }

    public List<Cart> readByClient(Cliente cliente) {
        if (cliente == null) {
            System.out.println("Cliente no válido");
            return null;
        }

        EntityManager manager = null;
        List<Cart> carts = null;
        try {
            manager = Manager.get();
            carts = manager
                    .createQuery("SELECT c FROM Cart c WHERE c.cliente_id = :client", Cart.class)
                    .setParameter("client", cliente)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Error al leer el carrito del cliente: " + e.getMessage());
        } finally {
            Manager.closeEntity(manager);
            return carts;
        }
    }

    public void updateCart(Cart cart) {
        if (cart == null || cart.getId() == 0) {
            System.out.println("Carrito no válido para actualizar");
            return;
        }

        EntityManager manager = null;
        EntityTransaction transaction = null;
        try {
            manager = Manager.get();
            transaction = manager.getTransaction();
            transaction.begin();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error al actualizar el carrito: " + e.getMessage());
        } finally {
            Manager.closeEntity(manager);
        }
    }

    public void deleteCart(Cart cart) {
        if (cart == null || cart.getId() == 0) {
            System.out.println("Carrito no válido para eliminar");
            return;
        }

        EntityManager manager = null;
        EntityTransaction transaction = null;
        try {
            manager = Manager.get();
            transaction = manager.getTransaction();
            transaction.begin();

            // Necesitamos encontrar el carrito en el contexto persistente antes de eliminarlo
            Cart cartToDelete = manager.find(Cart.class, cart.getId());
            if (cartToDelete != null) {
                manager.remove(cartToDelete);
            } else {
                System.out.println("Carrito no encontrado con ID: " + cart.getId());
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error al eliminar el carrito: " + e.getMessage());
        } finally {
            Manager.closeEntity(manager);
        }
    }
}
