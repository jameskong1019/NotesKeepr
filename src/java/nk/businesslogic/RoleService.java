package nk.businesslogic;

import nk.dataccess.NotesDBException;
import nk.dataccess.RoleRepository;
import java.util.List;
import nk.domainmodel.*;

public class RoleService {

    private List<Role> roleList;

    RoleRepository rr;

    public RoleService() {
        rr = new RoleRepository();
    }

    public List<Role> getAll() throws NotesDBException {
        roleList = rr.getAll();
        return roleList;
    }

    public int insert(Role role) throws NotesDBException {

        int rows = rr.insert(role);
        return rows;
    }

//    public int update(String username, String password, String email,
//            String firstname, String lastname, String phonenumber, String gender) throws NotesDBException {
//        User user = new User(username, password, email);
//        user.setFirstname(firstname);
//        user.setLastname(lastname);
//        user.setPhonenumber(phonenumber);
//        user.setGender(gender.charAt(0));
//        return rr.update(user);
//    }
    public Role get(String rolename) throws NotesDBException {
        Role role = rr.getRoleByName(rolename);
        return role;
    }

    public Role get(int roleid) throws NotesDBException {
        Role role = rr.getRoleById(roleid);
        return role;
    }

//    public int delete(String username) throws NotesDBException {
//        User user = rr.getUser(username);
//        return rr.delete(user);
//    }
}
