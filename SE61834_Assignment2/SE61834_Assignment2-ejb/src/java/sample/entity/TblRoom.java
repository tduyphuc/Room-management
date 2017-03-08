/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PhucTDSE61834
 */
@Entity
@Table(name = "tbl_room", catalog = "Assignment1DB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRoom.findAll", 
            query = "SELECT t FROM TblRoom t"),
    @NamedQuery(name = "TblRoom.findByRoomID", 
            query = "SELECT t FROM TblRoom t WHERE t.roomID = :roomID"),
    @NamedQuery(name = "TblRoom.findByDescription", 
            query = "SELECT t FROM TblRoom t WHERE t.description = :description"),
    @NamedQuery(name = "TblRoom.findByHourPrice", 
            query = "SELECT t FROM TblRoom t WHERE t.hourPrice = :hourPrice"),
    @NamedQuery(name = "TblRoom.findByDayPrice", 
            query = "SELECT t FROM TblRoom t WHERE t.dayPrice = :dayPrice"),
    @NamedQuery(name = "TblRoom.findByCategoryID", 
            query = "SELECT t FROM TblRoom t WHERE t.categoryID = :categoryID"),
    @NamedQuery(name = "TblRoom.findByLikeRoomID", 
            query = "SELECT t FROM TblRoom t WHERE t.roomID LIKE :roomID")
})

public class TblRoom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "roomID", nullable = false, length = 3)
    private String roomID;
    @Column(name = "description", length = 250)
    private String description;
    @Basic(optional = false)
    @Column(name = "hourPrice", nullable = false)
    private double hourPrice;
    @Basic(optional = false)
    @Column(name = "dayPrice", nullable = false)
    private double dayPrice;
    @Basic(optional = false)
    @Column(name = "categoryID", nullable = false, length = 5)
    private String categoryID;

    public TblRoom() {
    }

    public TblRoom(String roomID) {
        this.roomID = roomID;
    }

    public TblRoom(String roomID, double hourPrice, double dayPrice, String categoryID) {
        this.roomID = roomID;
        this.hourPrice = hourPrice;
        this.dayPrice = dayPrice;
        this.categoryID = categoryID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(double hourPrice) {
        this.hourPrice = hourPrice;
    }

    public double getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(double dayPrice) {
        this.dayPrice = dayPrice;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomID != null ? roomID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblRoom)) {
            return false;
        }
        TblRoom other = (TblRoom) object;
        if ((this.roomID == null && other.roomID != null) || (this.roomID != null && !this.roomID.equals(other.roomID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sample.entity.TblRoom[ roomID=" + roomID + " ]";
    }
    
}
