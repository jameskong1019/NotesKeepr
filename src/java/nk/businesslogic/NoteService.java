/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.businesslogic;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import nk.dataccess.NoteRepository;
import nk.dataccess.NotesDBException;
import nk.dataccess.UserRepository;
import nk.domainmodel.Note;
import nk.domainmodel.User;

/**
 *
 * @author james
 */
public class NoteService {

    private List<Note> noteList;

    NoteRepository nr;

    public NoteService() {
        nr = new NoteRepository();
    }

    public List<Note> getMyNoteAll() throws NotesDBException {
        noteList = nr.getMyNoteAll();
        return noteList;
    }

    public List<Note> getCollaboratedNoteAll() throws NotesDBException {
        noteList = nr.getCollaboratedNoteAll();
        return noteList;
    }

    public int insert(String title, String contents, User user) throws NotesDBException {
        Calendar cal = Calendar.getInstance();
        Date date = new Date(cal.getTimeInMillis());
        Note note = new Note();
        note.setTitle(title);
        note.setNoteid(0);
        note.setDatecreated(date);
        note.setContents(contents);
        note.setOwner(user);
        int rows = nr.insert(note);
        return rows;
    }

    public int update(int noteId, String title, String contents, User loginUser) throws NotesDBException {
        Calendar cal = Calendar.getInstance();
        Date date = new Date(cal.getTimeInMillis());
        Note note = nr.getMyNote(noteId);
        note.setTitle(title);
        note.setDatecreated(date);
        note.setContents(contents);
        return nr.update(note);
    }

    public Note getMyNote(int noteId) throws NotesDBException {
        Note note = nr.getMyNote(noteId);
        return note;
    }

    public Note getCollaboratedNote(int noteId) throws NotesDBException {
        Note note = nr.getCollaboratedNote(noteId);
        return note;
    }

    public int addCollaborator(int noteId, String username) throws NotesDBException {

        UserRepository ur = new UserRepository();
        User collaborator = ur.getUser(username);

        Note note = getMyNote(noteId);
        List<User> userlist = note.getUserList();
        userlist.add(collaborator);

        note.setUserList(userlist);

        return nr.addCollaborator(note, collaborator);
    }

    public int removeCollaborator(int noteId, String username) throws NotesDBException {

        UserRepository ur = new UserRepository();
        User collaborator = ur.getUser(username);

        Note note = getMyNote(noteId);
        List<User> userlist = note.getUserList();
        userlist.remove(collaborator);

        note.setUserList(userlist);

        return nr.removeCollaborator(note, collaborator);
    }

    public int delete(int noteId, User user) throws NotesDBException {
//        Calendar cal = Calendar.getInstance();
//        Date date = new Date(cal.getTimeInMillis());
//        Note note = new Note();
//        note.setNoteid(noteId);
//        note.setTitle("");
//        note.setContents("");
//        note.setDatecreated(date);
//        note.setOwner(user);

        Note note = nr.getMyNote(noteId);
        note.setOwner(user);

        return nr.delete(note);
    }
}
