/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.tbl_roomCategory;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PhucTDSE61834
 */
@Stateless
public class tbl_roomCategorySessionBean implements tbl_roomCategorySessionBeanLocal, tbl_roomCategorySessionBeanRemote {
    @PersistenceContext(unitName = "SE61834_Assignment2-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List loadCate() {
        Query query = em.createNamedQuery("TblroomCategory.findAll");
        List result = query.getResultList();
        return result;
    }
       
}
