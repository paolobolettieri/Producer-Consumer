/*******************************************************************************
 * Copyright (c) 2016, Paolo Bolettieri (NeMIS Lab., ISTI-CNR, Italy)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met: 
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer. 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/


package it.cnr.isti.framework.producerConsumer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import it.cnr.isti.framework.producerConsumer.impl.FileProducer;
import it.cnr.isti.framework.producerConsumer.impl.FileProducerParameters;

public class TestMain {
	
	private static final File SRC_DIR = new File("src/test/resources/files");
	private static final int NUM_CONSUMERS = 4;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting Producer Consumer Example ");
		
		Buffer<File> buffer = new Buffer<File>(500, true);
		AbstractProducer<File> producer = new FileProducer(SRC_DIR, buffer, new FileProducerParameters());
		producer.start();
		
		List<ConsumerExample<File>> consumers = new ArrayList<ConsumerExample<File>>();
		
		for (int i = 0; i < NUM_CONSUMERS; i++) {
			consumers.add(new ConsumerExample<File>(producer.getBuffer()));
			consumers.get(i).start();
		}

		for (int i = 0; i < NUM_CONSUMERS; i++) {
			consumers.get(i).join();
		}
		
		System.out.println("Example Finished!");

	}

}