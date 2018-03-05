package in.gogol.constants;

public enum Version {
	
	FIRST("v1");
	
	private final String vid;
	
	
	Version(String vid){
		this.vid = vid;
	}
	
	public String getVersionId(){
		return this.vid;
	}
}
