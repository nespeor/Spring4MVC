package com.nespeor.springmvc.model;

public class House {

	private Integer id;
	private String calle;
	private Integer num;
	private Integer codpostal;
	private String poblacion;
	private Integer tipo;
	private Integer numhab;
	private String descripcion;
	
	public House(){
		id=0;
	}
	
	public House(Integer id, String calle, Integer num, Integer codpostal, String poblacion, Integer tipo, Integer numhab, String descripcion){
		this.id = id;
		this.calle = calle;
		this.num = num;
		this.codpostal = codpostal;
		this.poblacion = poblacion;
		this.tipo = tipo;
		this.numhab = numhab;
		this.descripcion = descripcion;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getCodpostal() {
		return codpostal;
	}

	public void setCodpostal(Integer codpostal) {
		this.codpostal = codpostal;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getNumhab() {
		return numhab;
	}

	public void setNumhab(Integer numhab) {
		this.numhab = numhab;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof House))
			return false;
		House other = (House) obj;
		if (id != other.id)
			return false;
		return true;
	}

	//@Override
	//public String toString() {
	//	return "User [id=" + id + ", username=" + username + ", address=" + address
	//			+ ", email=" + email + "]";
	//}
	

	
}
