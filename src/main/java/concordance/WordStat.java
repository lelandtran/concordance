package concordance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordStat {

	private String word;
	private int occurrences;
	private List<Integer> locations;
	
	public WordStat(String word, int location){
		this.word = word;
		this.locations = new ArrayList<Integer>(Arrays.asList(location));
		this.occurrences = 1;
		
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getOccurrences() {
		return occurrences;
	}
	public void setOccurrences(int occurrences) {
		this.occurrences = occurrences;
	}
	public List<Integer> getLocations() {
		return locations;
	}
	public void setLocations(List<Integer> locations) {
		this.locations = locations;
	}
	public void incrementOccurences(int location){
		this.occurrences++;
		this.getLocations().add(location);
	}
}
