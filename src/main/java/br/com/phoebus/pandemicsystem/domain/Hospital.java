package br.com.phoebus.pandemicsystem.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Hospital implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String address;
	private String cnpj;
	private String coordinates;
	
	
	@ElementCollection
	@CollectionTable(name = "Phones")
	private List<String> phones;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	private List<Resource> resouces;
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "hospital")
	private Occupation occupation;
	
	public Hospital() {

	}

	public Hospital(Integer id, String name, String address, String cnpj, String coordinates, List<String> phones,
			List<Resource> resouces, Occupation occupation) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.cnpj = cnpj;
		this.coordinates = coordinates;
		this.phones = phones;
		this.resouces = resouces;
		this.occupation = occupation;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public List<Resource> getResouces() {
		return resouces;
	}

	public void setResouces(List<Resource> resouces) {
		this.resouces = resouces;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	
}
