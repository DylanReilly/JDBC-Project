package Program;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DAO 
{
    private static final String SERVER = "localhost";
    private static final int PORT = 3306;
    private static final String DATABASE = "movies";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static String connectionString;
    private static int count = 0;
    protected static ArrayList<Movie> movieList= new ArrayList<>();
    public DAO()
    {
        connectionString = "jdbc:mysql://"+SERVER+":"+PORT+"/"+DATABASE;
    }
    
    public ArrayList searchMovies(String title) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        ArrayList<Movie> movies = new ArrayList<>();
        Connection con = DriverManager.getConnection(connectionString, USERNAME, PASSWORD);
        PreparedStatement stmt = con.prepareStatement("select * from Movies where title like ?");
        stmt.setString(1, "%"+ title +"%");
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next())
        {
            Movie movie = new Movie();
            movie.setId(rs.getInt("id"));
            movie.setTitle(rs.getString("title"));
            movie.setGenre(rs.getString("genre"));
            movie.setDirector(rs.getString("director"));
            movie.setRunTime(rs.getString("runtime"));
            movie.setPlot(rs.getString("plot"));
            movie.setLocation(rs.getString("location"));
            movie.setPoster(rs.getString("poster"));
            movie.setRating(rs.getString("rating"));
            movie.setFormat(rs.getString("format"));
            movie.setYear(rs.getInt("year"));
            movie.setStarring(rs.getString("starring"));
            movie.setCopies(rs.getInt("copies"));
            movie.setBarcode(rs.getString("barcode"));
            movie.setUser_rating(rs.getString("user_rating"));
            movies.add(movie);
        }
        con.close();
        return movies;
    }
    
    public void removeMovie(String object) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(connectionString, USERNAME, PASSWORD);
        PreparedStatement stmt = con.prepareStatement("delete from Movies where title = ?");
        stmt.setString(1, object);
        stmt.executeUpdate();
        con.close();
    }
    
     public String addMovie(String values) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(connectionString, USERNAME, PASSWORD);
        PreparedStatement stmt = con.prepareStatement("insert into Movies(title, genre, director, runtime, rating) values(?,?,?,?,?)");
        StringTokenizer st = new StringTokenizer(values, "%%");
        
        String title = st.nextToken();
        stmt.setString(1, title);
        
        String genre = st.nextToken();
        stmt.setString(2, genre);
        
        String director = st.nextToken();
        stmt.setString(3, director);
        
        String runtime = st.nextToken();
        stmt.setString(4, runtime);
        
        String rating = st.nextToken();
        stmt.setString(5, rating);
        
        stmt.executeUpdate();
        con.close();
        
        return title;
    }
    
    public Movie watchMovie(String movieName) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(connectionString, USERNAME, PASSWORD);
        PreparedStatement stmt = con.prepareStatement("select * from Movies where title = ?");
        stmt.setString(1, movieName.trim());
        ResultSet rs = stmt.executeQuery();
        Movie movie = null;
        while(rs.next())
        {
            movie = new Movie();
            movie.setId(rs.getInt("id"));
            movie.setTitle(rs.getString("title"));
            movie.setGenre(rs.getString("genre"));
            movie.setDirector(rs.getString("director"));
            movie.setRunTime(rs.getString("runtime"));
            movie.setPlot(rs.getString("plot"));
            movie.setLocation(rs.getString("location"));
            movie.setPoster(rs.getString("poster"));
            movie.setRating(rs.getString("rating"));
            movie.setFormat(rs.getString("format"));
            movie.setYear(rs.getInt("year"));
            movie.setStarring(rs.getString("starring"));
            movie.setCopies(rs.getInt("copies"));
            movie.setBarcode(rs.getString("barcode"));
            movie.setUser_rating(rs.getString("user_rating"));
        }
        con.close();
        return movie;
    }
    
    public void modifyMovie(String values) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(connectionString, USERNAME, PASSWORD);
        StringTokenizer st = new StringTokenizer(values, "%%");
        String movie = st.nextToken();
        String attr = st.nextToken();
        String value = st.nextToken();
        PreparedStatement stmt = con.prepareStatement("update Movies set " + attr + "= ? where title = ?");
        stmt.setString(1, value);
        stmt.setString(2, movie);
        stmt.executeUpdate();
        con.close();
    }
}
