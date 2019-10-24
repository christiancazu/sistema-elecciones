package entidades;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@Entity
@Table(name = "ciudadano")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudadano.findAll", query = "SELECT c FROM Ciudadano c"),
    @NamedQuery(name = "Ciudadano.findById", query = "SELECT c FROM Ciudadano c WHERE c.id = :id"),
    @NamedQuery(name = "Ciudadano.findByDni", query = "SELECT c FROM Ciudadano c WHERE c.dni = :dni"),
    @NamedQuery(name = "Ciudadano.findByApellidos", query = "SELECT c FROM Ciudadano c WHERE c.apellidos = :apellidos"),
    @NamedQuery(name = "Ciudadano.findByNombres", query = "SELECT c FROM Ciudadano c WHERE c.nombres = :nombres"),
    @NamedQuery(name = "Ciudadano.findByDireccion", query = "SELECT c FROM Ciudadano c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Ciudadano.findBySexo", query = "SELECT c FROM Ciudadano c WHERE c.sexo = :sexo"),
    @NamedQuery(name = "Ciudadano.findByEstadocivil", query = "SELECT c FROM Ciudadano c WHERE c.estadocivil = :estadocivil"),
    @NamedQuery(name = "Ciudadano.findByCandidato", query = "SELECT c FROM Ciudadano c WHERE c.candidato = :candidato"),
    @NamedQuery(name = "Ciudadano.findByMiembromesa", query = "SELECT c FROM Ciudadano c WHERE c.miembromesa = :miembromesa"),
    @NamedQuery(name = "Ciudadano.findByUsuario", query = "SELECT c FROM Ciudadano c WHERE c.usuario = :usuario"),
    @NamedQuery(name = "Ciudadano.findByClave", query = "SELECT c FROM Ciudadano c WHERE c.clave = :clave")})
public class Ciudadano implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dni")
    private int dni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 128)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "sexo")
    private Character sexo;
    @Column(name = "estadocivil")
    private Character estadocivil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "candidato")
    private boolean candidato;
    @Column(name = "miembromesa")
    private Boolean miembromesa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "clave")
    private String clave;
    @OneToMany(mappedBy = "miembrodos")
    private List<Mesa> mesaList;
    @OneToMany(mappedBy = "miembrotres")
    private List<Mesa> mesaList1;
    @OneToMany(mappedBy = "miembrouno")
    private List<Mesa> mesaList2;
    @OneToMany(mappedBy = "ciudadano")
    private List<Partido> partidoList;
    @JoinColumn(name = "ubigeo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ubigeo ubigeo;

    public Ciudadano() {
    }

    public Ciudadano(Integer id) {
        this.id = id;
    }

    public Ciudadano(Integer id, int dni, String apellidos, String nombres, boolean candidato, String usuario, String clave) {
        this.id = id;
        this.dni = dni;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.candidato = candidato;
        this.usuario = usuario;
        this.clave = clave;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Character getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(Character estadocivil) {
        this.estadocivil = estadocivil;
    }

    public boolean getCandidato() {
        return candidato;
    }

    public void setCandidato(boolean candidato) {
        this.candidato = candidato;
    }

    public Boolean getMiembromesa() {
        return miembromesa;
    }

    public void setMiembromesa(Boolean miembromesa) {
        this.miembromesa = miembromesa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @XmlTransient
    public List<Mesa> getMesaList() {
        return mesaList;
    }

    public void setMesaList(List<Mesa> mesaList) {
        this.mesaList = mesaList;
    }

    @XmlTransient
    public List<Mesa> getMesaList1() {
        return mesaList1;
    }

    public void setMesaList1(List<Mesa> mesaList1) {
        this.mesaList1 = mesaList1;
    }

    @XmlTransient
    public List<Mesa> getMesaList2() {
        return mesaList2;
    }

    public void setMesaList2(List<Mesa> mesaList2) {
        this.mesaList2 = mesaList2;
    }

    @XmlTransient
    public List<Partido> getPartidoList() {
        return partidoList;
    }

    public void setPartidoList(List<Partido> partidoList) {
        this.partidoList = partidoList;
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
        if (!(object instanceof Ciudadano)) {
            return false;
        }
        Ciudadano other = (Ciudadano) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Ciudadano[ id=" + id + " ]";
    }

}
