# Producer-Consumer

A Simple Producer-Consumer Java Framework

## Sample Usage

###Simple File Producer

```java
public class FileProducer extends AbstractProducer<File> {

	private File srcFolder;

	public FileProducer(File srcFolder,
			Buffer<File> buffer) {
		super(buffer);
		this.srcFolder = srcFolder;
	}

	@Override
	public void produce() {
		File[] files = srcFolder.listFiles();
		for (int index = 0; index < files.length; index++) {
			buffer.put(new Data<File>(files[index], files[index].getName()));	
		}
	}
}
```

###Simple File Consumer
```java
public class FileConsumer extends AbstractConsumer<File> {

	public FileConsumer(Buffer<File> buffer) {
		super(buffer);
	}

	@Override
	protected void consume(Data<File> data) {
		System.out.println("consuming " + data.getID());
		System.out.println("file path: " + data.getContent().getPath());
	}
}
```
###Main Class
```java
public class Main {
	
	public static void main(String[] args) {		
		AbstractProducer<File> producer = new FileProducer(new File("folder"), new Buffer<File>(50, true));
		producer.start();
		
		List<AbstractConsumer<File>> consumers = new ArrayList<AbstractConsumer<File>>();
		
		for (int i = 0; i < 4; i++) {
			consumers.add(new FileConsumer<File>(producer.getBuffer()));
			consumers.get(i).start();
		}
	}
}
```
