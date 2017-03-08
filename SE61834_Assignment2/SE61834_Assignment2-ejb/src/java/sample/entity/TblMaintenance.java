/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PhucTDSE61834
 */
@Entity
@Table(name = "tbl_maintenance", catalog = "Assignment1DB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblMaintenance.findAll", query = "SELECT t FROM TblMaintenance t"),
    @NamedQuery(name = "TblMaintenance.findById", query = "SELECT t FROM TblMaintenance t WHERE t.id = :id"),
    @NamedQuery(name = "TblMaintenance.findByRequestDate", query = "SELECT t FROM TblMaintenance t WHERE t.requestDate = :requestDate"),
    @NamedQuery(name = "TblMaintenance.findByReason", query = "SELECT t FROM TblMaintenance t WHERE t.reason = :reason"),
    @NamedQuery(name = "TblMaintenance.findByRepairedDate", query = "SELECT t FROM TblMaintenance t WHERE t.repairedDate = :repairedDate"),
    @NamedQuery(name = "TblMaintenance.findByMend", query = "SELECT t FROM TblMaintenance t WHERE t.mend = :mend"),
    @NamedQuery(name = "TblMaintenance.findByCost", query = "SELECT t FROM TblMaintenance t WHERE t.cost = :cost"),
    @NamedQuery(name = "TblMaintenance.findByRoomID", query = "SELECT t FROM TblMaintenance t WHERE t.roomID = :roomID"),
    @NamedQuery(name = "TblMaintenance.findByRequestUser", query = "SELECT t FROM TblMaintenance t WHERE t.requestUser = :requestUser"),
    @NamedQuery(name = "TblMaintenance.findByRepairedUser", query = "SELECT t FROM TblMaintenance t WHERE t.repairedUser = :repairedUser"),
    @NamedQuery(name = "TblMaintenance.FilterRequestDate", query = "SELECT t FROM TblMaintenance t WHERE "
            + "t.requestDate BETWEEN :startDate AND :endDate "
            + "AND t.repairedUser IS NULL")
})
public class TblMaintenance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "requestDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    @Basic(optional = false)
    @Column(name = "reason", nullable = false, length = 250)
    private String reason;
    @Column(name = "repairedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date repairedDate;
    @Column(name = "mend", length = 250)
    private String mend;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cost", precision = 53)
    private Double cost;
    @Basic(optional = false)
    @Column(name = "roomID", nullable = false, length = 3)
    private String roomID;
    @Basic(optional = false)
    @Column(name = "RequestUser", nullable = false, length = 20)
    private String requestUser;
    @Column(name = "RepairedUser", length = 20)
    private String repairedUser;

    public TblMaintenance() {
    }

    public TblMaintenance(Date requestDate, String reason, String roomID, String requestUser) {
        this.requestDate = requestDate;
        this.reason = reason;
        this.roomID = roomID;
        this.requestUser = requestUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getRepairedDate() {
        return repairedDate;
    }

    public void setRepairedDate(Date repairedDate) {
        this.repairedDate = repairedDate;
    }

    public String getMend() {
        return mend;
    }

    public void setMend(String mend) {
        this.mend = mend;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }

    public String getRepairedUser() {
        return repairedUser;
    }

    public void setRepairedUser(String repairedUser) {
        this.repairedUser = repairedUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblMaintenance)) {
            return false;
        }
        TblMaintenance other = (TblMaintenance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sample.entity.TblMaintenance[ id=" + id + " ]";
    }
    
}
