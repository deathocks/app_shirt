/**
 * Auteurs : Julien Canuel et Soti
 * Fichier : TShirt.java
 * Date    : 20 octobre 2016
 * Cours   : 420-254-MO (TP2 Android)

/**
 * Classe contenant les données et les méthodes pour un T-Shirt.
 * Cette classe implémente l'interface Serializable.
 */

package com.montmo.app_shirt;

import android.graphics.Color;

import java.io.Serializable;

public class TShirt implements Serializable {

    // Constantes de la classe.

    // Tableau des images du T-Shirt de devant selon l'index du groupe radio des sexes.
    private static final int[] TAB_IMG_DEVANT = {
            R.drawable.tshirt_homme_devant, R.drawable.tshirt_femme_devant
    };

    // Tableau des images du T-Shirt d'arrière selon l'index du groupe radio des sexes.
    private static final int[] TAB_IMG_ARRIERE = {
            R.drawable.tshirt_homme_arriere, R.drawable.tshirt_femme_arriere
    };

    // Tableau des couleurs selon l'index du groupe radio des couleurs.
    // Traitement spécial dans le code pour remettre la couleur par défaut si le choix est blanc.
    private static final int[] TAB_COULEURS = {Color.WHITE, Color.RED, Color.BLUE,
            Color.YELLOW};

    // Tableau des échelles X et Y pour la dimension du Tshirt selon l'index
    // du groupe radio des tailles.
    private static final float[] TAB_ECHELLE_TSHIRT =
            {3f, 3.5f, 4f, 4.5f};

    // Tableau des échelles X et Y pour la dimension du logo et du texte
    // selon l'index du groupe radio des tailles.
    private static final float[] TAB_ECHELLE_LOGO_TEXTE =
            {1f, 1.3f, 1.6f, 1.8f};

    // Ressource de l'image du logo du devant.
    private static final int IMAGE_LOGO = R.drawable.logo_android;

    // Ressource de l'image du texte du devant.
    private static final int IMAGE_TEXTE = R.drawable.texte_android;

    // Ressource de l'image arrière.
    private static final int IMAGE_ARRIERE = R.drawable.android_nougat_arriere;

    // Ressource pour enlever une image.
    private static final int IMAGE_VIDE = android.R.color.transparent;

    // Valeurs par défaut.
    private static final int INDEX_SEXE_DEFAUT = 0;       // Homme.
    private static final int INDEX_COULEUR_DEFAUT = 0;    // Blanc.
    private static final int INDEX_TAILLE_DEFAUT = 1;     // Medium.
    private static final boolean LOGO_DEFAUT = false;     // Pas de logo.
    private static final boolean TEXTE_DEFAUT = false;    // Pas de texte.
    private static final boolean ARRIERE_DEFAUT = false;  // Pas T-Shirt arrière (devant).

    //================================================
    // Champs d'instance pour un objet de type TShirt.

    private int indexSexe;      // Index du groupe radio des sexes.
    private int indexCouleur;   // Index du groupe radio des couleurs.
    private int indexTaille;    // Index du groupe radio des tailles.

    private boolean logoTshirt;     // true si logo sur le devant, false si pas de logo.
    private boolean texteTshirt;    // true si texte sur le devant, false si pas de texte.
    private boolean arriereTshirt;  // true si T-Shirt arriere, false si devant.

    /**
     * Constructeur qui permet de construire un objet TShirt avec les valeurs par défaut.
     */
    public TShirt() {

        mettreValeursDefaut();

    }

    /**
     * Constructeur qui permet de construire un objet TShirt avec des valeurs.
     *
     * @param indexSexe     L'index du groupe radio des sexes.
     * @param indexCouleur  L'index du groupe radio des couleurs.
     * @param indexTaille   L'index du groupe radio des taille.
     * @param logoTshirt    Booléen qui indique s'il y a un logo sur le devant du T-Shirt.
     * @param texteTshirt   Booléen qui indique s'il y a un texte sur le devant du T-Shirt.
     * @param arriereTshirt Booléen qui indique si T-Shirt arrière.
     */
    public TShirt(int indexSexe, int indexCouleur, int indexTaille,
                  boolean logoTshirt, boolean texteTshirt, boolean arriereTshirt) {


        modifierTshirt(indexSexe, indexCouleur, indexTaille, logoTshirt, texteTshirt, arriereTshirt);

    }

    /**
     * Méthode qui permet de mettre les valeurs par défaut au T-Shirt.
     */
    public void mettreValeursDefaut() {


        modifierTshirt(INDEX_SEXE_DEFAUT, INDEX_COULEUR_DEFAUT, INDEX_TAILLE_DEFAUT, LOGO_DEFAUT, TEXTE_DEFAUT, ARRIERE_DEFAUT);

    }

    /**
     * Méthode qui permet de modifier le T-Shirt avec des valeurs.
     *
     * @param indexSexe     L'index du groupe radio des sexes.
     * @param indexCouleur  L'index du groupe radio des couleurs.
     * @param indexTaille   L'index du groupe radio des taille.
     * @param logoTshirt    Booléen qui indique s'il y a un logo sur le devant du T-Shirt.
     * @param texteTshirt   Booléen qui indique s'il y a un texte sur le devant du T-Shirt.
     * @param arriereTshirt Booléen qui indique si T-Shirt arrière.
     */
    public void modifierTshirt(int indexSexe, int indexCouleur, int indexTaille,
                               boolean logoTshirt, boolean texteTshirt, boolean arriereTshirt) {

        this.setArriereTshirt(arriereTshirt);
        this.setIndexCouleur(indexCouleur);
        this.setIndexSexe(indexSexe);
        this.setIndexTaille(indexTaille);
        this.setLogoTshirt(logoTshirt);
        this.setTexteTshirt(texteTshirt);

    }

    /**
     * Accesseur.
     *
     * @return L'index du groupe radio des sexes.
     */
    public int getIndexSexe() {
        return indexSexe;
    }

    /**
     * Mutateur.
     *
     * @param indexSexe L'index du groupe radio des sexes.
     */
    public void setIndexSexe(int indexSexe) {
        this.indexSexe = indexSexe;
    }

    /**
     * Accesseur.
     *
     * @return L'index du groupe radio des couleurs.
     */
    public int getIndexCouleur() {
        return indexCouleur;
    }

    /**
     * Mutateur.
     *
     * @param indexCouleur L'index du groupe radio des couleurs.
     */
    public void setIndexCouleur(int indexCouleur) {
        this.indexCouleur = indexCouleur;
    }

    /**
     * Accesseur.
     *
     * @return L'index du groupe radio des tailles.
     */
    public int getIndexTaille() {
        return indexTaille;
    }

    /**
     * Mutateur.
     *
     * @param indexTaille L'index du groupe radio des tailles.
     */
    public void setIndexTaille(int indexTaille) {
        this.indexTaille = indexTaille;
    }

    /**
     * Accesseur.
     *
     * @return true si un logo sur le devant du T-Shirt et false si pas de logo sur le devant.
     */
    public boolean isLogoTshirt() {
        return logoTshirt;
    }

    /**
     * Mutateur.
     *
     * @param logoTshirt Booléen qui indique s'il y a un logo sur le devant du T-Shirt.
     */
    public void setLogoTshirt(boolean logoTshirt) {
        this.logoTshirt = logoTshirt;
    }

    /**
     * Accesseur.
     *
     * @return true si un texte sur le devant du T-Shirt et false si pas de texte sur le devant.
     */
    public boolean isTexteTshirt() {
        return texteTshirt;
    }

    /**
     * Mutateur.
     *
     * @param texteTshirt Booléen qui indique s'il y a un texte sur le devant du T-Shirt.
     */
    public void setTexteTshirt(boolean texteTshirt) {
        this.texteTshirt = texteTshirt;
    }

    /**
     * Accesseur.
     *
     * @return true si T-Shirt arriere et false si T-Shirt devant.
     */
    public boolean isArriereTshirt() {
        return arriereTshirt;
    }

    /**
     * Mutateur.
     *
     * @param arriereTshirt Booléen qui indique si T-Shirt arrière.
     */
    public void setArriereTshirt(boolean arriereTshirt) {
        this.arriereTshirt = arriereTshirt;
    }

    /**
     * Méthode qui retourne la ressource de l'image du T-Shirt à utiliser.
     *
     *
     * @return L'image du T-Shirt.
     */
    public int obtenirImageTshirt() {
        int i;

        if (arriereTshirt) {
            i = TAB_IMG_ARRIERE[getIndexSexe()];

        } else {
            i = TAB_IMG_DEVANT[getIndexSexe()];
        }
        return i;
    }

    /**
     * Méthode qui retourne la couleur du T-Shirt à utiliser.
     *
     * @return La couleur du T-Shirt.
     */
    public int obtenirCouleurTshirt() {

        return TAB_COULEURS[getIndexCouleur()];
    }

    /**
     * Méthode qui retourne la ressource de l'image du logo à utiliser.
     *
     * @return L'image du logo.
     */
    public int obtenirImageLogo() {
        int i;
        // Le T-Shirt arrière a toujours le logo android_nougat_arriere et seulement lui.
        if (arriereTshirt) {
            i = IMAGE_ARRIERE;

        } else {
            if (isLogoTshirt()) {
                i = IMAGE_LOGO;
            } else {
                i = IMAGE_VIDE;
            }
        }

        return i;
    }

    /**
     * Méthode qui retourne la ressource de l'image du texte à utiliser.
     *
     * @return L'image du texte.
     */
    public int obtenirImageTexte() {
        int i;
        // Le T-Shirt arrière a toujours le logo android_nougat_arriere et seulement lui.
        if (arriereTshirt) {
            i = IMAGE_VIDE;

        } else {
            if (isTexteTshirt()) {
                i = IMAGE_TEXTE;
            } else {
                i = IMAGE_VIDE;
            }
        }
        return i;
    }

    /**
     * Méthode qui retourne l'échelle à utiliser pour l'affichage de la taille du T-Shirt.
     *
     * @return L'échelle de la taille du T-Shirt.
     */
    public float obtenirEchelleTshirt() {

        return TAB_ECHELLE_TSHIRT[getIndexTaille()];
    }

    /**
     * Méthode qui retourne l'échelle à utiliser pour l'affichage de la taille du logo et
     * du texte du T-Shirt.
     *
     * @return L'échelle de la taille du logo et du texte du T-Shirt.
     */
    public float obtenirEchelleLogoTexte() {

        return TAB_ECHELLE_LOGO_TEXTE[getIndexTaille()];

    }
}
