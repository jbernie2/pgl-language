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
