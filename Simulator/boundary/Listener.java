package boundary;

import function.IFunction;

import java.io.*;
import java.net.*;

public class Listener extends Thread {
	IFunction func;
	private static int port;

	public Listener(IFunction func, int port) {
		this.func = func;
		this.port = port;
	}

	@Override
	public void run() {
		ServerSocket server  = null;
	    try {
	        server = new ServerSocket(port);
	    } catch (IOException e) {
	        System.err.println("Kunne ikke lytte på port " + port + ". Husk at terminere tidligere program eller angiv anden lytte port.");
	        System.err.println(e);
	        System.exit(1);
	    }
	    Socket client = null;
	    while(true) {
	    	try {
	    		client = server.accept();
	    	} catch (IOException ie) {
	    		System.out.print("Fangede IO fejl: " + ie.getMessage());
	    		System.exit(1);
	    	}
			// Connection started
    		Thread CC = new Thread(new ClientConnection(client,func));
    		CC.start();
	    }
	}
}
