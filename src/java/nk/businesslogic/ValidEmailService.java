package nk.businesslogic;

import nk.dataccess.NotesDBException;
import nk.dataccess.RoleRepository;
import java.util.List;
import nk.dataccess.ValidEmailRepository;
import nk.domainmodel.*;

public class ValidEmailService {

    ValidEmailRepository vr;

    public ValidEmailService() {
        vr = new ValidEmailRepository();
    }

    public int insert(User user, String uuid) throws NotesDBException {
        
        ValidEmail validuser = new ValidEmail(user.getUsername(), uuid);
        int rows = vr.insert(validuser);
        return rows;
    }
    
     public ValidEmail getValidUserEmailByUuid(String uuid) throws NotesDBException {
        ValidEmail validuser = vr.getValidUserEmailByUuid(uuid);
        return validuser;
    }

    public int delete(String uuid) throws NotesDBException {
        ValidEmail validemail = vr.getValidUserEmailByUuid(uuid);
        return vr.delete(validemail);
    }
}
