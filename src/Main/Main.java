/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Modele.Requetes;
import Vue.TraitementControlePresenceFrame;

/**
 *
 * @author Louis
 */
public class Main {
        /**
     * @param args the command line arguments
     * Connexion base de données après lecture des paramètres de connexion fichier MDP_connexionBDD.txt
     */
    public static void main(String[] args) {

        Requetes.initConnexion();

        TraitementControlePresenceFrame TraitementFrame = new TraitementControlePresenceFrame();
        TraitementFrame.setVisible(true);
    }
}
