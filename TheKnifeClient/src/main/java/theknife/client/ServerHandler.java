package theknife.client;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import simple.socket.SocketUtils;

/**
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public class ServerHandler implements Runnable
{
    //TODO creare connessione con db, scrivere query, login
    private Socket socket;
    private Thread receiverThread;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }
    
    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException ex) {
            System.err.println("Errore nella chiusura del socket: " + ex.getMessage());
        }
        receiverThread.interrupt();
        System.out.println("Client closed.");
    }
    
    public void sendCmd(String cmd) {
        try {
            SocketUtils.send(socket, cmd);
        } catch (IOException ex) {
            System.err.println("Errore nell'invio del comando: " + ex.getMessage());
        }
    }
    
    public <T extends Serializable> void sendData(T thing) {
        try {
            SocketUtils.send(socket, thing);
        } catch (IOException ex) {
            System.err.println("Errore nell'invio dell'oggetto: " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        receiverThread = new Thread(() -> {
            /*try {
                String msg;

                while((msg = SocketUtils.receive(socket)) != null){
                    
                    if(msg.equals("quit")){
                        System.out.println("Server shutting down...");
                        socket.close();
                        break;
                    }

                    System.out.println(msg);
                }

            } catch(IOException e){
                System.out.println("Disconnected from server");
            }*/

        });
        receiverThread.setDaemon(true);
        receiverThread.start();
        
    }
}
