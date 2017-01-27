package common;

public enum DhmAction {
	Insert(0),
	Update(1),
	Delete(2),
	Retrieve(3);
	
	private final int code;
		
	DhmAction(final int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	/**
	 * Convert integer DhmAction code into its equivalent enumeration.
	 * If no match is found, throw NpsException.
	 * @throws NpsException 
	 */
	public static DhmAction fromCode(int code) throws NpsException {
		NpsTrace.error("Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());

		for (DhmAction type : DhmAction.values()) {
            if (type.getCode() == code) {
            	NpsTrace.error("Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
                return type;
            }
        }
		
        throw new NpsException(Status.DHM_FAILURE, "Unknown Dhm database action");
	}

}
