package died.guia06;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Alumno implements Comparable<Alumno> {

	private String nombre;
	private Integer nroLibreta;
	private List<Curso> cursando;
	private List<Curso> aprobados;
	
	public Alumno (String nombre, Integer nroLibreta) {
		this.nombre = nombre;
		this.nroLibreta = nroLibreta;
		this.cursando = new ArrayList<Curso>();
		this.aprobados = new ArrayList<Curso>();
		
	}

	public int creditosObtenidos() {
		return this.aprobados.stream().map((c) -> {return c.getCreditos();}).reduce(0, (tot, c) -> tot+c);
	}

	public void aprobar(Curso c) {
		this.cursando = this.cursando.stream().filter((e) -> !c.equals(e)).collect(Collectors.toList());
		this.aprobados.add(c);
	}

	public void inscripcionAceptada(Curso c) {
		this.cursando.add(c);
	}
	
	public boolean equals (Alumno other) {
		return this.nroLibreta.equals(other.nroLibreta);
	}

	@Override
	public int compareTo(Alumno o) {
		return this.nombre.compareToIgnoreCase(o.nombre);
	}
	
	public String toString () {
		return this.nroLibreta + " - " + this.nombre;
	}

	public long materiasInscripto(Integer cicloLectivo) {
		return this.cursando.stream().filter((c) -> c.getCicloLectivo() == cicloLectivo).count();
	}
	

}
