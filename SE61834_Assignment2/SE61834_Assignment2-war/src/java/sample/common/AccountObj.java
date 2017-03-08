/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.common;

import java.io.Serializable;

/**
 *
 * @author PhucTDSE61834
 */
public class AccountObj implements Serializable {

    private String username;
    private int role;

    public AccountObj() {
    }

    public AccountObj(String username, int role) {
        this.username = username;
        this.role = role;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the role
     */
    public int getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(int role) {
        this.role = role;
    }

}
