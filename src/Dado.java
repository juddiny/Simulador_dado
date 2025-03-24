import java.util.Random;

/**
 * Clase que representa un dado de 6 caras.
 */
public class Dado {
    private Random random;

    public Dado() {
        random = new Random();
    }

    /**
     * Simula el lanzamiento del dado.
     * @return Un número aleatorio entre 1 y 6.
     */
    public int lanzar() {
        return random.nextInt(6) + 1;
    }
}