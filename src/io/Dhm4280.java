package io;

import common.NpsException;
import common.NpsTrace;
import common.Status;

class Dhm4280 implements Dhm {
	
	@Override
	public void update(IA94795_input_t input, OA94795_output_t output) throws NpsException {
		NpsTrace.info(String.format("Entered %s with input:%s and output:%s" + 
				Thread.currentThread().getStackTrace()[1].getMethodName(), input, output));
		
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
	
	@Override
	public void retrieve(IA94795_input_t input, OA94795_output_t output) throws NpsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(IA94795_input_t input, OA94795_output_t output) throws NpsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(IA94795_input_t input, OA94795_output_t output) throws NpsException {
		// TODO Auto-generated method stub
		
	}

	private int Dhmu4280ActualDbCall(int nino, int xxxx) {
		// TODO Auto-generated method stub
		return 0;
	}
}
