package service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlTransient;

import pojo.*;

@Path("/ss")
@Produces(MediaType.APPLICATION_JSON)
public class StudentskaSluzba {
	private static ArrayList<Smer> smerovi = new ArrayList<>(); // dodao static da se pri novom requestu ne bi menjala opet vrednost
	
	static {
		Smer smer1 = new Smer();
		smer1.setId(1);
		smer1.setNaziv("SIT");
		smerovi.add(smer1);
		
		Smer smer2 = new Smer();
		smer2.setId(2);
		smer2.setNaziv("GRID");
		smerovi.add(smer2);
		
		Student s1 = new Student();
		s1.setId(1);
		s1.setIme("Aleksa");
		Student s2 = new Student();
		s1.setId(2);
		s1.setIme("Rambo");
		
		smer1.getStudenti().add(s1);
		smer1.getStudenti().add(s2);
		
		Student s3 = new Student();
		s1.setId(3);
		s1.setIme("Djura");
		Student s4 = new Student();
		s1.setId(4);
		s1.setIme("Pera");
		smer2.getStudenti().add(s3);
		smer2.getStudenti().add(s4);
	} //static blok se samo jendom izvrsava za sve instance StudenskeSluzbe
	
	@XmlTransient //ne prepoznaj studente kao nesto sto treba vracati
//	ako imas par getera i setera u Smeru onda ce vratiti listu svih studenata na tom smeru ako nema anotacije gore
//	zabranjuje serijalizaciju u dubinu :D
	@GET
	@Path("/smerovi")
	public ArrayList<Smer> getSmerovi () {
		ArrayList<Smer> temp = new ArrayList<>();
		for (Smer s : smerovi)
			if (!s.getObrisan())
				temp.add(s);
		return smerovi;
	}
	
	
	@Path("/smerovi/{id}")
	public Smer getSmeroviById (@PathParam("id") int id) {
		for (Smer smer : smerovi) {
			if (smer.getId() == id)
				return smer;
		}
		return null;
	}
	
	@POST
	@Path("/smerovi/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Smer createSmer (Smer smer) {
		smer.setId(hashCode());
		smerovi.add(smer);
		return smer;
	}
	
//	bez lokatora, losije :D
	@DELETE
	@Path("/smerovi/{smerID}/delete2")
	public void deleteSmer (@PathParam("smerID") int smerID) {
		Smer zabrisanje = null;
		for (Smer s : smerovi)
			if (s.getId() == smerID)
				zabrisanje = s;
		if (zabrisanje != null)
			smerovi.remove(zabrisanje);
	}
	
//	KAD METODA NEMA GET POST PUT... ONDA ONDA POSTAJE LOKATOR|
//  OBRADJUJE SVE ZAHTEVE NA /smerovi/{id} 
//	TAJ METOD SE NE ODAZIVA NI NA JEDAN SAMO PROSLEDJUJE DALJE ZAHTEV, SAD CEMO U POJO DA RADIMO
//	DODOA SI METODU getThis u Smer.java
	
	
//	@GET
//	@Path("/smerovi/{id}/studenti")
//	public ArrayList<Student> getStudentiBySmer (@PathParam("id") int id) {
//		for (Smer smer : smerovi) {
//			if (smer.getId() == id)
//				return smer.getStudenti();
//		}
//		return null;
//	}
//	
//	@GET
//	@Path("/smerovi/{id}/studenti/{idStudent}")
//	public Student getStudentBySmer (@PathParam("id") int id, @PathParam("idStudent") int idStudent) {
//		for (Smer smer : smerovi) {
//			if (smer.getId() == id)
//				for (Student s : smer.getStudenti()) {
//					if (s.getId() == idStudent)
//						return s;
//				}
//		}
//		return null;
//	}
	
//	@PUT
//	public Student addStudent(@)
}