package tfc;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;


@RunWith(value = Parameterized.class)
public class BA94535Test {
	
	@Parameter(value = 0)
    public short testPhase2LiveCode;
    @Parameter(value = 1)
    public String testAgent;
    @Parameter(value = 2)
    public short submissionType;
    @Parameter(value = 3)
    public short[] claimRoles;
    @Parameter(value = 4)
    public String[] ninos;
    @Parameter(value = 5)
    public long expectedStatus;
    @Parameter(value = 6)
    public boolean expUpdate;
    @Parameter(value = 7)
    public String expNino;
    @Parameter(value = 8)
    public short expDigitallyExcluded;
    @Parameter(value = 9)
    public String[] expectedLogEntries;


    @Parameters(name = "{index}: testInvoke({0}) = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
        	// Test all the different submission types with valid credentials
                {	BA94535.TRUE,
                	"Agent Kann", 
                	BA94535.TFCSUBTP_ADDITIONAL_CHILD, 
                	new short[]{BA94535.TFCCROLE_APPLICANT}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	true,
                	"AA000013",
                	BA94535.TRUE,
                	new String[]{}
                },
                {	BA94535.TRUE,
                	"Agent Kann", 
                	BA94535.TFCSUBTP_ADDITIONAL_CHILD_RETRY, 
                	new short[]{BA94535.TFCCROLE_APPLICANT}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	true,
                	"AA000013",
                	BA94535.TRUE,
                	new String[]{}
                },
                {	BA94535.TRUE,
                	"Agent Kann", 
                	BA94535.TFCSUBTP_RECONFIRMATION, 
                	new short[]{BA94535.TFCCROLE_APPLICANT}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	true,
                	"AA000013",
                	BA94535.TRUE,
                	new String[]{}
                },
                {	BA94535.TRUE,
                	"Agent Kann", 
                	BA94535.TFCSUBTP_RECONFIRMATION_RETRY, 
                	new short[]{BA94535.TFCCROLE_APPLICANT}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	true,
                	"AA000013",
                	BA94535.TRUE,
                	new String[]{}
                },
                {	BA94535.TRUE,
                	"Agent Kann", 
                	BA94535.TFCSUBTP_INITIAL_APPLICATION, 
                	new short[]{BA94535.TFCCROLE_APPLICANT}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	false,
                	"",
                	BA94535.FALSE,
                	new String[]{}
                },
                {	BA94535.TRUE,
                	"Agent Kann", 
                	BA94535.TFCSUBTP_INITIAL_APPLICATION_RETRY, 
                	new short[]{BA94535.TFCCROLE_APPLICANT}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	false,
                	"",
                	BA94535.FALSE,
                	new String[]{}
                },
                // Not live
                {	BA94535.FALSE,	
                	"Agent Kann",
                	BA94535.TFCSUBTP_ADDITIONAL_CHILD, 
                	new short[] {BA94535.TFCCROLE_APPLICANT}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	false,
                	"",
                	BA94535.FALSE,
                	new String[]{}
                },
                // No applicant in list
                {	BA94535.TRUE,	
                	"Agent Kann",
                	BA94535.TFCSUBTP_ADDITIONAL_CHILD, 
                	new short[] {BA94535.TFCCROLE_PARTNER}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	false,
                	"",
                	BA94535.FALSE,
                	new String[]{}
                },
                // No applicant in list
                {	BA94535.TRUE,	
                	"Agent Kann",
                	BA94535.TFCSUBTP_ADDITIONAL_CHILD, 
                	new short[] {BA94535.TFCCROLE_PARTNER, BA94535.TFCCROLE_OTHER}, 
                	new String[]{"AA000013", "AA000113"},
                	BA94535.SUCCESS,
                	false,
                	"",
                	BA94535.FALSE,
                	new String[]{}
                },
            	// Test all the different submission types with no contact agent
                {	BA94535.TRUE,
                	"", 
                	BA94535.TFCSUBTP_ADDITIONAL_CHILD, 
                	new short[]{BA94535.TFCCROLE_APPLICANT}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	true,
                	"AA000013",
                	BA94535.TRUE,
                	new String[]{}
                },
                {	BA94535.TRUE,
                	"", 
                	BA94535.TFCSUBTP_ADDITIONAL_CHILD_RETRY, 
                	new short[]{BA94535.TFCCROLE_APPLICANT}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	true,
                	"AA000013",
                	BA94535.TRUE,
                	new String[]{}
                },
                {	BA94535.TRUE,
                	"", 
                	BA94535.TFCSUBTP_RECONFIRMATION, 
                	new short[]{BA94535.TFCCROLE_APPLICANT}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	true,
                	"AA000013",
                	BA94535.TRUE,
                	new String[]{}
                },
                {	BA94535.TRUE,
                	"", 
                	BA94535.TFCSUBTP_RECONFIRMATION_RETRY, 
                	new short[]{BA94535.TFCCROLE_APPLICANT}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	true,
                	"AA000013",
                	BA94535.TRUE,
                	new String[]{}
                },
                {	BA94535.TRUE,
                	"", 
                	BA94535.TFCSUBTP_INITIAL_APPLICATION, 
                	new short[]{BA94535.TFCCROLE_APPLICANT}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	false,
                	"",
                	BA94535.TRUE,
                	new String[]{}
                },
                {	BA94535.TRUE,
                	"", 
                	BA94535.TFCSUBTP_INITIAL_APPLICATION_RETRY, 
                	new short[]{BA94535.TFCCROLE_APPLICANT}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	false,
                	"",
                	BA94535.FALSE,
                	new String[]{}
                },
                // return a failure status code !!!!
                {	BA94535.TRUE,
                	"Agent Kann", 
                	BA94535.TFCSUBTP_ADDITIONAL_CHILD, 
                	new short[]{BA94535.TFCCROLE_APPLICANT}, 
                	new String[]{"AA000013"},
                	BA94535.SUCCESS,
                	true,
                	"AA000013",
                	BA94535.TRUE,
                	new String[]{}
                },
        });
    }

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInvoke() {
		// Arrange
		TestableBA94535 bam = new TestableBA94535(testPhase2LiveCode);
		BA94535.OA94535_output_t output = bam.new OA94535_output_t();
		BA94535.IA94535_input_t input = bam.new IA94535_input_t();
		input.TFCCLAIM_tfc_claim.contact_agent_username = bam.new ts_string_t(testAgent);
		input.TFCCLAIM_tfc_claim.submission_type = submissionType;
		for (int i = 0; i < ninos.length; i++) {
			BA94535.TFCADT adult = bam.new TFCADT();
			adult.claim_role = claimRoles[i];
			adult.PERSDTL_pers_detls.input_nino_string = bam.new ts_string_t(ninos[i]);
			input.TFCCLAIM_tfc_claim.addAdult(adult);
		}
		
		// Act
		long status = bam.invoke(input, output);
		
		// Assert
		assertEquals(expectedStatus, status);
		if (expUpdate) {
			assertEquals(expDigitallyExcluded, bam.iDHU0065_input.digitally_excluded);
			assertEquals(expNino, bam.iDHU0065_input.nino.toString());
		}

//		for (int i = 0; i < expectedLogEntries.length; i++)
//			assertThat(bam.trace, containsString(expectedLogEntries[i]));
//		if (expectedLogEntries.length == 0)
//			assertEquals("", bam.trace);
	}
	
	class TestableBA94535 extends BA94535 {
		public IDHU0065_input_t iDHU0065_input;
		
		public TestableBA94535(short phase_2_live_code_ind) {
			super(phase_2_live_code_ind);
		}
		
		@Override
		public short DHMU0065_UpdateDigitExcluded(IDHU0065_input_t iDHU0065_input, ODHU0065_output_t oDHU0065_output) {
			this.iDHU0065_input = iDHU0065_input;
			return 0;
		}
	}
}
