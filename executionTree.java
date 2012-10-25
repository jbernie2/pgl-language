public class executionTree
{
	String text;
	String type;
	
	executionTree RHS = null;
	executionTree LHS = null;
	public executionTree(String a,String t)
	{
		text = a;
		type = t;
	}
	public void setRHS(String a,String t)
	{
		RHS = new executionTree(a,t);		
	}
	public void setLHS(String a,String t)
	{
		LHS = new executionTree(a,t);	
	}
	public executionTree getRHS()
	{return RHS;}
	public executionTree getLHS()
	{return LHS;}
	public String getText()
	{return text;}
	public String getType()
	{return type;}
	
	public void print(int level)
	{
		for(int i =0; i < level; i++)
		{
			System.out.print(" ");
		}
		System.out.println(type+" "+text);
		if(LHS != null)
			LHS.print(level+1);
		if(RHS != null)
			RHS.print(level+1);
	}
}
