package jwd.test.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Prevoznik {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(nullable = false, unique = true)
	private String naziv;

	@Column
	private String adresa;

	@Column(nullable = false, unique = true)
	private String pib;

	@OneToMany(mappedBy = "prevoznik", fetch = FetchType.LAZY)
	private List<Linija> linije = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public List<Linija> getLinije() {
		return linije;
	}

	public void setLinije(List<Linija> linije) {
		this.linije = linije;
	}

	public void addLinija(Linija linija) {
		if (!linija.getPrevoznik().equals(this)) {
			linija.setPrevoznik(this);
		}
		this.getLinije().add(linija);
	}

}