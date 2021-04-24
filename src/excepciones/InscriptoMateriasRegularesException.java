package excepciones;

public class InscriptoMateriasRegularesException extends Exception {

	public String getMessage() {
		return "El alumno ya se encuentra inscripto a todas las materias de cursado regular.";
	}
	
}
