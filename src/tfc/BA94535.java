package tfc;

import java.util.Arrays;

public class BA94535 {	
	String trace = new String();
	short phase_2_live_code_ind;
	
	public BA94535(short phase_2_live_code_ind) 
	{
		this.phase_2_live_code_ind = phase_2_live_code_ind;
	}
	
	public long invoke(IA94535_input_t IA94535_input, OA94535_output_t OA94535_output) 
	{
		long status = SUCCESS;
		if (TRUE == phase_2_live_code_ind)
		{
			ts_string_t applicant_nino = new ts_string_t();
			applicant_nino.SetNull();
			TRACE_CLASS("Entered contact agent...");
			
			for (int i = 0; i < IA94535_input.TFCCLAIM_tfc_claim.LTFCADT_list.entries(); i++)
			{
				TRACE_CLASS("Loop adult positions...");
				if (TFCCROLE_APPLICANT == IA94535_input.TFCCLAIM_tfc_claim.LTFCADT_list.TFCADT_tfc_adult[i].claim_role)
				{
					TRACE_CLASS("Applicant found");
					applicant_nino = IA94535_input.TFCCLAIM_tfc_claim.LTFCADT_list.TFCADT_tfc_adult[i].PERSDTL_pers_detls.input_nino_string;
					TRACE_CLASS("Breaking from for loop - applicant found");
					break;
				}
			}
			
			if (!applicant_nino.IsNull())
			{
				TRACE_CLASS("Applicant nino found - attempting to update DE status");
				boolean has_contact_agent = (!IA94535_input.TFCCLAIM_tfc_claim.contact_agent_username.IsNull() ||
						NIRS_STRING_NULL != IA94535_input.TFCCLAIM_tfc_claim.contact_agent_username);
				boolean is_recon_or_add_child = (TFCSUBTP_RECONFIRMATION == IA94535_input.TFCCLAIM_tfc_claim.submission_type) ||
						(TFCSUBTP_RECONFIRMATION_RETRY == IA94535_input.TFCCLAIM_tfc_claim.submission_type) ||
						(TFCSUBTP_ADDITIONAL_CHILD == IA94535_input.TFCCLAIM_tfc_claim.submission_type) ||
						(TFCSUBTP_ADDITIONAL_CHILD_RETRY == IA94535_input.TFCCLAIM_tfc_claim.submission_type);
						
				if (has_contact_agent)
				{
					status = BA94535_UpdateDigitalFlag(applicant_nino, TRUE);
					if (SUCCESS != status)
					{
						TRACE_CLASS("DE setting failed, returning status");
						return (status);
					}
					TRACE_CLASS("Updated DE successfully");
				}
				else if (is_recon_or_add_child)
				{
					status = BA94535_UpdateDigitalFlag(applicant_nino, FALSE);
					if (SUCCESS != status)
					{
						TRACE_CLASS("DE setting failed, returning status");
						return (status);
					}
					TRACE_CLASS("Updated DE successfully");					
				}
				else
				{
					TRACE_CLASS("DE flag doesn't need updating");
				}
			}
		}		
		
		return status;
	}
	
	private long BA94535_UpdateDigitalFlag(ts_string_t nino, short digitally_excluded) {
		short status;
		TRACE_CLASS("Entered BA94535_UpdateDigitalFlag" + nino + " and DE " + digitally_excluded);
		IDHU0065_input_t IDHU0065_input = new IDHU0065_input_t();
		ODHU0065_output_t ODHU0065_output = new ODHU0065_output_t();
		
		IDHU0065_input.nino = nino;
		IDHU0065_input.digitally_excluded = digitally_excluded;
		IDHU0065_input.LEDG_EQ_ledger_section.lower_ledger = LEDGER(IDHU0065_input.nino);
		
		TRACE_CLASS("Calling DHMU0065_UpdateDigitExcluded with input " + IDHU0065_input);
		status = DHMU0065_UpdateDigitExcluded(IDHU0065_input, ODHU0065_output);
		TRACE_CLASS("Returned from DHMU0065_UpdateDigitExcluded");
		if (SUCCESS != status)
		{
			TRACE_CLASS("TS_DEBUG_LV1 ******** DHMU0065 Failed ***********");
			TRACE_CLASS("Should be a TRACE_RTN");
			return (status);
		}
		
		TRACE_CLASS("Updated DE successfully");					
		return SUCCESS;
	}

	public short DHMU0065_UpdateDigitExcluded(IDHU0065_input_t iDHU0065_input, ODHU0065_output_t oDHU0065_output) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void TRACE_CLASS(String log)
	{
		trace += log;
	}

	public short LEDGER(ts_string_t nino)
	{
		return 0;
	}
	////////////////////////////////////////////////////////////////////////////////////////
	public static short TRUE = 1;
	public static short FALSE = 0;
	public static long SUCCESS = 0;
	public static short TFCCROLE_APPLICANT = 10;
	public static short TFCCROLE_PARTNER = 11;
	public static short TFCCROLE_OTHER = 12;
	public static short TFCSUBTP_RECONFIRMATION = 1;
	public static short TFCSUBTP_RECONFIRMATION_RETRY = 2;
	public static short TFCSUBTP_ADDITIONAL_CHILD = 3;
	public static short TFCSUBTP_ADDITIONAL_CHILD_RETRY = 4;	
	public static short TFCSUBTP_INITIAL_APPLICATION = 5;
	public static short TFCSUBTP_INITIAL_APPLICATION_RETRY = 6;
	public ts_string_t NIRS_STRING_NULL = new ts_string_t("");

	public class OA94535_output_t {

	}
	public class IA94535_input_t {
		TFCCLAIM TFCCLAIM_tfc_claim = new TFCCLAIM();
	}
	// IA94535_input.TFCCLAIM_tfc_claim.LTFCADT_list.TFCADT_tfc_adult[i].entries()
	public class TFCCLAIM {
		short submission_type;
		ts_string_t contact_agent_username = new ts_string_t();	
		TFCADT_LIST LTFCADT_list = new TFCADT_LIST();
		void addAdult(TFCADT adult) {
			LTFCADT_list.addAdult(adult);
		}
	}
	public class TFCADT_LIST {
		TFCADT[] TFCADT_tfc_adult = null;
		int entries() {
			if (TFCADT_tfc_adult != null)
				return TFCADT_tfc_adult.length;
			else
				return 0;
		}
		void addAdult(TFCADT adult) {
			if (TFCADT_tfc_adult == null) {
				TFCADT_tfc_adult = new TFCADT[1];
				TFCADT_tfc_adult[0] = adult;
			}
			else {
				TFCADT_tfc_adult = Arrays.copyOf(TFCADT_tfc_adult, TFCADT_tfc_adult.length + 1);
				TFCADT_tfc_adult[TFCADT_tfc_adult.length - 1] = adult;
			}
		}
	}
	public class TFCADT {
		short claim_role;
		PERSDTL PERSDTL_pers_detls = new PERSDTL();
	}
	public class PERSDTL {
		ts_string_t input_nino_string;
	}
	public class IDHU0065_input_t {
		ts_string_t nino;
		short digitally_excluded;
		LedgerSection LEDG_EQ_ledger_section = new LedgerSection();
	}
	public class LedgerSection {
		public short lower_ledger;
	}
	public class ODHU0065_output_t {

	}

	public class ts_string_t {
		public String str;
		
		public ts_string_t(String str) {
			this.str = str;
		}
		public ts_string_t() {
			this.str = null;
		}
		public void SetNull() {
			this.str = null;
			
		}
		public boolean IsNull() {
			if ((str == null) || str.isEmpty())
				return true;
			else
				return false;
		}
		
		@Override
		public String toString() {
			return str;
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////

}
