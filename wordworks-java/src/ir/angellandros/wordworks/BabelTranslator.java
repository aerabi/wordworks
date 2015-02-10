package ir.angellandros.wordworks;

import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.jlt.util.Language;
import it.uniroma1.lcl.jlt.util.ScoredItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Multimap;

public class BabelTranslator
{
	private BabelNet bn;
	
	/**
	 * Creates a new instance of BabelNet, and use it for translation.
	 */
	public BabelTranslator()
	{
		bn = BabelNet.getInstance();
	}
	
	/**
	 * Gets an instance of BabelNet as argument, and use it for translation.
	 */
	public BabelTranslator(BabelNet instance)
	{
		bn = instance;
	}
	
	/**
	 * Translates the input word from declared language into other declared language. 
	 * @param word the word to translate
	 * @param from source language
	 * @param to destination language
	 * @return collection of translations with score
	 * @throws IOException
	 */
	public Collection<ScoredItem<String>> translateWord(String word, Language from, Language to)
			throws IOException
	{
		if(word.length() == 1)
		{
			Collection<ScoredItem<String>> toReturn = new ArrayList<ScoredItem<String>>();
			toReturn.add(new ScoredItem<String>(word, 1));
			return toReturn;
		}
		
		Multimap<Language, ScoredItem<String>> translations = bn
				.getTranslations(from, word);

		Collection<ScoredItem<String>> toReturn = translations.get(to);
		if(toReturn.isEmpty())
		{
			toReturn.add(new ScoredItem<String>(word, 1));
		}
		
		return translations.get(to);

	}
	
	public List<String> translatePhrase(String phrase, Language from, Language to, String regex) throws IOException
	{
		List<String> toReturn = new ArrayList<String>();
		toReturn.add("");
		String[] words = phrase.split(regex);
		
		int startingIndex = (words.length == 1? 0: 1);
		
		for(int i=words.length; i >= startingIndex; --i)
		{
			try
			{
				toReturn = cartesianProduct(toReturn, translateWord(words[i], from, to));
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				continue;
			}
		}
		return toReturn;
	}
	
	public List<String> translatePhrase(String phrase, Language from, Language to) throws IOException
	{
		return translatePhrase(phrase, from, to, "(?=\\p{Upper})");
	}
	
	private List<String> cartesianProduct(List<String> a, Collection<ScoredItem<String>> collection)
	{
		List<String> toReturn = new ArrayList<String>();
		
		if(a == null || collection == null)
		{
			return null;
		}
		
		for(String aMember : a)
		{
			for(ScoredItem<String> bMember: collection)
			{
				toReturn.add(aMember + " " + bMember.getItem());
			}
		}
		
		return toReturn;		
	}

	public static void main(String[] args) throws IOException
	{
		BabelTranslator bt = new BabelTranslator();
		System.out.println(bt.translatePhrase("Door", Language.EN, Language.FA));
		System.out.println(bt.translatePhrase("GPS", Language.EN, Language.FA));
	}
}
