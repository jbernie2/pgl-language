/*
* Author: John Bernier
* Created: 10/2012
* token.java:
*		tokens used by the scanner to classify input and pass it to the parser
*/

public class token
{
	types type = null;
	String text = "";
	
	public token()
	{
		//empty constructor
	}
	public void setType(types x)
	{type = x;}
	public void setText(String x)
	{text = x;}
	public void setText(char x)
	{text += x;}
	
	public types getType()
	{return type;}
	public String getText()
	{return text;}
	
}
