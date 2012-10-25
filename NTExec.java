import java.util.LinkedList;

public class NTExec
{
	LinkedList<LinkedList<production>> productionList = new LinkedList<LinkedList<production>>();
	LinkedList<production> currentList = new LinkedList<production>();
	String name;
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
