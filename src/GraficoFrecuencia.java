import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Clase que genera y muestra el gráfico de frecuencia.
 */
public class GraficoFrecuencia extends JPanel {
    private DefaultCategoryDataset dataset;
    private Random random;
    private Map<Integer, Integer> frecuencia; // Mapa para almacenar la frecuencia de los resultados
    private int totalLanzamientos; // Contador de lanzamientos totales

    public GraficoFrecuencia() {
        dataset = new DefaultCategoryDataset();
        random = new Random();
        frecuencia = new HashMap<>();
        totalLanzamientos = 0;

        // Inicializar el mapa con frecuencias en 0
        for (int i = 1; i <= 6; i++) {
            frecuencia.put(i, 0);
        }

        // Simular 1000 lanzamientos al iniciar
        simularLanzamientos(1000);
        actualizarGrafico();

        // Crear el gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                "Frecuencia de Lanzamientos", // Título del gráfico
                "Resultado",                   // Etiqueta del eje X
                "Frecuencia",                  // Etiqueta del eje Y
                dataset,                       // Datos
                PlotOrientation.VERTICAL,      // Orientación
                true,                         // Incluir leyenda
                true,                         // Tooltips
                false                         // URLs
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);
    }

    /**
     * Simula una cantidad específica de lanzamientos de un dado.
     * @param cantidad La cantidad de lanzamientos a simular.
     */
    private void simularLanzamientos(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            int resultado = random.nextInt(6) + 1; // Generar un número aleatorio entre 1 y 6
            frecuencia.put(resultado, frecuencia.get(resultado) + 1); // Actualizar la frecuencia
            totalLanzamientos++;
        }
    }

    /**
     * Realiza un lanzamiento de dado y actualiza la frecuencia.
     * @return El resultado del lanzamiento.
     */
    public int realizarLanzamiento() {
        int resultado = random.nextInt(6) + 1;
        frecuencia.put(resultado, frecuencia.get(resultado) + 1);
        totalLanzamientos++;
        actualizarGrafico();
        return resultado; // Ahora retorna el resultado para usarlo en el controlador
    }

    /**
     * Actualiza el gráfico con los datos actuales de frecuencia.
     */
    public void actualizarGrafico() {
        dataset.clear();
        for (Map.Entry<Integer, Integer> entry : frecuencia.entrySet()) {
            dataset.addValue(entry.getValue(), "Frecuencia", entry.getKey());
        }
    }

    /**
     * Obtiene el número con mayor probabilidad y su porcentaje.
     * @return Un arreglo con el número y su porcentaje de aparición.
     */
    public Object[] getNumeroMasProbable() {
        int numeroMasProbable = 1;
        int maxFrecuencia = frecuencia.get(1);

        // Encontrar el número con mayor frecuencia
        for (Map.Entry<Integer, Integer> entry : frecuencia.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                numeroMasProbable = entry.getKey();
            }
        }

        // Calcular el porcentaje de aparición
        double porcentaje = (maxFrecuencia * 100.0) / totalLanzamientos;
        return new Object[]{numeroMasProbable, String.format("%.2f%%", porcentaje)};
    }
}