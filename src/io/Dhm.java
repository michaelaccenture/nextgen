package io;

import common.NpsException;

public interface Dhm {
	public void retrieve(IA94795_input_t input, OA94795_output_t output) throws NpsException;
	public void insert(IA94795_input_t input, OA94795_output_t output) throws NpsException;
	public void update(IA94795_input_t input, OA94795_output_t output) throws NpsException;
	public void delete(IA94795_input_t input, OA94795_output_t output) throws NpsException;
}
