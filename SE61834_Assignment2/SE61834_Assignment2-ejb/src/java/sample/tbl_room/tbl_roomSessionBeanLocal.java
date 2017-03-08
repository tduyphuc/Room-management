/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.tbl_room;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PhucTDSE61834
 */
@Local
public interface tbl_roomSessionBeanLocal {

    List searchRoom(String roomID);

    List showAll();

    boolean updateCate(String roomID, String categoryID);
    
}
