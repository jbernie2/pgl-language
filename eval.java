import java.util.HashMap;
import java.util.LinkedList;

public class eval
{
	HashMap<String,String> terminals = new HashMap<String,String>();
	HashMap<String,NTExec> nonterminals = new HashMap<String,NTExec>();
	
	LinkedList<NTExec> nonterminalList = new LinkedList<NTExec>();
	LinkedList<terminal> terminalList = new LinkedList<terminal>();
	
	
	public eval(executionTree execTree,String outFile)
	{
		terminals(execTree.getLHS());
		nonterminals(execTree.getRHS());
		codeGenerator generator = new codeGenerator(terminalList,nonterminalList,outFile);
	}
	private void terminals(executionTree execTree)
	{
		terminalDef(execTree.getLHS());
		if(execTree.getRHS() != null)
			terminals(execTree.getRHS());
	}
	private void terminalDef(executionTree execTree)
	{
		String term;
		String val;
		if(LHSnotNull(execTree))
		{
			if(textNull(execTree.getLHS()))
				term = execTree.getLHS().getText();
			else
				return;
		}
		else
			return;
		
		if(RHSnotNull(execTree))
		{
			if(textNull(execTree.getRHS()))
				val = execTree.getRHS().getText();
			else
				return;
		}
		else 
			return;
		
		System.out.println("term = "+term);
		System.out.println("val = "+val);
		
		
		if(!terminals.containsKey(term))
		{
			terminals.put(term,val);
			terminal temp = new terminal(term,val);
			terminalList.addFirst(temp);
		}
		else
		{
			System.out.println("terminal "+term+" already defined");	
		}
	}
	private void nonterminals(executionTree execTree)
	{
		if(LHSnotNull(execTree))
			nonterminalDef(execTree.getLHS());
		if(RHSnotNull(execTree))
			nonterminals(execTree.getRHS());
	}
	private void nonterminalDef(executionTree execTree)
	{
		String nt;
		NTExec prods;
		
		if(LHSnotNull(execTree))
		{
			if(textNull(execTree.getLHS()))
				nt = execTree.getLHS().getText();
			else
				return;
		}
		else
			return;
		
		System.out.println("nt name = "+nt);
		
		if(!nonterminals.containsKey(nt))
		{
			prods = new NTExec();
			prods.name = nt;
			nonterminals.put(nt,prods);
			nonterminalList.addLast(prods);
			
		}
		else
		{
			prods = nonterminals.get(nt);	
		}
		if(RHSnotNull(execTree))
			productions(prods,execTree.getRHS());		
	}
	private void productions(NTExec prods, executionTree execTree)
	{
		if(LHSnotNull(execTree))
			productionList(prods,execTree.getLHS());
		if(RHSnotNull(execTree))
		{
			prods.nextProductionList();
			productions(prods,execTree.getRHS());
		}
	}
	private void productionList(NTExec prods, executionTree execTree)
	{
		if(LHSnotNull(execTree))
		{
			int val;
			String nt;
			if(textNull(execTree.getLHS()))
				nt = execTree.getLHS().getText();
			else
				return;
			
			System.out.println("nt = "+nt);
			
			if(isInteger(nt))
			{
				val = Integer.parseInt(nt);
				prods.totalProb(val);
				prods.addtoProductionList(val);
			}
			else
				prods.addtoProductionList(nt);
		}
		if(RHSnotNull(execTree))
			productionList(prods,execTree.getRHS());
	}
	private boolean isInteger( String input )  
    {  
       try  
       {  
          Integer.parseInt( input );  
          return true;  
       }  
       catch(Exception e )  
       {  
          return false;  
       }  
    }
    private boolean LHSnotNull(executionTree execTree)
    {
    	try{
    		execTree.getLHS();
    	}catch(NullPointerException e){
    		return false;
    	}
    	return true;
    }
    private boolean RHSnotNull(executionTree execTree)
    {
    	try{
    		execTree.getRHS();
    	}catch(NullPointerException e){
    		return false;
    	}
    	return true;
    }
    private boolean textNull(executionTree execTree)
    {
    	try{
    		execTree.getText();
    	}catch(NullPointerException e){
    		return false;
    	}
    	return true;
    }
}
