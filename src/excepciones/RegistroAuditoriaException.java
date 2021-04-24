package excepciones;

public class RegistroAuditoriaException extends Exception {

	public String getMessage() {
		return "Hubo un fallo al escribir el registro/";
	}
	
}
