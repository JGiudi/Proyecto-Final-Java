package Primera_Entrega.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class DetalleComprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "comprobante_id")
    private Comprobante comprobante;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Column
    private Integer cantidad;

    @Column
    private Integer preciototal;

    public DetalleComprobante() {}

    public DetalleComprobante(Comprobante comprobante, Producto producto, Integer cantidad, Integer preciototal) {
        this.comprobante = comprobante;
        this.producto = producto;
        this.cantidad = cantidad;
        this.preciototal = preciototal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(int preciototal) {
        this.preciototal = preciototal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleComprobante that = (DetalleComprobante) o;
        return cantidad == that.cantidad && preciototal == that.preciototal && Objects.equals(id, that.id) && Objects.equals(comprobante, that.comprobante) && Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comprobante, producto, cantidad, preciototal);
    }

    @Override
    public String toString() {
        return "DetalleComprobante{" +
                "id=" + id +
                ", comprobante=" + comprobante +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", preciototal=" + preciototal +
                '}';
    }
}
