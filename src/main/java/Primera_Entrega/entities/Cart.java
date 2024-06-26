package Primera_Entrega.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;
    private double price;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto_id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente_id;

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Producto getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Producto producto_id) {
        this.producto_id = producto_id;
    }

    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id == cart.id && amount == cart.amount && Double.compare(cart.price, price) == 0 && Objects.equals(producto_id, cart.producto_id) && Objects.equals(cliente_id, cart.cliente_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, price, producto_id, cliente_id);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", amount=" + amount +
                ", price=" + price +
                ", producto_id=" + producto_id +
                ", cliente_id=" + cliente_id +
                '}';
    }
}
