package examplejaxrs;

public abstract class User {
    public String firstname;
    public String lastname;
    public String login;
    public String password;
    public String id_type;
    public String id_number;
    public String address;
    public String mobile_phone;
    public String home_phone;
    public String category;
    
    public User(String firstname, String lastname, String login, String password, String id_type,
                String id_number, String address, String mobile_phone, String home_phone, String category){
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.id_type = id_type;
        this.id_number = id_number;
        this.address = address;
        this.mobile_phone = mobile_phone;
        this.home_phone = home_phone;
        this.category = category;
    }
    
    public User(User user){
        this.firstname = user.firstname;
        this.lastname = user.lastname;
        this.login = user.login;
        this.password = user.password;
        this.id_type = user.id_type;
        this.id_number = user.id_number;
        this.address = user.address;
        this.mobile_phone = user.mobile_phone;
        this.home_phone = user.home_phone;
        this.category = user.category;
    }
    
    @Override
    public String toString() {
        return firstname + lastname + login + password + id_type
                + id_number + address + mobile_phone + home_phone + category;
    }
}
