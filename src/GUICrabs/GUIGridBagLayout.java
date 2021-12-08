package GUICrabs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayout extends JFrame {
    private static final String MENSAJE_INICIO="Bienvenido a Craps \n "
            +"Oprime el boton lanzar para iniciar el juego"
            +"\n si tu tiro de salida es 7 u 11 ganas con Natural :D"
            +"\n si tu tiro de salida es 2, 3 u 12 pierdes :c "
            +"\n Si sacas cualquier otro valor, estableceras el punto "
            +"\n Estando en el punto podras seguir lanzando los dados"
            +"\n pero ahora ganaras si sacas nuevamente el valor del punto "
            +"\n Sin que previamente hayas sacado 7 ";

    private Header headerProject;
    private JLabel dado1,dado2;
    private JButton lanzar,ayuda,salir;
    private JPanel panelDados;
    private ImageIcon imageDado;
    private JTextArea mensajeSalida, resultadosDados;
    private Escucha escucha;
    private ModeldelCraps modeldelCraps;

    public GUIGridBagLayout (){
        initGIU();
        //Default JFrame configuration
        this.setTitle("Juego Craps");
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGIU() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Create Listener Object and Control Object
        escucha = new Escucha();
        modeldelCraps = new ModeldelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);
        ayuda = new JButton(" ? ");
        ayuda.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);
        salir = new JButton(" ? ");
        salir.addActionListener(escucha);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(salir,constraints);

    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            modeldelCraps.calcularTiro();
            int[] caras = modeldelCraps.getCaras();
            imageDado = new ImageIcon(getClass().getResource("/resourses/"+caras[0]+".png"));
            dado1.setIcon(imageDado);
            imageDado = new ImageIcon(getClass().getResource("/resourses/"+caras[1]+".png"));
            dado2.setIcon(imageDado);
            modeldelCraps.determinarJuego();
            mensajeSalida.setRows(4);
            mensajeSalida.setText(modeldelCraps.getestadoToString()[1]);
            resultadosDados.setText(modeldelCraps.getestadoToString()[0]);
        }
    }


}
