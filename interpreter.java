import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class interpreter
{
	LinkedList<terminal> terminals;
	LinkedList<NTExec> nonterminals;
	HashMap<String,String> terminalMap;
	HashMap<String,NTExec> nonterminalMap;
	String name;
	
	LinkedList<String> results;
	
	public interpreter(LinkedList<terminal> t,LinkedList<NTExec> nt,
					   HashMap<String,String> tMap,HashMap<String,NTExec> ntMap)
	{
		terminals = t;
		nonterminals = nt;
		terminalMap = tMap;
		nonterminalMap = ntMap;
		interpretNT(nonterminals.getFirst());
		System.out.println();
		//return results;
	}
	public void interpretNT(NTExec current)
	{
		if(current == null)
			return;
		
		LinkedList<LinkedList<production>> prodList = current.getProductionList();
		LinkedList<production> currentProdList;
		production currentProd;
		
		Random rand = new Random();
		int probability = rand.nextInt(current.totalProb);
		int currentProb = 0;
		
		for(int i = 0; i < prodList.size();i++)
		{
			currentProdList = prodList.get(i);
			//System.out.println("here");
			if(currentProdList.size() == 0)
				;
			else if((currentProb+=(Integer)currentProdList.getFirst().getValue()) > probability)
			{
				i = prodList.size();//get out of the for loop
				for(int j = 1; j < currentProdList.size(); j++)
				{
					String next = (String) currentProdList.get(j).getValue();
					//System.out.println("next = "+next);	
					interpretNT(nonterminalMap.get(next));
					interpretT(terminalMap.get(next));
				}
			}
		}
	}
	public void interpretT(String current)
	{
		//System.out.println("here");
		if(current == null)
			return;
		else
		{
			//results.addLast(current);
			System.out.print(current);
		}
	}
}

