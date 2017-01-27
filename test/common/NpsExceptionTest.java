package common;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class NpsExceptionTest {

	@Parameter(value = 0)
    public Integer statusCode;
	@Parameter(value = 1)
    public Status status;
	@Parameter(value = 2)
    public String message;

	@Parameters(name = "{index}: test({0}) = {1}")
	public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
        	// Tests the NpsException(int statusCode, String message) constructor
                {	Integer.MIN_VALUE,	null,					"" },
                {	12345,				null,					""	},
                {	-1,					null,					"Test message 1" },
                {	0,					null,					"Test message 2" },
                {	1,					null,					"Test message 3" },
                {	Integer.MAX_VALUE,	null,					"Test message 4" },
            // Tests the NpsException(Status status, String message) constructor
                {	null,				Status.SUCCESS,			"" },
                {	null,				Status.BAM_FAILURE,		""	},
                {	null,				Status.DHM_FAILURE,		"Test message 5" },
                {	null,				Status.FAILURE,			"Test message 6" },
                {	null,				Status.GENERAL_FAILURE,	"Test message 7" },
                {	null,				Status.OTHER_FAIL,		"Test message 8" },
                {	null,				Status.TFC_NOT_LIVE,	"Test message 9" }
        });
    }


	/**
	 * Tests both constructors for the NpsException exception class.
	 */
	@Test
	public void test() {
		// Arrange
		NpsException npsException = null;
		
		// Act
		try {
			if (status != null)
				throw new NpsException(status, message);
			else if (statusCode != null)
				throw new NpsException(statusCode, message);
			else
				fail("Invalid test parameters.");
		}
		catch (NpsException e) {
			npsException = e;
		}
		
		// Assert
		assertTrue(npsException.getMessage().contains(message));
		if (status != null)
			assertEquals(status, npsException.getStatus());
		if (statusCode != null)
			assertEquals(statusCode.intValue(), npsException.getStatusCode());
	}

}
