import java.util.Random;


public class Itineraire {
	int[][] population;
	int[] distance_total;
	static private Random random = new Random(System.currentTimeMillis());
	public Itineraire(int nbr_ville , int nbr_iti)
	{
		init(nbr_ville,nbr_iti);
	}
	public Itineraire(int[][] itineraires ,int[] distance, int place)
	{
		population[0] = itineraires[place];
		distance_total[0] = distance[place];
	}
	public Itineraire(String ville[], int dist[][] ,int nbr_ville , int nbr_iti)//m=>nombre de ville prise en compte; n=> nombre de combinaison prise
	{
		
		//initialisation du tableau avec des -1
		init(nbr_ville,nbr_iti);
		
		
		int rand =random.nextInt(nbr_ville);
		
		for (int i=0 ; i<nbr_iti ; i++)
		{
			int j = 0;
			do
			{
				if (rand == population[i][j])
					{
						rand =random.nextInt(nbr_ville);
						j=0;
					}
				else if (population[i][j] == -1)
					{
						population[i][j]=rand;
						rand =random.nextInt(nbr_ville);
						j=0;
					}
				else if (population[i][nbr_ville-1]!= -1) break;
				else j++;
			}while ((j < nbr_ville) );
		}
		calcul_distance_total(dist);

	}

	private void init(int ville,int iti) {
		population = new int [iti][ville];
		distance_total = new int [iti];
		for(int i=0;i<iti;i++)
		{
			for(int j=0;j<ville;j++)
			{
				population[i][j]=-1;
			}
		}
	}
	private void calcul_distance_total(int dist[][])
	{
		for (int i = 0; i < population.length; i++) {
			for (int j = 0; j < (population[0].length-1); j++) {
				distance_total[i]+=dist[population[i][j]][population[i][j+1]];
			}
			distance_total[i]+=dist[population[0].length][0];
		}
		
	}
	public void get()
	{
		System.out.println("/==============================/");
		for(int i=0;i<population.length;i++)
		{
			for(int j=0;j<population[0].length;j++)
			{
				System.out.print(population[i][j] + "\t");
			}
			System.out.println(distance_total[i]);
		}
		System.out.println("/==============================/");
	}
	public Itineraire pivot() {
		Itineraire trier=new Itineraire( population[0].length, population.length);
		int rand =random.nextInt(population[0].length);
		trier.population[0]=population[rand];
		trier.distance_total[0]=distance_total[rand];
		for (int i = 0; i < (distance_total.length); i++) {
		
			if(i<rand)
			{
				if ((distance_total[rand]<=distance_total[i])&&(i!=rand))
				{
					trier.population[i+1]=population[i];
					trier.distance_total[i+1]=distance_total[i];
				}
				
				else if(distance_total[rand]>distance_total[i]&&(i!=rand))
				{
					for (int j = (i+1); j > 0; j--) {
						trier.population[j]=trier.population[j-1];
						trier.distance_total[j]=trier.distance_total[j-1];
					}
					trier.population[0]=population[i];
					trier.distance_total[0]=distance_total[i];
				}
			}
			else
			{
				if ((distance_total[rand]<=distance_total[i])&&(i!=rand))
				{
					trier.population[i]=population[i];
					trier.distance_total[i]=distance_total[i];
				}
				
				else if(distance_total[rand]>distance_total[i]&&(i!=rand))
				{
					for (int j = (i); j > 0; j--) {
						trier.population[j]=trier.population[j-1];
						trier.distance_total[j]=trier.distance_total[j-1];
					}
					trier.population[0]=population[i-1];
					trier.distance_total[0]=distance_total[i];
				}
			}
			
		}
		
		return trier;
	}
	
	public Itineraire reproduction()
	{
		Itineraire pere = new Itineraire( population,distance_total,0);
		Itineraire mere = new Itineraire( population,distance_total,0);
		/*
		 * deux boucle l'une pour suprimer les itinéraire déjà présent dans pere avant le pivot
		 *	l'autre pour agréger le père et la mère
		 */
		int rand =random.nextInt(population[0].length);
//		boolean test = false;
		for (int i = 0; i < rand; i++) 
		{
			for (int j = 0; j < population.length; j++) {
//				if (pere.population[i]==mere.population[j])
				{
					//mere.population[j]=
				}
				
			}
			
		}
		
		
		return null;
	}
}  

	
