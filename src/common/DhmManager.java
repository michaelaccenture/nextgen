package common;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import io.Dhm;
import io.IA94795_input_t;
import io.OA94795_output_t;

public class DhmManager {
	Map<String, Dhm> map = new HashMap<String, Dhm>();
	
	public int DhmsInvoke(IA94795_input_t input, OA94795_output_t output) {
		NpsTrace.info(String.format("Entered DhmsInvoke() with input:%s and output:%s", input, output));
		Status status = Status.SUCCESS;

		try 
		{
			Dhm dhm;
			DhmAction action = DhmAction.fromCode(input.dhmAction);
			String className = "Dhm" + input.dhmId;
			if (map.containsKey(className))
				dhm = map.get(className);
			else {
				dhm = (Dhm) Class.forName(className).newInstance();
				map.put(className, dhm);
			}
			
			performAction(dhm, action, input, output);
		}  
		catch (ClassNotFoundException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | NpsException e) {
			// set status code and message then swallow so rest of NPS is oblivious to this change 
			status = Status.DHM_FAILURE;
			NpsTrace.error(e.getStackTrace() + e.getMessage());
		}
		finally {
			NpsTrace.info(String.format("Leaving DhmsInvoke() with input:%s and output:%s", input, output));
		}
	
		return status.getCode();
	}	
	
	void performAction(Dhm dhm, DhmAction action, IA94795_input_t input, OA94795_output_t output) 
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
			throw new NpsException(Status.DHM_FAILURE, "Unknown Dhm action " + action);
		}		

		NpsTrace.info(String.format("Exiting performAction() with input:%s and output:%s", input, output));
	}
}

////parameterized constructor
//DhmAction action = DhmAction.fromCode(input.dhmAction);
//String className = "Dhm" + input.dhmId;
//Class<?> cl = Class.forName(className);
//Constructor<?> con = cl.getConstructor(OA94795_output_t.class, IA94795_input_t.class);
//Dhm dhm = (Dhm) con.newInstance(input, output);
