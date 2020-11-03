package hotel.chain.app;

import hotel.chain.app.constants.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class DBHandler extends DBConfigs {
    private Connection dbConnection;

    public DBHandler() throws ClassNotFoundException, SQLException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        //String url = "jdbc:mysql://localhost/hotel?serverTimezone=Asia/Almaty";
        String url = "jdbc:mysql://" + dbHost + "/" + dbName + "?serverTimezone=Asia/Almaty";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

        dbConnection = DriverManager.getConnection(url, dbLogin, dbPassword);
    }

    public Connection getDbConnection(){
        return dbConnection;
    }

    public void signUpGuest(Guest guest){

        User user = new User(guest);

        String insertUser = "INSERT INTO " + UsersTableColumns.TABLE_NAME + "("
                + UsersTableColumns.FIRSTNAME + ","
                + UsersTableColumns.LASTNAME + ","
                + UsersTableColumns.LOGIN + ","
                + UsersTableColumns.PASSWORD + ","
                + UsersTableColumns.ID_TYPE + ","
                + UsersTableColumns.ID_NUMBER + ","
                + UsersTableColumns.ADDRESS + ","
                + UsersTableColumns.MOBILE_PHONE + ","
                + UsersTableColumns.HOME_PHONE + ")"
                + "VALUES(?,?,?,?,?,?,?,?,?)";

        String selectUser = "SELECT id FROM " + UsersTableColumns.TABLE_NAME + " WHERE "
                + UsersTableColumns.LOGIN + " = ?";

        String insertGuest = "INSERT INTO " + GuestsTableColumns.TABLE_NAME + "("
                + GuestsTableColumns.ID + ")"
                + "VALUES(?)";

        String setGuestCategory = "INSERT INTO " + GuestCategoryRelationships.TABLE_NAME + "("
                + GuestCategoryRelationships.GUESTS_USER_id + ","
                + GuestCategoryRelationships.GUEST_CATEGORIES_id + ")"
                + "VALUES(?,?)";

        try
        {
            PreparedStatement psInsertUser = dbConnection.prepareStatement(insertUser);
                psInsertUser.setString(1, user.firstname);
                psInsertUser.setString(2, user.lastname);
                psInsertUser.setString(3, user.login);
                psInsertUser.setString(4, user.password);
                psInsertUser.setInt(5, user.id_type.getId());
                psInsertUser.setString(6, user.id_number);
                psInsertUser.setString(7, user.address);
                psInsertUser.setString(8, user.mobile_phone);
                psInsertUser.setString(9, user.home_phone);
            psInsertUser.executeUpdate();


            PreparedStatement psSelectUser = dbConnection.prepareStatement(selectUser);
                psSelectUser.setString(1, user.login);
            ResultSet rs = psSelectUser.executeQuery();

            rs.next();
            int userID = rs.getInt(UsersTableColumns.ID);
            int guestID = userID;



            PreparedStatement psInsertGuest = dbConnection.prepareStatement(insertGuest);
                psInsertGuest.setInt(1, guestID);
            psInsertGuest.executeUpdate();


            PreparedStatement psSetGuestCategory = dbConnection.prepareStatement(setGuestCategory);
                psSetGuestCategory.setInt(1, guestID);
                psSetGuestCategory.setInt(2, guest.category.getId());
            psSetGuestCategory.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }







    public User findUserByLogin(String login){

        String selectUser = "SELECT * FROM " + UsersTableColumns.TABLE_NAME + " WHERE "
                + UsersTableColumns.LOGIN + " = ?";

        User user = null;

        try
        {
            PreparedStatement psSelectUser = dbConnection.prepareStatement(selectUser);
                psSelectUser.setString(1, login);
                ResultSet rs = psSelectUser.executeQuery();

                if (rs.next()) {

                    int id_type_int = rs.getInt(UsersTableColumns.ID_TYPE);
                    Id_type id_type;
                    switch (id_type_int) {
                        case 1:
                            id_type = Id_type.US_PASSPORT;
                        case 2:
                            id_type = Id_type.DRIVING_LICENSE;
                        case 3:
                            id_type = Id_type.NOT_PROVIDED;
                        default:
                            id_type = Id_type.NOT_PROVIDED;
                    }

                    user = new User(
                            rs.getString(UsersTableColumns.FIRSTNAME),
                            rs.getString(UsersTableColumns.LASTNAME),
                            rs.getString(UsersTableColumns.LOGIN),
                            rs.getString(UsersTableColumns.PASSWORD),
                            id_type,
                            rs.getString(UsersTableColumns.ID_NUMBER),
                            rs.getString(UsersTableColumns.ADDRESS),
                            rs.getString(UsersTableColumns.MOBILE_PHONE),
                            rs.getString(UsersTableColumns.HOME_PHONE)
                    );
                }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return user;
    }
}
