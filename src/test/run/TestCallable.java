package test.run;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.io.FileUtils;

public class TestCallable implements Callable<Boolean>{
	
	private String url;
	private String  name;
	
	public TestCallable(String url, String name) {
		this.url = url;
		this.name = name;
	}



	@Override
	public Boolean call() {
		WebDownLoader webDownLoader = new WebDownLoader();
		webDownLoader.downloaFile(url, name);
		System.out.println("download the file name:" + this.name);
		return true;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		TestCallable t1 = new TestCallable("https://blog.kuangstudy.com/usr/themes/handsome/usr/img/sj/1.jpg","11.jpg");
		TestCallable t2 = new TestCallable("https://blog.kuangstudy.com/usr/themes/handsome/usr/img/sj/2.jpg","22.jpg");
		TestCallable t3 = new TestCallable("https://blog.kuangstudy.com/usr/themes/handsome/usr/img/sj/3.jpg","33.jpg");
		
		//
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		Future<Boolean> future1 = executorService.submit(t1);
		Future<Boolean> future2 = executorService.submit(t2);
		Future<Boolean> future3 = executorService.submit(t3);
		
		Boolean r1 = future1.get();
		Boolean r2 = future2.get();
		Boolean r3 = future3.get();
		
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		
		executorService.shutdownNow();
		
	}
}

class WebDownLoader{
	public void downloaFile(String url,String name) {
		try {
			FileUtils.copyURLToFile(new URL(url), new File(name));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


