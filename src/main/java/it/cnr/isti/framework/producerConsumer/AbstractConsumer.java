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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author paolo
 *
 * @param <T>
 */
public abstract class AbstractConsumer<T> extends Thread {

	private Buffer<T> buffer;
		
	protected Logger logger = LoggerFactory.getLogger(AbstractConsumer.class);

	/**
	 * @param buffer
	 */
	public AbstractConsumer(Buffer<T> buffer) {
		this.buffer = buffer;
	}
	
	
	/**
	 * 
	 */
	protected void open() {
		logger.debug("open consumer");
	}

	/**
	 * 
	 */
	protected void close() {
		logger.debug("close consumer");
	}
	
	/**
	 * @param data
	 */
	protected abstract void consume(Data<T> data);
	
	public Buffer<T> getBuffer() {
		return buffer;
	}

	@Override
	public void run() {
		open();
		try {
			while (true) {
				Data<T> data = buffer.take();
				if (data.isOver()) {
					buffer.put(data);
					break;
				}
				consume(data);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			close();
		}
	}

}