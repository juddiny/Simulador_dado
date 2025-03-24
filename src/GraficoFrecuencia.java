import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*; // Importación añadida para Dimension
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Clase que genera y muestra el gráfico de frecuencia.
 */
public class GraficoFrecuencia extends JPanel {
    private DefaultCategoryDataset dataset;
    private Random random;
    private Map<Integer, Integer> frecuencia;
    private int totalLanzamientos;

    public GraficoFrecuencia() {
        dataset = new DefaultCategoryDataset();
        random = new Random();
        frecuencia = new HashMap<>();
        totalLanzamientos = 0;

        // Inicializar frecuencias
        for (int i = 1; i <= 6; i++) {
            frecuencia.put(i, 0);
        }

        // Configuración del panel
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(
                (int)(Strings.VENTANA_ANCHO * Strings.GRAFICO_PORCENTAJE_ANCHO),
                (int)(Strings.VENTANA_ALTO * Strings.GRAFICO_PORCENTAJE_ALTO)
        ));

        // Simular lanzamientos iniciales
        simularLanzamientos(Strings.Lanzamientos);

        // Crear gráfico
        JFreeChart chart = ChartFactory.createBarChart(
                Strings.TITULO_GRAFICA,
                Strings.RESULTADOS_GRAFICA,
                Strings.VARIABLE_GRAFICA,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(
                        (int)(Strings.VENTANA_ANCHO * Strings.GRAFICO_PORCENTAJE_ANCHO),
                        (int)(Strings.VENTANA_ALTO * Strings.GRAFICO_PORCENTAJE_ALTO)
                );
            }
        };

        add(chartPanel, BorderLayout.CENTER);
    }

    private void simularLanzamientos(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            int resultado = random.nextInt(6) + 1;
            frecuencia.put(resultado, frecuencia.get(resultado) + 1);
            totalLanzamientos++;
        }
        actualizarGrafico();
    }

    public int realizarLanzamiento() {
        int resultado = random.nextInt(6) + 1;
        frecuencia.put(resultado, frecuencia.get(resultado) + 1);
        totalLanzamientos++;
        actualizarGrafico();
        return resultado;
    }

    public void actualizarGrafico() {
        SwingUtilities.invokeLater(() -> {
            dataset.clear();
            frecuencia.forEach((k, v) -> dataset.addValue(v, Strings.VARIABLE_GRAFICA, k));
        });
    }

    public Object[] getNumeroMasProbable() {
        int numeroMasProbable = 1;
        int maxFrecuencia = frecuencia.get(1);

        for (Map.Entry<Integer, Integer> entry : frecuencia.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                numeroMasProbable = entry.getKey();
            }
        }

        double porcentaje = (maxFrecuencia * 100.0) / totalLanzamientos;
        return new Object[]{numeroMasProbable, String.format("%.2f%%", porcentaje)};
    }
}