/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formulaire;

import Main.Constantes;
import Modele.Requetes;
import Vue.TraitementControlePresenceFrame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Louis
 */
public class Formulaire {

    /**
     * Creation d'un formulaire Excel pour relevé de présence d'un élève pour
     * toutes les matières
     *
     * @param nomEleve
     * @param prenomEleve
     */
    public static void creationFormulaireAbsenceEtudiantCSV(String nomEleve, String prenomEleve) {

        String contenu = "Relevé d'absence de l'élève " + nomEleve + " " + prenomEleve + "\n";

        HashMap<String, String> resultRequete = Requetes.requeteAbsenceEtudiant(nomEleve, prenomEleve);

        for (Map.Entry<String, String> entry : resultRequete.entrySet()) {
            contenu += entry.getKey() + ";" + entry.getValue() + "\n";
        }

        if (generateCsvFile(Constantes.nomFichier + nomEleve + prenomEleve + ".csv", contenu)) {
            TraitementControlePresenceFrame.notification("Le relevé de présence a été généré", "Notification éléve");
        } else {
            TraitementControlePresenceFrame.avertissement("Le fichier n'a pu être généré", "Erreur fichier");
        }

    }


    /**
     * Creation d'un formulaire Excel pour relevé de présence des élèves pour une matière donnée
     * @param nomMatiere
     */
    public static void creationFormulaireAbsenceMatiereCSV(String nomMatiere) {
        
        String contenu = "Relevé d'absence pour la matière " + nomMatiere + "\n";

        HashMap<String, String> resultRequete = Requetes.requeteAbsenceMatiere(nomMatiere);

        for (Map.Entry<String, String> entry : resultRequete.entrySet()) {
            contenu += entry.getKey() + ";" + entry.getValue() + "\n";
        }

        if (generateCsvFile(Constantes.nomFichier + nomMatiere + ".csv", contenu)) {
            TraitementControlePresenceFrame.notification("Le relevé de présence a été généré", "Notification éléve");
        } else {
            TraitementControlePresenceFrame.avertissement("Le fichier n'a pu être généré", "Erreur fichier");
        }
        
    }
        
    /**
     * Creation d'un formulaire Excel pour relevé de présence d'un élève pour
     * une matière donnée
     *
     * @param nomEleve
     * @param prenomEleve
     * @param nomMatiere
     */
    public static void creationFormulaireAbsenceEtudiantPourUneMatièreCSV(String nomEleve, String prenomEleve, String nomMatiere) {

        String contenu = "Relevé d'absence pour l'élève " + nomEleve + " " + prenomEleve + " pour la matière " + nomMatiere + "\n";

        ArrayList<String> resultRequete = Requetes.requeteAbsenceEtudiantPourUneMatière(nomEleve, prenomEleve, nomMatiere);

        for (String date : resultRequete) {
            contenu += date + "\n";
        }

        if (generateCsvFile(Constantes.nomFichier + nomEleve + prenomEleve + nomMatiere + ".csv", contenu)) {
            TraitementControlePresenceFrame.notification("Le relevé de présence a été généré", "Notification éléve");
        } else {
            TraitementControlePresenceFrame.avertissement("Le fichier n'a pu être généré", "Erreur fichier");
        }
        
    }
    
    
    /**
     * Génération du document Excel contenant les informations nécessaires avec
     * vérification si existence dossier
     *
     * @param sFileName
     * @param contenu
     * @return
     */
    private static boolean generateCsvFile(String sFileName, String contenu) {

        boolean fichierGenere = false;
        File csv = new File(Constantes.repertoire);

        if (!csv.exists() && !csv.isDirectory()) {
            new File(Constantes.repertoire).mkdir();
        }
        try {
            FileWriter writer = new FileWriter(Constantes.repertoire + "/" + sFileName);

            writer.append(contenu);

            writer.flush();
            writer.close();
            fichierGenere = true;

        } catch (NullPointerException e) {
            System.out.println("Erreur : pointeur null");
        } catch (IOException e) {
            System.out.println("Problème d'IO");
            e.printStackTrace();
        }
        return fichierGenere;
    }
}