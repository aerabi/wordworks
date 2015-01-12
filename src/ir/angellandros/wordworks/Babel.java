package ir.angellandros.wordworks;

import it.uniroma1.lcl.jlt.ling.Word;
import it.uniroma1.lcl.jlt.util.Language;
import it.uniroma1.lcl.jlt.util.ScoredItem;
import it.uniroma1.lcl.jlt.util.Strings;
import it.uniroma1.lcl.knowledge.KnowledgeBase;
import it.uniroma1.lcl.knowledge.graph.KnowledgeGraph;
import it.uniroma1.lcl.knowledge.graph.KnowledgeGraphFactory;
import it.uniroma1.lcl.knowledge.graph.KnowledgeGraphScorer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Babel {
	
	
	public static void main(String[] args) throws IOException {
		List<Word> sentence = Arrays.asList(
				new Word[]{new Word("bank", "n", Language.EN), new Word("bonus", "n", Language.EN),
						new Word("pay", "v", Language.EN), new Word("stock", "n", Language.EN)});
		disambiguate(sentence, KnowledgeBase.BABELNET, KnowledgeGraphScorer.DEGREE);
	}

	public static void disambiguate(Collection<Word> words, KnowledgeBase kb, KnowledgeGraphScorer scorer) throws IOException {
		KnowledgeGraphFactory factory = KnowledgeGraphFactory.getInstance(kb);
		KnowledgeGraph kGraph = factory.getKnowledgeGraph(words);
		Map<String, Double> scores = scorer.score(kGraph);
		for (String concept : scores.keySet()) {
			double score = scores.get(concept);
			for (Word word : kGraph.wordsForConcept(concept))
				word.addLabel(concept, score);
			}
			for (Word word : words) {
				System.out.println("\n\t" + word.getWord() + " -- ID " + word.getId() +
						" => SENSE DISTRIBUTION: ");
				for (ScoredItem<String> label : word.getLabels()) {
					System.out.println("\t  [" + label.getItem() + "]:" +
							Strings.format(label.getScore()));
				} 
			}
	}
}