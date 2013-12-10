import java.util.Random;


public class Itineraire {
	int[][] population;
	int[] distance_total;
	int pivot;
	static private Random random = new Random(System.currentTimeMillis());
	
	public Itineraire(int nbr_ville , int nbr_iti)
	{
		init(nbr_ville,nbr_iti);
		pivot =random.nextInt(nbr_iti);
	}
	
	public Itineraire(int[][] itineraires ,int[] distance, int n_pivot)
	{
		population = new int [1][itineraires[0].length];
		distance_total = new int [1];
		
		pivot =random.nextInt(n_pivot+1);
		
		
		population[0] = itineraires[pivot];
		distance_total[0] = distance[pivot];
	}
	
	public Itineraire(String[] ville, int[][] dist ,int nbr_ville , int nbr_iti)//m=>nombre de ville prise en compte; n=> nombre de combinaison prise
	{
		
		//initialisation du tableau avec des -1
		init(nbr_ville,nbr_iti);
		
		
		pivot = random.nextInt(nbr_iti);
		
		for (int i=0 ; i<nbr_iti ; i++)
		{
			int j = 0;
			do
			{
				if (pivot == population[i][j])
					{
					pivot =random.nextInt(nbr_iti);
						j=0;
					}
				else if (population[i][j] == -1)
					{
						population[i][j]=pivot;
						pivot =random.nextInt(nbr_iti);
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
	public void get_pivot()
	{
		System.out.println("indice de pivot" + "\t" + pivot);
	}
	
	public Itineraire pivot() {
		Itineraire trier=new Itineraire( population[0].length, population.length);
		//pivot =random.nextInt(population[0].length);
		trier.population[0]=population[pivot];
		trier.distance_total[0]=distance_total[pivot];
		trier.pivot=pivot;
		for (int i = 0; i < (distance_total.length); i++) {
		
			if(i<pivot)
			{
				if ((distance_total[pivot]<=distance_total[i])&&(i!=pivot))
				{
					trier.population[i+1]=population[i];
					trier.distance_total[i+1]=distance_total[i];
				}
				
				else if(distance_total[pivot]>distance_total[i]&&(i!=pivot))
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
				if ((distance_total[pivot]<=distance_total[i])&&(i!=pivot))
				{
					trier.population[i]=population[i];
					trier.distance_total[i]=distance_total[i];
				}
				
				else if(distance_total[pivot]>distance_total[i]&&(i!=pivot))
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
	
	public void reproduction(int dist[][])
	{
		for (int k = pivot; k < population.length; k++) 
		{
			Itineraire pere = new Itineraire( population,distance_total, pivot);
			//pere.get();
			Itineraire mere = new Itineraire( population,distance_total, pivot);
			//mere.get();
			Itineraire fils =new Itineraire( population[0].length, 1);
		
			int rand =random.nextInt(population[0].length);
	//		boolean test = false;

			
			
			for (int i = 0; i < rand; i++) 
			{
				fils.population[0][i]=pere.population[0][i];
				fils.distance_total[0]=pere.distance_total[0];
			}
			
			for (int i = rand; i < population[0].length; i++) 
			{
				fils.population[0][i]=-1;
				fils.distance_total[0]=-1;
			}
			
			for (int i = rand; i < population[0].length; i++) {
				for (int j = 0; j < population[0].length; j++) {
					if (verif(fils.population[0], mere.population[0][j], i) && fils.population[0][i]==-1)
					{
						fils.population[0][i] =  mere.population[0][j];
					}
						
				}
				
			}
			fils.calcul_distance_total(dist);
			fils.pivot=rand;
			population[k]=fils.population[0];
			distance_total[k]=fils.distance_total[0];
			
			if(k==pivot)
			{
				System.out.println("Exemple de reproduction:");
				System.out.println("\tpère:");
				pere.get();
				System.out.println("\tmère:");
				mere.get();
				System.out.println("\tfils:");
				fils.get();
				System.out.println("pivot: " + rand);
			}
			
		}
		
		
		
		
//		return void;
	}

	
	private boolean verif(int [] fils, int mere, int place )
	{
		/*
		 * Boucle de vérifiation de présence d'une ville
		 * un tableau d'itinéraire ; une ville a vérifier ; la place jusqu'ou verifier
		 */
		boolean test= true;
	
		for (int i = 0; i < place; i++) {
			if(fils[i]==mere) test=false;	
		}
		
		return test;
		
	}
}  

	
