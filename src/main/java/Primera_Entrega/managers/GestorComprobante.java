package Primera_Entrega.managers;

import Primera_Entrega.entities.Cliente;
import Primera_Entrega.entities.Comprobante;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class GestorComprobante {
    public void create(LocalDateTime fecha, Cliente cliente) {
        EntityManager manager = Manager.get();
        manager.getTransaction().begin();
        Comprobante comprobante = new Comprobante(fecha, cliente);
        manager.persist(comprobante);
        manager.getTransaction().commit();
        manager.close();
    }
}
