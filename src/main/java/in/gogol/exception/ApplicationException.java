package in.gogol.exception;

public class ApplicationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4251976105399888215L;
	
	public ApplicationException(String desc){
		super(desc);
	}
	
	public ApplicationException(){
		super();
	}

}
