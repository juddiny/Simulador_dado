import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Clase que controla la interacción entre la vista y el modelo.
 */
public class Controlador {
    private Dado dado;
    private DadoVista vista;
    private HistorialLanzamientos historial;
    private GraficoFrecuencia graficoFrecuencia;

    public Controlador(Dado dado, DadoVista vista, GraficoFrecuencia graficoFrecuencia) {
        this.dado = dado;
        this.vista = vista;
        this.historial = new HistorialLanzamientos();
        this.graficoFrecuencia = graficoFrecuencia;

        vista.getLanzarButton().addActionListener(e -> {
            try {
                int resultado = dado.lanzar();
                graficoFrecuencia.realizarLanzamiento(); // Actualiza el gráfico
                historial.añadirLanzamiento(new Lanzamiento(resultado));
                vista.actualizarDado(resultado);
                vista.actualizarTabla(historial.getHistorial());
                vista.actualizarGrafico();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, Strings.ERROR_LANZAMIENTO,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}