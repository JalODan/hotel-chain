package hotel.chain.app.entities;

import java.util.ArrayList;
import java.util.Collections;

public class Hotel {
    public int id;
    public String name;
    public String address;
    public String city;
    public String description;
    public ArrayList<RoomType> roomTypes;
    public ArrayList<Season> seasons;

    public Hotel(int id, String name, String address, String city, String description,
                 ArrayList<RoomType> roomTypes){
                //, ArrayList<Season> seasons){
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.description = description;
        this.roomTypes = new ArrayList<>(roomTypes);
        //this.seasons = new ArrayList<>(seasons);
    }


    public Hotel(int id, String name, String address, String city, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.description = description;
    }

    public Hotel(){
        id = 0;
        name = "empty";
        address = "empty";
        city = "empty";
        description = "empty";
        roomTypes = new ArrayList<>();
    }

    @Override
    public String toString()
    {
        return "id: " + id +
                ", name: " + name +
                ", address: " + address +
                ", city: " + city +
                ", description: " + description +
                ", roomTypes: " + roomTypes.toString();

    }

    public RoomType getRoomTypeByName(String roomTypeName)
    {
        RoomType res = new RoomType();
        for (RoomType roomType : roomTypes)
        {
            if (roomTypeName.equals(roomType.name))
            {
                res = roomType;
            }
        }

        return res;
    }
}