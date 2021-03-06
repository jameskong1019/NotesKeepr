/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nk.businesslogic.NoteService;
import nk.businesslogic.UserService;
import nk.dataccess.NotesDBException;
import nk.domainmodel.Note;
import nk.domainmodel.User;
import nk.viewmodels.UserList;

/**
 *
 * @author james
 */
public class NotesServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String url = "/WEB-INF/notes/notes.jsp";

        NoteService ns = new NoteService();

        if (loginUser != null) {

            try {
                UserService us = new UserService();
                User user = us.getUser(loginUser.getUsername());

                session.setAttribute("loginUser", user);
                request.setAttribute("noteList", user.getMyNoteList());
                request.setAttribute("noteListSize", user.getMyNoteList().size());

            } catch (NotesDBException ex) {

            }

            // view one user
            String action = request.getParameter("action");
            if (action != null && action.equals("edit")) {
                int noteId = Integer.parseInt(request.getParameter("noteID"));
                try {
                    Note note = ns.getMyNote(noteId);

                    if (note.getOwner().getUsername().equals(loginUser.getUsername())) {
                        request.setAttribute("selectedNote", note);
                    }
                } catch (NotesDBException ex) {
                    request.setAttribute("error", "edit");

                }

                url = "/WEB-INF/notes/editNote.jsp";
            } else if (action != null && action.equals("addNote")) {
                url = "/WEB-INF/notes/addNote.jsp";
            } else if (action != null && action.equals("view")) {
                int noteId = Integer.parseInt(request.getParameter("noteID"));
                try {
                    Note note = ns.getMyNote(noteId);

                    if (note.getOwner().getUsername().equals(loginUser.getUsername())) {
                        request.setAttribute("selectedNote", note);
                    }
                } catch (NotesDBException ex) {
                    request.setAttribute("error", "edit");

                }

                url = "/WEB-INF/notes/viewNote.jsp";
            }

            calculatePagination(request, action);
            getServletContext().getRequestDispatcher(url).forward(request, response);

        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/account/login.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        String action = request.getParameter("action");

        int noteID = 0;
        if (request.getParameter("noteID") != null) {
            noteID = Integer.parseInt(request.getParameter("noteID"));
        }
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");

        NoteService ns = new NoteService();

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "delete":
                try {
                    ns.delete(noteID, loginUser);
                    request.setAttribute("message", "deleted");
                } catch (NotesDBException ex) {
                    request.setAttribute("error", "delete");
                }
                break;

            case "save":
                try {
                    int result = ns.update(noteID, title, contents.replace("style=\"", "style=\"max-width:100%;"), loginUser);
                    if (ajax) {
                        if (result == 1) {
                            response.getWriter().write("true");
                        } else {
                            response.getWriter().write("false");
                        }
                    }
                    request.setAttribute("message", "saved");
                } catch (NotesDBException ex) {
                    request.setAttribute("error", "save");
                }
                break;

            case "add":
                try {
                    int result = ns.insert(title, contents.replace("style=\"", "style=\"max-width:100%;"), (User) session.getAttribute("loginUser"));
                    if (ajax) {
                        if (result == 1) {
                            response.getWriter().write("true");
                        } else {
                            response.getWriter().write("false");
                        }
                    }
                    request.setAttribute("message", "added");
                } catch (NotesDBException ex) {
                    request.setAttribute("error", "add");
                }
                break;

            case "addCollaborator":
                try {
                    String username = request.getParameter("username");
                    int result = ns.addCollaborator(noteID, username);
                    if (result == 1) {
                        response.getWriter().write("true");
                    } else {
                        response.getWriter().write("false");
                    }
                } catch (NotesDBException ex) {
                    Logger.getLogger(NotesServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;

            case "removeCollaborator":
                try {
                    String username = request.getParameter("username");
                    int result = ns.removeCollaborator(noteID, username);
                    if (result == 1) {
                        Note note = ns.getMyNote(noteID);

                        UserList ul = new UserList(note);
                        Gson gson = new Gson();
                        String userString = gson.toJson(ul);
                        response.getWriter().write(userString);

                    } else {
                        response.getWriter().write("false");
                    }
                } catch (NotesDBException ex) {
                    Logger.getLogger(NotesServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
        }

        try {
            UserService us = new UserService();
            User user = us.getUser(loginUser.getUsername());

            session.setAttribute("loginUser", user);

            request.setAttribute("noteList", user.getMyNoteList());
            request.setAttribute("noteListSize", user.getMyNoteList().size());

        } catch (NotesDBException ex) {

        }
        if (!ajax) {
            calculatePagination(request, action);
            getServletContext().getRequestDispatcher("/WEB-INF/notes/notes.jsp").forward(request, response);
        }
    }

    final int ITEMSIZE = 10;

    public void calculatePagination(HttpServletRequest request, String action) {

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        int size = loginUser.getMyNoteList().size();

        int currentPageNumber = 1;
        int totalPageNumber = 0;
        int startPageNumber = 0;
        int endPageNumber = 0;

        String page = request.getParameter("page");
        if (page != null) {
            currentPageNumber = Integer.parseInt(page);
        } else {
            currentPageNumber = 1;
        }

        if (size % ITEMSIZE != 0) {
            totalPageNumber = size / ITEMSIZE + 1;
        } else if (size % ITEMSIZE == 0) {
            totalPageNumber = size / ITEMSIZE;
        }

        if (totalPageNumber <= 5) {
            startPageNumber = 1;
            endPageNumber = totalPageNumber;
        } else if (totalPageNumber > 5) {
            startPageNumber = currentPageNumber != 1 ? (currentPageNumber - 5 > 1 ? currentPageNumber - 5 : 1) : 1;
            endPageNumber = startPageNumber + 9;
        }

        if (totalPageNumber < currentPageNumber) {
            currentPageNumber = currentPageNumber - 1;
        }

        if (action != null) {
            if (action.equals("add")) {
                request.setAttribute("page", totalPageNumber);
            } else {
                request.setAttribute("page", currentPageNumber);
            }
        } else {
            request.setAttribute("page", currentPageNumber);
        }

        request.setAttribute("startPageNumber", startPageNumber);
        request.setAttribute("endPageNumber", endPageNumber);
        request.setAttribute("totalPageNumber", totalPageNumber);
        request.setAttribute("ITEMSIZE", ITEMSIZE);
        request.setAttribute("userSize", size);
    }
}
