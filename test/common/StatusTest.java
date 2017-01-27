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
public class StatusTest {

	@Parameter(value = 0)
    public Integer code;
	@Parameter(value = 1)
    public Status expStatus;

	@Parameters(name = "{index}: testFromCode({0}) = {1}")
	public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
        	// Tests the fromCode(int statusCode). Where statusCode not known enumeration, returns GENERAL_FAILURE
                {	Integer.MIN_VALUE,	Status.GENERAL_FAILURE },
                {	12345,				Status.GENERAL_FAILURE },
                {	-1,					Status.OTHER_FAIL },
                {	0,					Status.SUCCESS },
                {	1,					Status.FAILURE },
                {	Integer.MAX_VALUE,	Status.GENERAL_FAILURE },
        });
    }
	
	@Test
	public void testFromCode() {
		// Arrange
		
		// Act
		Status status = Status.fromCode(code);
		
		// Assert
		assertEquals(expStatus, status);
	}

}
