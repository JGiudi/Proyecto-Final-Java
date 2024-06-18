package Primera_Entrega;

import jakarta.persistence.EntityManager;

public class GestorDetalleComprobante {
    public void create(Comprobante comprobante, Producto producto, int cantidad, int preciototal) {
        EntityManager manager = GestorGenerico.getEntityManager();
        manager.getTransaction().begin();
        DetalleComprobante detalleComprobante = new DetalleComprobante(comprobante, producto, cantidad, preciototal);
        manager.persist(detalleComprobante);
        manager.getTransaction().commit();
        manager.close();
    }
}
