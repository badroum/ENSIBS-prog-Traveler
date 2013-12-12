import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Main {
	
	/*
	 * Initialisation des variables globales d'environnements
	 */
	
	public static String ville[]	= new String[init_nbr_ville("villes.dat")];
	public static int position[][]	= new int[init_nbr_ville("villes.dat")][2];//[colonne][ligne]
	public static int dist[][]		= new int[init_nbr_ville("villes.dat")][init_nbr_ville("villes.dat")];
	
	public static void main(String [] args) throws FileNotFoundException, IOException
	{	
	
		init();
		
		getville();
		//get_tab_n_m(dist);
		
		//System.out.println(init_nbr_ville("villes.dat"));
		
		String[] tmp= new String[ville.length];
		
		for (int i = 0; i < ville.length; i++) {
			tmp[i]=Integer.toString(i+1);
		}

		 int nbr_ville = Integer.parseInt((String)JOptionPane.showInputDialog(null, 
			      "Valeur par default 10",
			      "Veuillez indiquer le nombre de ville !",
			      JOptionPane.QUESTION_MESSAGE, null, tmp, tmp[9]))-1;
		
		 //Demande le nombre d'itinéraire voulus
		 for (int i = 0; i < ville.length; i++) {
			 if(i<10) tmp[i]=Integer.toString(i*10);
			 else if(i>17)tmp[i] = "0";
			 else tmp[i]=Integer.toString((int)Math.pow(10,(i-8)));		 
			}
		 int nbr_iti=0;
			do
			{
			 	nbr_iti = Integer.parseInt((String)JOptionPane.showInputDialog(null, 
					      "Nombre d'itinéraire a prendre en compte",
					      "Plus y en à plus c'est long, pas forcéméne bon !",
					      JOptionPane.QUESTION_MESSAGE, null, tmp, tmp[5]));
			}while(nbr_iti==0);
			 
		//demande le nombre de génération voulus
			String[] tmp2= new String[8];
		for (int i = 0; i < 8; i++) {
			 tmp2[i]=Integer.toString((int)Math.pow(10,i));
			}
		 int nbr_gen=1;
		 nbr_gen = Integer.parseInt((String)JOptionPane.showInputDialog(null, 
		      "Nombre de génération à prendre en compte",
		      "Plus y en a plus c'est long, pas forcéménent bon !",
		      JOptionPane.QUESTION_MESSAGE, null, tmp2, tmp2[1]));

		
		Itineraire iti = new Itineraire(nbr_ville, nbr_iti);
		//iti.get();
		Itineraire trier = null;
		for (int i = 0; i < nbr_gen; i++) {
			trier = iti.pivot();
			//trier.get();
			trier.reproduction();
			//trier.get();
			trier.mutation();
			//trier.get();
			//trier.get_pivot();
		}
		
		trier.get();
		trier.get_best();
		
		new ImageJPanel(trier.get_best());
	}

	private static void init() {
		// TODO Auto-generated method stub
		String tab[] = new String[2];	
		int i=0;
		LineFileReader test = new LineFileReader("villes.dat");
		//*
		// *	tableau en entier
		// *
				test.open(); //ouverture de l'objet
				String temps;
				do
				{
					temps = test.readLine(); //On place la ligne courante dans temps
					
					if(temps != null)//On fait une vérification que la variable n'est pas null
					{
						tab=temps.split(" ");//on récupère chaque variable délimité par un espace
						ville[i]=tab[2];	//On attribue les valeurs récupérées dans les tableaux correspondants
						position[i][0]= Integer.parseInt(tab[0]);
						position[i][1]=	Integer.parseInt(tab[1]);
						i++;
					}
					
				}while(temps != null);//On continue la boucle tant que l’on n’est pas à la fin du document
				test.close();//On ferme le document (libère de l'espace et permet de le réutiliser après si besoin)
				
	}
/*
 * Créer le tableau des distance à partir des coordonner des villes
 */
	private static void getville() {
		
		// TODO Auto-generated method stub
		for(int i=0;i<ville.length;i++)
		{
			for(int j=0;j<ville.length;j++)
			{
				int A = position[j][0]-position[i][0];
				int B = position[j][1]-position[i][1];
				dist[i][j]= (int) Math.sqrt((A*A)+(B*B));
			}
		}
	}

	@SuppressWarnings("unused")
	private static void get_tab_n_m(int tab[][]) {
		/*
		 *impression du tableau 
		 */
				for(int i=0;i<tab.length;i++)
				{
					for(int j=0;j<tab[0].length;j++)
					{
						System.out.print(tab[i][j] + "\t");
					}
					System.out.println();
				}
		/*
		*/
	}

	/*
	 * Parcours le fichier pour savoir combien de ville il contient
	 */
	public static int init_nbr_ville(String fichier)
	{
		LineFileReader test = new LineFileReader("villes.dat");
		// création d'un objet LineFileReader
		
		int count=0;
		
		/*
		 * Boucle de parcours du fichier pour savoir combien de ligne,
		 * donc de ville il est composé
		 */
		test.open(); //ouverture de l'objet
		String temps = "";
		do
		{		
			temps = test.readLine();
			if (temps != null) 	count++;
			
		}while(temps != null);
		test.close();
		
		return count;
	}
}
