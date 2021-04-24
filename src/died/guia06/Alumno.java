package died.guia06;

import java.util.ArrayList;
import java.util.List;


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
		this.cursando.remove(c);
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
	

}
