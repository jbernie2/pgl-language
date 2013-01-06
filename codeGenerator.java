/*
* Author: John Bernier
* Created: 10/2012
* codeGenerator.java:
*		uses NTExec objects to generate a .java file equivalent to the .pgl file
*		This class is not currently being used interpretation is being used instead
*		However, this class can be activated by uncommenting it from eval.java
*/

import java.util.HashMap;
import java.util.LinkedList;
import java.io.*;

public class codeGenerator
{
	LinkedList<terminal> terminals;
	LinkedList<NTExec> nonterminals;
	String name;
	FileWriter fstream;
	BufferedWriter out;
	public codeGenerator(LinkedList<terminal> t,LinkedList<NTExec> nt,String outFile)
	{
		terminals = t;
		nonterminals = nt;
		name = formatOut(outFile);
		try{
			fstream = new FileWriter(name+".java");
			out = new BufferedWriter(fstream);
			writeHeading(name);
			out.close();
			
		}catch(IOException e){
			System.out.println("IO Error");
		}
	}
	private void writeHeading(String name)
	{
		try{
			out.write("import java.util.Random; \n");
			out.write("public class "+name+"\n");
			out.write("{\n\tpublic static Random rand = new Random();");
			out.write("\n\tpublic static void main(String args[])\n");
			out.write("\t{\n");
			out.write(nonterminals.getFirst().name + "();");
			out.write("System.out.println();");
			out.write("\t}");
			
			while(terminals.peek() != null)
			{
				terminal temp = terminals.removeFirst();
				out.write(writeTFunction(temp.name,temp.value));
			}
			while(nonterminals.peek() != null)
			{
				NTExec temp = nonterminals.removeFirst();
				out.write(writeNTFunction(temp.name, temp));
			}
			out.write("}");
			
			//out.write("test");
			
		}catch(IOException e){
			System.out.println("IO Error");
		}
	}
	private String writeNTFunction(String name,NTExec productions)
	{
		//System.out.println("writeNTFucntion\n");
		
		String function;
		int currentProb = 0;
		LinkedList<LinkedList<production>> prodList = productions.getProductionList();
		LinkedList<production> currentProd = prodList.getFirst();
		
		function = "private static void "+name+"()\n{\n";
		function += "int randNum = rand.nextInt("+productions.totalProb+");";
		
		production current;// = currentProd.removeFirst();
		//function += "if(randNum < "+current.getValue()+") {";
		
		int listCounter = 0;
		boolean bracket = false;
		while(prodList.peek() != null)
		{	
			//System.out.println("here\n");
			currentProd = prodList.removeFirst();
			
			int prodCounter = 0;
			while(currentProd.peek() != null)
			{
				current = currentProd.removeFirst();
				if(prodCounter == 0 && listCounter ==0)
				{
					//System.out.println("current.getValue() = "+current.getValue());
					currentProb += (Integer)current.getValue();
					function += "if(randNum < "+currentProb+") {";
					bracket = true;
				}
				else if(prodCounter == 0)
				{
					currentProb += (Integer)current.getValue();
					function += "else if(randNum < "+currentProb+") {";
					bracket = true;
				}
				else
					function += current.getValue()+"();";
				
				prodCounter++;
			}
			if(bracket)
				function += "}";
			bracket = false;
			
			listCounter++;
		}
		function += "}";
		
		return function;
	}
	private String writeTFunction(String name,String value)
	{
		String function;
		function = "private static void "+name+"() { System.out.print(\""+value+"\");}";
		return function;
	}
	private String formatOut(String a)
	{
		int i = 0;
		String b = "";
		while(a.charAt(i) != '.')
		{
			b += a.charAt(i);
			i++;
		}
		return b;
	}
}
