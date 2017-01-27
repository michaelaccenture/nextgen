package jam;

import java.math.BigDecimal;
import java.util.List;

import common.NpsException;
import common.NpsTrace;
import common.Status;
import io.CDO123;
import io.CDO411;
import io.IA12345_input_t;
import io.IA12345_input_t.AdultPotentialResponse;
import io.IA12345_input_t.AdultPotentialResponseDetail;

public class JA12345 {
//	/////////////////////////////////////////////
//	// These should be enumerations
//	private static int LIVECODE_TFC_PHASE2 = 12;
//	private static int GREEN = 1;
//	private static int DUMMY_NINO = Integer.MAX_VALUE;
//	//////////////////////////////////////////////
//			
//	public int jni_invoke(IA12345_input_t input, OA12345_output_t output) {
//		NpsTrace.debug(2, "Entered " + this.getClass().getSimpleName());
//		Status status = Status.SUCCESS;
//		
//		try {
//			invoke(input, output);
//		}
//		catch (NpsException ex) {
//			status = ex.getStatus();
//			NpsTrace.error(ex.getMessage());
//		}
//		catch (Exception ex) {
//			status = Status.FAILURE;
//			// NotificationMgr.NotifyEvent();  - usual stuff goes in here
//		}
//		
//		NpsTrace.debug(2, "Exiting " + this.getClass().getSimpleName());
//		return status.getCode();
//	}
//	
//	private void invoke(IA12345_input_t input, OA12345_output_t output) 
//			throws NpsException {
//		NpsTrace.debug(1, "Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());
//		
//		// Applicant applicant = new TfcPerson(input);
//		CDO411 history = getHistoryTable(input.getNino(), input.getUniqueClaimId());
//		performAggregation(input, history);
//		
//		NpsTrace.debug(1, "Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
//	}
//
//	private CDO411 getHistoryTable(int nino, int uniqueClaimId) throws NpsException {
//		NpsTrace.debug(1, "Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());
//		int status = DHMR4112_GetLatestClaim();
//		
//		if (Status.SUCCESS.getCode() != status) {
//			throw new NpsException(status, 
//					"description of error in " + Thread.currentThread().getStackTrace()[1].getMethodName()
//					+ "with nino " + nino + " and claimId " + uniqueClaimId);
//		}
//		
//		NpsTrace.debug(1, "Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
//		return new CDO411();				// simulate data read from latest claim
//	}
//
//
//	private CDO123 performAggregation(IA12345_input_t input, CDO411 history) throws NpsException {
//		NpsTrace.debug(1, "Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());
//		
//		int scheme = getApplicationScheme(input.getClaims());
//		String exclusionList = getExclusionList();
//		int highestAdultResponse = getHighestAdultResponse(input.getAdultPotentialResponses(), "");
//		int highestAdultDfeResponse = getHighestAdultResponse(input.getAdultPotentialResponses(), exclusionList);
//		
//		if (isTfcOrDfe(scheme)) {							// should be scheme.isTfcOrDfe() OO-style
//			int highestChildResponse = getHighestChildResponse(input.getChildPotentialResponses());			
//		}
//		
//		NpsTrace.debug(1, "Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
//		return new CDO123();		// simulate return data
//	}
//	
//	private int getApplicationScheme(List<String> claims) {
//		NpsTrace.debug(1, "Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());
//		int applicationScheme = 0;
//		
//		for (String claim: claims) {
//			// do whatever needs to be done here to set applicationScheme from claims
//		}
//			
//		NpsTrace.debug(1, "Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
//		return applicationScheme;			
//	}
//		
//	// All this belongs in the class AdultPotentialResponse *** NOT HERE ***
//	private int getHighestAdultResponse(List<AdultPotentialResponse> adultPotentialResponses, String exclusionList)
//			throws NpsException {
//		NpsTrace.debug(1, "Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());
//		NpsTrace.debug(1, "Exclusion list:" + exclusionList);
//		int highestAdultResponse = 0;
//
//		for (AdultPotentialResponse response : adultPotentialResponses) {
//			for (AdultPotentialResponseDetail detail : response.getDetails()) {
//				if (detail.getResponseType() > highestAdultResponse) {
//					if (exclusionList.contains(detail.getResponseCode()) == false) {
//						highestAdultResponse = detail.getResponseType();
//						NpsTrace.debug(1, "Upping highestAdultResponse to " + highestAdultResponse);
//					}
//					else {
//						NpsTrace.debug(1, "highestAdultResponse unchanged as " + detail.getResponseCode() + "in exclusion list");
//					}
//				}
//			}
//		}
//		
//		NpsTrace.debug(1, "Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
//		return highestAdultResponse;
//	}
//
//	private int getHighestChildResponse(List<AdultPotentialResponse> childPotentialResponses) 
//			throws NpsException {
//		NpsTrace.debug(1, "Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());
//		int highestChildResponse = 0;
//		boolean greenChild = true;
//		checkCodeLive(LIVECODE_TFC_PHASE2);
//		
//		for (AdultPotentialResponse response: childPotentialResponses) {
//			for (AdultPotentialResponseDetail detail: response.getDetails()) {
//				if (detail.getResponseType() == GREEN) {
//					String applicantNino = getApplicantNino(input);
//					
//					if (DUMMY_NINO != detail.getNino()) {
//						NpsTrace.debug(1, "Non dummy nino " + detail.getNino());
//						boolean rivalClaim = checkCompetingClaims(input, OA80143_output);
//						
//						if (rivalClaim) {
//							NpsTrace.debug(1, "Rival claims found");
//							for (RivalClaim rival: OA80143_output.getRivalClaims()) {
//								generateTfcWorkItem();
//								
//								if (input.override_ind() == false) {
//									highestChildResponse = detail.getResponseType();
//									NpsTrace.debug(1, "Set highest child response to " + highestChildResponse);
//									greenChild = false;
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		
//		if (true == greenChild) {
//			checkCrnIsNeeded(input.claimList, highestChildResponse, greenChild);
//		}
//		
//		if (highestChildResponse == RED) {
//			checkRaisedChildIndicators();
//		}
//		else if (highestChildResponse <= AMBER) {
//			BigDecimal topUpAmount = calculateTopUp();
//			CDO414 cdo414 = new CDO414(nino, claimId, childId, epStart, epEnd, topUpLevel, topUpAmout, scheme);
//			insertTfcResponse(cdo414);
//		}
//		
//		NpsTrace.debug(1, "Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
//	}
//
//	private boolean isTfcOrDfe(int scheme) {
//		// should check against TFC and DFE code to return true/false
//		return false;
//	}
//	
//	private String getExclusionList() throws NpsException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	private void generateTfcWorkItem() throws NpsException {
//		NpsTrace.debug(1, "Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());
//		
//		int status = BA94657_GenTFCWorkItem();
//		
//		if (Status.SUCCESS != Status.fromCode(status)) {
//			throw new NpsException(Status.BAM_FAILURE, 
//					"description of error in " + Thread.currentThread().getStackTrace()[1].getMethodName()
//					+ "with data blah blah blah");
//		}
//		
//		NpsTrace.debug(1, "Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
//	}
//
//	private String getApplicantNino(IA12345_input_t input) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	private void checkCodeLive(int tfcLive) throws NpsException {
//		NpsTrace.debug(1, "Entered " + Thread.currentThread().getStackTrace()[1].getMethodName());
//		
//		if (tfcLive <= 0) {
//			throw new NpsException(Status.TFC_NOT_LIVE, 
//					"exiting JAM as TFC code not live yet " + Thread.currentThread().getStackTrace()[1].getMethodName());
//		}
//		
//		NpsTrace.debug(1, "Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
//	}
//
//	// This would normally be a call to another module
//	private int BA94657_GenTFCWorkItem() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	// This would normally be a call to another module
//	private int DHMR4112_GetLatestClaim() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
}
