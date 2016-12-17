package nk.dataccess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import nk.domainmodel.*;

public class UserRepository {

    public int insert(User user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
            return 1;
        } catch (Exception ex) {

            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, "Cannot insert " + user.toString(), ex);
            trans.rollback();
//            throw new NotesDBException();
            return -1;
        } finally {
            em.close();
        }
    }

    public int update(User user, User loginUser) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {

            if (loginUser.getRoleList() != null && loginUser.getUsername().equals(user.getUsername())) {
                loginUser.setRoleList(user.getRoleList());
                loginUser.setMyNoteList(user.getMyNoteList());
            } else {
                loginUser.setRoleList(loginUser.getRoleList());
                loginUser.setMyNoteList(loginUser.getMyNoteList());
            }
            
            trans.begin();
            em.merge(user);
            trans.commit();
            return 1;

        } catch (Exception ex) {

            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, "Cannot insert " + user.toString(), ex);
            trans.rollback();
            throw new NotesDBException();
        } finally {
            em.close();
        }

    }
    
      public int update(User user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            
            trans.begin();
            em.merge(user);
            trans.commit();
            return 1;

        } catch (Exception ex) {

            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, "Cannot insert " + user.toString(), ex);
            trans.rollback();
            throw new NotesDBException();
        } finally {
            em.close();
        }

    }

    public int delete(User user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {

            trans.begin();
            em.remove(em.merge(user));
            trans.commit();
            return 1;

        } catch (Exception ex) {

            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, "Cannot insert " + user.toString(), ex);
            trans.rollback();
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }

    public User getUser(String username) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User user;
            user = em.find(User.class, username);
            return user;
        } catch (Exception ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, "Cannot read username " + username, ex);
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }
    
     public User getUserByEmail(String email) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User user;
            user = em.createNamedQuery("User.findByEmail",User.class).setParameter("email", email).getSingleResult();

            return user;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<User> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {

            List<User> users;
            users = em.createNamedQuery("User.findAll", User.class).getResultList();

            return users;
        } catch (Exception ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }
}
