


import java.util.Random;


public class Itineraire {
	int[][] population;
	int[] distance_total;
	int pivot;
	static private Random random = new Random(System.currentTimeMillis());
	/**
	 * constructeur
	 * @param nbr_ville
	 */
	public Itineraire(int nbr_ville)//Surcharge pour la création d'un itinéraire seul
	{
		init(nbr_ville,1);
		pivot =random.nextInt(nbr_ville);
	}
	/**
	 * constructeur
	 * @param itineraires
	 * @param distance
	 * @param n_pivot
	 */
	public Itineraire(int[][] itineraires ,int[] distance, int n_pivot)
	{
		population = new int [1][itineraires[0].length];
		distance_total = new int [1];
		
		pivot =random.nextInt(n_pivot+1);
		
		
		population[0] = itineraires[pivot];
		distance_total[0] = distance[pivot];
	}
	/**
	 * constructeur
	 * @param nbr_ville
	 * @param nbr_iti
	 */
	public Itineraire(int nbr_ville , int nbr_iti)
	{
		
		//initialisation du tableau avec des -1
		init(nbr_ville,nbr_iti);
		pivot = random.nextInt(Main.init_nbr_ville("villes.dat"));
		
		for (int i=0 ; i<nbr_iti ; i++)
		{
			int j = 0;
			do
			{
				if (pivot == population[i][j])
					{
					pivot =random.nextInt(Main.init_nbr_ville("villes.dat"));
						j=0;
					}
				else if (population[i][j] == -1)
					{
						population[i][j]=pivot;
						pivot =random.nextInt(Main.init_nbr_ville("villes.dat"));
						j=0;
					}
				else if (population[i][nbr_ville-1]!= -1) break;
				else j++;
			}while ((j < nbr_ville) );
		}
		calcul_distance_total();

	}

	/**
	 * Initialisation du tableau avec des -1
	 * @param ville
	 * @param iti
	 */
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
	private void calcul_distance_total()
	{
		for (int i = 0; i < population.length; i++) {
			for (int j = 1; j < (population[0].length); j++) {
				distance_total[i]+=Main.dist[population[i][j-1]][population[i][j]];		 
			}
			distance_total[i]+=Main.dist[population[0].length][0];
		}
		
	}
	/**
	 * Affiche les itinéraires contenus dans la population
	 */
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
	
	/**
	 * Affiche le meilleur itinéraire
	 * @return Le meilleur itinéraire de la population
	 */
	public Itineraire get_best() {
		// TODO Auto-generated method stub
		Itineraire best=new Itineraire( population,distance_total, 0);
		for(int i=1; i < population.length;i++){
			if (best.distance_total[0]>distance_total[i])
			{
				best.distance_total[0]=distance_total[i];
				best.population[0]=population[i];
			}
		}
			
		System.out.print("Le meilleur itineraire est " + "\t");
		best.get();
		return best;
	}
	
	/**
	 * Affiche le numéro du pivot
	 */
	public void get_pivot()
	{
		System.out.println("indice de pivot" + "\t" + pivot);
	}
	
	/**
	 * fait le pivot
	 * @return
	 */
	public Itineraire pivot() {
		Itineraire trier=new Itineraire( population[0].length, population.length);
		pivot =random.nextInt(population.length);
		trier.population[0]=population[pivot];
		trier.distance_total[0]=distance_total[pivot];
		trier.pivot=pivot;
		for (int i = 0; i < (distance_total.length); i++) {
		
			if(i<pivot)//Tant que l’on n’a pas dépassé le pivot on trie la population
			{
				if ((distance_total[pivot]<=distance_total[i])&&(i!=pivot))//si plus grand on le met après
				{
					trier.population[i+1]=population[i];
					trier.distance_total[i+1]=distance_total[i];
				}
				
				else if(distance_total[pivot]>distance_total[i]&&(i!=pivot))//si plus petit on le met après en décalant tous
				{
					for (int j = (i+1); j > 0; j--) {
						trier.population[j]=trier.population[j-1];
						trier.distance_total[j]=trier.distance_total[j-1];
					}
					trier.population[0]=population[i];
					trier.distance_total[0]=distance_total[i];
				}
			}
			else//Si l’on dépasse le pivot on continue à trier la population de la même façon
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
	
	/**
	 * fait la reproduction
	 */
	public void reproduction()
	{
		for (int k = pivot; k < population.length; k++) 
		{
			//Choix du père, mère et déclaration du fils.
			Itineraire pere = new Itineraire( population,distance_total, pivot);
			//pere.get();
			Itineraire mere = new Itineraire( population,distance_total, pivot);
			//mere.get();
			Itineraire fils =new Itineraire( population[0].length);
		
			//choix d'un pivot
			int rand =random.nextInt(population[0].length);
				
			//On met le contenu du père avant le pivot dans le fils.
			for (int i = 0; i < rand; i++) 
			{
				fils.population[0][i]=pere.population[0][i];
				fils.distance_total[0]=pere.distance_total[0];
			}
			
			//On met le contenu de la mère s’il n'est pas déjà présent
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
			//On calcul la nouvelle distance du fils
			fils.calcul_distance_total();
			//On lui donne son pivot de création
			fils.pivot=rand;
			//On écrase les populations après le pivot avec le fils
			population[k]=fils.population[0];
			distance_total[k]=fils.distance_total[0];
			
		/*	exemple de pivot
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
		*/
		}
		
		
		
		
//		return void;
	}
	
	/**
	 * reproduction de la population
	 * pour chaque itinéraire de la population inverse deux villes
	 * un échange de deux mêmes villes est une mutation même si rien ne se passe
	 */
	public void mutation(){
	
		for (int i = 0; i < population.length; i++) {
			int m = random.nextInt(population[0].length);
			int n = random.nextInt(population[0].length);
			Itineraire mutation =new Itineraire( population[0].length);
			int tmp;
			mutation.population[0]=population[i];
			tmp = population[i][m];
			mutation.population[0][m] = population[i][n];
			mutation.population[0][n] = tmp;
			population[i]=mutation.population[0];
			mutation.calcul_distance_total();
			distance_total[i]=mutation.distance_total[0];
		}
		
	}
	/**
	 * Boucle de vérification de présence d'une ville
	 * un tableau d'itinéraire ; une ville à vérifier ; la place jusqu'où vérifier
	 */
	private boolean verif(int [] fils, int mere, int place )
	{
		
		boolean test= true;
	
		for (int i = 0; i < place; i++) {
			if(fils[i]==mere) test=false;	
		}
		
		return test;
		
	}
}  

	
