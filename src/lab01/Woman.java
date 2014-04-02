import java.util.ArrayList;

public class Woman {

	private String name;
	private ArrayList<Man> preferences;
	private boolean married;
	private Man partner;

	public Woman(String name) {
		this.name = name;
		this.preferences = new ArrayList<Man>();
		this.married = false;
	}

	public void addPref(Man man) {
		preferences.add(man);
	}

	public boolean isMarried() {
		return married;
	}

	public boolean prefers(Man man) {
		for (Man m : preferences) {
			if (m.equals(man)) {
				return true;
			}
			if (m.equals(partner)) {
				return false;
			}
		}
		return false;
		// return (preferences.indexOf(man) > preferences.indexOf(partner));
	}

	public void setPartner(Man man) {
		partner = man;
		married = true;
	}

	public void divorce() {
		partner.divorce();
		partner = null;
		married = false;
	}

	public String toString() {
		return name;
	}

	public Man partner() {
		return partner;
	}

	/*public void optimize() {
		ArrayList<Man> temp = new ArrayList<Man>();
		for (Man m : preferences) {
			temp.add(m);
		}
		preferences = temp;		
	}*/
}
