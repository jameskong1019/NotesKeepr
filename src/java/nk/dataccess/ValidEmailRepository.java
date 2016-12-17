package nk.dataccess;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import nk.dataccess.DBUtil;
import nk.domainmodel.*;

public class ValidEmailRepository {

    public int insert(ValidEmail validuser) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(validuser);
            trans.commit();
            return 1;
        } catch (Exception ex) {

            Logger.getLogger(ValidEmailRepository.class.getName()).log(Level.SEVERE, "Cannot insert " + validuser.toString(), ex);
            trans.rollback();
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }
    
      public ValidEmail getValidUserEmailByUuid(String uuid) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            ValidEmail validemail;
            validemail = em.createNamedQuery("ValidEmail.findByValidUserUUID",ValidEmail.class).setParameter("validUserUUID", uuid).getSingleResult();
            return validemail;
        } catch (Exception ex) {
//            Logger.getLogger(ValidEmailRepository.class.getName()).log(Level.SEVERE, "Cannot read uuid " + uuid, ex);
            return null;
        } finally {
            em.close();
        }
    }

    public int update(Role role) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(role);
            trans.commit();
            return 1;

        } catch (Exception ex) {

            Logger.getLogger(ValidEmailRepository.class.getName()).log(Level.SEVERE, "Cannot insert " + role.toString(), ex);
            trans.rollback();
            throw new NotesDBException();
        } finally {
            em.close();
        }

    }

    public int delete(ValidEmail validemail) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {

            trans.begin();
            em.remove(em.merge(validemail));
            trans.commit();
            return 1;

        } catch (Exception ex) {

            Logger.getLogger(ValidEmailRepository.class.getName()).log(Level.SEVERE, "Cannot delete " + validemail.toString(), ex);
            trans.rollback();
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }

  
    
    public Role getRoleById(int roleId) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Role role;
            role = em.find(Role.class, roleId);

            return role;
        } catch (Exception ex) {
            Logger.getLogger(ValidEmailRepository.class.getName()).log(Level.SEVERE, "Cannot read username " + roleId, ex);
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }

    public List<Role> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {

            List<Role> roles;
            roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();

            return roles;
        } catch (Exception ex) {
            Logger.getLogger(ValidEmailRepository.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }
}
