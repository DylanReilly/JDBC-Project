package Network;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ChatClient
{
    private static int count = 0;
    private static Map<Integer, String> myMovies = new TreeMap();
    public static void main(String[] args)
            
    {

        try
        {
            //Setting up connection
            Socket socket = new Socket("localhost", 8080);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            Scanner in = new Scanner(socket.getInputStream());
            Scanner kb = new Scanner(System.in);
            String line = "";
            System.out.println("Welcome to NetflixRipoff!");
            //******************************************************************************************
            while (!line.equals("close"))
            {
                //User Interface
                System.out.println("Use commands: ");
                System.out.println("    Search: search + your movie name.");
                System.out.println("    Add: add + follow onscreen requests.");
                System.out.println("    Remove: remove + your movie name.");
                System.out.println("    Watch: watch + movie name.");
                System.out.println("    Recommend: recommend + director OR genre");
                System.out.println("    View your movies: view.");
                System.out.println("    Modify: modify + follow onscreen instructions.");
                //******************************************************************************************
                System.out.print(":> ");
                line = kb.nextLine();
                //Aux. Functions
                //******************************************************************************************
//                if (line.contains("search"))
//                {
//                    int fBreak = line.indexOf(" ");
//                    String title = line.substring(fBreak + 1);
//                    for (Map.Entry<Integer, String> entry : myMovies.entrySet())
//                    {
//                        if (entry.getValue().contains(title));
//                        {
//                            System.out.println(entry.getValue());
//                            line = "view ";
//                        }
//                    }
//                }
                //******************************************************************************************
                if (line.equalsIgnoreCase("add"))
                {
                    line = addMovie();
                }
                //******************************************************************************************
                if (line.equalsIgnoreCase("view"))
                {
                    for (Map.Entry<Integer, String> entry : myMovies.entrySet())
                    {
                        System.out.println(entry.getValue());
                        line = "view ";
                    }
                }
                //******************************************************************************************
                if (line.equalsIgnoreCase("modify"))
                {
                    line = modifyMovie();
                }
                //******************************************************************************************
                if (line.equalsIgnoreCase("close"))
                {
                    line = "close ";
                    out.print(line + "\n");
                    break;
                }
                //******************************************************************************************
                //Networky bit
                out.print(line + "\n");
                out.flush();
                String s = in.nextLine();
                //******************************************************************************************
                //More Aux. Functions
                if (line.contains("watch "))
                {
                    myMovies.put(count, JSONStringFormat(s));
                    count++;
                }
                //******************************************************************************************
                if (line.contains("recommend "))
                {
                    int fBreak = line.indexOf(" ");
                    String type = line.substring(fBreak + 1);

                }
                //check if string is a json array
                //******************************************************************************************
                if (s.trim().startsWith("["))
                {
                    printTable();
                    JSONArrayFormat(s);

                    String tableLine = "";
                    for (int i = 0; i < 134; i++)
                    {
                        tableLine += "-";
                    }
                    System.out.println(tableLine);
                } 
                else
                {
                    System.out.println(s);
                }
            }
            //******************************************************************************************
            out.close();
            in.close();
            socket.close();
        } 
        catch (IOException ex)
        {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //******************************************************************************************
    //formatting method
    public static String padd(String s, int size)
    {
        while (s.length() < size)
        {
            s += " ";
        }
        return s;
    }

    //******************************************************************************************
    //converts json array to json strings
    public static void JSONArrayFormat(String s)
    {
        //System.out.println("S:"+ s);
        try
        {
            //System.out.println(s);
            StringReader sr = new StringReader(s);
            JsonReader reader = Json.createReader(sr);
            JsonArray arr = reader.readArray();
            for (int i = 0; i < arr.size(); i++)
            {
                JsonObject object = arr.getJsonObject(i);

                String id = object.getJsonString("id").getString();
                String title = object.getJsonString("title").getString();
                String genre = object.getJsonString("genre").getString();
                String director = object.getJsonString("director").getString();
                String runtime = object.getJsonString("runtime").getString();
                String rating = object.getJsonString("rating").getString();

                if (id.length() > 6)
                {
                    id = id.substring(0, 4);
                    id += "...";
                }
                if (title.length() > 30)
                {
                    title = title.substring(0, 27);
                    title += "...";
                }
                if (genre.length() > 50)
                {
                    genre = genre.substring(0, 47);
                    genre += "...";
                }
                if (director.length() > 20)
                {
                    director = director.substring(0, 17);
                    director += "...";
                }
                if (runtime.length() > 10)
                {
                    runtime = runtime.substring(0, 7);
                    runtime += "...";
                }
                if (rating.length() > 10)
                {
                    rating = rating.substring(0, 7);
                    rating += "...";
                }

                String output = "|" + padd(id, 7) + "|" + padd(title, 30) + "|" + padd(genre, 50) + "|" + padd(director, 20) + "|" + padd(runtime, 10) + "|" + padd(rating, 10) + "|";
                //printTable(output);
                System.out.println(output);
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    //******************************************************************************************
    //converts string to json string
    public static String JSONStringFormat(String s)
    {
        String output = null;
        try
        {
            StringReader sr = new StringReader(s);
            JsonReader reader = Json.createReader(sr);
            JsonObject object = reader.readObject();

            String id = object.getJsonString("id").getString();
            String title = object.getJsonString("title").getString();
            String genre = object.getJsonString("genre").getString();
            String director = object.getJsonString("director").getString();
            String runtime = object.getJsonString("runtime").getString();
            String rating = object.getJsonString("rating").getString();

            if (id.length() > 6)
            {
                id = id.substring(0, 4);
                id += "...";
            }
            if (title.length() > 30)
            {
                title = title.substring(0, 27);
                title += "...";
            }
            if (genre.length() > 50)
            {
                genre = genre.substring(0, 47);
                genre += "...";
            }
            if (director.length() > 20)
            {
                director = director.substring(0, 17);
                director += "...";
            }
            if (runtime.length() > 10)
            {
                runtime = runtime.substring(0, 7);
                runtime += "...";
            }
            if (rating.length() > 10)
            {
                rating = rating.substring(0, 7);
                rating += "...";
            }

            output = "|" + padd(id, 7) + "|" + padd(title, 30) + "|" + padd(genre, 50) + "|" + padd(director, 20) + "|" + padd(runtime, 10) + "|" + padd(rating, 10) + "|";
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return output;
    }

    //******************************************************************************************
    //prints table headings
    public static void printTable()
    {
        String tableLine = "";
        for (int i = 0; i < 134; i++)
        {
            tableLine += "-";
        }
        System.out.println("|" + padd("id", 7) + "|" + padd("title", 30) + "|" + padd("genre", 50) + "|" + padd("director", 20) + "|" + padd("runtime", 10) + "|" + padd("rating", 10));
        System.out.println(tableLine);
    }

    //******************************************************************************************
    //aux. add movie
    public static String addMovie()
    {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter Title: ");
        String title = kb.nextLine().trim();

        System.out.println("Enter Genre: ");
        String genre = kb.nextLine().trim();

        System.out.println("Enter Director: ");
        String director = kb.nextLine().trim();

        System.out.println("Enter runtime: ");
        String runtime = kb.nextLine().trim();

        System.out.println("Enter Rating: ");
        String rating = kb.nextLine().trim();

        String returnValue = "add " + "%%" + title + "%%" + genre + "%%" + director + "%%" + runtime + "%%" + rating;
        return returnValue;
    }

    //******************************************************************************************
    //aux.modify movie
    public static String modifyMovie()
    {
        Scanner kb = new Scanner(System.in);
        System.out.println("What movie do you want to edit: ");
        String name = kb.nextLine().trim();
        System.out.println("What attribute do you want to edit: ");
        String attr = kb.nextLine();
        System.out.println("What do you want to replace it with: ");
        String value = kb.nextLine();
        String returnString = "modify " + name + "%%" + attr + "%%" + value;
        return returnString;
    }

    //aux. modify movie(lost the will to live at this point)
    public static String recommend(String type)
    {
        for (Map.Entry<Integer, String> entry : myMovies.entrySet())
        {

        }
        return null;
    }
}
