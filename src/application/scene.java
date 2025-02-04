package application;

public class scene {

	private int Scene_ID;
	private String Artist_Name;
	private int Period;
	private int Start_Time;
	private int Episode;
	private int Artist_ID;

	public scene(int scene_ID, String artist_Name, int period, int start_Time, int episode,int artist_ID) {
		super();
		this.Scene_ID = scene_ID;
		this.Artist_Name = artist_Name;
		this.Period = period;
		this.Start_Time = start_Time;
		this.Episode = episode;
		this.Artist_ID=artist_ID;
		
	}

	public int getArtist_ID() {
		return Artist_ID;
	}

	public void setArtist_ID(int artist_ID) {
		Artist_ID = artist_ID;
	}

	public int getScene_ID() {
		return Scene_ID;
	}

	public void setScene_ID(int scene_ID) {
		Scene_ID = scene_ID;
	}

	public String getArtist_Name() {
		return Artist_Name;
	}

	public void setArtist_Name(String artist_Name) {
		Artist_Name = artist_Name;
	}

	public int getPeriod() {
		return Period;
	}

	public void setPeriod(int period) {
		Period = period;
	}

	public int getStart_Time() {
		return Start_Time;
	}

	public void setStart_Time(int start_Time) {
		Start_Time = start_Time;
	}

	public int getEpisode() {
		return Episode;
	}

	public void setEpisode(int episode) {
		Episode = episode;
	}

	@Override
	public String toString() {
		return "scene [Scene_ID=" + Scene_ID + ", Artist_Name=" + Artist_Name + ", Period=" + Period + ", Start_Time="
				+ Start_Time + ", Episode=" + Episode + ", Artist_ID=" + Artist_ID + "]";
	}
	

}
