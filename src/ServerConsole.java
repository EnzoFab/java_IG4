import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.ChatIF;


public class ServerConsole implements ChatIF {
	
	private EchoServer server;
	
	final public static int DEFAULT_PORT = 5555;
	
	public ServerConsole(int port){
		server = new EchoServer(port, this);
	}
	
	private void accept(){// permet de saisir au clavier du coté serveur  
		  new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub

			    try
			    {
			      BufferedReader fromConsole = 
			        new BufferedReader(new InputStreamReader(System.in));
			      String message;

			      while (true) 
			      {
			        message = fromConsole.readLine();
			        server.handleMessageFromUI(message);
			      }
			    } 
			    catch (Exception ex) 
			    {
			      System.out.println
			        ("Unexpected error while reading from console!");
			    }
			}
			  
		  }).start();
		  
		  try {
			server.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR - Could not listen for clients!");
		}
	  }
	
	
	
	@Override
	public void display(String message) {
		// TODO Auto-generated method stub
		System.out.println("Server MSG>");
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   int port = 0; //Port to listen on

		    try
		    {
		      port = Integer.parseInt(args[0]); //Get port from command line
		    }
		    catch(Throwable t)
		    {
		      port = DEFAULT_PORT; //Set port to 5555
		    }
			
		    ServerConsole sv = new ServerConsole(port);
		    
		    sv.accept();
	}
	



}
