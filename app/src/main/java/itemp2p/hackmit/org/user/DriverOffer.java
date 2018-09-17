package itemp2p.hackmit.org.user;

import java.io.Serializable;
import java.util.Date;

public class UserRequest implements Serializable {
    public User user;
    public int estimatedArrival;

    public double price;
    public int requestID;

    public static class Driver implements Serializable{
        String name;
        String imgUrl;

        public Driver(String name, String imgUrl) {
            this.name = name;
            this.imgUrl = imgUrl;
        }
    }

    public DriverOffer(Driver driver, int estimatedArrival, double price, int requestID) {
        this.driver = driver;
        this.estimatedArrival = estimatedArrival;
        this.price = price;
        this.requestID = requestID;
    }
}
