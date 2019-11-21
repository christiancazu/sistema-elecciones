/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ciber
 */
@Entity
@Table(name = "mesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesa.findAll", query = "SELECT m FROM Mesa m"),
    @NamedQuery(name = "Mesa.findById", query = "SELECT m FROM Mesa m WHERE m.id = :id")})
public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "miembrodos", referencedColumnName = "id")
    @ManyToOne
    private Ciudadano miembrodos;
    @JoinColumn(name = "miembrotres", referencedColumnName = "id")
    @ManyToOne
    private Ciudadano miembrotres;
    @JoinColumn(name = "miembrouno", referencedColumnName = "id")
    @ManyToOne
    private Ciudadano miembrouno;
    @JoinColumn(name = "ubigeo", referencedColumnName = "id")
    @ManyToOne
    private Ubigeo ubigeo;

    public Mesa() {
    }

    public Mesa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ciudadano getMiembrodos() {
        return miembrodos;
    }

    public void setMiembrodos(Ciudadano miembrodos) {
        this.miembrodos = miembrodos;
    }

    public Ciudadano getMiembrotres() {
        return miembrotres;
    }

    public void setMiembrotres(Ciudadano miembrotres) {
        this.miembrotres = miembrotres;
    }

    public Ciudadano getMiembrouno() {
        return miembrouno;
    }

    public void setMiembrouno(Ciudadano miembrouno) {
        this.miembrouno = miembrouno;
    }

    public Ubigeo getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
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
        if (!(object instanceof Mesa)) {
            return false;
        }
        Mesa other = (Mesa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Mesa[ id=" + id + " ]";
    }
    
}
