package cinema;
import java.util.Scanner;

public class Cinema {
	static String[] historique = new String[10];	//historique
	static Scanner sc = new Scanner(System.in);
	static String[] film = {"Les sept samouraïs", "8 1/2", "Nostalghia"};  //liste des films
	static int[][] salle = {{10, 7, 0},{15, 5, 0},{5, 10, 0}};				//liste des salles avec contenance, prix et nombre de places prises
	
	public static void main(String[] args) {
		
		String choix;
		int choixFilm, choixSalle, nbPlaces;

		do {
			afficherMenu();
			choix = sc.next();
			ajouterTab(historique, choix);   //Ajout dans l'historique

			switch(choix) {

			//Choisir un film
			case "1" :
				choixFilm = choisirFilm();  //Choisit le film
				System.out.print("Entrez le nombre de places : ");
				nbPlaces = sc.nextInt();
				ajouterTab(historique, Integer.toString(nbPlaces));   //Ajout dans l'historique
				if (estPleine(choixFilm, nbPlaces))				//Vérifie si la salle est pleine
					System.out.println("La salle est pleine");
				else {
					System.out.printf("Le film %s est diffusé dans la salle %d\n", film[choixFilm-1], choixFilm);
					payer(choixFilm, nbPlaces);	//Fait payer le client
					salle [choixFilm-1][2] += nbPlaces;					//Incrémente le nombre de places prises pour le film donné
					System.out.println("Bon visionnage");
				}

				break;

				//Vider une salle
			case "2" :
				System.out.print("Entrez la salle à vider : ");
				choixSalle = sc.nextInt();
				ajouterTab(historique, Integer.toString(choixSalle));   //Ajout dans l'historique
				salle[choixSalle-1][2] = 0;
				break;

				//Historique
			case "3" :
				afficherTab(historique);
				break;

				//Quitter
			case "4" :
				System.out.println("Au revoir");
				break;
			}

		} while (!choix.equals("4"));   //Boucle tant qu'on n'a pas choisi de quitter
		sc.close();
	}

	//Affiche le menu principal
	public static void afficherMenu() {
		System.out.println("1. Choisir un film");
		System.out.println("2. Vider une salle");
		System.out.println("3. Historique");
		System.out.println("4. Quitter");
	}

	//Menu pour choisir un film
	public static int choisirFilm() {
		int filmChoisi;

		do {
			for (int i = 0; i < film.length ; i++)
				System.out.println(i+1 + ". " + film[i] );   //Affichage du menu
			filmChoisi = sc.nextInt();
			ajouterTab(historique, Integer.toString(filmChoisi));   //Ajout dans l'historique
		} while(filmChoisi < 1 && filmChoisi > film.length);   //Boucle tant que l'entrée est erronée
		return filmChoisi;
	}

	//Vérifie si une salle est pleine
	public static boolean estPleine(int film, int nbPlaces) {
		boolean plein = false;
		if (salle[film-1][2] + nbPlaces >= salle[film-1][0])
			plein = true;
		return plein;
	}

	//Système de paye pour un film donné
	public static void payer(int film, int nbPlaces) {
		int aPayer, paye;

		aPayer = salle[film-1][1] * nbPlaces;

		do {
			System.out.println("Somme à payer : " + aPayer);
			paye = sc.nextInt();  //Récupère la somme payée
			aPayer -= paye;
			ajouterTab(historique, Integer.toString(paye));  //Ajout dans l'historique
		}while (aPayer > 0);  //Boucle tant que la somme à payer est due
	}

	//Affiche un tableau
	public static void afficherTab(String[] tab) {
		for (String elem:tab) {
			if (elem == null)
				System.out.print("_ ");
			else
				System.out.print(elem + " ");
		}
		System.out.println();
	}

	//Ajoute un élément à la fin du tableau, décale à gauche si le tableau est plein
	public static void ajouterTab(String[] tab, String ajout) {
		int i = 0;

		while (i < tab.length && tab[i] != null) i++;
		if (i != tab.length)
			tab[i] = ajout;
		else {
			for (int j = 0; j < tab.length - 1; j++)
				tab[j] = tab[j+1];
			tab[tab.length - 1] = ajout;
		}

		
	}


}
