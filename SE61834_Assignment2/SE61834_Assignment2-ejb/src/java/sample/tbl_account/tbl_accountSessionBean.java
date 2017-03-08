/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.tbl_account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sample.entity.TblAccount;

/**
 *
 * @author PhucTDSE61834
 */
@Stateless
public class tbl_accountSessionBean implements tbl_accountSessionBeanLocal, tbl_accountSessionBeanRemote {
    @PersistenceContext(unitName = "SE61834_Assignment2-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public int checkLogin(String username, String password) {
        String jpql = "Select a From TblAccount a "
                + "Where a.username=:username And a.password=:password";
        Query query = em.createQuery(jpql);
        
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            TblAccount acc = (TblAccount)query.getSingleResult();
            return acc.getRole();
        } catch (Exception e) {
            return -1;
        }
    }
}
