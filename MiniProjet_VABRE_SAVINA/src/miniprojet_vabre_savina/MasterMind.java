package miniprojet_vabre_savina;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import static javax.swing.UIManager.getColor;

/*
 * SAVINA Liza
 * VABRE Aliénor
 * 29/11/2024
 */

/**
 *
 * @author alien
 */
public class MasterMind extends JFrame {
    
    private Color[] couleurs = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, 
                            Color.MAGENTA, Color.ORANGE, Color.PINK, Color.CYAN};// Au plus 8 couleurs
    private Map<Color, Integer> couleurToNumero = new HashMap<>();
    private JButton btnValider;

    private Integer[][] plateauDeJeu = null; 
    private JButton[][] grilleButtons = null;
    private JButton[] combinaisonButtons = null;
    private int[] combinaisonNumerique = null; 
     
    private int[] combinaisonSecrete = null;
    
    private JButton[][] indicesButtons = null;
    private int NbCouleur;
    private int Taille;
    private int Tentative;
    private static MasterMind instance;

    /**
     * Constructeur principal
     */
    // Etre sur d'avoir un singleton
    public static MasterMind Instance(int nbCouleur,int taille, int tentative)
    {
        if (null==MasterMind.instance)
        {
            MasterMind.instance = new MasterMind(nbCouleur,taille, tentative);
        }
        return MasterMind.instance;
    }
    
    public MasterMind(int nbCouleur,int taille, int tentative) {
        setTitle("MasterMind");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(700, 600);
        this.NbCouleur = nbCouleur;
        this.Taille = taille;
        this.Tentative = tentative;
        this.grilleButtons = new JButton[this.Tentative][this.Taille];
        this.combinaisonButtons = new JButton[this.Taille];
        this.combinaisonNumerique = new int[this.Taille];
        this.plateauDeJeu = new Integer[this.Tentative][this.Taille];
        this.combinaisonSecrete = new int[this.Taille];
        this.indicesButtons = new JButton[this.Tentative][this.Taille];
        initMapCouleurs(); // Associe les couleurs aux numéros
        initCustomComponent(); // On pose les composant sur la JFRame MasterMind
        //initComponents();
        configurerPanneauGrille();// Croisement nombre de tentatives avec nombre de couleurs
        configurerPanneauCombinaison();
        configurerPanneauIndices();

        // Listener pour le bouton Valider
        btnvalider.addActionListener(e -> validerCombinaison());
        
        lancerPartie();
    }

    /**
     * Initialise la correspondance entre les couleurs et les numéros.
     */
    private void initMapCouleurs() 
    {
        for (int i = 0; i < this.NbCouleur; i++) 
        {
            couleurToNumero.put(couleurs[i], i + 1); // Associe les couleurs à des numéros (1, 2, 3, 4, ...)
        }
    }

    /**
     * Configure les boutons dans le panneau de grille.
     */
    private void configurerPanneauGrille() {
        PanneauGrille.setLayout(new GridLayout(this.Tentative, this.Taille, 3, 3));
        for (int i = 0; i < this.Tentative; i++) {
            for (int j = 0; j < this.Taille; j++) {
                grilleButtons[i][j] = new JButton();
                grilleButtons[i][j].setEnabled(false); // Désactivé par défaut
                grilleButtons[i][j].setBackground(Color.WHITE);
                PanneauGrille.add(grilleButtons[i][j]);
            }
        }
    }

    /**
     * Configure les boutons dans le panneau de combinaison.
     */
    private void configurerPanneauCombinaison() {
        Combinaison.setLayout(new GridLayout(1, this.Taille, 3, 3));
        // Ajuster la taille des boutons pour correspondre au tableau principal
        

        for (int i = 0; i < this.Taille; i++) {
            combinaisonButtons[i] = new JButton();
            combinaisonButtons[i].setBackground(couleurs[0]); // Couleur initiale
            combinaisonNumerique[i] = couleurToNumero.get(couleurs[0]); // Numéro initial
            final int index = i;
            combinaisonButtons[i].addActionListener(e -> {
                // Change la couleur et met à jour le numéro correspondant
                Color currentColor = combinaisonButtons[index].getBackground();
                int nextIndex = (indexCouleur(couleurs, currentColor) + 1) % this.NbCouleur;
                Color nextColor = couleurs[nextIndex];
                combinaisonButtons[index].setBackground(nextColor);
                combinaisonNumerique[index] = couleurToNumero.get(nextColor);
            });
            
            Combinaison.add(combinaisonButtons[i]);
        }
    }
    
    private void configurerPanneauIndices() {
        panneauIndices.setLayout(new GridLayout(this.Tentative, this.Taille, 3, 1));
        for (int i = 0; i < this.Tentative; i++) {
            for (int j = 0; j < this.Taille; j++) {
                indicesButtons[i][j] = new JButton();
                indicesButtons[i][j].setEnabled(false);
                indicesButtons[i][j].setBackground(Color.GRAY); // Par défaut, gris
                panneauIndices.add(indicesButtons[i][j]);
            }
        }
    }
    
    /**
     * Retourne l'index d'une couleur donnée dans le tableau de couleurs.
     */
    private int indexCouleur(Color[] couleurs, Color couleur) {
        for (int i = 0; i < couleurs.length; i++) {
            if (couleurs[i].equals(couleur)) {
                return i;
            }
        }
        return -1; // Couleur non trouvée
    }
    
    private int couleurEnNumero(Color couleur) {
        return couleurToNumero.getOrDefault(couleur, -1); // Retourne -1 si la couleur n'existe pas
    }
    
    private void lancerPartie() {
        Random random = new Random();
        for (int i = 0; i < this.Taille; i++) {
            combinaisonSecrete[i] = random.nextInt(this.NbCouleur) + 1;
        }
        System.out.println("Combinaison secrète : ");
        for (int n : combinaisonSecrete) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
    
    /**
     * Valide la combinaison en cours, l'ajoute au plateau et l'affiche dans la grille.
     */
   
    
    private int ligneActuelle = 0; 

    public void validerCombinaison() {
       
        
    // Récupérer la combinaison saisie en couleur.
       Color[] combinaisonSaisie = new Color[this.Taille];
        for (int i = 0; i < this.Taille; i++) {
            combinaisonSaisie[i] = combinaisonButtons[i].getBackground(); 
        }
 
    // Afficher la combinaison dans la matrice de jeu
        for (int colonne = 0; colonne < this.Taille; colonne++) 
        {
            grilleButtons[ligneActuelle][colonne].setBackground(combinaisonSaisie[colonne]);
        }

        int[] combinaisonSaisieNumerique = new int[this.Taille];
        for (int i = 0; i < this.Taille; i++) {
            combinaisonSaisieNumerique[i] = couleurEnNumero(combinaisonButtons[i].getBackground());
        }

////        Pion[] combinaisonSaisie = new Pion[4];
////        for (int i = 0; i < 4; i++) {
////            combinaisonSaisie[i] = new Pion(combinaisonButtons[i].getBackground()); 
////        }
////         Add
////        Combinaison combinaisonUtilisateur = new Combinaison(combinaisonSaisie);
////        End
////        
////     Afficher la combinaison dans la matrice de jeu
////        for (int colonne = 0; colonne < 4; colonne++) {
////            grilleButtons[ligneActuelle][colonne].setBackground(combinaisonSaisie[colonne].getCouleur());
////        }
////
////        int[] combinaisonSaisieNumerique = new int[4];
////        for (int i = 0; i < 4; i++) {
////            combinaisonSaisieNumerique[i] = couleurEnNumero(combinaisonButtons[i].getBackground());
////        }
        //Ici, on va utiliser le plateau jeu
        
    boolean[] secretUtilise = new boolean[this.Taille]; // Pour marquer les numéros déjà utilisés
    boolean[] saisieUtilisee = new boolean[this.Taille];

    // Vérification des pions bien placés (noirs)
        int pionsBienPlaces = 0;
        for (int i = 0; i < this.Taille; i++) {
            if (combinaisonSaisieNumerique[i] == combinaisonSecrete[i]) {
                pionsBienPlaces++;
                secretUtilise[i] = true;
                saisieUtilisee[i] = true;
            }
        }

    // Vérification des pions mal placés (blancs)
    int pionsMalPlaces = 0;
    for (int i = 0; i < this.Taille; i++) {
        if (!saisieUtilisee[i]) {
            for (int j = 0; j < 4; j++) {
                if (!secretUtilise[j] && combinaisonSaisieNumerique[i] == combinaisonSecrete[j]) {
                    pionsMalPlaces++;
                    secretUtilise[j] = true;
                    saisieUtilisee[i] = true;
                    break;
                }
            }
        }
    }


    // Étape 4 : Afficher les indices à gauche de la matrice
    int index = 0;

    // Affiche les pions noirs (bien placés)
        for (int i = 0; i < pionsBienPlaces; i++) 
        {
            indicesButtons[ligneActuelle][index].setBackground(Color.BLACK);
            index++;
        }

    // Affiche les pions blancs (mal placés)
        for (int i = 0; i < pionsMalPlaces; i++) {
            indicesButtons[ligneActuelle][index].setBackground(Color.WHITE);
            index++;
        }
        
        if (pionsBienPlaces == this.Taille) {
            JOptionPane.showMessageDialog(null, "Bravo ! Vous avez trouvé la combinaison !");
            reinitialiserPartie();
            return;
        }

        if (ligneActuelle == grilleButtons.length - 1) { // Dernière tentative échouée
            System.out.println("Défaite : dernière tentative atteinte.");
            JOptionPane.showMessageDialog(null, "Dommage ! Vous avez perdu. La combinaison secrète était : " + afficherCombinaisonSecrete());
            reinitialiserPartie();
            return;
        }
        
        System.out.println("Ligne actuelle : " + ligneActuelle);
        ligneActuelle++;
        
    }
        private String[] nomsCouleurs = {"RED", "BLUE", "GREEN", "YELLOW"};
        
        private String afficherCombinaisonSecrete() {
            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            for (int i = 0; i < combinaisonSecrete.length; i++) {
                sb.append(nomsCouleurs[combinaisonSecrete[i] - 1]); // Récupère le nom de la couleur
                if (i < combinaisonSecrete.length - 1) {
                    sb.append(" | ");
                }
            }
            sb.append(" ]");
            return sb.toString();
        }



    private void reinitialiserPartie() {
        ligneActuelle = 0;
        //plateauDeJeu = new Integer[8][4];
        lancerPartie();
        for (JButton[] ligne : grilleButtons) {
            for (JButton bouton : ligne) {
                bouton.setBackground(Color.WHITE);
            }
        }
        for (JButton[] ligneIndices : indicesButtons) {
            for (JButton bouton : ligneIndices) {
                bouton.setBackground(Color.GRAY);
            }
        }
    }
    
   
    
        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
       
    private void initCustomComponent() 
    {

        PanneauGrille = new javax.swing.JPanel();
        Combinaison = new javax.swing.JPanel();
        btnvalider = new javax.swing.JButton();
        panneauIndices = new javax.swing.JPanel();
//        JButton btnReinitialiser = new javax.swing.JButton("reinitialiser le jeu");
//         btnReinitialiser.addActionListener(new java.awt.event.ActionListener() 
//         {
//            public void actionPerformed(java.awt.event.ActionEvent evt) 
//            {
//                MasterMind.instance = null;
//                MasterMind.instance.setVisible(false);
//            }
//        });
        
        // A virer mais a voir
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanneauGrille.setBackground(new java.awt.Color(200, 200, 200));
        PanneauGrille.setLayout(new java.awt.GridLayout(this.Tentative, this.NbCouleur, 3, 3));

        Combinaison.setBackground(new java.awt.Color(200, 200, 200));

        javax.swing.GroupLayout CombinaisonLayout = new javax.swing.GroupLayout(Combinaison);
        Combinaison.setLayout(CombinaisonLayout);
        CombinaisonLayout.setHorizontalGroup(
            CombinaisonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 227, Short.MAX_VALUE)
        );
        CombinaisonLayout.setVerticalGroup(
            CombinaisonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        btnvalider.setText("valider");
        btnvalider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvaliderActionPerformed(evt);
            }
        });
        //this.add(btnReinitialiser, BorderLayout.NORTH);
        panneauIndices.setBackground(new java.awt.Color(200, 200, 200));

        javax.swing.GroupLayout panneauIndicesLayout = new javax.swing.GroupLayout(panneauIndices);
        panneauIndices.setLayout(panneauIndicesLayout);
        panneauIndicesLayout.setHorizontalGroup(
            panneauIndicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
        );
        panneauIndicesLayout.setVerticalGroup(
            panneauIndicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panneauIndices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanneauGrille, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Combinaison, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(btnvalider)
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panneauIndices, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanneauGrille, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnvalider))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Combinaison, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>                        

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanneauGrille = new javax.swing.JPanel();
        Combinaison = new javax.swing.JPanel();
        btnvalider = new javax.swing.JButton();
        panneauIndices = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanneauGrille.setBackground(new java.awt.Color(200, 200, 200));
        PanneauGrille.setLayout(new java.awt.GridLayout(8, 4, 3, 3));

        Combinaison.setBackground(new java.awt.Color(200, 200, 200));

        javax.swing.GroupLayout CombinaisonLayout = new javax.swing.GroupLayout(Combinaison);
        Combinaison.setLayout(CombinaisonLayout);
        CombinaisonLayout.setHorizontalGroup(
            CombinaisonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 227, Short.MAX_VALUE)
        );
        CombinaisonLayout.setVerticalGroup(
            CombinaisonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        btnvalider.setText("valider");
        btnvalider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvaliderActionPerformed(evt);
            }
        });

        panneauIndices.setBackground(new java.awt.Color(200, 200, 200));

        javax.swing.GroupLayout panneauIndicesLayout = new javax.swing.GroupLayout(panneauIndices);
        panneauIndices.setLayout(panneauIndicesLayout);
        panneauIndicesLayout.setHorizontalGroup(
            panneauIndicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
        );
        panneauIndicesLayout.setVerticalGroup(
            panneauIndicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panneauIndices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanneauGrille, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Combinaison, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(btnvalider)
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panneauIndices, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanneauGrille, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnvalider))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Combinaison, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnvaliderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvaliderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnvaliderActionPerformed

    /**
     * @param args the command line arguments
     */
 
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Combinaison;
    private javax.swing.JPanel PanneauGrille;
    private javax.swing.JButton btnvalider;
    private javax.swing.JPanel panneauIndices;
    // End of variables declaration//GEN-END:variables

    

   


}
