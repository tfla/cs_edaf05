package lab01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Timmy Larsson, Housam Abbas Implementation of the Gales-Shapley
 *         algorithm, prints result to stdout.
 */
public class GS {

	private int N, engagedCount;
	private String[][] womenPref;
	private String[][] menPref;
	private String[] men;
	private String[] women;
	private String[] womenPartner;
	private String[] menPartner;
	private boolean[] menEngaged;

	/**
	 * Constructor
	 * 
	 * @param m
	 *            String[] of names for "male"-objects.
	 * @param w
	 *            String[] of names for "female"-objects.
	 * @param mp
	 *            String[] of preferences for "male"-objects.
	 * @param wp
	 *            String[] of preferences for "female"-objects..
	 */
	public GS(String[] m, String[] w, String[][] mp, String[][] wp) {
		N = mp.length;
		engagedCount = 0;
		men = m;
		women = w;
		menPref = mp;
		womenPref = wp;
		menEngaged = new boolean[N];
		womenPartner = new String[N];
		menPartner = new String[N];
		calcMatches();
	}

	/**
	 * Calculates (and prints) the result.
	 */
	private void calcMatches() {
		while (engagedCount < N) {
			int free;
			for (free = 0; free < N; free++)
				if (!menEngaged[free])
					break;
			for (int i = 0; i < N && !menEngaged[free]; i++) {
				int index = womenIndexOf(menPref[free][i]);
				if (womenPartner[index] == null) {
					womenPartner[index] = men[free];
					menPartner[free] = women[index];
					menEngaged[free] = true;
					engagedCount++;
				} else {
					String currentPartner = womenPartner[index];
					if (morePreference(currentPartner, men[free], index)) {
						womenPartner[index] = men[free];
						menPartner[free] = women[index];
						menEngaged[free] = true;
						menEngaged[menIndexOf(currentPartner)] = false;
					}
				}
			}
		}
		printCouples();
	}

	/**
	 * Compares two partners to eachother.
	 * 
	 * @param curPartner
	 *            The current partner.
	 * @param newPartner
	 *            The partner with which to compare.
	 * @param index
	 *            The index for current partner.
	 * @return
	 */
	private boolean morePreference(String curPartner, String newPartner,
			int index) {
		for (int i = 0; i < N; i++) {
			if (womenPref[index][i].equals(newPartner))
				return true;
			if (womenPref[index][i].equals(curPartner))
				return false;
		}
		return false;
	}

	private int menIndexOf(String str) {
		for (int i = 0; i < N; i++)
			if (men[i].equals(str))
				return i;
		return -1;
	}

	private int womenIndexOf(String str) {
		for (int i = 0; i < N; i++)
			if (women[i].equals(str))
				return i;
		return -1;
	}

	public void printCouples() {
		for (int i = 0; i < N; i++) {
			// System.out.println(womenPartner[i] + " -- " + women[i]);
			System.out.println(men[i] + " -- " + menPartner[i]);
		}
	}

	public static void main(String[] args) {
		String[] m = null;
		String[] w = null;
		String[][] mp = null;
		String[][] wp = null;
		File file = null;
		if (args.length < 1) {
			file = new File(System.getProperty("user.dir")
					+ "/src/lab01/sm-random-500.in");
		} else {
			file = new File(args[0]);
		}
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int n = 0;
		while (scan.hasNextLine()) {
			String s = scan.nextLine();
			if (s.startsWith("#")) {
				// System.out.println("kommentar: " + s);
			} else if (s.startsWith("n")) {
				n = Integer.parseInt(s.split("=")[1]);
				m = new String[n];
				w = new String[n];
				mp = new String[n][n];
				wp = new String[n][n];
				/*
				 * System.out.println("n = " + n);
				 * System.out.println("w.length = " + w.length);
				 * System.out.println("m.length = " + m.length);
				 * System.out.println("mp.length = " + mp.length);
				 * System.out.println("wp.length = " + wp.length);
				 */
			} else if (s.matches("\\d+:[\\s+\\S+]+")) {
				// System.out.println(s);
				int number = Integer.parseInt(s.split(":")[0]);
				String[] st = s.split(": ")[1].split(" ");
				// System.out.println("number: " + number);
				if (number % 2 == 0) {
					for (int j = 0; j < st.length; j++) {
						/*
						 * System.out.println("wp[" + ((number / 2) - 1) + "]["
						 * + j + "] = " + m[Integer.valueOf(st[j]) / 2]);
						 */
						wp[number / 2 - 1][j] = m[Integer.valueOf(st[j]) / 2];
					}
				} else {
					for (int j = 0; j < st.length; j++) {
						/*
						 * System.out.println("mp[" + number / 2 + "][" + j +
						 * "] = " + w[Integer.valueOf(st[j]) / 2 - 1]);
						 */
						mp[number / 2][j] = w[Integer.valueOf(st[j]) / 2 - 1];
					}
				}
			} else if (s.matches("\\d+\\s+\\S+")) {
				int i = Integer.parseInt(s.split(" ")[0]);
				if (i % 2 == 0) {
					/*
					 * System.out.println("w[" + ((i / 2) - 1) + "] = " +
					 * s.split(" ")[1]);
					 */
					w[(i / 2) - 1] = s.split(" ")[1];

				} else {
					/*
					 * System.out.println("m[" + i / 2 + "] = " +
					 * s.split(" ")[1]);
					 */
					m[i / 2] = s.split(" ")[1];

				}

			}
		}
		scan.close();

		if (m != null && w != null && mp != null && wp != null) {
			GS gs = new GS(m, w, mp, wp);
		}
	}
}
