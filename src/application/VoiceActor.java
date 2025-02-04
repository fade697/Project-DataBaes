package application;

public class VoiceActor {

	private int Voice_Actor_ID;
	private int Voice_Actor_Salary;
	private String Character_Name;
	private String Address;
	private int Phone_Number;

	public VoiceActor(int voice_Actor_ID, int voice_Actor_Salary, String character_Name, String address,int phone_Number) {
		super();
		Voice_Actor_ID = voice_Actor_ID;
		Voice_Actor_Salary = voice_Actor_Salary;
		Character_Name = character_Name;
		Address = address;
		Phone_Number = phone_Number;
	}

	public int getVoice_Actor_ID() {
		return Voice_Actor_ID;
	}

	public void setVoice_Actor_ID(int voice_Actor_ID) {
		Voice_Actor_ID = voice_Actor_ID;
	}

	public int getVoice_Actor_Salary() {
		return Voice_Actor_Salary;
	}

	public void setVoice_Actor_Salary(int voice_Actor_Salary) {
		Voice_Actor_Salary = voice_Actor_Salary;
	}

	public String getCharacter_Name() {
		return Character_Name;
	}

	public void setCharacter_Name(String character_Name) {
		Character_Name = character_Name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public int getPhone_Number() {
		return Phone_Number;
	}

	public void setPhone_Number(int phone_Number) {
		Phone_Number = phone_Number;
	}

	@Override
	public String toString() {
		return "VoiceActor [Voice_Actor_ID=" + Voice_Actor_ID + ", Voice_Actor_Salary=" + Voice_Actor_Salary
				+ ", Character_Name=" + Character_Name + ", Address=" + Address + ", Phone_Number=" + Phone_Number
				+ "]";
	}
	

}
