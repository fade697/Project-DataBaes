package application;

public class voice {
	private String titel;
	private int VIP;
	private int ID;
	
	
	
	@Override
	public String toString() {
		return "voice [titel=" + titel + ", VIP=" + VIP + ", ID=" + ID + "]";
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public voice(String titel, int vIP, int iD) {
		super();
		this.titel = titel;
		VIP = vIP;
		ID = iD;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public int getVIP() {
		return VIP;
	}
	public void setVIP(int vIP) {
		VIP = vIP;
	}
	

}
