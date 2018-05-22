package com.example.kinesiscommercesystemsample.common.messaging;

import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessor;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessorCheckpointer;

public interface KinesisRecordProcessor<T extends Message> extends IRecordProcessor {

	void processRecord(T message);

	void checkpoint(IRecordProcessorCheckpointer checkpointer);
}
