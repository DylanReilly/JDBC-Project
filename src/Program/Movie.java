package Program;

import java.util.Objects;

public class Movie
{
    private int id;
    private String title;
    private String genre;
    private String director;
    private String runTime;
    private String plot;
    private String location;
    private String poster;
    private String rating;
    private String format;
    private int year;
    private String starring;
    private int copies;
    private String barcode;
    private String user_rating;

    public Movie(int id, String title, String genre, String director, String runTime, String plot, String location, String poster, String rating, String format, int year, String starring, int copies, String barcode, String user_rating) 
    {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runTime = runTime;
        this.plot = plot;
        this.location = location;
        this.poster = poster;
        this.rating = rating;
        this.format = format;
        this.year = year;
        this.starring = starring;
        this.copies = copies;
        this.barcode = barcode;
        this.user_rating = user_rating;
    }
    
    public Movie(int id, String title, String director, String runTime, String rating)
    {
        this.id = id;
        this.title = title;
        this.director = director;
        this.runTime = runTime;
        this.rating = rating;
    }

    public Movie() 
    {
        
    }
    
    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getGenre() 
    {
        return genre;
    }

    public void setGenre(String genre) 
    {
        this.genre = genre;
    }

    public String getDirector()
    {
        return director;
    }

    public void setDirector(String director) 
    {
        this.director = director;
    }

    public String getRunTime() 
    {
        return runTime;
    }

    public void setRunTime(String runTime) 
    {
        this.runTime = runTime;
    }

    public String getPlot() 
    {
        return plot;
    }

    public void setPlot(String plot) 
    {
        this.plot = plot;
    }

    public String getLocation() 
    {
        return location;
    }

    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getPoster()
    {
        return poster;
    }

    public void setPoster(String poster) 
    {
        this.poster = poster;
    }

    public String getRating() 
    {
        return rating;
    }

    public void setRating(String rating) 
    {
        this.rating = rating;
    }

    public String getFormat() 
    {
        return format;
    }

    public void setFormat(String format) 
    {
        this.format = format;
    }

    public int getYear() 
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public String getStarring() 
    {
        return starring;
    }

    public void setStarring(String starring)
    {
        this.starring = starring;
    }

    public int getCopies() 
    {
        return copies;
    }

    public void setCopies(int copies) 
    {
        this.copies = copies;
    }

    public String getBarcode() 
    {
        return barcode;
    }

    public void setBarcode(String barcode
    ) {
        this.barcode = barcode;
    }

    public String getUser_rating() 
    {
        return user_rating;
    }

    public void setUser_rating(String user_rating) 
    {
        this.user_rating = user_rating;
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 13 * hash + this.id;
        hash = 13 * hash + Objects.hashCode(this.title);
        hash = 13 * hash + Objects.hashCode(this.genre);
        hash = 13 * hash + Objects.hashCode(this.director);
        hash = 13 * hash + Objects.hashCode(this.runTime);
        hash = 13 * hash + Objects.hashCode(this.plot);
        hash = 13 * hash + Objects.hashCode(this.location);
        hash = 13 * hash + Objects.hashCode(this.poster);
        hash = 13 * hash + Objects.hashCode(this.rating);
        hash = 13 * hash + Objects.hashCode(this.format);
        hash = 13 * hash + this.year;
        hash = 13 * hash + Objects.hashCode(this.starring);
        hash = 13 * hash + this.copies;
        hash = 13 * hash + Objects.hashCode(this.barcode);
        hash = 13 * hash + Objects.hashCode(this.user_rating);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final Movie other = (Movie) obj;
        if (this.id != other.id) 
        {
            return false;
        }
        if (this.runTime != other.runTime)
        {
            return false;
        }
        if (this.year != other.year) 
        {
            
            return false;
        }
        if (this.copies != other.copies) 
        {
            return false;
        }
        if (this.barcode != other.barcode)
        {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) 
        {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre))
        {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) 
        {
            return false;
        }
        if (!Objects.equals(this.plot, other.plot))
        {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) 
        {
            return false;
        }
        if (!Objects.equals(this.poster, other.poster)) 
        {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating)) 
        {
            return false;
        }
        if (!Objects.equals(this.format, other.format)) 
        {
            return false;
        }
        if (!Objects.equals(this.starring, other.starring)) 
        {
            return false;
        }
        if (!Objects.equals(this.user_rating, other.user_rating)) 
        {
            return false;
        }
        return true;
    }
    
    public String toJsonString() 
    {
        String json = "{";
        json+= "\"id\":\""+id+"\",";
        json+= "\"title\":\""+title+"\",";
        json+= "\"genre\":\""+genre+"\",";
        json+= "\"director\":\""+director+"\",";
        json+= "\"runtime\":\""+runTime+"\",";
        json+= "\"rating\":\""+rating+"\"";
        json += "}";
        //return "|id= " + padd("" + id, 4) + " |title= " + padd(title, title.length() + 4) + " |genre= " + padd(genre, genre.length()+4) + " |director= " +padd(director, director.length()+4) + " |runTime= " + padd(runTime, runTime.length()+4) + " |location= " + padd(location, location.length()+4) + " |poster= " + poster + " |rating= " + padd(rating, rating.length()+4) + " |format= " + padd(format, format.length()+4) + " |year= " + padd("" + year, 6) + " |starring= " + padd(starring, starring.length()+4) + " |copies= " + padd("" + copies, 6) + " |barcode= " + padd("" + barcode, barcode.length()+4) + " |user_rating= " + padd("" + user_rating, user_rating.length()+4) + '|';
        return json;
    }
}
