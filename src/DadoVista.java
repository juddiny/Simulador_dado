import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

/**
 * Clase que representa la interfaz gráfica del dado.
 */
public class DadoVista extends JPanel {
    private JLabel dadoLabel;
    private JButton lanzarButton;
    private JTable tablaLanzamientos;
    private GraficoFrecuencia graficoFrecuencia;

    public DadoVista(GraficoFrecuencia graficoFrecuencia) {
        this.graficoFrecuencia = graficoFrecuencia; // Inicializar graficoFrecuencia

        // Usar BorderLayout para organizar los componentes
        setLayout(new BorderLayout());

        // Panel superior para el dado
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dadoLabel = new JLabel(redimensionarImagen("dado.png", 100, 100)); // Imagen inicial del dado
        panelSuperior.add(dadoLabel);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central para el botón
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lanzarButton = new JButton(Strings.BOTON_LANZAR);

        // Agregar un margen al botón
        lanzarButton.setBorder(new EmptyBorder(10, 20, 10, 20)); // Margen: arriba, izquierda, abajo, derecha
        panelCentral.add(lanzarButton);

        // Agregar un margen al panel central para separar el botón de la tabla y el gráfico
        panelCentral.setBorder(new EmptyBorder(10, 0, 20, 0)); // Margen: arriba, izquierda, abajo, derecha
        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior para la tabla y el gráfico
        JPanel panelInferior = new JPanel(new GridLayout(1, 2)); // 1 fila, 2 columnas

        // Crear la tabla con un título personalizado
        String[] columnas = {getTituloTabla()}; // Título de la columna
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0); // Modelo de tabla con el título
        tablaLanzamientos = new JTable(modeloTabla);
        tablaLanzamientos.setEnabled(false); // La tabla no es editable
        panelInferior.add(new JScrollPane(tablaLanzamientos));

        panelInferior.add(graficoFrecuencia);
        add(panelInferior, BorderLayout.SOUTH);
    }

    public JButton getLanzarButton() {
        return lanzarButton;
    }

    public void actualizarDado(int resultado) {
        dadoLabel.setIcon(redimensionarImagen("dado" + resultado + ".png", 100, 100));
    }

    /**
     * Actualiza la tabla con los últimos lanzamientos.
     * @param historial Lista de los últimos lanzamientos.
     */
    public void actualizarTabla(LinkedList<Lanzamiento> historial) {
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaLanzamientos.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla

        for (Lanzamiento lanzamiento : historial) {
            modeloTabla.addRow(new Object[]{lanzamiento.getResultado()}); // Agregar cada lanzamiento a la tabla
        }

        // Actualizar el título de la tabla
        modeloTabla.setColumnIdentifiers(new String[]{getTituloTabla()});
    }

    /**
     * Obtiene el título de la tabla con el número más probable y su porcentaje.
     * @return El título de la tabla.
     */
    private String getTituloTabla() {
        Object[] numeroMasProbable = graficoFrecuencia.getNumeroMasProbable();
        return "Últimos Lanzamientos - " + numeroMasProbable[0] + " (" + numeroMasProbable[1] + ")";
    }

    public void actualizarGrafico() {
        graficoFrecuencia.actualizarGrafico(); // Llamar al método sin argumentos
    }

    /**
     * Método para cargar y redimensionar una imagen desde la carpeta resources.
     * @param nombreImagen El nombre del archivo de la imagen.
     * @param ancho El ancho deseado de la imagen.
     * @param alto El alto deseado de la imagen.
     * @return Un ImageIcon con la imagen redimensionada.
     */
    private ImageIcon redimensionarImagen(String nombreImagen, int ancho, int alto) {
        try {
            java.net.URL imageURL = getClass().getClassLoader().getResource(nombreImagen);
            if (imageURL == null) {
                throw new RuntimeException("No se pudo encontrar la imagen: " + nombreImagen);
            }
            ImageIcon iconoOriginal = new ImageIcon(imageURL);
            Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(imagenRedimensionada);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la imagen: " + nombreImagen, e);
        }
    }
}