package it.cnr.isti.framework.producerConsumer.examples;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.cnr.isti.framework.producerConsumer.AbstractConsumer;
import it.cnr.isti.framework.producerConsumer.Buffer;
import it.cnr.isti.framework.producerConsumer.Data;

public class SimpleFileConsumer extends AbstractConsumer<File> {

		public SimpleFileConsumer(Buffer<File> buffer) {
			super(buffer);
		}

		@Override
		protected void consume(Data<File> data) {
			logger.info("consuming " + data.getID());
			logger.info("file path: " + data.getContent().getPath());
		}
		
	}

