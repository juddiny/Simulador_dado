import java.util.LinkedList;

/**
 * Clase que gestiona el historial de los últimos 10 lanzamientos.
 */
public class HistorialLanzamientos {
    private LinkedList<Lanzamiento> historial;

    public HistorialLanzamientos() {
        historial = new LinkedList<>();
    }

    /**
     * Añade un lanzamiento al historial.
     * @param lanzamiento El lanzamiento a añadir.
     */
    public void añadirLanzamiento(Lanzamiento lanzamiento) {
        if (historial.size() == 10) {
            historial.removeFirst();
        }
        historial.add(lanzamiento);
    }

    public LinkedList<Lanzamiento> getHistorial() {
        return historial;
    }
}