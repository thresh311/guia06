package excepciones;

public class CreditosInsuficientesException extends Exception {

	public String getMessage() {
		return "El alumno no posee creditos suficientes para ser inscripto en la materia.";
	}
	
}
