import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class modifier2CSV {

    public static void main(String[] args) {
        // Spécifiez le chemin du fichier d'entrée et de sortie
        String fichierEntree = "csv/test.csv";
        String fichierSortie = "csv/bdd.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(fichierEntree));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fichierSortie))) {

            // Lire la première ligne
            String ligne = br.readLine();

            // Nouvelles valeur pour les variables
            String premiereLigneModifiee = "id,champion1_team1,champion2_team1,champion3_1,champion4_team1,champion5_team1,champion1_team2,champion2_team2,champion3_team2,champion4_team2,champion5_team2,winner";


            // Séparer les noms des champions par une virgule
            String[] valeur = ligne.split(",");

            // Créer un ensemble pour stocker les noms de champions déjà rencontrés
            String[] champions = new String[150]; // car on a 150 champions

            

            // Parcourir chaque nom de champion dans la première ligne pour remplir le tableau de champion
            for (int i=1; i<151; i++){ // de 1 à 151 car on skip l'id
                String nom = valeur[i];
                nom = nom.substring(0, nom.length() - 1);
                champions[i-1] = nom;
            }

            // Écrire la première ligne modifiée dans le fichier de sortie
            bw.write(premiereLigneModifiee);

            bw.newLine(); // saut de ligne
            while((ligne = br.readLine()) != null){
                String nouvelleLigne = "";
                String[] mot = ligne.split(",");
                nouvelleLigne = nouvelleLigne + mot[0] + ",";

                int acc = 0; // accumulateur pour recuper le nom du champion en fonction de sa position
                for(int i=1; i<301; i++){ // 301 car 150*150 champions
                    String tmp = mot[i];
                    if(tmp.equals("1.0")){
                        nouvelleLigne = nouvelleLigne + champions[acc] + ",";
                    }
                    acc++;
                    if(acc==150) acc=0; // quand on atteint la fin de la premiere team on recommence pour la seconde team
                }

                String winner;
                if(mot[301].equals("1.0")){ // verification du winner
                    winner = "team1";
                }else{
                    winner = "team2";
                }
                nouvelleLigne = nouvelleLigne + winner;

                bw.write(nouvelleLigne);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
