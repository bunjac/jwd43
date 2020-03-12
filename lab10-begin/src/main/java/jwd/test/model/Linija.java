package jwd.test.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Linija {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(nullable = false)
	private int brojMesta;

	@Column
	private double cenaKarte;

	@Column
	private String vremePolaska;

	@Column(nullable = false)
	private String destinacija;

	@ManyToOne(fetch = FetchType.EAGER)
	private Prevoznik prevoznik;

	@OneToMany(mappedBy = "linija")
	private List<Rezervacija> rezervacije = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public String getVremePolaska() {
		return vremePolaska;
	}

	public void setVremePolaska(String vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}

	public Prevoznik getPrevoznik() {
		return prevoznik;
	}

	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
		if (!prevoznik.getLinije().contains(this)) {
			prevoznik.getLinije().add(this);
		}
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public void addRezervacija(Rezervacija rez) {
		if (!rez.getLinija().equals(this)) {
			rez.setLinija(this);
		}
		this.getRezervacije().add(rez);
	}

}