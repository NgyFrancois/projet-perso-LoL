import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class modifierCSV {

    public static void main(String[] args) {
        // Spécifiez le chemin du fichier d'entrée et de sortie
        String fichierEntree = "CSV/Lol_matchs.csv";
        String fichierSortie = "CSV/test.csv";

        // Modifier la première ligne du fichier CSV
        modifierCSV(fichierEntree, fichierSortie);
    }

    public static void modifierCSV(String fichierEntree, String fichierSortie) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichierEntree));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fichierSortie))) {

            // Lire la première ligne
            String ligne = br.readLine();

            // Séparer les noms des champions par une virgule
            String[] champions = ligne.split(",");

            // Créer un ensemble pour stocker les noms de champions déjà rencontrés
            Set<String> championsRencontres = new HashSet<>();

            // Créer une liste pour stocker la première ligne modifiée
            List<String> premiereLigneModifiee = new ArrayList<>();

            // Parcourir chaque nom de champion dans la première ligne
            for (String champion : champions) {
                // Si c'est un nom de champion et qu'il n'a pas encore été rencontré
                if (!champion.equals("id") && !champion.equals("Team1") && !champion.equals("Team2") && !championsRencontres.contains(champion)) {
                    // Ajouter le nom de champion avec le suffixe "1"
                    premiereLigneModifiee.add(champion + "1");
                    // Ajouter le nom de champion à l'ensemble des champions rencontrés
                    championsRencontres.add(champion);
                } else if (!champion.equals("id") && championsRencontres.contains(champion)) {
                    // Ajouter le nom de champion avec le suffixe "2"
                    premiereLigneModifiee.add(champion + "2");
                } else {
                    // Ajouter les autres éléments tels quels à la première ligne modifiée
                    premiereLigneModifiee.add(champion);
                }
            }

            // Écrire la première ligne modifiée dans le fichier de sortie
            bw.write(String.join(",", premiereLigneModifiee));


            // Copie la suite du fichier
            bw.newLine(); // saut de ligne
            while((ligne = br.readLine()) != null){
                bw.write(ligne);
                bw.newLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
