/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.tbl_account;

import javax.ejb.Local;

/**
 *
 * @author PhucTDSE61834
 */
@Local
public interface tbl_accountSessionBeanLocal {

    int checkLogin(String username, String password);
    
}
