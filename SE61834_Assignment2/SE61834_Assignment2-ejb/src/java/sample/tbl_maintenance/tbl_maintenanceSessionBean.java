/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_maintenance;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sample.entity.TblMaintenance;

/**
 *
 * @author PhucTDSE61834
 */
@Stateless
public class tbl_maintenanceSessionBean implements tbl_maintenanceSessionBeanLocal, tbl_maintenanceSessionBeanRemote {

    @PersistenceContext(unitName = "SE61834_Assignment2-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void createReport(String requestUser, String roomID, String reason) {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        TblMaintenance report = new TblMaintenance(ts, reason, roomID, requestUser);
        em.persist(report);
    }

    @Override
    public List filterDate(String date) {
        Query query = em.createNamedQuery("TblMaintenance.FilterRequestDate");
        DateFormat dfStart = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dfEnd = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        try {
            Date start = dfStart.parse(date);
            query.setParameter("startDate", start);
            Date end = dfEnd.parse(date + " 23:59:59");
            query.setParameter("endDate", end);
            List result = query.getResultList();
            return result;
        } catch (ParseException ex) {
            return null;
        }
    }

    @Override
    public boolean updateReport(int id, String mend, double cost, String repairedUser) {
        TblMaintenance report = em.find(TblMaintenance.class, id);
        if (report != null) {
            report.setMend(mend);
            report.setCost((double) cost);
            report.setRepairedUser(repairedUser);
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            report.setRepairedDate(date);
            return true;
        }
        return false;
    }

}
