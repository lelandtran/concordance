package concordance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Concordance {
	private static final List<Character> sentenceEnders = Arrays.asList('.', '?', '!');

	public static void main(String args[]){
		if (args.length == 1){
			System.out.println("Coming soon: Option for reading in a file.");
			return;
		} else if (args.length > 1){
			System.out.println("Run the program with no arguments.");
		}
		String testString = "Document: The fox i.e. Al the quick brown fox jumps over the brown dog. "+
				"He didn't have any regard, interest, etc. for the dog at all! "+
				"Could you imagine that?";
		List<WordStat> concordance = Concordance.concordance(testString);
		concordance.forEach((wordStat) -> {
			System.out.print(wordStat.getWord() 
				+" {"+wordStat.getOccurrences()+":");
			for (int i = 0; i < wordStat.getLocations().size(); i++){
				System.out.print(wordStat.getLocations().get(i));
				System.out.print(i==wordStat.getLocations().size()-1?"":",");
			}
			System.out.println("}");
		});
	}
	
	public static List<WordStat> concordance(String document){
		Map<String, WordStat> wordStatMap = new TreeMap<>();
		StringTokenizer tokenizer = new StringTokenizer(document, " ");
		
		int sentenceIndex = 1;
		String postPeriod = null;
		while (postPeriod != null || tokenizer.hasMoreTokens()){
			String curr = null;
			boolean sentenceEnd = false;
			// use the string saved in the buffer in case the last word ended with a period
			if (postPeriod != null) {
				curr = postPeriod;
				postPeriod = null;
			} else {
				curr = tokenizer.nextToken().trim();
			}
			// logic for checking if the sentence count need be incremented
			boolean periodEnd = sentenceEnders.contains(curr.charAt(curr.length()-1));
			if (periodEnd) {
				// save the next token to a buffer to check if the first character is uppercase
				if (tokenizer.hasMoreTokens()){
					postPeriod = tokenizer.nextToken();
					if (Character.isUpperCase(postPeriod.charAt(0))){
						sentenceEnd = true;
					}
				}
			}
			// sanitize the word TODO: how do we know whether to strip the punctuation or not? bonus: vs i.e.
			curr = sentenceEnd ? curr.substring(0, curr.length()-1) : curr;
			String currKey = extractKey(curr);
			if (currKey != null && wordStatMap.containsKey(currKey)){
				WordStat oldWordStat = wordStatMap.get(currKey);
				oldWordStat.incrementOccurences(sentenceIndex);
			} else {
				wordStatMap.put(currKey, new WordStat(currKey, sentenceIndex));
			}
			
			if (sentenceEnd){
				sentenceIndex++;
			}
		}
		
		return mapToList(wordStatMap);
	}
	
	private static List<WordStat> mapToList(Map<String, WordStat> wordStatMap){
		List<WordStat> wordStatList = new ArrayList<WordStat>();
		for (Entry<String, WordStat> entry : wordStatMap.entrySet()){
			wordStatList.add(entry.getValue());
		}
		return wordStatList;
	}
	
	private static String extractKey(String str){
		Pattern p = Pattern.compile("((?:[a-zA-Z]\\.)+[a-zA-Z]?|[a-zA-Z.']+)");
		Matcher m = p.matcher(str);
		if (m.find()){
			String g = m.group(1);
//			System.out.println("extracting key: " + g);
			return g.toLowerCase();
		}
		return null;
	}
	
}
