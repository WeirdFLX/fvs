package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.LinienabfolgeDTO;
import entity.Linienabfolge;
/**
 * 
 * DAO f�r {@link Linienabfolge} und {@link LinienabfolgeDTO}
 *
 * @author Felix & Silas
 *
 */
@Stateless
@LocalBean
public class LinienabfolgeDAO implements DAO<Linienabfolge, LinienabfolgeDTO> {

	@PersistenceContext
	EntityManager em;
	/**
	 * Linienabfolge by ID
	 */
	@Override
	public LinienabfolgeDTO get(int id) {
		return new LinienabfolgeDTO(em.find(Linienabfolge.class, id));
	}
	/**
	 * Linienabfolgen by BuslinieHin und BuslinieR�ck + Sortierung ASC/DESC
	 */
	@SuppressWarnings("unchecked")
	public List<LinienabfolgeDTO> getByBuslinien(int bidh, int bidr, String sortierung) {
		Query q = em.createQuery("SELECT l FROM Linienabfolge l WHERE l.buslinieH.bid = '" + bidh + "' AND l.buslinieR.bid ='" + bidr + "' ORDER BY l.position " + sortierung + "");
		List<Linienabfolge> linienabfolgeEntities = new ArrayList<Linienabfolge>();
		List<LinienabfolgeDTO> linienabfolgeDTOs = new ArrayList<LinienabfolgeDTO>();
		
		linienabfolgeEntities = q.getResultList();
		linienabfolgeEntities.forEach((linienabfolgeEntity) -> linienabfolgeDTOs.add(new LinienabfolgeDTO(linienabfolgeEntity)) );
		
		return linienabfolgeDTOs;
	}

	/**
	 * Linienabfolgen by BuslinieHin + Sortierung ASC/DESC
	 */
	@SuppressWarnings("unchecked")
	public List<LinienabfolgeDTO> getByBuslinieH(int bidh, String sortierung) {
		Query q = em.createQuery("SELECT l FROM Linienabfolge l WHERE l.buslinieH.bid = '" + bidh + "' ORDER BY l.position " + sortierung + "");
		List<Linienabfolge> linienabfolgeEntities = new ArrayList<Linienabfolge>();
		List<LinienabfolgeDTO> linienabfolgeDTOs = new ArrayList<LinienabfolgeDTO>();
		
		linienabfolgeEntities = q.getResultList();
		linienabfolgeEntities.forEach((linienabfolgeEntity) -> linienabfolgeDTOs.add(new LinienabfolgeDTO(linienabfolgeEntity)) );
		
		return linienabfolgeDTOs;
	}
	
	/**
	 * Linienabfolgen by BuslinieR�ck + Sortierung ASC/DESC
	 */
	@SuppressWarnings("unchecked")
	public List<LinienabfolgeDTO> getByBuslinieR(int bidr, String sortierung) {
		Query q = em.createQuery("SELECT l FROM Linienabfolge l WHERE l.buslinieR.bid = '" + bidr + "' ORDER BY l.position " + sortierung + "");
		List<Linienabfolge> linienabfolgeEntities = new ArrayList<Linienabfolge>();
		List<LinienabfolgeDTO> linienabfolgeDTOs = new ArrayList<LinienabfolgeDTO>();
		
		linienabfolgeEntities = q.getResultList();
		linienabfolgeEntities.forEach((linienabfolgeEntity) -> linienabfolgeDTOs.add(new LinienabfolgeDTO(linienabfolgeEntity)) );
		
		return linienabfolgeDTOs;
	}
	
	/**
	 * Linienabfolgen by HaltestelleStart ODER HaltestelleEnde + Sortierung ASC/DESC
	 */
	@SuppressWarnings("unchecked")
	public List<LinienabfolgeDTO> getByHid(int hid, String sortierung) {
		Query q = em.createQuery("SELECT l FROM Linienabfolge l WHERE l.verbindung.haltestelleS.hid = '" + hid + "' OR l.verbindung.haltestelleE.hid ='" + hid + "' GROUP BY l.buslinieH.bid, l.buslinieR.bid ORDER BY l.position " + sortierung + "");
		List<Linienabfolge> linienabfolgeEntities = new ArrayList<Linienabfolge>();
		List<LinienabfolgeDTO> linienabfolgeDTOs = new ArrayList<LinienabfolgeDTO>();
		
		linienabfolgeEntities = q.getResultList();
		linienabfolgeEntities.forEach((linienabfolgeEntity) -> linienabfolgeDTOs.add(new LinienabfolgeDTO(linienabfolgeEntity)) );
		
		return linienabfolgeDTOs;
	}
	
	/**
	 * Alle Linienabfolgen
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LinienabfolgeDTO> getAll() {
		Query q = em.createQuery("SELECT l FROM Linienabfolge l");
		List<Linienabfolge> linienabfolgeEntities = new ArrayList<Linienabfolge>();
		List<LinienabfolgeDTO> linienabfolgeDTOs = new ArrayList<LinienabfolgeDTO>();
		
		linienabfolgeEntities = q.getResultList();
		linienabfolgeEntities.forEach((linienabfolgeEntity) -> linienabfolgeDTOs.add(new LinienabfolgeDTO(linienabfolgeEntity)) );
		
		return linienabfolgeDTOs;
	}

	/**
	 * Alle Linienabfolgen mit Sortierung
	 */
	@SuppressWarnings("unchecked")
	public List<LinienabfolgeDTO> getAll(String sortierung) {
		Query q = em.createQuery("SELECT l FROM Linienabfolge l ORDER BY l.position " + sortierung + "");
		List<Linienabfolge> linienabfolgeEntities = new ArrayList<Linienabfolge>();
		List<LinienabfolgeDTO> linienabfolgeDTOs = new ArrayList<LinienabfolgeDTO>();
		
		linienabfolgeEntities = q.getResultList();
		linienabfolgeEntities.forEach((linienabfolgeEntity) -> linienabfolgeDTOs.add(new LinienabfolgeDTO(linienabfolgeEntity)) );
		
		return linienabfolgeDTOs;
	}
	
	@Override
	public void save(LinienabfolgeDTO linienabfolgeDTO) {
		em.persist(linienabfolgeDTO.toEntity());
	}

	@Override
	public void update(LinienabfolgeDTO linienabfolgeDTO, String[] parms) {
		//TODO Parms parsen (siehe UserDAO)
		em.merge(linienabfolgeDTO.toEntity());
	}

	public void update(LinienabfolgeDTO linienabfolgeDTO) {
		//TODO Parms parsen (siehe UserDAO)
		em.merge(linienabfolgeDTO.toEntity());
	}
	
	@Override
	public void delete(LinienabfolgeDTO linienabfolgeDTO) {
		em.remove(linienabfolgeDTO.toEntity());		
	}
}
