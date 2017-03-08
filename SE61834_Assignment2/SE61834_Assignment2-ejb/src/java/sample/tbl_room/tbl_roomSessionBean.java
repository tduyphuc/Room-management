/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.tbl_room;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sample.entity.TblRoom;

/**
 *
 * @author PhucTDSE61834
 */
@Stateless
public class tbl_roomSessionBean implements tbl_roomSessionBeanLocal, tbl_roomSessionBeanRemote {
    @PersistenceContext(unitName = "SE61834_Assignment2-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List searchRoom(String roomID) {
        Query query = em.createNamedQuery("TblRoom.findByLikeRoomID");
        query.setParameter("roomID", "%" + roomID + "%");
        List result = query.getResultList();
        return result;
    }

    @Override
    public List showAll() {
        Query query = em.createNamedQuery("TblRoom.findAll");
        List result = query.getResultList();
        return result;
    }

    @Override
    public boolean updateCate(String roomID, String categoryID) {
        TblRoom room = em.find(TblRoom.class, roomID);
        if(room != null){
            room.setCategoryID(categoryID);
            em.merge(room);
            return true;
        }
        return false;
    }
       
    
}
