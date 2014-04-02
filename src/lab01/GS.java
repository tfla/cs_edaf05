import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * @author Timmy Larsson & Housam Abbas
 * 
 */
public class GS {

	private LinkedList<Man> freeMen;
	private LinkedList<Man> temp;

	/**
	 * Constructor, creates a stable matching and prints it to stdout.
	 * 
	 * @param men
	 *            A LinkedList<Man> containing all the men for which to find
	 *            matches.
	 */
	public GS(LinkedList<Man> men) {
		this.freeMen = new LinkedList<Man>(men);
		temp = new LinkedList<Man>(men);
		calculate();
		print();
	}

	/**
	 * Calculates the stable mathings.
	 */
	private void calculate() {
		while (!freeMen.isEmpty()) {
			Man m = freeMen.peek();
			for (int i = 0; i < freeMen.size() && m.partner() == null; i++) {
				Woman w = m.highest();
				if (!w.isMarried()) {
					m.marry(w);
					freeMen.poll();
				} else {
					if (w.prefers(m)) {
						freeMen.add(w.partner());
						w.divorce();
						m.marry(w);
						freeMen.poll();
					}
				}
			}

		}
	}

	/**
	 * Prints the stable matchings to stdout.
	 */
	private void print() {
		for (Man m : temp) {
			System.out.println(m.toString() + " -- " + m.partner().toString());
		}
	}

	/**
	 * Main-method. Parses a file for men, women and their preferences.
	 * 
	 * @param args
	 *            The path to the file to parse, if none is specified it will
	 *            parse for the Illiad.
	 */
	public static void main(String[] args) {
		LinkedList<Man> m = new LinkedList<Man>();
		ArrayList<Woman> w = new ArrayList<Woman>();
		File file = null;
		if (args.length < 1) {
			file = new File(System.getProperty("user.dir")
					+ "/sm-illiad.in");
		} else {
			file = new File(args[0]);
		}
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scan.hasNextLine()) {
			String s = scan.nextLine();
			if (s.matches("\\d+:[\\s+\\S+]+")) {
				int number = Integer.parseInt(s.split(":")[0]);
				String[] st = s.split(": ")[1].split(" ");
				if (number % 2 == 0) {
					for (int j = 0; j < st.length; j++) {
						w.get(number / 2 - 1).addPref(
								m.get(Integer.valueOf(st[j]) / 2));

					}
				} else {
					for (int j = 0; j < st.length; j++) {
						m.get(number / 2).addPref(
								w.get(Integer.valueOf(st[j]) / 2 - 1));
					}
				}
			} else if (s.matches("\\d+\\s+\\S+")) {
				int i = Integer.parseInt(s.split(" ")[0]);
				if (i % 2 == 0) {
					w.add((i / 2) - 1, new Woman(s.split(" ")[1]));
				} else {
					m.add(i / 2, new Man(s.split(" ")[1]));
				}
			}
		}
		scan.close();
		/*for (Woman wo : w) {
			wo.optimize();
		}*/

		if (m != null && w != null) {
			@SuppressWarnings("unused")
			GS gs = new GS(m);
		}
	}
}
