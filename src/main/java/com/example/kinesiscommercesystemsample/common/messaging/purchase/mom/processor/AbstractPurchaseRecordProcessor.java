package com.example.kinesiscommercesystemsample.common.messaging.purchase.mom.processor;

import com.amazonaws.services.kinesis.clientlibrary.exceptions.InvalidStateException;
import com.amazonaws.services.kinesis.clientlibrary.exceptions.ShutdownException;
import com.amazonaws.services.kinesis.clientlibrary.exceptions.ThrottlingException;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessorCheckpointer;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.ShutdownReason;
import com.amazonaws.services.kinesis.model.Record;
import com.example.kinesiscommercesystemsample.common.messaging.KinesisRecordProcessor;
import com.example.kinesiscommercesystemsample.common.messaging.purchase.entity.PurchaseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.List;

@Slf4j
public abstract class AbstractPurchaseRecordProcessor implements KinesisRecordProcessor<PurchaseMessage> {

	private String kinesisShardId;

	@Autowired
	protected ObjectMapper objectMapper;

	@Override
	public void initialize(String shardId) {
		log.info("Initializing record processor for shard: " + shardId);
		this.kinesisShardId = shardId;
	}

	@Override
	public void processRecords(List<Record> records, IRecordProcessorCheckpointer checkpointer) {
		// recordsは、先頭が過去で末尾が未来の順番で格納されている

		if (log.isDebugEnabled()) {
			log.debug("recordsSize={}", records.size());
		}

		StopWatch stopWatch = new StopWatch();
		stopWatch.start("processRecords");

		for (Record record : records) {
			try {
				val message = objectMapper.readValue(record.getData().array(), PurchaseMessage.class);
				processRecord(message);
			} catch (IOException e) {
				log.error("Json deserialization error occured. ", e);
				// TODO : デシリアライズできなかったメッセージはどこかに退避して運用者に通知する仕組みが必要。 (基本、起こらない想定だが。。)
			}
		}

		// TODO : List<Records> の単位でのチェックポイントになるため、内部エラーなどが起こった場合などのロールバックがこの単位と一致していないとマズい。
		// TODO : processRecord()で個別のレコードを処理する際に、処理済みであることを判別できるような仕組みを自前で用意する必要がある。
		checkpoint(checkpointer);

		stopWatch.stop();
		if (log.isDebugEnabled()) {
			log.debug("{}", stopWatch.toString());
		}
	}

	@Override
	public void shutdown(IRecordProcessorCheckpointer checkpointer, ShutdownReason reason) {
		log.info("Shutting down record processor for shard: " + kinesisShardId);
		if (reason == ShutdownReason.TERMINATE) {
			checkpoint(checkpointer);
		}
	}

	@Override
	public void checkpoint(IRecordProcessorCheckpointer checkpointer) {
		log.info("Checkpointing shard " + kinesisShardId);
		try {
			checkpointer.checkpoint();
		} catch (ShutdownException se) {
			log.info("Caught shutdown exception, skipping checkpoint.", se);
		} catch (ThrottlingException e) {
			log.error("Caught throttling exception, skipping checkpoint.", e);
		} catch (InvalidStateException e) {
			log.error("Cannot save checkpoint to the DynamoDB table used by the Amazon Kinesis Client Library.", e);
		}
	}

}
