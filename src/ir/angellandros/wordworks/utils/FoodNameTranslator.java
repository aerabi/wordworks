package ir.angellandros.wordworks.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import ir.angellandros.wordworks.BabelTranslator;
import it.uniroma1.lcl.jlt.util.Language;

public class FoodNameTranslator
{
	BabelTranslator bt;
	
	public FoodNameTranslator()
	{
		bt = new BabelTranslator();
	}
	
	List<String> translate(String name) throws IOException
	{
		List<String> toReturn = new ArrayList<String>();
		for(String phrase : name.split(",|AND"))
		{
			if(toReturn.isEmpty())
				toReturn = bt.translatePhrase(phrase, Language.EN, Language.FA, " ");
			else
				toReturn = cartesianProduct(toReturn, bt.translatePhrase(phrase, Language.EN, Language.FA, " "));
		}
		return toReturn;
	}
	
	private List<String> cartesianProduct(List<String> a, List<String> b)
	{
		List<String> toReturn = new ArrayList<String>();
		
		if(a == null || b == null)
		{
			return null;
		}
		
		for(String aMember : a)
		{
			for(String bMember: b)
			{
				toReturn.add(aMember + "، " + bMember);
			}
		}
		
		return toReturn;		
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("/home/m.aerabi/Dev/DataSets/dataset.txt")); 
		FoodNameTranslator fnt = new FoodNameTranslator();
		
		for(String line = in.readLine(); line != null; line = in.readLine())
		{
			line = line.replaceAll("\"", "");
			String[] l = line.split("\t");
			System.out.println(l[0] + "|" + l[1]);
			for(String translation : fnt.translate(l[1]))
				System.out.println(l[0] + "|" + translation);
		}
		
		
		in.close();
	}

}
