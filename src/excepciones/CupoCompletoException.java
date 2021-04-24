package excepciones;

public class CupoCompletoException extends Exception {

	public String getMessage() {
		return "El cupo del curso ya se encuentra completo.";
	}
	
}
