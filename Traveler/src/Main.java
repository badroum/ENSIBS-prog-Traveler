public class Main {

	public static void main(String [] args)
	{	
		//System.out.println("Hello world");
		LineFileReader test = new LineFileReader("villes.dat");// création d'un objet LineFileReader
		
		/*
		 * initialisation des variables d'environement
		 */
		int count = init_nbr_ville(test);
		String tab[] = new String[2];
		String ville[]	= new String[count];
		int position [][]= new int[count][2];//[colonne][ligne]
		int dist [] []	= new int[count][count];
		int i=0;
//*
// *	tableau en entier
// *
		test.open(); //ouverture de l'objet
		String temps;
		do
		{
			temps = test.readLine(); //on place la ligne courante dans temps
			//System.out.println(temps);
			
			if(temps != null)
			{
				tab=temps.split(" ");
				//System.out.println(tab[0] +" "+ tab[1] +" "+ tab[2] +" "+ i);
				ville[i]=tab[2];
				position[i][0]= Integer.parseInt(tab[0]);
				position[i][1]=	Integer.parseInt(tab[1]);
				i++;
			}
			
		}while(temps != null);
		test.close();
		
		for(i=0;i<ville.length;i++)
		{
			for(int j=0;j<ville.length;j++)
			{
				int A = position[j][0]-position[i][0];
				int B = position[j][1]-position[i][1];
				dist[i][j]= (int) Math.sqrt((A*A)+(B*B));
			}
		}

		get_tab_n_m(dist);
/*		
		Population pop = new Population(ville,dist,10,10);
		
		for(i=0;i<pop.population.length;i++)
		{
			//System.out.print(pop[i] + "\t");
			
			for(int j=0;j<pop.population[0].length;j++)
			{
				System.out.print(pop.population[i][j] + "\t");
			}
			System.out.println();
		}
*/
		Itineraire iti = new Itineraire(ville,dist, 6, 15);
		iti.get();
		Itineraire trier = iti.pivot();
		
		trier.get();
		
		
	}

	private static void get_tab_n_m(int dist[][]) {
		// TODO Auto-generated method stub
		/*
		 *impression du tableau 
		 */
				for(int i=0;i<dist.length;i++)
				{
					for(int j=0;j<dist[0].length;j++)
					{
						System.out.print(dist[i][j] + "\t");
					}
					System.out.println();
				}
		/*
		*/
	}

	public static int init_nbr_ville(LineFileReader fichier)
	{
		int count=0;
		
		/*
		 * Boucle de parcour du fichier pour savoir combien de ligne,
		 * donc de ville il est composé
		 */
		fichier.open(); //ouverture de l'objet
		String temps = "";
		do
		{
		
			temps = fichier.readLine();
			if (temps != null) 	count++;
			
		}while(temps != null);
		fichier.close();
		
		return count;
	}
}
