package it.cnr.isti.framework.producerConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.cnr.isti.framework.producerConsumer.AbstractConsumer;
import it.cnr.isti.framework.producerConsumer.Buffer;
import it.cnr.isti.framework.producerConsumer.Data;

public class ConsumerExample<T> extends AbstractConsumer<T> {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerExample.class);

		public ConsumerExample(Buffer<T> buffer) {
			super(buffer);
		}

		@Override
		protected void consume(Data<T> data) {
			logger.info("consuming " + data.getID());
		}
		
	}

