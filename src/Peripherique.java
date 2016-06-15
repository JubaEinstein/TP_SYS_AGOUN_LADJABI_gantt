import java.util.LinkedList;
import java.util.Queue;


public class Peripherique {
	String nomPeriph;
	int dureeES;
	Queue<Processus> file;
	int entre;
	
	public Peripherique(String nom,int tempsES)
	{
		nomPeriph=nom;
		dureeES=tempsES;
		file=new LinkedList();
		entre=0;
	}
	void ajouterProcessus(Processus processus)
	{
		if(file.isEmpty())
			entre=0;
		
		file.add(processus);
	}
	
	Processus fileBloque()
	{
		if (file.isEmpty())
			return null;
		else
			if (entre==dureeES)
			{
				entre=0;
				return file.poll();
				
			}
			else
				return null;
	}
	
	void incrementerCompteur()
	{
		if (!file.isEmpty())
		{
			entre++;
			file.peek().restant.peek().execute++;
		}
	}
}
