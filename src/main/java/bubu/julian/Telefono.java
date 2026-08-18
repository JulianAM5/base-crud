package bubu.julian;

/**
 * Telefono
 */
public class Telefono {

    private int personaId;
    private String numTelefono;

    public Telefono(int personaId, String numTelefono) {
        this.personaId = personaId;
        this.numTelefono = numTelefono;
    }

    public int getPersonaId() {
        return personaId;
    }

    public String getNumTelefono() {
        return numTelefono;
    }
}
