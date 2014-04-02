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
	 * The main method, builds a graph of words from the files given by args[0]
	 * argument and runs a BFS on it to determine the path from the two words 
	 * specified in the file given by args[1].
	 * 
	 * @param args
	 *            The files for which to execute the program.
	 */
	public static void main(String[] args) {
		ArrayList<Word> wordList = new ArrayList<Word>();
		Scanner scan = null;
		HashSet<Word> set = new HashSet<Word>();
		File wordFile = null;
		File pairFile = null;
		if (args.length == 0) {
			wordFile = new File(System.getProperty("user.dir")
					+ "/words-10.dat");
			pairFile = new File(System.getProperty("user.dir")
					+ "/words-10-test.in");
		} else {
			wordFile = new File(args[0]);
			pairFile = new File(args[1]);
		}

		/**
		 * Handle the file of words.
		 */
		try {
			scan = new Scanner(wordFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scan.hasNextLine()) {
			Word s = new Word(scan.nextLine());
			wordList.add(s);
			set.add(s);
		}
		scan.close();
		for (Word e : wordList) {
			for (Word w : set) {
				if (e.match(w) && !w.equals(e)) {
					e.add(w);
				}
			}
		}
		BFS bfs = new BFS();
		
		/**
		 * Handle the file of pairs.
		 */
		try {
			scan = new Scanner(pairFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scan.hasNextLine()) {
			String[] words = scan.nextLine().split(" ");
			Word w1 = wordList.get(wordList.indexOf(new Word(words[0])));
			Word w2 = wordList.get(wordList.indexOf(new Word(words[1])));
			LinkedList<Word> path = bfs.findPath(w1, w2);
			if (path == null) {
				System.out.println("-1");
			} else {
				System.out.println(path.size());
			}
		}
		scan.close();
	}
}
