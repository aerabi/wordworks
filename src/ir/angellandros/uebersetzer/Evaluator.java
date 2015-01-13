package ir.angellandros.uebersetzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluator
{
	public double evaluate(List<String> text1, List<String> text2, int n)
	{
		Map<String, Integer> words1 = getNGramMap(text1, n);
		Map<String, Integer> words2 = getNGramMap(text2, n);

		double tp = 0.0;
		double fp = 0.0;
		double fn = 0.0;

		for (String word : words1.keySet())
		{
			if (!words2.containsKey(word))
			{
				words2.put(word, 0);
			}
		}

		for (String word : words2.keySet())
		{
			if (!words1.containsKey(word))
			{
				words1.put(word, 0);
			}
		}

		for (String word : words1.keySet())
		{
			int count1 = words1.get(word);
			int count2 = words2.get(word);

			tp += min(count1, count2);
			fp += max(count1 - count2, 0);
			fn += max(count2 - count1, 0);
		}

		System.out.printf("TP = %.0f, FP = %.0f, FN = %.0f\n", tp, fp, fn);

		double f1 = 2 * tp / (2 * tp + fp + fn);
		return f1;
	}

	public double evaluate(String[] split1, String[] split2, int n)
	{
		List<String> l1 = new ArrayList<String>();
		List<String> l2 = new ArrayList<String>();

		for (String word : split1)
		{
			l1.add(word);
		}

		for (String word : split2)
		{
			l2.add(word);
		}

		return evaluate(l1, l2, n);
	}

	private Map<String, Integer> getNGramMap(List<String> text, int n)
	{
		Map<String, Integer> words = new HashMap<String, Integer>();

		for (int i = 0; i < text.size() - n + 1; i++)
		{
			String multiple = "";

			for (int j = i; j < i + n; j++)
			{
				multiple += text.get(j);
			}

			if (words.containsKey(multiple))
			{
				int count = words.get(multiple);
				words.put(multiple, count + 1);
			} else
			{
				words.put(multiple, 1);
			}
		}

		return words;
	}

	private double min(double a, double b)
	{
		return (a < b ? a : b);
	}

	private double max(double a, double b)
	{
		return (a > b ? a : b);
	}

	public static void main(String[] args)
	{
		Evaluator evaluator = new Evaluator();

		String[] wittgenstein =
		{ "Die Welt ist alles was der Fall ist",
				"Die Welt ist die Gesamheit der Tatsachen nicht der Dinge",
				"Die Tatsachen im logischen Raum sind die Welt" };
		String[] wittgensteinPersische =
		{ "جهان همه‌ی آن‌چه است که واقع است",
				"جهان مجموعه‌ی بوده‌ها است نه مجموعه‌ی شیءها",
				"بوده‌ها در فضای منطقی عبارت اند از جهان" };
		String[] wittgensteinPersischeGoogle =
		{ "Die Welt ist worum es geht",
				"Das Set nicht eine Sammlung von Objekten",
				"Ist der logische Raum sind die Welt" };
		
		for(int i=0; i<wittgenstein.length; i++)
		{
			double f1 = evaluator.evaluate(wittgenstein[i].split(" "), wittgensteinPersischeGoogle[i].split(" "), 1);
			System.out.println(f1);
			double f2 = evaluator.evaluate(wittgenstein[i].split(" "), wittgensteinPersischeGoogle[i].split(" "), 2);
			System.out.println(f2);
			double f3 = evaluator.evaluate(wittgenstein[i].split(" "), wittgensteinPersischeGoogle[i].split(" "), 3);
			System.out.println(f3);
		}
	}

}
