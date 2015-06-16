import boundary.Menu;
import boundary.Listener;
import entity.Entity;
import entity.IEntity;
import function.Function;
import function.IFunction;


public class Main {

	public static void main(String[] args) {
		
		int listenPort = 8000;
		if (args.length > 0) {
		    try {
		    	listenPort = Integer.parseInt(args[0]);
		    } catch (NumberFormatException e) {
		        System.err.println("Fejl i argument. Argument angiver portnummeret der skal lyttes på og skal være en integer.");
		        System.exit(1);
		    }
		}
		
		IEntity data = new Entity();
		IFunction func = new Function(data);
		Listener list = new Listener(func, listenPort);
		Menu menu = new Menu(func);
		func.setBoundary(menu);
		

		list.start();
		menu.run();
	}

}
