package died.guia06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import died.guia06.util.Registro;
import excepciones.CreditosInsuficientesException;
import excepciones.CupoCompletoException;
import excepciones.InscriptoMateriasRegularesException;
import excepciones.RegistroAuditoriaException;

/**
 * Clase que representa un curso. Un curso se identifica por su ID y por su nombre y ciclo lectivo.
 * Un curso guarda una lista de los inscriptos actuales que tienen.
 * Un curso, al aprobarlo, otorga una cantidad de creditos definidas en el curso.
 * Un curso requiere que para inscribirnos tengamos al menos la cantidad de creditos requeridas, y que haya cupo disponible
 * @author marti
 *
 */
public class Curso {

	private Integer id;
	private String nombre;
	private Integer cicloLectivo;
	private Integer cupo; 
	private List<Alumno> inscriptos;
	private Integer creditos;
	private Integer creditosRequeridos;
	
	private Registro log;
	
	public Curso() {
		super();
		this.inscriptos = new ArrayList<Alumno>();
		this.log = new Registro();
	}
	

	/**
	 * Este método, verifica si el alumno se puede inscribir y si es así lo agrega al curso,
	 * agrega el curso a la lista de cursos en los que está inscripto el alumno y retorna verdadero.
	 * Caso contrario retorna falso y no agrega el alumno a la lista de inscriptos ni el curso a la lista
	 * de cursos en los que el alumno está inscripto.
	 * 
	 * Para poder inscribirse un alumno debe
	 * 		a) tener como minimo los creditos necesarios
	 *      b) tener cupo disponibles
	 *      c) puede estar inscripto en simultáneo a no más de 3 cursos del mismo ciclo lectivo.
	 * @param a
	 * @return
	 * @throws CreditosInsuficientesException 
	 * @throws CupoCompletoException 
	 * @throws InscriptoMateriasRegularesException 
	 * @throws RegistroAuditoriaException 
	 */
	public Boolean inscribir(Alumno a) throws CreditosInsuficientesException, CupoCompletoException, InscriptoMateriasRegularesException, RegistroAuditoriaException {
		
		if(a.creditosObtenidos() < this.creditosRequeridos)
			throw new CreditosInsuficientesException();
		
		if(this.inscriptos.stream().count() == this.cupo) 
			throw new CupoCompletoException();
		
		if(a.materiasInscripto(this.cicloLectivo) >= 3) 
			throw new InscriptoMateriasRegularesException();
		
		this.inscriptos.add(a);
		
		try {	
			log.registrar(this, "inscribir ",a.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RegistroAuditoriaException();
		}
		return true;
	}
	
	
	/**
	 * imprime los inscriptos en orden alfabetico
	 */
	public void imprimirInscriptos() {
		try {
			log.registrar(this, "imprimir listado",this.inscriptos.size()+ " registros ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Integer getCreditos() {
		return creditos;
	}
	
	public Integer getCicloLectivo() {
		return cicloLectivo;
	}


	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}

	public boolean equals(Curso other) {
		return this.id == other.id;
	}
	
	public String toString () {
		return "ID: " + this.id + " - " + this.nombre;
	}
}
