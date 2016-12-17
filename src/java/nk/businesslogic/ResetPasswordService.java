package nk.businesslogic;

import java.util.Calendar;
import java.util.Date;
import nk.dataccess.NotesDBException;
import nk.dataccess.RoleRepository;
import java.util.List;
import nk.dataccess.ResetPasswordRepository;
import nk.dataccess.ValidEmailRepository;
import nk.domainmodel.*;

public class ResetPasswordService {

    ResetPasswordRepository rr;

    public ResetPasswordService() {
        rr = new ResetPasswordRepository();
    }

    public int insert(User user, String uuid) throws NotesDBException {

        Calendar cal = Calendar.getInstance();
        Date date = new Date(cal.getTimeInMillis());
        ResetPassword resetpassword = new ResetPassword(user.getUsername(), date, uuid);
        int rows = rr.insert(resetpassword);
        return rows;
    }

    public ResetPassword getResetPasswordlByUuid(String uuid) throws NotesDBException {
        ResetPassword resetpassword = rr.getResetPasswordlByUuid(uuid);
        return resetpassword;
    }
    
    public ResetPassword getResetPassword(String username) throws NotesDBException {
        ResetPassword resetpassword = rr.getResetPassword(username);
        return resetpassword;
    }

    public int delete(String uuid) throws NotesDBException {
        ResetPassword resetpassword = rr.getResetPasswordlByUuid(uuid);
        return rr.delete(resetpassword);
    }
}
