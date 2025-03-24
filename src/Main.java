import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear los componentes principales
            Dado dado = new Dado();
            GraficoFrecuencia grafico = new GraficoFrecuencia();
            DadoVista vista = new DadoVista(grafico);

            // Crear el controlador
            new Controlador(dado, vista, grafico);

            // Configurar y mostrar la ventana principal
            JFrame frame = new JFrame(Strings.TITULO_VENTANA);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(vista);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}