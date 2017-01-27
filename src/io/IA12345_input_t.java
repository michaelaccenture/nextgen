package io;

import java.util.List;
import java.util.ArrayList;

public class IA12345_input_t {
	
	int nino;
	int uniqueClaimId;
	
	@Override
	public String toString() {
		return String.format("NINO:%d ClaimId:%d", nino, uniqueClaimId);
	}

	public int getNino() {
		return nino;
	}

	public int getUniqueClaimId() {
		return uniqueClaimId;
	}
	
	// should be real claims
	public List<String> getClaims() {
		return new ArrayList<String>();
	}
	
	public List<AdultPotentialResponse> getChildPotentialResponses() {
		return new ArrayList<AdultPotentialResponse>();
	}

	public List<AdultPotentialResponse> getAdultPotentialResponses() {
		return new ArrayList<AdultPotentialResponse>();
	}
	
	public class AdultPotentialResponse {
		public List<AdultPotentialResponseDetail> details = new ArrayList<>();
		
		public List<AdultPotentialResponseDetail> getDetails() {
			return details;
		}
	}
	
	public class AdultPotentialResponseDetail {
		public int getNino() {
			return nino;
		}

		int nino;
		short responseType;
		String responseCode;
		
		public short getResponseType() {
			return responseType;
		}
		
		public String getResponseCode() {
			return responseCode;
		}
	}
	
	
}

