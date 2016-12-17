package nk.businesslogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import nk.dataccess.NotesDBException;
import nk.dataccess.UserRepository;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import nk.dataccess.RoleRepository;
import nk.domainmodel.*;
import nk.servlet.AccountServlet;

public class UserService {

    private List<User> userList;

    UserRepository ur;

    public UserService() {
        ur = new UserRepository();
    }

    public List<User> getAll() throws NotesDBException {
        userList = ur.getAll();
        return userList;
    }

    public int insert(String username, String password, String email, String firstname, String lastname, String phonenumber, String gender) throws NotesDBException {

        User user = new User(username, password, email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhonenumber(phonenumber);
        user.setGender(gender.charAt(0));
        user.setActivate(0);
        int rows = ur.insert(user);
        return rows;
    }

    public int update(User user) throws NotesDBException {
        return ur.update(user);
    }

    public int update(String username,
            String firstname, String lastname, String password, String phonenumber, String gender, String permission, User loginUser) throws NotesDBException {
        User user = null;
        user = ur.getUser(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPassword(password);
        user.setPhonenumber(phonenumber);
        user.setGender(gender.charAt(0));
        user.setActivate(1);

        if (permission != null) {
            return promoteUser(user, loginUser, permission);
        } else {
            return ur.update(user, loginUser);
        }

    }

    public int promoteUser(User user, User loginUser, String permission) {

        Role role = new Role();
        role.setRoleid(1);
        role.setRolename("admin");

        List<User> userList = new ArrayList<User>();
        try {
            List<User> userAllList = ur.getAll();
            for (User addedUser : userAllList) {
                if (!addedUser.getRoleList().isEmpty()) {
                    userList.add(addedUser);

                }
            }
        } catch (NotesDBException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        if (permission.equals("admin")) {
            if (!userList.contains(user)) {
                userList.add(user);
            }
            List<Role> roleList = new ArrayList<>();
            roleList.add(role);
            user.setRoleList(roleList);
        } else {
            userList.remove(user);
            user.setRoleList(null);
        }

        role.setUserList(userList);
        RoleRepository rr = new RoleRepository();

        try {
            rr.update(role);

        } catch (NotesDBException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        UserRepository ur = new UserRepository();
        try {
            return ur.update(user, loginUser);

        } catch (NotesDBException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public User getUser(String username) throws NotesDBException {
        User user = ur.getUser(username);
        return user;
    }

    public User getUserByEmail(String email) throws NotesDBException {
        User user = ur.getUserByEmail(email);
        return user;
    }

    public int delete(String username) throws NotesDBException {
        User user = ur.getUser(username);
        return ur.delete(user);
    }

    public boolean resetPassword(HttpServletRequest request, String email) {
        String template;
        template = request.getServletContext().getRealPath("/WEB-INF/emailtemplates/resetpassword.html");
        HashMap<String, String> contents = new HashMap<>();

        UserService us = new UserService();

        UUID uuidObject = UUID.randomUUID();

        String uuid = uuidObject.toString();
        String link = request.getRequestURL().toString() + "?reset=" + uuid;

        ResetPasswordService rs = new ResetPasswordService();

        try {
            User user = ur.getUserByEmail(email);

            if (user != null) {
                ResetPassword resetpassword = rs.getResetPassword(user.getUsername());
                if (resetpassword != null) {

                    rs.delete(resetpassword.getResetPasswordUUID());
                    rs.insert(user, uuid);
                    contents.put("username", user.getUsername());
                    contents.put("firstname", user.getFirstname());
                    contents.put("lastname", user.getLastname());
                    contents.put("link", link);

                    WebMailService.sendMail(request, user.getEmail(), "Reset password", template, contents);
                    return true;
                } else {
                    rs.insert(user, uuid);
                    contents.put("username", user.getUsername());
                    contents.put("firstname", user.getFirstname());
                    contents.put("lastname", user.getLastname());
                    contents.put("link", link);

                    WebMailService.sendMail(request, user.getEmail(), "Reset password", template, contents);
                    return true;
                }
            }
        } catch (NotesDBException ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
     public boolean welcomeUser(HttpServletRequest request, String username) {
        String template;
        template = request.getServletContext().getRealPath("/WEB-INF/emailtemplates/welcome.html");
        HashMap<String, String> contents = new HashMap<>();

        UserService us = new UserService();

        try {
            User user = us.getUser(username);

            if (user != null) {
                contents.put("firstname", user.getFirstname());
                contents.put("lastname", user.getLastname());

                WebMailService.sendMail(request, user.getEmail(), "Welcome, " + user.getUsername(), template, contents);
                return true;
            }
        } catch (NotesDBException ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean notifyUserPassword(HttpServletRequest request, String username) {
        String template;
        template = request.getServletContext().getRealPath("/WEB-INF/emailtemplates/notificationResetPassword.html");
        HashMap<String, String> contents = new HashMap<>();

        UserService us = new UserService();

        try {
            User user = us.getUser(username);

            if (user != null) {
                contents.put("firstname", user.getFirstname());
                contents.put("lastname", user.getLastname());

                WebMailService.sendMail(request, user.getEmail(), "You have been reset your password", template, contents);
                return true;
            }
        } catch (NotesDBException ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean validateUser(HttpServletRequest request, String username) {
        String template;
        template = request.getServletContext().getRealPath("/WEB-INF/emailtemplates/validation.html");
        HashMap<String, String> contents = new HashMap<>();

        UUID uuidObject = UUID.randomUUID();

        String uuid = uuidObject.toString();
        String link = "http://localhost:8080/NotesKeepr/account" + "?valid=" + uuid;

        ValidEmailService vs = new ValidEmailService();

        UserService us = new UserService();

        try {
            User user = us.getUser(username);
            vs.insert(user, uuid);

            if (user != null) {
                contents.put("firstname", user.getFirstname());
                contents.put("lastname", user.getLastname());
                contents.put("link", link);

                WebMailService.sendMail(request, user.getEmail(), "NotesKeepr Validation", template, contents);
                return true;
            }
        } catch (NotesDBException ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
