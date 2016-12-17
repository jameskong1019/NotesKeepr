package nk.dataccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {

    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("NotesKeeperPU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }

    public static void closeEmFactory() {
        emf.close();
    }
}
