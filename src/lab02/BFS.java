package lab02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	private ArrayList<Word> words;

	public BFS(ArrayList<Word> words) {
		this.words = words;
	}

	public int calculate(Word root, Word destination) {
		Queue<Word> q = new LinkedList<Word>();
		q.add(root);
		int i = 0;
		while (!q.isEmpty()) {
			Word t = q.poll();
			// System.out.println(t);
			if (t != null && t.equals(destination)) {
				i++;
				return i;
			}
			for (Word w : t.neighbors()) {
				// System.out.println("adding " + w + " to queue.");
				q.add(w);
			}
		}
		return -1;
	}
}
