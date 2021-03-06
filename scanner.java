/*
* Author: John Bernier
* Created: 10/2012
* scanner.java:
*		Tokenizes a .pgl file and passes the tokens to the parser in parse.java
*/

import java.io.*;

public class scanner
{
	BufferedReader reader;
	int line = 1;
	public scanner(BufferedReader reader)
	{
		this.reader = reader;
	}
	// each if/while/switch block checks for a different type of token
	public token getNextToken()
	{
		char c = readNext(reader);
		token t = new token();
		
		while(Character.isWhitespace(c))//gets rid of white space
		{
			if(c == '\n')
				line++;
			c = readNext(reader);	
		}
	
		if(c == '/') //checks for comment
		{
			char temp = c;
			mark(reader);
			c = readNext(reader);
			if(c == '/')
			{
				c = readNext(reader);
				while(c != '\n')
				{
					c = readNext(reader);
				}
				line++;
			
				while(Character.isWhitespace(c))
				{			
					if(c == '\n')
						line++;	
					c = readNext(reader);	
				}
			}
			else
			{
				reset(reader);
				t.setText("error: line "+line+", invalid character "+temp+c);
				t.setType(types.ERR);
			}
		}
		
		if(c == -1 || c == 65535)//checks for eof
		{
			t.setType(types.EOF);	
		}
		else if(Character.isLetter(c) || c == '_')//checks if variable
		{
			t.setText(c);
			mark(reader);
			c = readNext(reader);
			while(Character.isLetterOrDigit(c) || c == '_')
			{
				t.setText(c);
				mark(reader);
				c = readNext(reader);
			}
			reset(reader);
			
			//checks for reserve words
			if(!reserveWord(t))
				t.setType(types.VAR);
				
		}
		else if(Character.isDigit(c))//checks for number/production probability
		{
			t.setText(c);
			mark(reader);
			c = readNext(reader);
			while(Character.isDigit(c))
			{
				t.setText(c);
				mark(reader);
				c = readNext(reader);
			}
			reset(reader);
			t.setType(types.NUM);
		}
		else if(c == '\"') //checks for nonterminal definition 
		{
			//t.setText(c);
			c = readNext(reader);
			while(c != '\"')
			{
				t.setText(c);
				mark(reader);
				c = readNext(reader);
			}
			//t.setText(c);
			t.setType(types.LIT);
			
		}
		else if(c == '\'')//checks for nonterminal definition
		{
			//t.setText(c);
			c = readNext(reader);
			while(c != '\'')
			{
				t.setText(c);
				mark(reader);
				c = readNext(reader);
			}
			//t.setText(c);
			t.setType(types.LIT);
			
		}
		else if(c == '-')// checks for -> character sequence
		{
			char temp = c;
			mark(reader);
			c = readNext(reader);
			if(c == '>')
			{
				t.setText(temp);
				t.setText(c);
				t.setType(types.GEN);
			}
			else
			{
				reset(reader);
				t.setText("error: line "+line+", invalid character "+temp+c);
				t.setType(types.ERR);
			}
		}
		else// checks for all other operators that are supported
		{
			t.setText(c);
			switch(c)
			{
				case '=':
					t.setType(types.EQ);
					break;
				case '|':
					t.setType(types.OR);
					break;
				case '(':
					t.setType(types.LP);
					break;
				case ')':
					t.setType(types.RP);
					break;
				case '{':
					t.setType(types.LB);
					break;
				case '}':
					t.setType(types.RB);
					break;
				case ';':
					t.setType(types.END);
					break;
				default:
					t.setText("error: line "+line+", invalid character "+c);
					t.setType(types.ERR);
			}
		}
		return t;
	}
	
	// checks if a token is a reserve word
	private boolean reserveWord(token a)
	{
		if(a.getText().equals("define"))
		{
			a.setType(types.DEFINE);
			return true;
		}
		else if(a.getText().equals("terminals"))
		{
			a.setType(types.TDEF);
			return true;
		}
		else if(a.getText().equals("nonterminals"))
		{
			a.setType(types.NTDEF);
			return true;
		}
		
		return false;
	}
	
	//these methods are just wrappers for BufferedReader methods
	//so that the try/catch statements wouldnt clutter up the code
	private char readNext(BufferedReader reader)
	{
		int x;
		char c = '\0';
		try{
			x = reader.read();
			c = (char)x;
		}catch(IOException e){
			System.out.println("end of file");
		}
		return c;
	}
	//marks the buffer at a given position
	//this allows a reader to be rolled back
	private void mark(BufferedReader reader)
	{
		try{reader.mark(1);}
		catch(IOException e){
			System.out.println("scanner error");
		}
	}
	//resets the buffer to the position labeled in mark
	private void reset(BufferedReader reader)
	{
		try{reader.reset();}
		catch(IOException e){
			System.out.println("scanner error");
		}
	}
}

