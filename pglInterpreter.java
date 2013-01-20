/*
* Author: John Bernier
* Created: 1/2013
* pglInterpreter.java:
*		This class can be used to call the pgl Interpreter from within a java program
*		instead of using it as a stand alone.
*/

import java.util.LinkedList;

public class pglInterpreter
{
	public pglInterpreter()
	{
		
	}
	public void interpret(String filename)
	{
		if(!filename.endsWith(".pgl"))
		{
			System.out.println("wrong file type: .pgl file expected");	
			return;
		}
		parse p = new parse(filename,null);
	}
	public void interpret(String filename, LinkedList<String> results)
	{
		if(!filename.endsWith(".pgl"))
		{
			System.out.println("wrong file type: .pgl file expected");	
			return;
		}
		
		parse p = new parse(filename,results);
	}
}
