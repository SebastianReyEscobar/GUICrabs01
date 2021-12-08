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
        this.setUndecorated(true);
        this.setBackground(new Color(255,255,255,0));
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
        salir = new JButton(" Salir ");
        salir.addActionListener(escucha);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(salir,constraints);


        imageDado = new ImageIcon(getClass().getResource("/resourses/dado.png"));
        dado1 = new JLabel(imageDado);
        dado2 = new JLabel(imageDado);
        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelDados,constraints);

        resultadosDados = new JTextArea(4,31);
        resultadosDados.setBorder(BorderFactory.createTitledBorder("Resultados"));
        resultadosDados.setText("Debes lanzar los dados :D");
        resultadosDados.setBackground(new Color(255,255,255,0));
        resultadosDados.setEditable(false);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(resultadosDados,constraints);


        lanzar = new JButton("lanzar");
        lanzar.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(lanzar,constraints);

        mensajeSalida = new JTextArea(4,31);
        mensajeSalida.setText("Usa el boton ( ? ) Para ver las reglas del juego :3 ");
        mensajeSalida.setBorder(BorderFactory.createTitledBorder("Mensajes"));
        mensajeSalida.setBackground(null);
        mensajeSalida.setEditable(false);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(mensajeSalida,constraints);

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
      if(e.getSource()==lanzar){
          modeldelCraps.calcularTiro();
          int[] caras = modeldelCraps.getCaras();
          imageDado = new ImageIcon(getClass().getResource("/resourses/"+caras[0]+".png"));
          dado1.setIcon(imageDado);
          imageDado = new ImageIcon(getClass().getResource("/resourses/"+caras[1]+".png"));
          dado2.setIcon(imageDado);
          modeldelCraps.determinarJuego();
          mensajeSalida.setText(modeldelCraps.getestadoToString()[1]);
          resultadosDados.setText(modeldelCraps.getestadoToString()[0]);
      }else {
        if(e.getSource()==ayuda){
            JOptionPane.showMessageDialog(null,MENSAJE_INICIO);
        }else{
            System.exit(0);
        }

      }

        }
    }


}
