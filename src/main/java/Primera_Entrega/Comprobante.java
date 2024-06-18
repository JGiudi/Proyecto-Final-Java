package Primera_Entrega;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Comprobante {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int fecha;

    @Column
    private Cliente cliente;

    public Comprobante(){}
    public Comprobante(int fecha, Cliente cliente) {
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comprobante that = (Comprobante) o;
        return fecha == that.fecha && Objects.equals(id, that.id) && Objects.equals(cliente, that.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, cliente);
    }
}
