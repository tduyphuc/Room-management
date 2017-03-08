/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.tbl_account;

import javax.ejb.Remote;

/**
 *
 * @author PhucTDSE61834
 */
@Remote
public interface tbl_accountSessionBeanRemote {

    int checkLogin(String username, String password);
    
}
