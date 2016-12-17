/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.viewmodels;

import java.util.ArrayList;
import java.util.List;
import nk.domainmodel.Note;
import nk.domainmodel.User;

/**
 *
 * @author james
 */
public class UserList {

    private List<UserViewModel> userlist;

    public UserList(Note note) {

        userlist = new ArrayList<UserViewModel>();

        for (User user : note.getUserList()) {
            UserViewModel uservm = new UserViewModel(user);
            userlist.add(uservm);
        }

    }

    public List<UserViewModel> getUserlist() {
        return userlist;
    }
}
