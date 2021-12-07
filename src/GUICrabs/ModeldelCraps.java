package GUICrabs;

/**
 * ModelCraps apply crabs Rules
 * estado = 1 natural winner
 * estado = 2 Craps Looser
 * estado = 3 stablish point
 * estado = 4 Point Winner
 * estado = 5 Point Looser
 * @author Sebastian Rey Escobar
 * @version 1.0.0
 * @date 20/11/2021
 */

public class ModeldelCraps {
    //dado1 y dado2 son son los objetos que permiten mostrar el valor de la cara visible del dado
    private Dado dado1, dado2;
    // Tiro es el valor total del tiro (suma de dos caras de dados), punto es el valor del punto en caso que haya hecho un punto,
    //flag es el atributo que me permite saber si estoy en el tiro de salida o en el de punto
    private int tiro, punto, estado, flag;
    // Me permite saber el mensaje correcto de acuerdo al estado que obtengo despues de jugar
    private String estadoToString;
    // es un vector de tamaño dos que contienen los dos valores de las caras de los dadods almancenados
    private int[] caras;

    /**
     * Class Constructor
     */

    public ModeldelCraps() {
        dado1 = new Dado();
        dado2 = new Dado();
        caras = new int[2];
        flag = 0;
    }

    /**
     * Method that stablish the tiro value according to each dice
     */
    public void calcularTiro() {
        caras[0] = dado1.getCara();
        caras[1] = dado2.getCara();
        tiro = caras[0] + caras[1];
    }

    /**
     * Establish game set according to a estado atribute value
     * estado = 1 natural winner
     * estado = 2 Craps Looser
     * estado = 3 stablish point
     * @author Sebastian Rey Escobar
     * @version 1.0.0
     */
    public void determinarJuego() {
        if(flag == 0){
            if (tiro == 7 || tiro == 11){
                estado = 1;
            }else {
                if(tiro == 3 || tiro == 2 || tiro == 12){
                    estado = 2;
                }else {
                    estado= 3;
                    punto = tiro;
                    flag = 1;
                }
            }
        }else{
            //ronda punto
            rondaPunto();
        }
    }

    /**
     * Establish game set according to a estado atribute value
     * estado = 4 Point Winner
     * estado = 5 Point Looser
     */
    private void rondaPunto(){
        if(tiro == punto){
            estado = 4;
            flag = 0;
        }

        if (tiro == 7) {
            estado = 5;
            flag =0;
        }
    }

    public int getTiro() {
        return tiro;
    }

    public int getPunto() {
        return punto;
    }

    /**
     * Establish message game state according to estado atribute value
     * @return Message from the View class   */
    public String getestadoToString() {
        switch (estado){
            case 1: estadoToString = "Sacaste natural, haz ganado!!";
            break;
            case 2: estadoToString = "Sacaste Craps, haz perdido!!";
            break;
            case 3: estadoToString = "Estableciste punto en "+punto+"continua lanzando!!"+
                                      "\n pero si sacas 7 ants que" + punto+ "vas a perder :c";
            break;
            case 4: estadoToString = "Volviste a sacar "+punto+", has ganado";
            break;
            case 5: estadoToString = "Sacaste 7 antes que "+punto+"has perdido!!";
            break;
        }
        return estadoToString;
    }

    public int[] getCaras() {
        return caras;
    }
}

