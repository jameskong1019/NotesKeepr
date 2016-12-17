/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nk.businesslogic.UserService;
import nk.dataccess.NotesDBException;
import nk.domainmodel.User;

/**
 *
 * @author james
 */
public class AdminUsersServlet extends HttpServlet {

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
        User user = (User) session.getAttribute("loginUser");

        String url = "/WEB-INF/admin/users.jsp";

        if (user != null) {
            if (user.getRoleList().size() != 0) {
                UserService us = new UserService();
                List<User> userList = null;
                try {
                    userList = us.getAll();
                } catch (NotesDBException ex) {
                    // serious error, something wrong with the database
                    throw new ServletException("Could not obtain list of users");
                }
                request.setAttribute("userList", userList);

                // view one user
                String action = request.getParameter("action");
                if (action != null && action.equals("view")) {
                    String username = request.getParameter("username");
                    try {
                        User selectedUser = us.getUser(username);
                        request.setAttribute("selectedUser", selectedUser);
                    } catch (NotesDBException ex) {
                        request.setAttribute("error", "edit");
                    }

                    url = "/WEB-INF/admin/editUser.jsp";
                } else if (action != null && action.equals("addUser")) {
                    url = "/WEB-INF/admin/addUser.jsp";
                }

                calculatePagination(request, action);
                getServletContext().getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect("/NotesKeepr/notes");
            }
        } else {
            response.sendRedirect("/NotesKeepr/login");
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

        String action = request.getParameter("action");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phonenumber = request.getParameter("phonenumber");
        String gender = request.getParameter("gender");
        String permission = request.getParameter("permission");

        String url = "/WEB-INF/admin/users.jsp";

        HttpSession session = request.getSession();

        UserService us = new UserService();

        if (action == null) {
            action = "";
        }

        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser != null) {
            switch (action) {
                case "delete":
                    try {
                        us.delete(username);
                        request.setAttribute("message", "deleted");
                    } catch (NotesDBException ex) {
                        request.setAttribute("error", "delete");
                    }
                    break;
                case "save":
                    try {
                        us.update(username, firstname, lastname, password, phonenumber, gender, permission, (User) session.getAttribute("loginUser"));
                        request.setAttribute("message", "saved");
                    } catch (NotesDBException ex) {
                        request.setAttribute("error", "save");
                    }
                    break;
                case "add":
                    try {
                        int row = us.insert(username, password, email, firstname, lastname, phonenumber, gender);

                        if (row == 1) {
                            us.validateUser(request, username);
                        }
                        request.setAttribute("message", "added");
                    } catch (NotesDBException ex) {
                        request.setAttribute("error", "add");
                    }
                    break;
            }

            List<User> userList = null;
            try {
                userList = us.getAll();
                request.setAttribute("userList", userList);
            } catch (NotesDBException ex) {
                // serious error, something wrong with the database
                throw new ServletException("Database error.  Could not obtain list of users");
            }
            calculatePagination(request, action);
            getServletContext().getRequestDispatcher(url).forward(request, response);

        } else {
            response.sendRedirect("/NotesKeepr/login");
        }

    }

    final int ITEMSIZE = 10;

    public void calculatePagination(HttpServletRequest request, String action) {

        UserService us = new UserService();

        int size = 0;
        try {

            size = us.getAll().size();

        } catch (NotesDBException e) {

        }

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
        request.setAttribute("noteSize", size);
    }

}
