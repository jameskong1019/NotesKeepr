package nk.dataccess;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import nk.dataccess.DBUtil;
import nk.domainmodel.*;

public class RoleRepository {

    public int insert(Role role) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(role);
            trans.commit();
            return 1;
        } catch (Exception ex) {

            Logger.getLogger(RoleRepository.class.getName()).log(Level.SEVERE, "Cannot insert " + role.toString(), ex);
            trans.rollback();
            throw new NotesDBException();
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

            Logger.getLogger(RoleRepository.class.getName()).log(Level.SEVERE, "Cannot insert " + role.toString(), ex);
            trans.rollback();
            throw new NotesDBException();
        } finally {
            em.close();
        }

    }

    public int delete(Role role) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {

            trans.begin();
            em.remove(em.merge(role));
            trans.commit();
            return 1;

        } catch (Exception ex) {

            Logger.getLogger(RoleRepository.class.getName()).log(Level.SEVERE, "Cannot insert " + role.toString(), ex);
            trans.rollback();
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }

    public Role getRoleByName(String rolename) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Role role;
            role = em.find(Role.class, rolename);

            return role;
        } catch (Exception ex) {
            Logger.getLogger(RoleRepository.class.getName()).log(Level.SEVERE, "Cannot read username " + rolename, ex);
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
            Logger.getLogger(RoleRepository.class.getName()).log(Level.SEVERE, "Cannot read username " + roleId, ex);
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
            Logger.getLogger(RoleRepository.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }
}
