package application;

public class Anime {
	private String Name_Of_Writer;
	private String Title;
	private String Copy_Right_Date;
	private int Scene_ID;
	private int Writer_ID;

	public Anime(String name_Of_Writer, String title, String copy_Right_Date,Integer scene_ID,Integer writer_ID) {
		super();
		this.Name_Of_Writer = name_Of_Writer;
		this.Title = title;
		this.Copy_Right_Date = copy_Right_Date;
		this.Scene_ID=scene_ID;
		this.Writer_ID=writer_ID;
	}

	

	public int getScene_ID() {
		return Scene_ID;
	}

	public void setScene_ID(int scene_ID) {
		Scene_ID = scene_ID;
	}

	public int getWriter_ID() {
		return Writer_ID;
	}

	public void setWriter_ID(int writer_ID) {
		Writer_ID = writer_ID;
	}

	public String getName_Of_Writer() {
		return Name_Of_Writer;
	}

	public void setName_Of_Writer(String name_Of_Writer) {
		Name_Of_Writer = name_Of_Writer;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getCopy_Right_Date() {
		return Copy_Right_Date;
	}

	public void setCopy_Right_Date(String copy_Right_Date) {
		Copy_Right_Date = copy_Right_Date;
	}

	@Override
	public String toString() {
		return "Anime [Name_Of_Writer=" + Name_Of_Writer + ", Title=" + Title + ", Copy_Right_Date=" + Copy_Right_Date
				+ ", Scene_ID=" + Scene_ID + ", Writer_ID=" + Writer_ID + "]";
	}
	

}
