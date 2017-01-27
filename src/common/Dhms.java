package common;
import io.Dhm;
import io.IA94795_input_t;
import io.OA94795_output_t;

public class Dhms {
	
	public static int DhmsInvoke(IA94795_input_t input, OA94795_output_t output) {
		NpsTrace.info(String.format("Entered DhmsInvoke() with input:%s and output:%s", input, output));
		Status status = Status.SUCCESS;

		try {
			DhmAction action = DhmAction.fromCode(input.dhmAction);
			String className = "Dhm" + input.dhmId;
			Dhm dhm = (Dhm) Class.forName(className).newInstance();
			
			performAction(dhm, action, input, output);
		} 
		catch (ClassNotFoundException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | NpsException e) {
			// set status code and message then log & swallow so rest of NPS is oblivious to this change 
			status = Status.DHM_FAILURE;
			NpsTrace.error(e.getStackTrace() + e.getMessage());
		}
		finally {
			NpsTrace.info(String.format("Leaving DhmsInvoke() with input:%s and output:%s", input, output));
		}
		
		return status.getCode();
	}	

	public static int populate(IA94795_input_t input, OA94795_output_t output, String... dhmIds) {
		NpsTrace.info(String.format("Entered populate() with input:%s and output:%s", input, output));
		Status status = Status.SUCCESS;

		try {
			DhmAction action = DhmAction.fromCode(input.dhmAction);
			
			for(String dhmId : dhmIds){
				String className = "Dhm" + dhmId;
				Dhm dhm = (Dhm) Class.forName(className).newInstance();
			
				performAction(dhm, action, input, output);
			}
		} 
		catch (ClassNotFoundException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | NpsException e) {
			// set status code and message then log & swallow so rest of NPS is oblivious to this change 
			status = Status.DHM_FAILURE;
			NpsTrace.error(e.getStackTrace() + e.getMessage());
		}
		finally {
			NpsTrace.info(String.format("Leaving DhmsInvoke() with input:%s and output:%s", input, output));
		}
		
		return status.getCode();
	}	

	static void performAction(Dhm dhm, DhmAction action, IA94795_input_t input, OA94795_output_t output) 
			throws NpsException {
		NpsTrace.info(String.format("Entered performAction() with input:%s and output:%s", input, output));
		
		switch (action) {
		case Insert: 
			dhm.insert(input, output); 
			break;
		case Update:
			dhm.update(input, output);
			break;
		case Delete:
			dhm.delete(input, output);
			break;
		case Retrieve:
			dhm.retrieve(input, output); 
			break;
		default:
			throw new NpsException(Status.DHM_FAILURE, "Unrecognised Dhm action " + action);
		}	
		
		NpsTrace.info(String.format("Exiting performAction() with input:%s and output:%s", input, output));
	}
}
