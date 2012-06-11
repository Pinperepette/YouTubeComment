package fip;

import java.net.*;
import java.io.*;
import java.util.*;

public class finder
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		URL url;
		InputStream is = null;
		BufferedReader in;
		String line;
		FileWriter fw; 
		PrintWriter out = null;
		
		try 
		{
			fw = new FileWriter("youtube.txt");
			out = new PrintWriter(fw);
			url = new URL("http://www.youtube.com/all_comments?v=OBg9dqyhpXI");
			
			try
			{
				is = url.openStream();  // throws an IOException
				in = new BufferedReader(new InputStreamReader(is));
				boolean nextLine = true;
				boolean name = false;
				boolean comment = false;
				String commentString = "";
				
				while (nextLine) 
				{
					try
					{
						line = in.readLine(); // Will throw an exception if there is no next line
						if (comment)
						{
							int stopPoint = line.indexOf("</p>");
							line = line.substring(13, stopPoint);
							comment = false;
							commentString = line;
						}
						if (line.indexOf("comment-text") != -1) comment = true;
						if (name)
						{
							int stopPoint = line.indexOf("class") - 3;
							line = line.substring(31, stopPoint);
							names.add(line);
							out.println(line + ": " + commentString);
							commentString = "";
							name = false;
						}
						if (line.indexOf("\"author") != -1) name = true;
					}
					catch (Exception e)
					{
						nextLine = false;
					}
				}
				Random r = new Random();
				int winner = r.nextInt(names.size());
				out.println("");
				out.println("The Winner is " + names.get(winner));
			}				
			catch (Exception e)
			{}
		} 
		catch (Exception e) 
		{} 
		finally 
		{
			try 
			{
				is.close();
				out.close();
			} 
			catch (IOException ioe)
			{}
		}
	}
}
