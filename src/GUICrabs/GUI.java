package GUICrabs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used as View Craps Class
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {
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
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon imageDado;
    private JTextArea mensajeSalida, resultadosDados;
    private JSeparator separator;
    private Escucha escucha;
    private ModeldelCraps modeldelCraps;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Juego Craps");
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        escucha = new Escucha();
        modeldelCraps = new ModeldelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH);
        imageDado = new ImageIcon(getClass().getResource("/resourses/dado.png"));
        dado1 = new JLabel(imageDado);
        dado2 = new JLabel(imageDado);
        lanzar = new JButton("lanzar");
        lanzar.addActionListener(escucha);
        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);

        this.add(panelDados,BorderLayout.CENTER);

        mensajeSalida = new JTextArea(7,31);
        mensajeSalida.setText(MENSAJE_INICIO);
        JScrollPane scroll = new JScrollPane(mensajeSalida);
        panelResultados = new JPanel();
        panelResultados.setBorder(BorderFactory.createTitledBorder("¿Que debes hacer?"));
        panelResultados.add(scroll);
        panelResultados.setPreferredSize(new Dimension(370,180));
        this.add(panelResultados,BorderLayout.EAST);

        resultadosDados = new JTextArea(4,31);
        separator = new JSeparator();
        separator.setPreferredSize(new Dimension(350,5));
        separator.setBackground(Color.black);

    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
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
            panelResultados.removeAll();
            panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));
            panelResultados.add(resultadosDados);
            panelResultados.add(separator);
            panelResultados.add(mensajeSalida);
            mensajeSalida.setRows(4);
            mensajeSalida.setText(modeldelCraps.getestadoToString()[1]);
            resultadosDados.setText(modeldelCraps.getestadoToString()[0]);
            revalidate();
            repaint();
        }
    }
}
