/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.tbl_maintenance;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PhucTDSE61834
 */
@Local
public interface tbl_maintenanceSessionBeanLocal {

    void createReport(String requestUser, String roomID, String reason);

    List filterDate(String date);

    boolean updateReport(int id, String mend, double cost, String repairedUser);
    
}
