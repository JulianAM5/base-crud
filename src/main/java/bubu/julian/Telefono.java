package bubu.julian;

/**
 * Telefono
 */
public class Telefono {

    private int id;
    private int personaId;
    private String numTelefono;

    public Telefono(int id, int personaId, String numTelefono) {
        this.id = id;
        this.personaId = personaId;
        this.numTelefono = numTelefono;
    }

    public int getId() {
        return id;
    }

    public int getPersonaId() {
        return personaId;
    }

    public String getNumTelefono() {
        return numTelefono;
    }
}
