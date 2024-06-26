package Primera_Entrega.managers;

import jakarta.persistence.*;

public class Manager {
    //Manager genérico
    private static final String PERSISTENCE_UNIT_NAME = "commercePU";
    private static EntityManagerFactory factory;
    static {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    public static EntityManager get(){
        return factory.createEntityManager();
    }
    public static void close(){
        if(factory != null && factory.isOpen()){
            factory.close();
        }
    }
    public static void closeEntity(EntityManager entityManager){
        if(entityManager != null && entityManager.isOpen()){
            entityManager.close();
        }
    }
}