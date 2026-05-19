package theknife.server;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import simple.socket.*;

/**
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public class ClientHandler implements Runnable 
{
    private Socket clientSocket;
    private Connection dbConnection;

    public ClientHandler(Socket socket, Connection dbConnection) 
    {
      this.clientSocket = socket;
      this.dbConnection = dbConnection;
    }
    
    public ClientHandler(Connection dbConnection) 
    {
      this.dbConnection = dbConnection;
    }

    @Override
    public void run() 
    {
      
    }
}