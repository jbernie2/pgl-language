/*
* Author: John Bernier
* Created: 10/2012
* NTExec.java:
*		Stores the list of productions and their probabilities for a nonterminal
*		these objects all the .pgl code to execute
*/

import java.util.LinkedList;

public class NTExec
{
	//list of production list and thier probabilites
	LinkedList<LinkedList<production>> productionList = new LinkedList<LinkedList<production>>();
	//the productions in each production list
	LinkedList<production> currentList = new LinkedList<production>();
	String name;
	//the sum of all the probablilites for all the production lists of a nonterminal
	int totalProb = 0;
	public NTExec()
	{
		productionList.add(currentList);
	}
	public void addtoProductionList(int a)
	{
		production temp = new production(a);
		currentList.addFirst(temp);
	}
	public void addtoProductionList(String a)
	{
		production temp = new production(a);
		currentList.add(temp);
	}
	public void nextProductionList()
	{
		 currentList = new LinkedList<production>();
		 productionList.add(currentList);
	}
	public void totalProb(int a)
	{
		totalProb += a;
	}
	public LinkedList<LinkedList<production>> getProductionList()
	{
		return productionList;	
	}
}
