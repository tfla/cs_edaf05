package lab01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GS {

	private int N, engagedCount;
	private String[][] menPref;
	private String[][] womenPref;
	private String[] men;
	private String[] women;
	private String[] menPartner;
	private boolean[] menEngaged;

	public GS(String[] m, String[] w, String[][] mp, String[][] wp) {
		N = mp.length;
		engagedCount = 0;
		men = m;
		women = w;
		menPref = mp;
		womenPref = wp;
		menEngaged = new boolean[N];
		menPartner = new String[N];
		calcMatches();
	}

	private void calcMatches() {
		while (engagedCount < N) {
			int free;
			for (free = 0; free < N; free++)
				if (!menEngaged[free])
					break;
			for (int i = 0; i < N && !menEngaged[free]; i++) {
				int index = menIndexOf(womenPref[free][i]);
				if (menPartner[index] == null) {
					menPartner[index] = women[free];
					menEngaged[free] = true;
					engagedCount++;
				} else {
					String currentPartner = menPartner[index];
					if (morePreference(currentPartner, women[free], index)) {
						menPartner[index] = women[free];
						menEngaged[free] = true;
						menEngaged[womenIndexOf(currentPartner)] = false;
					}
				}
			}
		}
		printCouples();
	}

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
		for (int i = 0; i < N; i++)
			System.out.println(men[i] + " -- " + menPartner[i]);
	}

	public static void main(String[] args) {
		String[] m = null;
		String[] w = null;
		String[][] mp = null;
		String[][] wp = null;
		File file = new File(
				"/home/tfla/.workspace/edaf05/src/lab01/sm-friends.in");
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int n = 0;
		while (scan.hasNextLine()) {
			String s = scan.nextLine();
			if (s.startsWith("#"))
				System.out.println("kommentar: " + s);
			else if (s.startsWith("n")) {
				n = Integer.parseInt(s.split("=")[1]);
				m = new String[n];
				w = new String[n];
				System.out.println("n = " + n);
				System.out.println("w.length = " + w.length);
				System.out.println("m.length = " + m.length);
			} else if (s.matches("\\d+:[\\s+\\S+]+")) {
				System.out.println(s);
				// for (int i = 0; i < n * 2; i++) {
				wp = new String[n][n];
				mp = new String[n][n];
				int number = Integer.parseInt(s.split(":")[0]);
				String[] st = s.split(": ")[1].split(" ");
				System.out.println("number: " + number);
				if (number % 2 == 0) {
					for (int j = 0; j < st.length; j++) {
						System.out.println("wp[" + ((number / 2) - 1) + "]["
								+ j + "] = " + st[j]);
						wp[number / 2 - 1][j] = st[j];
					}
				} else {
					for (int j = 0; j < st.length; j++) {
						System.out.println("mp[" + number / 2 + "][" + j
								+ "] = " + st[j]);
						mp[number / 2][j] = st[j];
					}
				}
			} else if (s.matches("\\d+\\s+\\D+")) {
				int i = Integer.parseInt(s.split(" ")[0]);
				if (i % 2 == 0) {
					System.out.println("w[" + ((i / 2) - 1) + "] = "
							+ s.split(" ")[1]);
					w[(i / 2) - 1] = s.split(" ")[1];

				} else {
					System.out.println("m[" + i / 2 + "] = " + s.split(" ")[1]);
					m[i / 2] = s.split(" ")[1];

				}

			}
		}
		scan.close();

		// m = { "Ross", "Chandler", "Joey" };
		// w = { "Monica", "Phoebe", "Rachel" };

		// String[][] mp = { { w[(6 / 2) - 1], w[(4 / 2) - 1], w[(2 / 2) - 1] },
		// { w[(2 / 2) - 1], w[(6 / 2) - 1], w[(4 / 2) - 1] },
		// { w[(6 / 2) - 1], w[(4 / 2) - 1], w[(2 / 2) - 1] } };
		//
		// String[][] wp = { { m[3 / 2], m[5 / 2], m[1 / 2] },
		// { m[5 / 2], m[1 / 2], m[3 / 2] },
		// { m[1 / 2], m[5 / 2], m[3 / 2] } };

		if (m != null && w != null && mp != null && wp != null) {
			GS gs = new GS(m, w, mp, wp);
		}
	}
}
