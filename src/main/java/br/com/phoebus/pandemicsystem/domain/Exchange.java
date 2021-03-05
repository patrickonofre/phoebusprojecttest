package br.com.phoebus.pandemicsystem.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Exchange {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "hospital_id", referencedColumnName = "id")
	private Hospital hospital;
	
	private Date date;
	
	@ManyToMany
	@JoinColumn(name = "resource_id", referencedColumnName = "id")
	private List<Resource> resource = new ArrayList<Resource>();
	
	public Exchange() {

	}

	public Exchange(Hospital hospital, List<Resource> resources) {
		super();
		this.hospital = hospital;
		this.resource = resources;
		this.date = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public List<Resource> getResources() {
		return resource;
	}

	public void setResources(List<Resource> resources) {
		this.resource = resources;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Resource> getResource() {
		return resource;
	}

	public void setResource(List<Resource> resource) {
		this.resource = resource;
	}
	
}
