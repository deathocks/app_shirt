package com.montmo.app_shirt;

import android.graphics.Color;
import android.content.res.Resources;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkLogo, checkText;
    private Button buttonSave, buttonResto, buttonCancel;
    private RadioGroup groupSexe, groupSize, groupColor;
    private Switch switchBack;
    private ImageView imageTShirt, imageLogo, imageText;

    private TShirt tShirt;
    private Resources res;

    // Variables de travail.
    private String texteSauve;
    private String texteRestore;
    private String texteErreur;
    private String texteFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recupererComposant();
        tShirt = new TShirt();

        initialeComposant();

        ecouterComposants();

        actualiserTShirt();

        // Accéder aux ressources.
        res = getResources();

        recupererRessources();
    }

    // Méthode qui récupère les ressources nécessaires.
    private void recupererRessources() {
        // Récupérer le texte du résultat depuis les ressources.
        texteRestore = res.getString(R.string.restore);
        texteErreur = res.getString(R.string.erreur);
        texteSauve = res.getString(R.string.sauver);
        texteFile = res.getString(R.string.file);
    }

    // Méthode qui récupère les composants de l'interface graphique.
    private void recupererComposant() {
        checkLogo = (CheckBox) findViewById(R.id.case_logo);
        checkText = (CheckBox) findViewById(R.id.case_text);

        buttonSave = (Button) findViewById(R.id.button_save);
        buttonResto = (Button) findViewById(R.id.button_restore);
        buttonCancel = (Button) findViewById(R.id.button_cancel);

        groupSexe = (RadioGroup) findViewById(R.id.sexe_shirt);
        groupSize = (RadioGroup) findViewById(R.id.taille_shirt);
        groupColor = (RadioGroup) findViewById(R.id.color_shirt);

        switchBack = (Switch) findViewById(R.id.switch_shirt);

        imageTShirt = (ImageView) findViewById(R.id.image_tshirt);
        imageLogo = (ImageView) findViewById(R.id.image_logo);
        imageText = (ImageView) findViewById(R.id.image_texte);

    }

    // méthode qui permet d'initialiser les composants avec les valeurs du T-Shirt
    private void initialeComposant() {
        checkLogo.setChecked(tShirt.isLogoTshirt());
        checkText.setChecked(tShirt.isTexteTshirt());
        switchBack.setChecked(tShirt.isArriereTshirt());
        ((RadioButton) groupSexe.getChildAt(tShirt.getIndexSexe())).setChecked(true);
        ((RadioButton) groupColor.getChildAt(tShirt.getIndexCouleur())).setChecked(true);
        ((RadioButton) groupSize.getChildAt(tShirt.getIndexTaille())).setChecked(true);
    }

    // Méthode qui initialise l'écoute des composants.
    private void ecouterComposants() {
        checkLogo.setOnCheckedChangeListener(ecouterCheck);
        checkText.setOnCheckedChangeListener(ecouterCheck);

        groupColor.setOnCheckedChangeListener(ecouterGroup);
        groupSexe.setOnCheckedChangeListener(ecouterGroup);
        groupSize.setOnCheckedChangeListener(ecouterGroup);

        switchBack.setOnClickListener(ecouterSwitch);

        buttonCancel.setOnClickListener(ecouteurBouton);
        buttonResto.setOnClickListener(ecouteurBouton);
        buttonSave.setOnClickListener(ecouteurBouton);

    }

    // Définition de la variable qui écoute le bouton pour sauver, restore et cancel
    private View.OnClickListener ecouteurBouton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button bouton = (Button) view;
            String nomFichier = texteFile;

            File fichier;
            ObjectOutputStream ficSortie;
            ObjectInputStream ficEntree;

            //sauvegarder l'objet du T-Shirt sur la mémoire interne de l'appareil
            // (sous-répertoire donné avec getFilesDir()).
            if (buttonSave == bouton) {
                //Création du fichier
                try {
                    fichier = new File(getFilesDir(), nomFichier);

                    ficSortie = new ObjectOutputStream(new FileOutputStream(fichier));

                    ficSortie.writeObject(tShirt);
                    ficSortie.close();

                    Toast toast = Toast.makeText(getApplicationContext(), texteSauve, Toast.LENGTH_SHORT);
                    toast.show();
                } catch (IOException e) {
                    Toast toast = Toast.makeText(getApplicationContext(), texteErreur, Toast.LENGTH_SHORT);
                    toast.show();
                }
                // lire l'objet du T-Shirt sur la mémoire interne de l'appareil
                // (sous-répertoire donné avec getFilesDir()).
            } else if (buttonResto == bouton) {
                try {
                    fichier = new File(getFilesDir(), nomFichier);
                    ficEntree = new ObjectInputStream(new FileInputStream(fichier));

                    tShirt = (TShirt) ficEntree.readObject();
                    ficEntree.close();
                    initialeComposant();
                    actualiserTShirt();
                    if (tShirt.isArriereTshirt()) {
                        switchBack.setText(res.getString(R.string.texte_view_back));
                    } else {
                        switchBack.setText(res.getString(R.string.texte_view));
                    }

                    Toast toast = Toast.makeText(getApplicationContext(), texteRestore, Toast.LENGTH_SHORT);
                    toast.show();
                } catch (ClassNotFoundException | IOException e) {
                    Toast toast = Toast.makeText(getApplicationContext(), texteErreur, Toast.LENGTH_SHORT);
                    toast.show();

                }
                // exécuter la méthode du T-Shirt qui permet de mettre les valeurs par défaut,
                // exécuter la méthode qui permet d'initialiser les composants et exécuter
                // la méthode qui permet d'actualiser l'image du T-Shirt.
            } else if (buttonCancel == bouton) {
                tShirt.mettreValeursDefaut();
                initialeComposant();
                switchBack.setText(res.getString(R.string.texte_view));
                actualiserTShirt();

            }
        }
    };
    // Définition de la variable qui écoute le switch de avant arrière.
    private View.OnClickListener ecouterSwitch = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Switch switchBack = (Switch) view;
            if (switchBack.isChecked()) {
                tShirt.setArriereTshirt(switchBack.isChecked());
                switchBack.setText(res.getString(R.string.texte_view_back));
            } else {
                tShirt.setArriereTshirt(switchBack.isChecked());

                switchBack.setText(res.getString(R.string.texte_view));
            }
            actualiserTShirt();
        }
    };
    // Définition de la variable qui écoute les RadioGroup.
    private RadioGroup.OnCheckedChangeListener ecouterGroup = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            RadioButton bouton = (RadioButton) findViewById(i);
            int indexBouton = radioGroup.indexOfChild(bouton);

            if (indexBouton == groupColor.indexOfChild(bouton)) {
                tShirt.setIndexCouleur(indexBouton);
            } else if (indexBouton == groupSexe.indexOfChild(bouton)) {
                tShirt.setIndexSexe(indexBouton);
            } else if (indexBouton == groupSize.indexOfChild(bouton)) {
                tShirt.setIndexTaille(indexBouton);
            }
            actualiserTShirt();
        }
    };
    // Définition de la variable qui écoute les checkBox pour le logo et texte.
    private CompoundButton.OnCheckedChangeListener ecouterCheck = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            CheckBox checkBox = (CheckBox) compoundButton;
            if (checkBox == checkLogo) {
                tShirt.setLogoTshirt(b);
            }
            if (checkBox == checkText) {
                tShirt.setTexteTshirt(b);
            }

            actualiserTShirt();
        }
    };

    //méthode qui permet d'actualiser l'image du T-Shirt
    private void actualiserTShirt() {
        //Obtenir l'image du T-Shirt et modifier l'image du T-Shirt par celle obtenue
        imageTShirt.setImageResource(tShirt.obtenirImageTshirt());
        //Obtenir la couleur du T-Shirt
        if (tShirt.obtenirCouleurTshirt() == Color.WHITE) {
            imageTShirt.clearColorFilter();
        } else {
            imageTShirt.setColorFilter(tShirt.obtenirCouleurTshirt());
        }
        //Obtenir l'image du logo et modifier l'image du logo par celle obtenue.
        imageLogo.setImageResource(tShirt.obtenirImageLogo());
        //Obtenir l'image du texte et modifier l'image du texte par celle obtenue.
        imageText.setImageResource(tShirt.obtenirImageTexte());
        imageTShirt.setScaleX(tShirt.obtenirEchelleTshirt());
        imageTShirt.setScaleY(tShirt.obtenirEchelleTshirt());

        //Obtenir l'échelle pour le T-Shirt et modifier les échelles X et Y de l'image du T-Shirt
        // par celle obtenue
        imageText.setScaleX(tShirt.obtenirEchelleLogoTexte());
        imageText.setScaleY(tShirt.obtenirEchelleLogoTexte());
        //Obtenir l'échelle pour le logo et le texte et modifier les échelles X et Y de l'image du
        // logo et de l'image du texte par celle obtenue.
        imageLogo.setScaleX(tShirt.obtenirEchelleLogoTexte());
        imageLogo.setScaleY(tShirt.obtenirEchelleLogoTexte());

    }

}
