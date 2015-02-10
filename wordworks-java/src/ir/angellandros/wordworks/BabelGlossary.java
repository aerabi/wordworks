package ir.angellandros.wordworks;

import it.uniroma1.lcl.babelnet.BabelGloss;
import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.babelnet.BabelSynsetComparator;
import it.uniroma1.lcl.jlt.util.Language;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BabelGlossary
{
	private BabelNet bn;
	
	public BabelGlossary()
	{
		bn = BabelNet.getInstance();
	}
	
	public BabelGlossary(BabelNet instance)
	{
		bn = instance;
	}
	
	public List<String> getGloss(String lemma, Language source, Language dest) throws IOException
	{
		List<String> toReturn = new ArrayList<String>(); 
		List<BabelSynset> synsets = bn.getSynsets(source, lemma);
		Collections.sort(synsets, new BabelSynsetComparator(lemma));
		
		for (BabelSynset synset : synsets)
		{
			String gloss = getGloss(synset, dest); 
			if(gloss != null)
			{
				toReturn.add(gloss);
			}
		}
		
		return toReturn;
	}
	
	public String getGloss(BabelSynset synset, Language lang) throws IOException
	{
		String id = synset.getId();
		List<BabelGloss> glosses = bn.getGlosses(id);
		
		for (BabelGloss gloss : glosses)
		{
			if(gloss.getLanguage() == lang)
			{
				return gloss.getGloss();
			}
		}
		
		return null;
	}

	public static void main(String[] args) throws IOException
	{
		BabelGlossary bg = new BabelGlossary();
		List<String> list = bg.getGloss("play", Language.EN, Language.FA);
		for(String str : list)
		{
			System.out.println(str);
		}
	}

}
