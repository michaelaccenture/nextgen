package common;

public enum Status {
	SUCCESS(0),
	FAILURE(1),
	OTHER_FAIL(-1),
	DHM_FAILURE(-2),
	TFC_NOT_LIVE(-3),
	BAM_FAILURE(-4),
	GENERAL_FAILURE(-100);
	
	private final int code;
		
	Status(final int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	/**
	 * Convert integer status code into its equivalent enumeration.
	 * If no match is found, set it to a default value of GENERAL_FAILURE.
	 */
	public static Status fromCode(int code) {
		NpsTrace.error("Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());

		for (Status type : Status.values()) {
            if (type.getCode() == code) {
            	NpsTrace.error("Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
                return type;
            }
        }
		
        return GENERAL_FAILURE;
	}
}
