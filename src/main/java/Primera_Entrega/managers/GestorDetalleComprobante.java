package Primera_Entrega.managers;

import Primera_Entrega.entities.Producto;
import Primera_Entrega.entities.Comprobante;
import Primera_Entrega.entities.DetalleComprobante;
import jakarta.persistence.EntityManager;

public class GestorDetalleComprobante {
    public void create(Comprobante comprobante, Producto producto, int cantidad, int preciototal) {
        EntityManager manager = Manager.get();
        manager.getTransaction().begin();
        DetalleComprobante detalleComprobante = new DetalleComprobante(comprobante, producto, cantidad, preciototal);
        manager.persist(detalleComprobante);
        manager.getTransaction().commit();
        manager.close();
    }
}
