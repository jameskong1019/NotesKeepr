package nk.dataccess;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import nk.dataccess.DBUtil;
import nk.domainmodel.*;

public class ResetPasswordRepository {

    public int insert(ResetPassword validuser) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(validuser);
            trans.commit();
            return 1;
        } catch (Exception ex) {
//            trans.rollback();
        } finally {
            em.close();
        }
        return 0;

    }

    public ResetPassword getResetPasswordlByUuid(String uuid) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            ResetPassword resetpassword;
            resetpassword = em.createNamedQuery("ResetPassword.findByResetPasswordUUID", ResetPassword.class).setParameter("resetPasswordUUID", uuid).getSingleResult();
            return resetpassword;
        } catch (Exception ex) {
//            Logger.getLogger(ValidEmailRepository.class.getName()).log(Level.SEVERE, "Cannot read uuid " + uuid, ex);
            return null;
        } finally {
            em.close();
        }
    }

    public ResetPassword getResetPassword(String username) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            ResetPassword resetpassword;
            resetpassword = em.find(ResetPassword.class, username);

            return resetpassword;
        } catch (Exception ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, "Cannot read username " + username, ex);
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }

    public int delete(ResetPassword resetpassword) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {

            trans.begin();
            em.remove(em.merge(resetpassword));
            trans.commit();
            return 1;

        } catch (Exception ex) {

            Logger.getLogger(ResetPasswordRepository.class.getName()).log(Level.SEVERE, "Cannot delete " + resetpassword.toString(), ex);
            trans.rollback();
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }
}
