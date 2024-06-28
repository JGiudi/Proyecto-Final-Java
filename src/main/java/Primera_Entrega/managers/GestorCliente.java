package Primera_Entrega.managers;

import Primera_Entrega.entities.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class GestorCliente {

    public void create(String nombre, String apellido, Integer edad, Integer dni, Integer puntos) {
        EntityManager manager = Manager.get();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Cliente clienteExistente = manager.find(Cliente.class, dni);
            if (clienteExistente != null) {
                System.out.println("Ya existe un cliente con DNI: " + dni);
            } else {
                Cliente cliente = new Cliente(nombre, apellido, edad, dni, puntos);
                manager.persist(cliente);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error al crear cliente: " + e.getMessage());
        } finally {
            manager.close();
        }
    }

    public List<Cliente> readAll() {
        EntityManager manager = Manager.get();
        List<Cliente> lista = null;
        try {
            TypedQuery<Cliente> query = manager.createQuery("SELECT c FROM Cliente c", Cliente.class);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al leer todos los clientes: " + e.getMessage());
        } finally {
            manager.close();
        }
        return lista;
    }

    public Cliente read(int dni) {
        EntityManager manager = Manager.get();
        Cliente cliente = null;
        try {
            cliente = manager.find(Cliente.class, dni);
        } catch (Exception e) {
            System.out.println("Error al buscar cliente por DNI: " + e.getMessage());
        } finally {
            manager.close();
        }
        return cliente;
    }

    public void update(String nombre, String apellido, int edad, int dni, int puntos) {
        EntityManager manager = Manager.get();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Cliente cliente = manager.find(Cliente.class, dni);
            if (cliente != null) {
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setEdad(edad);
                cliente.setPuntos(puntos);
                transaction.commit();
            } else {
                System.out.println("Cliente no encontrado con DNI: " + dni);
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        } finally {
            manager.close();
        }
    }

    public void delete(int dni) {
        EntityManager manager = Manager.get();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Cliente cliente = manager.find(Cliente.class, dni);
            if (cliente != null) {
                manager.remove(cliente);
                transaction.commit();
            } else {
                System.out.println("Cliente no encontrado con DNI: " + dni);
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        } finally {
            manager.close();
        }
    }
}
