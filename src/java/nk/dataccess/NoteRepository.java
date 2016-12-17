/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.dataccess;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import nk.businesslogic.NoteService;
import nk.domainmodel.Note;
import nk.domainmodel.User;

/**
 *
 * @author james
 */
public class NoteRepository {

    public int insert(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            User owner = note.getOwner();
            owner.getMyNoteList().add(0, note);

            trans.begin();
            em.merge(owner);
            em.persist(note);
            trans.commit();
            return 1;
        } catch (Exception ex) {

            Logger.getLogger(NoteRepository.class.getName()).log(Level.SEVERE, "Cannot insert " + note.toString(), ex);
            trans.rollback();
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }

    public int update(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(note);
            trans.commit();
            return 1;

        } catch (Exception ex) {

            Logger.getLogger(NoteRepository.class.getName()).log(Level.SEVERE, "Cannot insert " + note.toString(), ex);
            trans.rollback();
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }

    public int addCollaborator(Note note, User collaborator) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {

            collaborator.getCollaborateNoteList().add(0, note);

            trans.begin();
            em.merge(note);
            em.merge(collaborator);
            trans.commit();
            return 1;

        } catch (Exception ex) {

            Logger.getLogger(NoteRepository.class.getName()).log(Level.SEVERE, "Cannot insert " + note.toString(), ex);
            trans.rollback();
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }

    public int removeCollaborator(Note note, User collaborator) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {

            collaborator.getCollaborateNoteList().remove(note);

            trans.begin();
            em.merge(note);
            em.merge(collaborator);
            trans.commit();
            return 1;

        } catch (Exception ex) {

            Logger.getLogger(NoteRepository.class.getName()).log(Level.SEVERE, "Cannot insert " + note.toString(), ex);
            trans.rollback();
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }

    public int delete(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            User owner = note.getOwner();
            owner.getMyNoteList().remove(note);  // remove the note from the owner’s notes

            List<User> collaborators = note.getUserList();

            for (User collaborator : collaborators) {

                removeCollaborator(note, collaborator);

            }

            trans.begin();
            em.merge(owner);	// update the entity manager with the changed owner object
            em.remove(em.merge(note));	// remove the note
            trans.commit();
            return 1;
        } finally {
            em.close();
        }

    }

    public Note getMyNote(int noteId) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Note note;
            note = em.find(Note.class, noteId);

            return note;
        } catch (Exception ex) {
            Logger.getLogger(NoteRepository.class.getName()).log(Level.SEVERE, "Cannot read username " + noteId, ex);
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }

    public Note getCollaboratedNote(int noteId) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Note note;
            note = em.find(Note.class, noteId);

            return note;
        } catch (Exception ex) {
            Logger.getLogger(NoteRepository.class.getName()).log(Level.SEVERE, "Cannot read username " + noteId, ex);
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }

    public List<Note> getMyNoteAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {

            List<Note> notes;
            notes = em.createNamedQuery("Note.findAll", Note.class).getResultList();

            return notes;
        } catch (Exception ex) {
            Logger.getLogger(NoteRepository.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }

    public List<Note> getCollaboratedNoteAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {

            List<Note> notes;
            notes = em.createNamedQuery("Note.findAll", Note.class).getResultList();

            return notes;
        } catch (Exception ex) {
            Logger.getLogger(NoteRepository.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new NotesDBException();
        } finally {
            em.close();
        }
    }
}
