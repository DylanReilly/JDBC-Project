package Network;

import java.io.PrintWriter;
import java.util.Scanner;
import Program.DAO;
import Program.Movie;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable
{

    private final Scanner in;
    private final ChatServer server;
    private final PrintWriter out;
    DAO dao = new DAO();

    public ClientHandler(Scanner anIn, PrintWriter out, ChatServer cs)
    {

        in = anIn;
        server = cs;
        this.out = out;
    }

    @Override
    public void run()
    {
        String msg;
        while ((msg = in.nextLine()) != null)
        {
            int fBreak = msg.indexOf(" ");
            String fWord = msg.substring(0, fBreak);
            //login NOT FUNCTIONING
            if (fWord.equalsIgnoreCase("login"))
            {
                int sBreak = msg.indexOf(" ", fBreak + 1);
                String username = msg.substring(fBreak + 1, sBreak);
                String pass = msg.substring(sBreak + 1);
                String correctPassword = server.getPassword(username);
                if (pass.equalsIgnoreCase(correctPassword))
                {
                    server.registerUser(username, out);
                }
            } 
            //******************************************************************************************
            //search function
            else if (fWord.equalsIgnoreCase("search"))
            {
                try
                {
                    String movieName = msg.substring(fBreak + 1);
                    ArrayList<Movie> searchReturn = new ArrayList(dao.searchMovies(movieName));
                    String json = "[";
                    boolean isFirst = true;
                    for (Movie m : searchReturn)
                    {
                        if (!isFirst)
                        {
                            json += ",";
                        }
                        json += m.toJsonString();
                        isFirst = false;
                    }
                    json += "]";
                    out.println(json);
                    out.flush();

                } 
                catch (ClassNotFoundException | SQLException ex)
                {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            //******************************************************************************************
            //remove function
            else if (fWord.equalsIgnoreCase("remove"))
            {
                try
                {
                    String movieName = msg.substring(fBreak + 1);
                    dao.removeMovie(movieName);
                    out.println(movieName + " removed.");
                    out.flush();

                } catch (ClassNotFoundException | SQLException ex)
                {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            //******************************************************************************************
            //add function
            else if (fWord.equalsIgnoreCase("add"))
            {
                try
                {
                    String values = msg.substring(fBreak + 1);
                    String title = dao.addMovie(values);
                    out.println(title + " added.");
                    out.flush();

                } 
                catch (ClassNotFoundException | SQLException ex)
                {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            //******************************************************************************************
            //modify function
            else if (fWord.equalsIgnoreCase("modify"))
            {
                try
                {
                    String values = msg.substring(fBreak + 1);
                    dao.modifyMovie(values);
                    out.println("Movie modified.");
                    out.flush();

                } 
                catch (ClassNotFoundException | SQLException ex)
                {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            //******************************************************************************************
            //watch function
            else if (fWord.equalsIgnoreCase("watch"))
            {
                try
                {
                    String movieName = msg.substring(fBreak + 1);
                    Movie movie = dao.watchMovie(movieName);
                    String json = "";
                    json += movie.toJsonString();
                    out.println(json);
                    out.flush();
                } 
                catch (ClassNotFoundException | SQLException ex)
                {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            else
            {
                out.print("\n");
                out.flush();
            }
            //******************************************************************************************

        }
        System.out.println("Program Terminated");
    }
}
