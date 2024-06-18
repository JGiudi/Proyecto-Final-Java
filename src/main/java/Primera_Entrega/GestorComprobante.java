package Primera_Entrega;

import jakarta.persistence.*;

public class GestorComprobante {
    public void create(int fecha, Cliente cliente) {
        EntityManager manager = GestorGenerico.getEntityManager();
        manager.getTransaction().begin();
        Comprobante comprobante = new Comprobante(fecha, cliente);
        manager.persist(comprobante);
        manager.getTransaction().commit();
        manager.close();
    }
}
