package Primera_Entrega.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private Integer edad;

    @Column
    private Integer dni;

    @Column
    private Integer puntos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comprobante> comprobantes;

    @OneToMany(mappedBy = "cliente_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts;

    public Cliente() {}

    public Cliente(String nombre, String apellido, Integer edad, Integer dni, Integer puntos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.puntos = puntos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public List<Comprobante> getComprobantes() {
        return comprobantes;
    }

    public void setComprobantes(List<Comprobante> comprobantes) {
        this.comprobantes = comprobantes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) &&
                Objects.equals(nombre, cliente.nombre) &&
                Objects.equals(apellido, cliente.apellido) &&
                Objects.equals(edad, cliente.edad) &&
                Objects.equals(dni, cliente.dni) &&
                Objects.equals(puntos, cliente.puntos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, edad, dni, puntos);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", dni=" + dni +
                ", puntos=" + puntos +
                '}';
    }
}
