/*
* Author: John Bernier
* Created: 1/2013
* interpreter.java:
*		interprets a .pgl file that has been converted into NTExec objects
*		reads in the hashes and linked lists generated in eval.java
*/
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
					   HashMap<String,String> tMap,HashMap<String,NTExec> ntMap,
					   LinkedList<String> results)
	{
		terminals = t;
		nonterminals = nt;
		terminalMap = tMap;
		nonterminalMap = ntMap;
		this.results = results;
		//get first production in the list, this will be the first nonterminal
		// in the .pgl file
		interpretNT(nonterminals.getFirst());
	}
	private void interpretNT(NTExec current)
	{
		if(current == null)
			return;
		
		//a list of the all the different lists of productions that a 
		//nonterminal can go to ie. if A -> a b(10) | C D (20)
		//then (10, a, b) will be one list and (20, C, D) will be in another
		//the probabilities for a given production string will always be the first
		//thing in the list
		
		LinkedList<LinkedList<production>> prodList = current.getProductionList();
		LinkedList<production> currentProdList;
		production currentProd;
		
		//generating a random number in the range from 0 to the sum of all the probabilities
		//for all of a nonterminals production lists.
		Random rand = new Random();
		int probability = rand.nextInt(current.totalProb);
		int currentProb = 0;
		
		for(int i = 0; i < prodList.size();i++)
		{
			currentProdList = prodList.get(i);
			//System.out.println("here");
			//if there are no productionLists for a nonterminal, do nothing
			if(currentProdList.size() == 0)
				;
			//we add the probabilities becuase we generated a number between 0 and
			//the sum of all probabilities - 1, therefore if we have two probabilities
			//of 5 and 10, then the number generated will be between 0 and 14, so 
			//in order for the first number to be used it must be between 0 and 4 
			//and for the second probability to be used the number must be between 5 and 14,
			//so we must add the numbers as we go along so that each production list's probabilities
			//are correctly used.
			else if((currentProb+=(Integer)currentProdList.getFirst().getValue()) > probability)
			{
				//we want to exit the for loop if we get into this if statement
				//because we only want the first production list that satisfies 
				//the condition, we dont want to use all production lists
				i = prodList.size();//get out of the for loop
				
				//for each production in the production list (besides the first one
				//which was the probability), execute the rules for that 
				//terminal/nonterminal
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
	private void interpretT(String current)
	{
		//System.out.println("here");
		if(current == null)
			return;
		else
		{
			//currently just prints out the results, but
			//soon this class will have the option of returning 
			//a linked list with the results so that they can be used else where
			if(results != null)
				results.addLast(current);
			else
				System.out.print(current);
		}
	}
	public LinkedList<String> getResults()
	{
		return results;	
	}
}

