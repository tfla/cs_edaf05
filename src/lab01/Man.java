import java.util.LinkedList;

public class Man {

	private String name;
	private LinkedList<Woman> preferences;
	private Woman partner;

	public Man(String name) {
		this.name = name;
		this.preferences = new LinkedList<Woman>();
	}

	public void addPref(Woman woman) {
		preferences.add(woman);
	}

	public Woman highest() {
		return preferences.poll();
	}

	public void marry(Woman w) {
		partner = w;
		w.setPartner(this);
	}

	public void divorce() {
		partner = null;
	}

	public String toString() {
		return name;
	}

	public Woman partner() {
		return partner;
	}

}
