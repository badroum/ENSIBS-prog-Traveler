import java.util.Random;


public class Population {
	int[][] population;
	static Random random = new Random(System.currentTimeMillis());
	
	public Population(String ville[] , int dist[][],int m , int n)//m=>nombre de ville prise en compte; n=> nombre de combinaison prise
	{
		population = new int [m][n];
		
		//initialisation du tableau avec des -1
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				population[i][j]=-1;
			}
		}
		
		
		int rand =random.nextInt(m);
		
		for (int i=0 ; i<n ; i++)
		{
			int j = 0;
			do
			{
				if (rand == population[i][j])
					{
						rand =random.nextInt(m);
						j=0;
					}
				else if (population[i][j] == -1)
					{
						population[i][j]=rand;
						rand =random.nextInt(m);
						j=0;
					}
				else if (population[i][m-1]!= -1) break;
				else j++;
			}while ((j < m) );
		}
	
		String adaptation[] = new String[population.length];
		
		for(int i=0;i<population.length;i++)
		{
			for (int j = 0; j < population[0].length; j++) {
//				adaptation[i]+=dist[population[i][j]][population[i][j+1]];
			}
		}
	}
}  
