import java.util.LinkedList;
import java.util.Iterator;

/**
 * @author Timmy Larsson & Housam Abbas
 */
public class BFS {
	/**
	 * Constructor, creates a BFS-object.
	 */
	public BFS() {

	}

	/**
	 * Finds and returns the shortest path from root to detination, using BFS.
	 * @param root The Word to start the search at.
	 * @param destination The Word to search for.
	 * @return A LinkedList<Word> containing the shortest path from root to destination.
	 */
	public LinkedList<Word> findPath(Word root, Word destination) {
		LinkedList<Word> closedList = new LinkedList<Word>();
		LinkedList<Word> openList = new LinkedList<Word>();
		openList.add(root);
		root.setPathParent(null);

		while (!openList.isEmpty()) {
			Word temp = openList.removeFirst();
			if (temp.equals(destination)) {
				return constructPath(destination);
			} else {
				closedList.add(temp);

				Iterator<Word> i = temp.neighbors().iterator();
				while (i.hasNext()) {
					Word neighborWord = i.next();
					if (!closedList.contains(neighborWord)
							&& !openList.contains(neighborWord)) {
						neighborWord.setPathParent(temp);
						openList.add(neighborWord);
					}
				}
			}
		}
		return null;
	}

	/**
	 * Helps findPath construct a path from root to the destination by adding all parents to the top of a list.
	 * @param word The Word to start building the path from.
	 * @return A LinkedList<Word> containing the shortest path from the first parentless Word to word..
	 */
	private LinkedList<Word> constructPath(Word word) {
		LinkedList<Word> path = new LinkedList<Word>();
		while (word.getPathParent() != null) {
			path.addFirst(word);
			word = word.getPathParent();
		}
		return path;
	}
}
