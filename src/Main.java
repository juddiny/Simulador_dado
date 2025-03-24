import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Dado dado = new Dado();
            GraficoFrecuencia grafico = new GraficoFrecuencia();
            DadoVista vista = new DadoVista(grafico);
            new Controlador(dado, vista, grafico);

            JFrame frame = new JFrame(Strings.TITULO_VENTANA);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(vista);

            frame.pack();
            frame.setSize(Strings.VENTANA_ANCHO, Strings.VENTANA_ALTO); // Forzar tamaño
            frame.setResizable(false); // Opcional: evitar redimensionado
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}