package Primera_Entrega;

import Primera_Entrega.entities.*;
import Primera_Entrega.managers.*;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear algunos productos
        GestorProducto gestorProducto = new GestorProducto();
        gestorProducto.create("Producto 1", 100, 10);
        gestorProducto.create("Producto 2", 200, 20);

        // Crear un cliente
        GestorCliente gestorCliente = new GestorCliente();
        gestorCliente.create("Juan", "Perez", 30, 12345678, 0);

        // Leer todos los productos
        List<Producto> productos = gestorProducto.readAll();
        System.out.println("Productos:");
        for (Producto producto : productos) {
            System.out.println(producto);
        }

        // Leer todos los clientes
        List<Cliente> clientes = gestorCliente.readAll();
        System.out.println("Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }

        // Agregar productos al carrito del cliente
        Cliente cliente = gestorCliente.read(12345678);
        if (cliente != null) {
            Producto producto1 = gestorProducto.readOne(1);
            Producto producto2 = gestorProducto.readOne(2);

            if (producto1 != null && producto2 != null) {
                GestorCart gestorCart = new GestorCart();
                gestorCart.addToCart(2, producto1, cliente);
                gestorCart.addToCart(1, producto2, cliente);

                // Leer carrito del cliente
                List<Cart> carts = gestorCart.readByClient(cliente);
                System.out.println("Carrito del cliente:");
                for (Cart cart : carts) {
                    System.out.println(cart);
                }

                // Crear un comprobante para el cliente
                GestorComprobante gestorComprobante = new GestorComprobante();
                gestorComprobante.create(LocalDateTime.now(), cliente);

                // Crear detalle de comprobante
                Comprobante comprobante = new Comprobante(LocalDateTime.now(), cliente);
                GestorDetalleComprobante gestorDetalleComprobante = new GestorDetalleComprobante();
                gestorDetalleComprobante.create(comprobante, producto1, 2, producto1.getPrecio() * 2);
            } else {
                System.out.println("Uno o ambos productos no fueron encontrados.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }

        // Actualizar producto
        gestorProducto.update(1, "Producto 1 Modificado", 150, 15);

        // Eliminar producto
        gestorProducto.delete(2);
    }
}
