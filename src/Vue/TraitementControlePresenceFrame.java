package Vue;

import Main.Constantes;
import Modele.Requetes;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;


/**
 *
 * @author Louis, Kevin
 */
public class TraitementControlePresenceFrame extends javax.swing.JFrame {

    protected JFrame frame = new JFrame("Resultats");
    protected JPanel PanneauTitre;
    protected JLabel titre1;
    protected JLabel titre2;
    protected JPanel PanneauChoix;
    protected JComboBox choixAction;
    protected JList listeNomsEleves = new JList();
    protected JList listePrenomsEleves = new JList();
    protected JList listeMatieres = new JList();
    protected JLabel textRequest;
    protected JPanel PanneauFormulaire = new JPanel();
    protected CardLayout cl = new CardLayout();
    protected JPanel PanneauChamps = new JPanel();
    protected String[] list = {"VIDE", "ELEVE", "MATIERE", "ELEVEMATIERE", "PROFESSEUR"};
    
    protected JPanel jPanelVide = new JPanel();
    protected JPanel jPanelEleve = new JPanel();
    protected JPanel jPanelMatiere = new JPanel();
    protected JPanel jPanelEleveMatiere = new JPanel();
    protected JPanel jPanelProfesseur = new JPanel();
    
    protected JPanelChamp PanelChampNomEleve = new JPanelChamp("Nom");
    protected JPanelChamp PanelChampPrenomEleve = new JPanelChamp("Prenom");
    protected JPanelChamp PanelChampMatiereMatiere = new JPanelChamp("Matiere");
    protected JPanelChamp PanelChampNomEleveMatiere = new JPanelChamp("Nom");
    protected JPanelChamp PanelChampPrenomEleveMatiere = new JPanelChamp("Prenom");
    protected JPanelChamp PanelChampMatiereEleveMatiere = new JPanelChamp("Matiere");
    protected JPanelChamp PanelChampNomProfesseur = new JPanelChamp("Nom");
    protected JPanelChamp PanelChampPrenomProfesseur = new JPanelChamp("Prenom");
    
    protected JTextField resultats = new JTextField("resultats");
    protected JPanel PanneauBouton = new JPanel();
    protected JButton creationBouton = new JButton("Générer l'exportation !");

    /**
     * Initialisation des composants de la fenêtre 
     */
    public TraitementControlePresenceFrame() {
        initComponents();
        initPanels();
        this.setLocationRelativeTo(null);
        choixAction.addActionListener(new ItemAction());
        creationBouton.addActionListener(new BoutonListener());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanneauTitre = new javax.swing.JPanel();
        titre2 = new javax.swing.JLabel();
        titre1 = new javax.swing.JLabel();
        PanneauChoix = new javax.swing.JPanel();
        textRequest = new javax.swing.JLabel();
        choixAction = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Traitement des données - Contrôle de présence");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(500, 400));
        setResizable(false);
        getContentPane().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

        PanneauTitre.setMinimumSize(new java.awt.Dimension(500, 80));
        PanneauTitre.setOpaque(false);
        PanneauTitre.setPreferredSize(new java.awt.Dimension(500, 80));

        titre2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        titre2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titre2.setText("Contrôle de présence");
        titre2.setMaximumSize(null);
        titre2.setMinimumSize(null);
        titre2.setPreferredSize(new java.awt.Dimension(500, 40));
        PanneauTitre.add(titre2);

        titre1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        titre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titre1.setText("Traitement des données");
        titre1.setMaximumSize(new java.awt.Dimension(200, 50));
        titre1.setMinimumSize(null);
        titre1.setName(""); // NOI18N
        titre1.setPreferredSize(new java.awt.Dimension(500, 25));
        PanneauTitre.add(titre1);

        getContentPane().add(PanneauTitre);

        PanneauChoix.setMaximumSize(null);
        PanneauChoix.setMinimumSize(new java.awt.Dimension(500, 90));
        PanneauChoix.setPreferredSize(new java.awt.Dimension(500, 90));
        PanneauChoix.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        textRequest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textRequest.setText("Quel objet est concerné par votre requête?");
        textRequest.setMaximumSize(null);
        textRequest.setMinimumSize(null);
        textRequest.setPreferredSize(new java.awt.Dimension(500, 30));
        PanneauChoix.add(textRequest);

        choixAction.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Un élève", "Une matière", "Un élève dans une matière", "Un professeur" }));
        choixAction.setMaximumSize(null);
        choixAction.setMinimumSize(null);
        choixAction.setPreferredSize(new java.awt.Dimension(200, 20));
        choixAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choixActionActionPerformed(evt);
            }
        });
        PanneauChoix.add(choixAction);

        getContentPane().add(PanneauChoix);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initPanels() {
        int width = 400;
        int hgapGridLayout = 20;
        int vgapGridLayout = 10;

        PanneauFormulaire.setPreferredSize(new java.awt.Dimension(width, 250));
        PanneauFormulaire.setLayout(new java.awt.GridLayout(2, 1, 20, 30));

        int heightPanneauChamp = 80;
        jPanelVide.setPreferredSize(new java.awt.Dimension(width, heightPanneauChamp));
        jPanelVide.setLayout(new java.awt.GridLayout(1, 1, hgapGridLayout, vgapGridLayout));

        jPanelEleve.setPreferredSize(new java.awt.Dimension(width, heightPanneauChamp));
        jPanelEleve.setLayout(new java.awt.GridLayout(2, 1, hgapGridLayout, vgapGridLayout));
        jPanelEleve.add(PanelChampNomEleve);
        jPanelEleve.add(PanelChampPrenomEleve);

        jPanelMatiere.setPreferredSize(new java.awt.Dimension(width, heightPanneauChamp));
        jPanelMatiere.setLayout(new java.awt.GridLayout(1, 1, hgapGridLayout, vgapGridLayout));
        jPanelMatiere.add(PanelChampMatiereMatiere);

        jPanelEleveMatiere.setPreferredSize(new java.awt.Dimension(width, heightPanneauChamp));
        jPanelEleveMatiere.setLayout(new java.awt.GridLayout(3, 1, hgapGridLayout, vgapGridLayout));
        jPanelEleveMatiere.add(PanelChampNomEleveMatiere);
        jPanelEleveMatiere.add(PanelChampPrenomEleveMatiere);
        jPanelEleveMatiere.add(PanelChampMatiereEleveMatiere);

        jPanelProfesseur.setPreferredSize(new java.awt.Dimension(width, heightPanneauChamp));
        jPanelProfesseur.setLayout(new java.awt.GridLayout(2, 1, hgapGridLayout, vgapGridLayout));
        jPanelProfesseur.add(PanelChampNomProfesseur);
        jPanelProfesseur.add(PanelChampPrenomProfesseur);

        PanneauChamps.setLayout(cl);
        PanneauChamps.add(jPanelVide, list[0]);
        PanneauChamps.add(jPanelEleve, list[1]);
        PanneauChamps.add(jPanelMatiere, list[2]);
        PanneauChamps.add(jPanelEleveMatiere, list[3]);
        PanneauChamps.add(jPanelProfesseur, list[4]);
        PanneauFormulaire.add(PanneauChamps);

        PanneauBouton.add(creationBouton);
        PanneauBouton.setPreferredSize(new java.awt.Dimension(width, 70));
        PanneauFormulaire.add(PanneauBouton);

        this.getContentPane().add(PanneauFormulaire);
        cl.show(PanneauChamps, list[0]);
        this.setVisible(true);
        
        listeNomsEleves = Requetes.liste(Constantes.TableEleves,Constantes.ChampEleveNom);
        listePrenomsEleves = Requetes.liste(Constantes.TableEleves,Constantes.ChampElevePrenom);
        listeMatieres = Requetes.liste(Constantes.TableCours,Constantes.ChampCoursDesignation);
        
        AutoCompleteDecorator.decorate(listeNomsEleves, PanelChampNomEleve.getText(), ObjectToStringConverter.DEFAULT_IMPLEMENTATION);
        AutoCompleteDecorator.decorate(listePrenomsEleves, PanelChampPrenomEleve.getText(), ObjectToStringConverter.DEFAULT_IMPLEMENTATION);
        AutoCompleteDecorator.decorate(listeMatieres, PanelChampMatiereMatiere.getText(), ObjectToStringConverter.DEFAULT_IMPLEMENTATION);
        AutoCompleteDecorator.decorate(listeNomsEleves, PanelChampNomEleveMatiere.getText(), ObjectToStringConverter.DEFAULT_IMPLEMENTATION);
        AutoCompleteDecorator.decorate(listeMatieres, PanelChampMatiereEleveMatiere.getText(), ObjectToStringConverter.DEFAULT_IMPLEMENTATION);
    
    }
    
    
    private void choixActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choixActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_choixActionActionPerformed
    
    /**
     * Les champs de la JTable sont initialisés selon l'item selectionné de la liste déroulante
     * @param args the command line arguments
     */
    class ItemAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (choixAction.getSelectedIndex()) {
                case 1:
                    cl.show(PanneauChamps, list[1]);
                    break;
                case 2:
                    cl.show(PanneauChamps, list[2]);
                    break;
                case 3:
                    cl.show(PanneauChamps, list[3]);
                    break;
                case 4:
                    cl.last(PanneauChamps);
                    break;
                default:
                    break;
            }

        }
    }

    
    /**
     * Récupération des valeurs entrées par l'utilisateur lors de la validation du choix 
     * Elles sont traitées dans TraitementControlePresence.java puis analysées
     * 
     */
    class BoutonListener implements ActionListener {
        //Redéfinition de la méthode actionPerformed()

        @Override
        public void actionPerformed(ActionEvent arg0) {

            switch (choixAction.getSelectedIndex()) {
                case 1:
                    Requetes.creationFormulaireAbsenceEtudiant(
                            PanelChampNomEleve.getContenu(),
                            PanelChampPrenomEleve.getContenu());
                    break;
                case 2:
                    Requetes.creationFormulaireAbsenceMatiere(
                            PanelChampMatiereMatiere.getContenu());
                    break;
                case 3:
                    Requetes.creationFormulaireAbsenceEtudiantPourUneMatière(
                            PanelChampNomEleveMatiere.getContenu(),
                            PanelChampPrenomEleveMatiere.getContenu(),
                            PanelChampMatiereEleveMatiere.getContenu());
                    break;
                case 4:
                    Requetes.creationFormulaireAbsenceEtudiant(
                            PanelChampNomProfesseur.getContenu(),
                            PanelChampPrenomProfesseur.getContenu());
                    break;
                default:
                    ;
            }
        }
    }


    /**
     * Message d'avertissement si matière/élève non repertorié
     * @param message
     * @param titre
     */
    public static void avertissement(String message, String titre) {

        JOptionPane.showMessageDialog(null, message, titre, JOptionPane.WARNING_MESSAGE);

    }

    /**
     * Message d'information lors de la génération du document Excel
     * @param message
     * @param titre
     */
    public static void notification(String message, String titre) {

        JOptionPane.showMessageDialog(null, message, titre, JOptionPane.INFORMATION_MESSAGE);
    }
}
/*
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanneauChoix;
    private javax.swing.JPanel PanneauTitre;
    private javax.swing.JComboBox choixAction;
    private javax.swing.JLabel textRequest;
    private javax.swing.JLabel titre1;
    private javax.swing.JLabel titre2;
    // End of variables declaration//GEN-END:variables
}*/
