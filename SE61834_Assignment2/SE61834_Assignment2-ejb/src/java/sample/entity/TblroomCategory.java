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
@Table(name = "tbl_roomCategory", catalog = "Assignment1DB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblroomCategory.findAll", query = "SELECT t FROM TblroomCategory t"),
    @NamedQuery(name = "TblroomCategory.findByCategoryID", query = "SELECT t FROM TblroomCategory t WHERE t.categoryID = :categoryID"),
    @NamedQuery(name = "TblroomCategory.findByCategoryName", query = "SELECT t FROM TblroomCategory t WHERE t.categoryName = :categoryName"),
    @NamedQuery(name = "TblroomCategory.findByDescription", query = "SELECT t FROM TblroomCategory t WHERE t.description = :description")})
public class TblroomCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "categoryID", nullable = false, length = 5)
    private String categoryID;
    @Basic(optional = false)
    @Column(name = "categoryName", nullable = false, length = 30)
    private String categoryName;
    @Column(name = "description", length = 50)
    private String description;

    public TblroomCategory() {
    }

    public TblroomCategory(String categoryID) {
        this.categoryID = categoryID;
    }

    public TblroomCategory(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryID != null ? categoryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblroomCategory)) {
            return false;
        }
        TblroomCategory other = (TblroomCategory) object;
        if ((this.categoryID == null && other.categoryID != null) || (this.categoryID != null && !this.categoryID.equals(other.categoryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sample.entity.TblroomCategory[ categoryID=" + categoryID + " ]";
    }
    
}
