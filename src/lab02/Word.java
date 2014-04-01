package lab02;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * ord representerar varje unika kompinationer
 */
public class Word {

	private String word;
	private LinkedList<Word> words;

	public Word(String word) {
		this.word = word;
		words = new LinkedList<Word>();
	}

	public boolean match(Word w) {
		String matchWord = word.substring(1, 5);// 4 bokst�ver
		String w5 = w.toString();
		//System.out.println(matchWord);
		for (int i = 0; i < 4; i++) {
			char c = matchWord.charAt(i);
			if (w5.contains(c + "")) {
				// try to solve dublicate of chars
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

	public void print() {
		for (Word w : words) {
			System.out.print(" " + w + " ");
		}
		System.out.println();
	}

	public LinkedList<Word> neighbors() {
		return words;
	}

}
