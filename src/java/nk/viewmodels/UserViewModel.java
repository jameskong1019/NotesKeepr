/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.viewmodels;

import nk.domainmodel.User;

/**
 *
 * @author james
 */
public class UserViewModel {

    private String username;

    public UserViewModel(User user) {
        username = user.getUsername();

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
