package common;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import common.NpsTrace.Log;

@RunWith(value = Parameterized.class)
public class NpsTraceTest {

	@Parameter(value = 0)
    public String message;
	@Parameter(value = 1)
    public NpsTrace.Log loggingLevel;

	@Parameters(name = "{index}: test({0}) = {1}")
	public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {	"test message 1",	Log.ALL},
                {	"test message 2",	Log.DEBUG},
                {	"test message 3",	Log.INFO},
                {	"test message 4",	Log.WARN},
                {	"test message 5",	Log.ERROR},
                {	"test message 6",	Log.FATAL},
                {	"test message 7",	Log.TRACE},
                {	"test message 8",	Log.OFF}
        });
    }
	
	@Before
	public void setUp() throws Exception {
		NpsTrace.clearLastLogMessage();
	}
	
	/**
	 * Test only TRACE log messages make it to the log.
	 */
	@Test
	public void testTrace() {
		// Arrange
		NpsTrace.setLevel(loggingLevel);
		boolean expWrittenToLog = (loggingLevel == Log.TRACE) && (loggingLevel != Log.OFF) ? true : false;
		
		// Act
		NpsTrace.trace(message);
		
		// Assert
		if (expWrittenToLog)
			assertTrue(NpsTrace.getLastLogMessage().contains(message));
		else
			assertFalse(NpsTrace.getLastLogMessage().contains(message));
	}

	/**
	 * Test all log messages make it to the log.
	 */
	@Test
	public void testDebug() {
		// Arrange
		NpsTrace.setLevel(loggingLevel);
		boolean expWrittenToLog = (loggingLevel == Log.OFF) || (loggingLevel == Log.ALL) ? false : true;
		
		// Act
		NpsTrace.debug(message);
		
		// Assert
		if (expWrittenToLog)
			assertTrue(NpsTrace.getLastLogMessage().contains(message));
		else
			assertFalse(NpsTrace.getLastLogMessage().contains(message));
	}

	/**
	 * Test all log messages except DEBUG make it to the log.
	 */
	@Test
	public void testInfo() {
		// Arrange
		NpsTrace.setLevel(loggingLevel);
		boolean expWrittenToLog = ((loggingLevel == Log.OFF) || (loggingLevel == Log.ALL) || (loggingLevel == Log.DEBUG)) 
				? false : true;
		
		// Act
		NpsTrace.info(message);
		
		// Assert
		if (expWrittenToLog)
			assertTrue(NpsTrace.getLastLogMessage().contains(message));
		else
			assertFalse(NpsTrace.getLastLogMessage().contains(message));
	}

	/**
	 * Test all log messages except DEBUG and INFO make it to the log. 
	 */
	@Test
	public void testWarn() {
		// Arrange
		NpsTrace.setLevel(loggingLevel);
		boolean expWrittenToLog = (loggingLevel == Log.FATAL) || (loggingLevel == Log.TRACE) || 
				(loggingLevel == Log.ERROR) || (loggingLevel == Log.WARN) ? true : false;
		
		// Act
		NpsTrace.warn(message);
		
		// Assert
		if (expWrittenToLog)
			assertTrue(NpsTrace.getLastLogMessage().contains(message));
		else
			assertFalse(NpsTrace.getLastLogMessage().contains(message));
	}

	/**
	 * Test only ERROR and FATAL messages make it to the log.
	 */
	@Test
	public void testError() {
		// Arrange
		NpsTrace.setLevel(loggingLevel);
		boolean expWrittenToLog = (loggingLevel == Log.FATAL) || (loggingLevel == Log.TRACE) || 
				(loggingLevel == Log.ERROR) ? true : false;
		
		// Act
		NpsTrace.error(message);
		
		// Assert
		if (expWrittenToLog)
			assertTrue(NpsTrace.getLastLogMessage().contains(message));
		else
			assertFalse(NpsTrace.getLastLogMessage().contains(message));
	}

	/**
	 * Test only FATAL messages make it to the log.
	 */
	@Test
	public void testFatal() {
		// Arrange
		NpsTrace.setLevel(loggingLevel);
		boolean expWrittenToLog = (loggingLevel == Log.FATAL) || (loggingLevel == Log.TRACE)? 
				true : false;
		
		// Act
		NpsTrace.fatal(message);
		
		// Assert
		if (expWrittenToLog)
			assertTrue(NpsTrace.getLastLogMessage().contains(message));
		else
			assertFalse(NpsTrace.getLastLogMessage().contains(message));
	}

}
