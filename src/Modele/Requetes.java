/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Main.Constantes;
import Vue.TraitementControlePresenceFrame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Louis, Kevin
 */
public class Requetes {

    /**
     *
     */
    protected static Connection conn;

    public static void initConnexion() {
        try {
            org.postgresql.Driver driver = new org.postgresql.Driver();
            System.out.println("DRIVER OK ! ");

            InputStream ips = new FileInputStream("MDP_connexionBDD.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String url = br.readLine();
            String user = br.readLine();
            String passwd = br.readLine();
            br.close();

            Requetes.conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connection effective !");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Requete concernant les absences d'un élève
     *
     * @param nomEleve
     * @param prenomEleve
     */
    public static HashMap<String, String> requeteAbsenceEtudiant(String nomEleve, String prenomEleve) {

        HashMap<String, String> resultRequete = new HashMap<String, String>();

        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            //On crée notre requête
            String query = ("SELECT COUNT, personne.personne_nom, personne.personne_prenom, "
                    + "seance_date, matiere_libelle, matiere_libellecourt "
                    + "FROM (SELECT COUNT(*),personne_nom, personne_prenom FROM personne,"
                    + " seance, cours, matiere, presence, eleve WHERE personne.personne_id= eleve.personne_id "
                    + "AND presence.eleve_id=eleve.eleve_id AND presence.presence IN (1,2) "
                    + "AND presence.seance_id=seance.seance_id AND seance.cours_id=cours.cours_id "
                    + "AND cours.matiere_id=matiere.matiere_id GROUP BY personne_nom, personne_prenom )ad,"
                    + " personne, seance, cours, matiere, presence, eleve "
                    + "WHERE personne.personne_id= eleve.personne_id *"
                    + "AND presence.eleve_id=eleve.eleve_id AND presence.presence IN (1,2) "
                    + "AND presence.seance_id=seance.seance_id AND seance.cours_id=cours.cours_id "
                    + "AND cours.matiere_id=matiere.matiere_id AND ad.personne_nom=personne.personne_nom "
                    + "AND ad.personne_prenom=personne.personne_prenom "
                    + "WHERE personne.personne_nom = '" + nomEleve + "' AND personne.personne_prenom = '" + prenomEleve
                    + "' ORDER BY seance_date;");

            ResultSet res = state.executeQuery(query);

            while (res.next()) {
                resultRequete.put(res.getString("matiere_libellecourt"), res.getString("COUNT"));
            }

            res.close();
            state.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultRequete;

    }

    /**
     * Requete concernant les absences pour une matière
     *
     * @param nomMatiere
     */
    public static HashMap<String, String> requeteAbsenceMatiere(String nomMatiere) {

        HashMap<String, String> resultRequete = new HashMap<String, String>();

        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            //On crée notre requête
            String query = ("SELECT az.matiere_libelle, az.matiere_libellecourt, COUNT, personne_nom, personne_prenom "
                    + "FROM (SELECT  COUNT(*), "
                    + "matiere_libelle, matiere_libellecourt, matiere.matiere_id "
                    + "FROM personne, seance, cours, matiere, presence, eleve "
                    + "WHERE personne.personne_id= eleve.personne_id "
                    + "AND presence.eleve_id=eleve.eleve_id "
                    + "AND presence.presence IN (1,2) "
                    + "AND presence.seance_id=seance.seance_id "
                    + "AND seance.cours_id=cours.cours_id "
                    + "AND cours.matiere_id=matiere.matiere_id "
                    + "GROUP BY matiere_libelle,matiere_libellecourt,matiere.matiere_id) az, "
                    + "personne, seance, cours, matiere, presence, eleve "
                    + "WHERE personne.personne_id= eleve.personne_id "
                    + "AND presence.eleve_id=eleve.eleve_id "
                    + "AND presence.presence IN (1,2) "
                    + "AND presence.seance_id=seance.seance_id "
                    + "AND seance.cours_id=cours.cours_id "
                    + "AND cours.matiere_id=matiere.matiere_id "
                    + "AND az.matiere_id = matiere.matiere_id "
                    + "WHERE matiere_libelle = '" + nomMatiere + "';");

            ResultSet res = state.executeQuery(query);

            while (res.next()) {
                resultRequete.put(res.getString("personne_nom") + " " + res.getString("personne_prenom"), res.getString("COUNT"));
            }

            res.close();
            state.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return resultRequete;
    }

    
    /**
     * Requete concernant les absences d'un élève dans une matière
     *
     * @param nomEleve
     * @param prenomEleve
     * @param nomMatiere
     */
    public static ArrayList<String> requeteAbsenceEtudiantPourUneMatière(String nomEleve, String prenomEleve, String nomMatiere) {

        ArrayList<String> resultRequete = new ArrayList<String>();

        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            //On crée notre requête
            String query = ("SELECT COUNT, personne.personne_nom, personne.personne_prenom, "
                    + "seance_date, matiere_libelle, matiere_libellecourt "
                    + "FROM (SELECT COUNT(*),personne_nom, personne_prenom FROM personne,"
                    + " seance, cours, matiere, presence, eleve WHERE personne.personne_id= eleve.personne_id "
                    + "AND presence.eleve_id=eleve.eleve_id AND presence.presence IN (1,2) "
                    + "AND presence.seance_id=seance.seance_id AND seance.cours_id=cours.cours_id "
                    + "AND cours.matiere_id=matiere.matiere_id GROUP BY personne_nom, personne_prenom )ad,"
                    + " personne, seance, cours, matiere, presence, eleve "
                    + "WHERE personne.personne_id= eleve.personne_id *"
                    + "AND presence.eleve_id=eleve.eleve_id AND presence.presence IN (1,2) "
                    + "AND presence.seance_id=seance.seance_id AND seance.cours_id=cours.cours_id "
                    + "AND cours.matiere_id=matiere.matiere_id AND ad.personne_nom=personne.personne_nom "
                    + "AND ad.personne_prenom=personne.personne_prenom "
                    + "WHERE personne.personne_nom = '" + nomEleve + "' AND personne.personne_prenom = '" + prenomEleve + "' "
                    + "AND matiere_libellecourt = '" + nomMatiere + "' "
                    + "ORDER BY seance_date;");

            ResultSet res = state.executeQuery(query);

            while (res.next()) {
                resultRequete.add(res.getString("seance_date"));
            }

            res.close();
            state.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultRequete;
    }
    

    public static JList liste(String table, String champ) {

        DefaultListModel listModel = new DefaultListModel();

        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            // automatically fills in the specified fields
            String query = ("SELECT " + table + ". " + champ + " FROM " + table + ";");

            ResultSet r = state.executeQuery(query);

            while (r.next()) {
                listModel.addElement(r.getString(champ));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Erreur", JOptionPane.WARNING_MESSAGE);
        }
        return new JList(listModel);
    }
}
