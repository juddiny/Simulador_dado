import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class DadoVista extends JPanel {
    private JLabel dadoLabel;
    private JButton lanzarButton;
    private JTable tablaLanzamientos;
    private GraficoFrecuencia graficoFrecuencia;

    public DadoVista(GraficoFrecuencia graficoFrecuencia) {
        this.graficoFrecuencia = graficoFrecuencia;
        inicializarComponentes();
        configurarInterfaz();
    }

    private void inicializarComponentes() {
        dadoLabel = new JLabel();
        lanzarButton = new JButton(Strings.BOTON_LANZAR);
        tablaLanzamientos = new JTable(new DefaultTableModel(new String[]{"Últimos Lanzamientos"}, 0));
    }

    private void configurarInterfaz() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setPreferredSize(new Dimension(Strings.VENTANA_ANCHO, Strings.VENTANA_ALTO));

        // Configuración del dado
        configurarDado(gbc);

        // Configuración del botón
        configurarBoton(gbc);

        // Configuración del panel inferior (tabla y gráfico)
        configurarPanelInferior(gbc);
    }

    private void configurarDado(GridBagConstraints gbc) {
        JPanel panelDado = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dadoLabel.setIcon(redimensionarImagen("dado.png",
                (int)(Strings.VENTANA_ANCHO * Strings.DADO_PORCENTAJE_ANCHO),
                (int)(Strings.VENTANA_ALTO * Strings.DADO_PORCENTAJE_ALTO)));
        panelDado.add(dadoLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = Strings.DADO_PORCENTAJE_ALTO;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelDado, gbc);
    }

    private void configurarBoton(GridBagConstraints gbc) {
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lanzarButton.setPreferredSize(new Dimension(
                (int)(Strings.VENTANA_ANCHO * Strings.BOTON_PORCENTAJE_ANCHO),
                (int)(Strings.VENTANA_ALTO * Strings.BOTON_PORCENTAJE_ALTO)));
        panelBoton.add(lanzarButton);

        gbc.gridy = 1;
        gbc.weighty = Strings.BOTON_PORCENTAJE_ALTO;
        add(panelBoton, gbc);
    }

    private void configurarPanelInferior(GridBagConstraints gbc) {
        JPanel panelInferior = new JPanel(new GridLayout(1, 2, 10, 0));
        panelInferior.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Configurar tabla
        JScrollPane scrollTabla = new JScrollPane(tablaLanzamientos);
        scrollTabla.setPreferredSize(new Dimension(
                (int)(Strings.VENTANA_ANCHO * Strings.TABLA_PORCENTAJE_ANCHO),
                (int)(Strings.VENTANA_ALTO * Strings.TABLA_PORCENTAJE_ALTO)));
        panelInferior.add(scrollTabla);

        // Configurar gráfico
        graficoFrecuencia.setPreferredSize(new Dimension(
                (int)(Strings.VENTANA_ANCHO * Strings.GRAFICO_PORCENTAJE_ANCHO),
                (int)(Strings.VENTANA_ALTO * Strings.GRAFICO_PORCENTAJE_ALTO)));
        panelInferior.add(graficoFrecuencia);

        gbc.gridy = 2;
        gbc.weighty = 1.0 - (Strings.DADO_PORCENTAJE_ALTO + Strings.BOTON_PORCENTAJE_ALTO);
        gbc.fill = GridBagConstraints.BOTH;
        add(panelInferior, gbc);
    }

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

    public JButton getLanzarButton() {
        return lanzarButton;
    }

    public void actualizarDado(int resultado) {
        SwingUtilities.invokeLater(() -> {
            dadoLabel.setIcon(redimensionarImagen("dado" + resultado + ".png",
                    (int)(Strings.VENTANA_ANCHO * Strings.DADO_PORCENTAJE_ANCHO),
                    (int)(Strings.VENTANA_ALTO * Strings.DADO_PORCENTAJE_ALTO)));
        });
    }

    public void actualizarTabla(LinkedList<Lanzamiento> historial) {
        SwingUtilities.invokeLater(() -> {
            DefaultTableModel model = (DefaultTableModel) tablaLanzamientos.getModel();
            model.setRowCount(0);
            historial.forEach(lanzamiento ->
                    model.addRow(new Object[]{lanzamiento.getResultado()})
            );
        });
    }

    public void actualizarGrafico() {
        graficoFrecuencia.actualizarGrafico();
    }
}