import java.util.LinkedList;

/**
 * @author Timmy Larsson & Housam Abbas
 */
public class Word {
	private String word;
	private LinkedList<Word> words;
	private Word pathParent;

	public Word(String word) {
		this.word = word;
		words = new LinkedList<Word>();
	}

	public boolean match(Word w) {
		String matchWord = word.substring(1, 5);
		String w5 = w.toString();
		for (int i = 0; i < 4; i++) {
			char c = matchWord.charAt(i);
			if (w5.contains(c + "")) {
				int index = w5.indexOf(c);
				StringBuilder sb = new StringBuilder(w5);
				sb = sb.deleteCharAt(index);
				w5 = sb.toString();
			} else {
				return false;
			}
		}
		return true;
	}

	public String toString() {
		return word;
	}

	public void add(Word w) {
		words.add(w);
	}

	@Override
	public boolean equals(Object arg0) {
		Word obj = (Word) arg0;
		if (this.toString().equals(obj.toString())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return word.hashCode();
	}

	public LinkedList<Word> neighbors() {
		return words;
	}

	public void setPathParent(Word word) {
		pathParent = word;
	}

	public Word getPathParent() {
		return pathParent;
	}
}
