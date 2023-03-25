package org.ditalia.bbb.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reservaciones")
public class Reservacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate fecha = LocalDate.now();
	@OneToOne
	@JoinColumn(name="folioVestido")
	private Vestido vestido;
	@OneToOne
	@JoinColumn(name="idAccesorio")
	private Accesorio accesorio;
	@OneToOne
	@JoinColumn(name="idCategoria")
	private Categoria categoria;
	@OneToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Vestido getVestido() {
		return vestido;
	}
	public void setVestido(Vestido vestido) {
		this.vestido = vestido;
	}
	public Accesorio getAccesorio() {
		return accesorio;
	}
	public void setAccesorio(Accesorio accesorio) {
		this.accesorio = accesorio;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String toString() {
		return "Reservacion [id=" + id + ", fecha=" + fecha + ", vestido=" + vestido + ", accesorio=" + accesorio
				+ ", categoria=" + categoria + ", usuario=" + usuario + "]";
	}
	
}