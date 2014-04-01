package lab02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

/**
 * @author Timmy Larsson & Housam Abbas
 */
public class WordLadders {
	/**
	 * The main method, builds a graph of words from the file given as an
	 * argument and runs a BFS on it to determine the path from one word to
	 * another.
	 * 
	 * @param args
	 *            The file for which to execute the program.
	 */
	public static void main(String[] args) {
		ArrayList<Word> wordList = new ArrayList<Word>();
		Scanner scan = null;
		HashSet<Word> set = new HashSet<Word>();
		File wordFile = null;
		File pairFile = null;
		if (args.length == 0) {
			wordFile = new File(System.getProperty("user.dir")
					+ "/src/lab02/words-250.dat");
			pairFile = new File(System.getProperty("user.dir")
					+ "/src/lab02/words-250-test.in");
		} else {
			wordFile = new File(args[0]);
			pairFile = new File(args[1]);
		}
		try {
			scan = new Scanner(wordFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scan.hasNextLine()) {
			Word s = new Word(scan.nextLine());
			wordList.add(s);
			/* Word key = new Word(s); */
			set.add(s);
		}
		scan.close();

		for (Word outer : wordList) {
			for (Word inner : set) {
				if (outer.match(inner) && !inner.equals(outer)) {
					inner.add(outer);
				}
			}
		}
		BFS bfs = new BFS(wordList);

		try {
			scan = new Scanner(pairFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scan.hasNextLine()) {
			String[] words = scan.nextLine().split(" ");
			Word w1 = wordList.get(wordList.indexOf(new Word(words[0])));
			Word w2 = wordList.get(wordList.indexOf(new Word(words[1])));
			System.out.println(bfs.calculate(w1, w2));
		}
		scan.close();
		/*
		 * for (Word e : wordList) { System.out.print(e + "→"); for (Word w :
		 * set) { if (e.match(w) && !w.equals(e)) { w.add(e);
		 * System.out.print(w.toString() + "→"); } } System.out.println(""); }
		 */
	}
}