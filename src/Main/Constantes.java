/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.HashMap;

/**
 *
 * @author Louis
 */
public final class Constantes {

    public final static HashMap presence = new HashMap();
    static {
        presence.put("1", "Présent");
        presence.put("2", "Absent");
        presence.put("3", "Excusé");
    }
    public final static String repertoire = "csv";
    public final static String nomFichier = "FormulaireAbsence";
    
    public final static String TableEleves = "eleves";
    public final static String TableCours = "cours";
    
     public final static String ChampEleveNom = "eleves_nom";
     public final static String ChampElevePrenom = "eleves_prenom";
     public final static String ChampCoursDesignation = "cours_designation";
}
