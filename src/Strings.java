/**
 * Clase que centraliza todos los mensajes y textos utilizados en la aplicación.
 */
public class Strings {
    // Nombres y titulos y mensajes en el código
    public static final String TITULO_VENTANA = "Simulador de Dado";
    public static final String BOTON_LANZAR = "Lanzar Dado";
    public static final String ERROR_LANZAMIENTO = "Error al lanzar el dado";
    // Dimensiones (en píxeles de la ventana)
    public static final int VENTANA_ANCHO = 500;
    public static final int VENTANA_ALTO = 500;

    // Porcentajes de componentes (en porcentaje de participacion dendtro de la ventana principal)
    public static final double DADO_PORCENTAJE_ANCHO = 0.3;
    public static final double DADO_PORCENTAJE_ALTO = 0.3;
    public static final double BOTON_PORCENTAJE_ANCHO = 0.3;
    public static final double BOTON_PORCENTAJE_ALTO = 0.1;
    public static final double GRAFICO_PORCENTAJE_ANCHO = 0.4;
    public static final double GRAFICO_PORCENTAJE_ALTO = 0.4;
    public static final double TABLA_PORCENTAJE_ANCHO = 0.4;
    public static final double TABLA_PORCENTAJE_ALTO = 0.4;

    // cantidad de lanzamientos (estos se muestran inicialmnete en la tabla y son el punto de referencia para el calculo del numero mas probable)
    public static final int Lanzamientos=10;
}