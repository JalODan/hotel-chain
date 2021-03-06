package hotel.chain.app.controllers.profile;

import com.google.gson.Gson;
import hotel.chain.app.constants.bookings.BookingState;
import hotel.chain.app.database.BookingDBHandler;
import hotel.chain.app.database.ProfileDBHandler;
import hotel.chain.app.entities.BookingForProfile;
import hotel.chain.app.entities.Schedule;
import hotel.chain.app.roles.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/profile")
public class ProfileService {

    @POST
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editProfile(String request) {

        ProfileEditRequest parser = new ProfileEditRequest(request);
        ProfileDBHandler db = new ProfileDBHandler();
        db.editProfile(parser);
        db.closeConnection();

        return Response.ok().build();
    }

    @POST
    @Path("/getBookings")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookings(String request) {

        ProfileGetBookingsRequest parser = new ProfileGetBookingsRequest(request);
        int guestId = parser.getGuestId();
        BookingState bookingState = parser.getBookingState();

        BookingDBHandler db = new BookingDBHandler();
        ArrayList<BookingForProfile> bookings = db.getBookingsOfAGuest(guestId, bookingState);
        db.closeConnection();

        Gson gson = new Gson();

        return Response.ok(gson.toJson(bookings)).build();
    }

    @POST
    @Path("/modifyBooking")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyBooking(String request) {


        return Response.ok().build();
    }

    @POST
    @Path("/cancelBooking")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelBooking(String request) {

        BookingDBHandler db = new BookingDBHandler();
        db.cancelBooking(new ProfileCancelBookingRequest(request).getBookingID());
        db.closeConnection();
        return Response.ok().build();
    }


    @POST
    @Path("/setSchedule")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setSchedule (String request) {

        ScheduleSetRequest parser = new ScheduleSetRequest(request);
        ProfileDBHandler db = new ProfileDBHandler();
        db.setSchedule(parser);
        db.closeConnection();
        return Response.ok().build();
    }

    @POST
    @Path("/getSchedule")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSchedule (String request) {

        ScheduleGetRequest parser = new ScheduleGetRequest(request);
        ProfileDBHandler db = new ProfileDBHandler();
        Schedule schedule = db.getSchedule(parser);
        return Response.ok(new Gson().toJson(schedule)).build();
    }


}
