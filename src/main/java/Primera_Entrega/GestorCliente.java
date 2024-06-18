package Primera_Entrega;

import jakarta.persistence.*;

import java.util.List;

public class GestorCliente {
    public void create (String nombre, String apellido, int edad, int dni, int puntos) {
        EntityManager manager = GestorGenerico.getEntityManager();
        manager.getTransaction().begin();
        Cliente cliente = new Cliente(nombre,apellido,edad,dni,puntos);
        manager.persist(cliente);
        manager.getTransaction().commit();
        manager.close();
    }
}
