/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nk.businesslogic.ResetPasswordService;
import nk.businesslogic.UserService;
import nk.businesslogic.ValidEmailService;
import nk.dataccess.NotesDBException;
import nk.dataccess.UserRepository;
import nk.domainmodel.Note;
import nk.domainmodel.ResetPassword;
import nk.domainmodel.Role;
import nk.domainmodel.User;
import nk.domainmodel.ValidEmail;

/**
 *
 * @author james
 */
public class AccountServlet extends HttpServlet {

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

        String action = request.getParameter("action");
        String logout = request.getParameter("logout");
        String validUuid = request.getParameter("valid");
        String resetUuid = request.getParameter("reset");

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        String message = null;
        if (validUuid != null && !validUuid.isEmpty()) {
            ValidEmailService vs = new ValidEmailService();
            UserService us = new UserService();
            UserRepository ur = new UserRepository();
            try {
                ValidEmail validemail = vs.getValidUserEmailByUuid(validUuid);

                if (validemail != null) {
                    User user = us.getUser(validemail.getUsername());
                    user.setActivate(1);
                    int result = ur.update(user);
                    if (result == 1) {
                        vs.delete(validUuid);
                        us.welcomeUser(request, validemail.getUsername());
                        message = "Account is activated, Please sign in!";
                        request.setAttribute("displayMessage", message);
                        getServletContext().getRequestDispatcher("/WEB-INF/account/login.jsp").forward(request, response);
                    }

                } else {
                    message = "Account was activated, Please sign in!";
                    request.setAttribute("displayMessage", message);
                    getServletContext().getRequestDispatcher("/WEB-INF/account/login.jsp").forward(request, response);
                }
            } catch (NotesDBException ex) {
                Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (resetUuid != null && !resetUuid.isEmpty()) {
            request.setAttribute("resetUuid", resetUuid);
            getServletContext().getRequestDispatcher("/WEB-INF/account/resetNewPassword.jsp").forward(request, response);
        } else {

            if (loginUser != null) {

                if (logout != null) {

                    message = "User logged out.";
                    request.setAttribute("displayMessage", message);
                    session.invalidate();

                    getServletContext().getRequestDispatcher("/WEB-INF/account/login.jsp").forward(request, response);
                } else {

                    if (action != null && action.equals("change")) {

                        getServletContext().getRequestDispatcher("/WEB-INF/account/edit.jsp").forward(request, response);

                    } else {
                        getServletContext().getRequestDispatcher("/WEB-INF/account/view.jsp").forward(request, response);
                    }
                }
            } else {
                if (action != null && action.equals("signup")) {
                    getServletContext().getRequestDispatcher("/WEB-INF/account/signUp.jsp").forward(request, response);
                } else if (action != null && action.equals("resetPassword")) {
                    getServletContext().getRequestDispatcher("/WEB-INF/account/resetPassword.jsp").forward(request, response);
                } else {
                    getServletContext().getRequestDispatcher("/WEB-INF/account/login.jsp").forward(request, response);
                }
            }
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

        String message = null;

        UserService us = new UserService();
        HttpSession session = request.getSession();
        User user = null;

        log(action);
        
        if (action == null) {
            if (!username.isEmpty() && !password.isEmpty()) {

                try {
                    user = us.getUser(username);
                } catch (NotesDBException ex) {
                    Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (user != null) {
                    if (user.getActivate() != 0) {

                        if (user.getPassword().equals(password)) {

                            session.setAttribute("loginUser", user);

                            if (!user.getRoleList().isEmpty()) {
                                response.sendRedirect("admin/users");

                            } else {
                                response.sendRedirect("notes");
                            }

                        } else {
                            message = "Invalid username or password";
                            request.setAttribute("displayMessage", message);
                            request.setAttribute("loginError", "error");
                            getServletContext().getRequestDispatcher("/WEB-INF/account/login.jsp").forward(request, response);
                        }

                    } else {
                        message = "Please activate your account!";
                        request.setAttribute("displayMessage", message);
                        getServletContext().getRequestDispatcher("/WEB-INF/account/login.jsp").forward(request, response);
                    }

                } else {
                    message = "Invalid username or password";
                    request.setAttribute("displayMessage", message);
                    request.setAttribute("loginError", "error");
                    getServletContext().getRequestDispatcher("/WEB-INF/account/login.jsp").forward(request, response);
                }

            } else {
                message = "Both values are required";
                request.setAttribute("displayMessage", message);
                request.setAttribute("loginError", "error");
                getServletContext().getRequestDispatcher("/WEB-INF/account/login.jsp").forward(request, response);
            }
        } else {

            if (action.equals("save")) {
                try {
                    us.update(username, firstname, lastname, password, phonenumber, gender, permission, (User) session.getAttribute("loginUser"));
                    session.setAttribute("loginUser", us.getUser(username));
                    response.sendRedirect("account");
                } catch (NotesDBException ex) {
                    Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equals("delete")) {
                try {
                    us.delete(username);
                    request.setAttribute("displayMessage", "Account is deleted");
                    session.removeAttribute("loginUser");
                    getServletContext().getRequestDispatcher("/WEB-INF/account/login.jsp").forward(request, response);
                } catch (NotesDBException ex) {
                    request.setAttribute("error", "delete");
                    Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equals("signup")) {
                try {
                    int row = us.insert(username, password, email, firstname, lastname, phonenumber, gender);
                    if (row == 1) {
                        request.setAttribute("displayMessage", "Account is registered,<br> Activate your account and sign in!");
                        us.validateUser(request, username);
                        getServletContext().getRequestDispatcher("/WEB-INF/account/login.jsp").forward(request, response);
                    } else {
                        request.setAttribute("error", "All values are required");
                        getServletContext().getRequestDispatcher("/WEB-INF/account/signUp.jsp").forward(request, response);
                    }
                } catch (NotesDBException ex) {
                    Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equals("resetPassword")) {
                if (us.resetPassword(request, email)) {
                    request.setAttribute("displayMessage", "Email to reset password is sent, <br> Please check your email!");
                    getServletContext().getRequestDispatcher("/WEB-INF/account/login.jsp").forward(request, response);

                } else {
                    request.setAttribute("displayMessage", "Please enter valid email address you used for your account");
                    getServletContext().getRequestDispatcher("/WEB-INF/account/resetPassword.jsp").forward(request, response);
                }

            } else if (action.equals("resetNewPassword")) {
                String uuid = request.getParameter("resetUuid");
                String newPassword = request.getParameter("resetNewPassword");
                ResetPasswordService rs = new ResetPasswordService();
                try {
                    ResetPassword resetpassword = rs.getResetPasswordlByUuid(uuid);
                    if (resetpassword != null) {
                        Date resetDate = resetpassword.getResetTime();
                        Calendar cal = Calendar.getInstance();
                        Date currentDate = new Date(cal.getTimeInMillis());

                        long spendTime = (currentDate.getTime() - resetDate.getTime()) / 1000 / 60 / 60;

                        if (spendTime > 24) {
                            request.setAttribute("displayMessage", "Your link is expired or used once. Please try to reset password again.");
                            getServletContext().getRequestDispatcher("/WEB-INF/account/resetPassword.jsp").forward(request, response);
                        } else {
                            User resetUser = us.getUser(resetpassword.getUsername());
                            resetUser.setPassword(newPassword);

                            if (us.update(resetUser) == 1) {

                                rs.delete(uuid);
                                us.notifyUserPassword(request, resetpassword.getUsername());
                                request.setAttribute("displayMessage", "Your password was reset,<br> Please sign in again.");
                                getServletContext().getRequestDispatcher("/WEB-INF/account/login.jsp").forward(request, response);
                            } else {
                                request.setAttribute("displayMessage", "Error occured, Please enter your new password again");
                                getServletContext().getRequestDispatcher("/WEB-INF/account/resetPassword.jsp").forward(request, response);
                            }

                        }

                    } else {
                        request.setAttribute("displayMessage", "Your link is expired or used once. Please try to reset password again.");
                        getServletContext().getRequestDispatcher("/WEB-INF/account/resetPassword.jsp").forward(request, response);
                    }

                } catch (NotesDBException ex) {
                    Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (action.equals("checkUser")) {

                String noteID = request.getParameter("noteID");
                User loginUser = (User) session.getAttribute("loginUser");

                try {
                    user = us.getUser(username);

                } catch (NotesDBException ex) {
                    Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (user != null && !username.equals(loginUser.getUsername())) {

                    if (user.getCollaborateNoteList() != null) {
                        List<Note> notes = user.getCollaborateNoteList();
                        for (Note note : notes) {

                            if (note.getNoteid() == Integer.parseInt(noteID)) {
                                response.getWriter().write("false");
                                return;
                            }
                        }
                    }

                    response.getWriter().write("true");
                } else {
                    response.getWriter().write("false");
                }

            }
        }
    }

}
