package hotel.chain.app.controllers.bookings;

import java.util.Date;

public class Season {

    public int id;
    public String season_name;
    public float priceFactor;
    public Date starts;
    public Date ends;

    Season(int id, String season_name, float priceFactor, Date starts, Date ends){
        this.id = id;
        this.season_name = season_name;
        this.priceFactor = priceFactor;
        this.starts = starts;
        this.ends = ends;
    }

}


