package jam;

import common.NpsException;
import common.NpsTrace;
import common.Status;

public class JA98765 {

	public int jni_invoke(IA98765_input_t input, OA98765_output_t output) {
		NpsTrace.info("Entered " + this.getClass().getSimpleName());
		Status status = Status.SUCCESS;
		
		try {
			invoke(input, output);
		}
		catch (NpsException ex) {
			// set status code and message then swallow so rest of NPS is oblivious to this change 
			status = ex.getStatus();
			NpsTrace.error(ex.getMessage());
		}
		catch (Exception ex) {
			status = Status.FAILURE;
			// NotificationMgr.NotifyEvent();  - usual stuff goes in here
		}
		
		NpsTrace.info("Exiting " + this.getClass().getSimpleName());
		return status.getCode();
	}

	private void invoke(IA98765_input_t input, OA98765_output_t output) 
			throws NpsException {
		NpsTrace.info("Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());	
		
		// Do some stuff
		String applicantNino = getApplicantNino(input);
		methodA();
		methodB();
		methodC();
		
		if (isPhase2Live(input)) {
			updateDigitallyExcluded(applicantNino, input);
			doSomethingElse(input);
			checkAnotherThing(input);
			ohAndAnotherThing(input);
		}
		
		NpsTrace.info("Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	private void updateDigitallyExcluded(String applicantNino, IA98765_input_t input) 
			throws NpsException {
		NpsTrace.info("Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		if (applicantNino.isEmpty() == false) {
			if (hasContactAgent(input)) {
				setDigitalExcludedFlag(applicantNino, true);
			}
			else if (isAfterInitialApplication(input)) {
				setDigitalExcludedFlag(applicantNino, false);
			}
		}	
		
		NpsTrace.info("Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	private boolean isAfterInitialApplication(IA98765_input_t input) 
			throws NpsException {
		NpsTrace.info("Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());
		SubmissionType st = SubmissionType.fromCode(input.getSubmissionType());
		boolean afterInitialApplication = false;
		
		if (st == SubmissionType.RECONFIRMATION) afterInitialApplication = true;
		if (st == SubmissionType.RECONFIRMATION_RETRY) afterInitialApplication = true;
		if (st == SubmissionType.ADDITIONAL_CHILD) afterInitialApplication = true;
		if (st == SubmissionType.ADDITIONAL_CHILD_RETRY) afterInitialApplication = true;
		
		NpsTrace.info(String.format("isAfterInitialApplication set to {0}. Exiting {1}",  
				afterInitialApplication, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return afterInitialApplication;
	}

	private boolean hasContactAgent(IA98765_input_t input) {
		NpsTrace.info("Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());
		boolean hasContactAgent = false;
		
		if (input.getContactAgentUsername().isEmpty()) hasContactAgent = true;
		if (input.getContactAgentUsername() != null) hasContactAgent = true;
		
		NpsTrace.info(String.format("hasContactAgent set to {0}. Exiting {1}",
				hasContactAgent, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return hasContactAgent;
	}

	private void setDigitalExcludedFlag(String nino, boolean digitallyExcluded) throws NpsException {
		NpsTrace.info("Entered " + Thread.currentThread().getStackTrace()[1].getMethodName()
				+ "with nino " + nino + " and digitallyExcluded " + digitallyExcluded);
		// set up IDHU0065_input and IDHU0065_output then calls DHMU0065_UpdateDigitExcluded()
		// ...
		// ...
		// ...
		
		// Make the call to the external module DHMU0065_UpdateDigitExcluded()
		int status = DHMU0065_UpdateDigitExcluded(nino, digitallyExcluded);
		
		// Instead of returning status codes all the way back up the calling chain, 
		// throw an exception if the status return code indicates it all went wrong.
		if (Status.SUCCESS.getCode() != status) {
			throw new NpsException(status, 
					"Call to DHMU0065_UpdateDigitExcluded() failed in " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
		NpsTrace.info("Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	private boolean isPhase2Live(IA98765_input_t input) {
		NpsTrace.info("Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());
		// pulls the relevant stuff out of refdata in order to decide if phase 2 is live or not
		NpsTrace.info("Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return false;
	}

	private String getApplicantNino(IA98765_input_t input) {
		NpsTrace.info("Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());
		// rattles round some list of adults in a for loop, looking for the first adult that's
		// an applicant. It then pulls out the adult's nino and returns this.
		NpsTrace.info("Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return "";
	}
		
	public enum SubmissionType {
		RECONFIRMATION(0),
		RECONFIRMATION_RETRY(1),
		ADDITIONAL_CHILD(2),
		ADDITIONAL_CHILD_RETRY(3),
		INITIAL_APPLICATION(4),
		INITIAL_APPLICATION_RETRY(5),
		GENERAL_FAILURE(-100);
		
		private final int code;
			
		/**
		 * Constructor to initialise enumerations to int values.
		 * 
		 * @param code - integer value for this enumeration
		 */
		SubmissionType(final int code) {
			this.code = code;
		}

		/**
		 * Get status code.
		 * @return status code as an integer
		 */
		public int getCode() {
			return code;
		}

		public static SubmissionType fromCode(int code) throws NpsException {
			for (SubmissionType type : SubmissionType.values()) {
	            if (type.getCode() == code) {
	                return type;
	            }
	        }
			
			throw new NpsException(Status.OTHER_FAIL, 
					"Unknown submission type in" + 
					Thread.currentThread().getStackTrace()[1].getMethodName() + " code:" + code);
		}
	}	
	
	////////////////////////////////////////////////////////////////////////////////////////
	// THESE WOULD BE IN OTHER MODULES
	////////////////////////////////////////////////////////////////////////////////////////
	private int DHMU0065_UpdateDigitExcluded(String nino, boolean digitallyExcluded) {
		// TODO Auto-generated method stub
		return 5;		// oh! non-zero is bad!
	}
	
	public class OA98765_output_t {

	}
	
	public class IA98765_input_t {
		short submissionType;
		String contactAgentUsername;
	
		public short getSubmissionType() {
			return submissionType;
		}
	
		public String getContactAgentUsername() {
			return contactAgentUsername;
		}	
	}
	////////////////////////////////////////////////////////////////////////////////////////
	
	private void ohAndAnotherThing(IA98765_input_t input) {
		// TODO Auto-generated method stub		
	}

	private void checkAnotherThing(IA98765_input_t input) {
		// TODO Auto-generated method stub
	}

	private void doSomethingElse(IA98765_input_t input) {
		// TODO Auto-generated method stub
	}

	private void methodC() {
		// TODO Auto-generated method stub		
	}

	private void methodB() {
		// TODO Auto-generated method stub
	}

	private void methodA() {
		// TODO Auto-generated method stub
	}
}
