package io;

import common.NpsException;
import common.NpsTrace;
import common.Status;

class Dhm4280a implements Dhm {

	private int parm1;
	private short parm2;
	private String parm3;
	
	@Override
	public void update(IA94795_input_t input, OA94795_output_t output) throws NpsException {
		NpsTrace.info(String.format("Entered %s with input:%s and output:%s" + 
				Thread.currentThread().getStackTrace()[1].getMethodName(), input, output));

		if (dataHasChanged(input, output)) {
			updateDb(input, output);
			cacheResults(input, output);
		}
		
		NpsTrace.info(String.format("Exiting %s with input:%s and output:%s" + 
				Thread.currentThread().getStackTrace()[1].getMethodName(), input, output));
	}

	private void updateDb(IA94795_input_t input, OA94795_output_t output) throws NpsException {
		NpsTrace.info(String.format("Entered %s with input:%s and output:%s" + 
				Thread.currentThread().getStackTrace()[1].getMethodName(), input, output));

		// perform update write to db
		// set up IDHU4280_input and IDHU4280_output then calls Dhmu4280ActualDbCall()
		// ...
		// ...
		// ...
		
		// Make the call to the external module Dhmu4280ActualDbCall()
		int status = Dhmu4280ActualDbCall(input.nino, input.xxxx);
		
		// Instead of returning status codes all the way back up the calling chain, 
		// throw an exception if the status return code indicates it all went wrong.
		if (Status.SUCCESS.getCode() != status) {
			throw new NpsException(status, 
						"Call to Dhmu4280ActualDbCall failed in " + 
						Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
		NpsTrace.info(String.format("Exiting %s with input:%s and output:%s" + 
				Thread.currentThread().getStackTrace()[1].getMethodName(), input, output));
	}

	private int Dhmu4280ActualDbCall(int nino, int xxxx) {
		// dummy - this should be call to real method in another model
		return 0;
	}

	@Override
	public void retrieve(IA94795_input_t input, OA94795_output_t output) throws NpsException {
		// Do the DHM read and throw an exception if anything goes wrong
		
		cacheResults(input, output);
	}
	
	@Override
	public void insert(IA94795_input_t input, OA94795_output_t output) throws NpsException {
		// Do the DHM insert and throw an exception if anything goes wrong
		
		cacheResults(input, output);
	}


	@Override
	public void delete(IA94795_input_t input, OA94795_output_t output) throws NpsException {
		// Do the DHM delete and throw an exception if anything goes wrong
		
		clearResults();
	}

	private void clearResults() {
		// Set to whatever the default values are in the db
		parm1 = Integer.MAX_VALUE;
		parm2 = Short.MAX_VALUE;
		parm3 = null;	
	}

	private void cacheResults(IA94795_input_t input, OA94795_output_t output) {
		// cache whatever is in 94795 which is from Dhm4280
		parm1 = output.parm1;
		parm2 = output.parm2;
		parm3 = output.parm3;		
	}
	
	public boolean dataHasChanged(IA94795_input_t input, OA94795_output_t output) {
		boolean changed = false;
		changed |= (parm1 != output.parm1);
		changed |= (parm2 != output.parm2);
		changed |= !(parm3.equals(output.parm3));
		return changed;
	}
}
