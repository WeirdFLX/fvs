package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.BuslinieDTO;
import entity.Buslinie;

/**
 * 
 * DAO f�r {@link Buslinie} und {@link BuslinieDTO}
 *
 * @author Felix & Silas
 *
 */
@Stateless
@LocalBean
public class BuslinieDAO implements DAO<Buslinie, BuslinieDTO> {

	@PersistenceContext
	EntityManager em;

	/**
	 * Buslinien by ID
	 */
	@Override
	public BuslinieDTO get(int id) {
		return new BuslinieDTO(em.find(Buslinie.class, id));
	}

	/**
	 * Alle Buslinien
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BuslinieDTO> getAll() {
		Query q = em.createQuery("SELECT b FROM Buslinie b");
		List<Buslinie> buslinieEntities = new ArrayList<Buslinie>();
		List<BuslinieDTO> buslinieDTOs = new ArrayList<BuslinieDTO>();

		buslinieEntities = q.getResultList();
		buslinieEntities.forEach((buslinieEntity) -> buslinieDTOs.add(new BuslinieDTO(buslinieEntity)));

		return buslinieDTOs;
	}

	/**
	 * Buslinien by Nummer
	 */
	@SuppressWarnings("unchecked")
	public List<BuslinieDTO> getByNummer(int nummer) {
		Query q = em.createQuery("SELECT b FROM Buslinie b WHERE b.nummer='" + nummer + "' ORDER BY b.richtung ASC");

		List<Buslinie> buslinieEntities = new ArrayList<Buslinie>();
		List<BuslinieDTO> buslinieDTOs = new ArrayList<BuslinieDTO>();

		buslinieEntities = q.getResultList();
		buslinieEntities.forEach((buslinieEntity) -> buslinieDTOs.add(new BuslinieDTO(buslinieEntity)));

		return buslinieDTOs;
	}

	/**
	 * Buslinie by Nummer und Richtung
	 */
	public BuslinieDTO getByNummerRichtung(int nummer, String richtung) {
		Query q = em.createQuery(
				"SELECT b FROM Buslinie b WHERE b.nummer= '" + nummer + "' AND b.richtung='" + richtung + "'",
				Buslinie.class);

		return new BuslinieDTO((Buslinie) q.getSingleResult());
	}

	/**
	 * Gibt es die Nummer schon?
	 */
	@SuppressWarnings("unchecked")
	public List<BuslinieDTO> existsByNummer(int nummer) {
		Query q = em.createQuery("SELECT b.nummer FROM Buslinie b WHERE b.nummer= '" + nummer + "'");

		List<Buslinie> buslinieEntities = new ArrayList<Buslinie>();
		List<BuslinieDTO> buslinieDTOs = new ArrayList<BuslinieDTO>();

		buslinieEntities = q.getResultList();
		buslinieEntities.forEach((buslinieEntity) -> buslinieDTOs.add(new BuslinieDTO(buslinieEntity)));

		return buslinieDTOs;
	}

	@Override
	public void save(BuslinieDTO buslinieDTO) {
		em.persist(buslinieDTO.toEntity());
	}

	@Override
	public void update(BuslinieDTO buslinieDTO, String[] parms) {
		// TODO Parms parsen (siehe UserDAO)
		em.merge(buslinieDTO.toEntity());
	}

	@Override
	public void delete(BuslinieDTO buslinieDTO) {
		em.remove(buslinieDTO.toEntity());
	}
}
