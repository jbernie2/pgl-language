/*
* Author: John Bernier
* Created: 10/2012
* production.java:
*		stores the values relating to a production in the NTExec object
*		the production object also doubles as both a probability value and 
*		a terminal/nonterminal value
*		ie.in the statement A -> B(10) | c(9)
*			values B(nonterminal), 10(probability), c(terminal), and 9(probability)
*			are all stored in separate production objects
*/
public class production
{
	String name = null;
	int prob = 0;
	public production(int a)
	{
		prob = a;
	}
	public production(String a)
	{
		name = a;
	}
	public Object getValue()
	{
		if(name != null)
			return name;
		else
			return (Integer) prob;
	}
}
