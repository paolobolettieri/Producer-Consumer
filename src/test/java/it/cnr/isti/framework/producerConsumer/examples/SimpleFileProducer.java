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


package it.cnr.isti.framework.producerConsumer.examples;


import it.cnr.isti.framework.producerConsumer.AbstractProducer;
import it.cnr.isti.framework.producerConsumer.Buffer;
import it.cnr.isti.framework.producerConsumer.Data;

import java.io.File;

public class SimpleFileProducer extends AbstractProducer<File> {

	private File srcFolder;

	public SimpleFileProducer(File srcFolder,
			Buffer<File> buffer) {
		super(buffer);
		this.srcFolder = srcFolder;
	}

	@Override
	public void produce() {
		logger.info("scanning: " + srcFolder.getPath());

		File[] files = srcFolder.listFiles();

		for (int index = 0; index < files.length; index++) {
			try {
				buffer.put(new Data<File>(files[index], files[index].getName()));
			} catch (InterruptedException e) {
				logger.error("Error putting file " + files[index]);
				e.printStackTrace();
			}		
		}
	}
}