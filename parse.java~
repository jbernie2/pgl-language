import java.io.*;
public class parse
{
	static scanner scan;
	token t;
	public static void main(String args[])
	{
		FileReader input;
		BufferedReader reader;
		if(args.length > 0)
		{
			try{
				input = new FileReader(args[0]);
				reader = new BufferedReader(input);
				scan = new scanner(reader);
			}catch(FileNotFoundException e)
			{
				System.out.println("file not found");
			}
		}
		else
		{
			System.out.println("arguments invalid");	
		}
		
		parse p = new parse();
		executionTree execTree = new executionTree(null,"root");
		p.parse(execTree);
		
		execTree.print(0);
		
		eval evaluateTree = new eval(execTree,args[0]);
	}
	public void parse(executionTree execTree)
	{
		t = scan.getNextToken();
		
		System.out.println("parse");
		if(matchType(t,types.DEFINE))
		{
			execTree.setLHS(null,"TERMINALS");
			execTree.setRHS(null,"NONTERMINALS");
			
			define_terminals(execTree.getLHS());
			define_nonterminals(execTree.getRHS());
		}
		else
		{
			System.out.println("error");	
		}
	}
	public void define_terminals(executionTree execTree)
	{
		System.out.println("define_terminals");
		if(match(t,types.DEFINE))
		{
			if(match(t,types.TDEF))
			{
				match(t,types.LB);
				terminal_definitionList(execTree);
				match(t,types.RB);
			}
		}
		else
		{
			System.out.println("error");
		}
	}
	public void terminal_definitionList(executionTree execTree)
	{
		System.out.println("terminal_definitionList");
		if(matchType(t,types.VAR))
		{
			execTree.setLHS(null,"TDEF");
			execTree.setRHS(null,"TDEFLIST");
			
			terminal_definition(execTree.getLHS());
			terminal_definitionList(execTree.getRHS());
		}
	}
	public void terminal_definition(executionTree execTree)
	{
		
		
		System.out.println("terminal_definition");
		execTree.setLHS(t.getText(),"T");
		match(t,types.VAR);
		match(t,types.EQ);
		execTree.setRHS(t.getText(),"LIT");
		match(t,types.LIT);
		match(t,types.END);
	}
	public void define_nonterminals(executionTree execTree)
	{
		System.out.println("define_nonterminals");
		if(match(t,types.DEFINE))
		{
			if(match(t,types.NTDEF))
			{
				
				match(t,types.LB);
				nonterminal_definitionList(execTree);
				match(t,types.RB);
			}
		}
		else
		{
			System.out.println("error");	
		}
	}
	public void nonterminal_definitionList(executionTree execTree)
	{
		System.out.println("nonterminal_definitionList");
		if(matchType(t,types.VAR))
		{
			execTree.setLHS(null,"NTDEF");
			execTree.setRHS(null,"NTLIST");
			
			nonterminal_definition(execTree.getLHS());
			nonterminal_definitionList(execTree.getRHS());
		}
		
	}
	public void nonterminal_definition(executionTree execTree)
	{
		System.out.println("nonterminal_definition");
		
		execTree.setLHS(t.text,"NT");
		execTree.setRHS(null,"GENERATES");
		
		match(t,types.VAR);
		match(t,types.GEN);
		ntexpression(execTree.getRHS());
		match(t,types.END);
	}
	public void ntexpression(executionTree execTree)
	{
		System.out.println("ntexpression");
		if(matchType(t,types.VAR))
		{
			execTree.setLHS(null,"PRODUCTION");
			execTree.setRHS(null,"PRODUCTIONLIST");
			
			production(execTree.getLHS());
			productionList(execTree.getRHS());
		}
	}
	public void production(executionTree execTree)
	{
		System.out.println("production");
		if(matchType(t,types.VAR))
		{
			execTree.setLHS(t.text,"GEN");
			execTree.setRHS(null,"GENLIST");
			match(t,types.VAR);
			productionTail(execTree.getRHS());
		}
	}
	public void productionList(executionTree execTree)
	{
		System.out.println("productionList");
		if(matchType(t,types.OR))
		{
			match(t,types.OR);
			ntexpression(execTree);
		}
	}
	public void productionTail(executionTree execTree)
	{
		System.out.println("productionTail");
		if(matchType(t,types.VAR))
		{	
			production(execTree);
		}
		else if(matchType(t,types.LP))
		{
			match(t,types.LP);
			
			execTree.setLHS(t.text,"NUM");
			execTree.setRHS(null,null);
			match(t,types.NUM);
			match(t,types.RP);	
		}
	}
	private boolean match(token x, types a)
	{
		if(x.getType().equals(a))
		{
			System.out.println("matched "+x.text+" to "+a);
			t = scan.getNextToken();
			return true;
		}
		System.out.println("\n error");
		System.out.println("trying to match "+x.getText()+" with "+a+"\n");
		return false;
	}
	private boolean matchType(token x, types a)
	{
		if(x.getType().equals(a))
		{
			return true;
		}
		return false;
	}
	private boolean matchText(token x, String a)
	{
		
		if(x.getText().equals(a))
		{
			return true;
		}
		return false;
	}
}
