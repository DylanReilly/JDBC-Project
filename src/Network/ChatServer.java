package Network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer
{

    protected ArrayList<PrintWriter> outs;
    private Map<String, String> users;
    private Map<String, PrintWriter> loggedUsers;

    public static void main(String[] args)
    {
        ChatServer server = new ChatServer();
        server.initialiseServer(8080);
    }

    public void registerUser(String user, PrintWriter out)
    {
        loggedUsers.put(user, out);
    }

    public void returnResults(String username, ArrayList results)
    {
        PrintWriter out = loggedUsers.get(username);
        if (out != null)
        {
            out.println(results + "\n");
            out.flush();
        }
    }

    public String getPassword(String username)
    {
        return users.get(username);
    }

    private void initialiseServer(int i)
    {
        users = new HashMap<>();
        loggedUsers = new HashMap<>();
        users.put("Dylan", "1234");
        users.put("toby", "toby");
        users.put("john", "john");
        users.put("mary", "mary");
        outs = new ArrayList();
        try
        {
            //create socket
            ServerSocket ss = new ServerSocket(i);
            System.out.println("Listening...");
            while (true)
            {
                Socket sock = ss.accept();
                PrintWriter out = new PrintWriter(sock.getOutputStream());
                Scanner in = new Scanner(sock.getInputStream());
                outs.add(out);
                ClientHandler ch = new ClientHandler(in, out, this);
                Thread t = new Thread(ch);
                t.start();
            }
        } 
        catch (IOException ex)
        {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
