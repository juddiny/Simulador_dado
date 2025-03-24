import javax.swing.*;
import java.util.LinkedList;

public class Controlador {
    private final Dado dado;
    private final DadoVista vista;
    private final HistorialLanzamientos historial;
    private final GraficoFrecuencia graficoFrecuencia;

    public Controlador(Dado dado, DadoVista vista, GraficoFrecuencia graficoFrecuencia) {
        this.dado = dado;
        this.vista = vista;
        this.historial = new HistorialLanzamientos();
        this.graficoFrecuencia = graficoFrecuencia;

        configurarControlador();
    }

    private void configurarControlador() {
        vista.getLanzarButton().addActionListener(e -> manejarLanzamiento());
    }

    private void manejarLanzamiento() {
        try {
            int resultado = dado.lanzar();
            graficoFrecuencia.realizarLanzamiento();
            historial.añadirLanzamiento(new Lanzamiento(resultado));

            // Esta llamada actualizará automáticamente el título
            vista.actualizarTabla(historial.getHistorial());
            vista.actualizarDado(resultado);
        } catch (Exception ex) {
            // Manejo de errores
        }
    }

    private void actualizarModelo(int resultado) {
        graficoFrecuencia.realizarLanzamiento();
        historial.añadirLanzamiento(new Lanzamiento(resultado));
    }

    private void actualizarVista(int resultado) {
        vista.actualizarDado(resultado);
        vista.actualizarTabla(historial.getHistorial());
        vista.actualizarGrafico();
    }

    private void mostrarError(Exception ex) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                    vista,
                    Strings.ERROR_LANZAMIENTO + "\nDetalle: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        });
    }
}