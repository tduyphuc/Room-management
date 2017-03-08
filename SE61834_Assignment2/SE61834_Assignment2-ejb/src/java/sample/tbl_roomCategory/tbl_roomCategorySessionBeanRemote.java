/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.tbl_roomCategory;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author PhucTDSE61834
 */
@Remote
public interface tbl_roomCategorySessionBeanRemote {

    List loadCate();
    
}
