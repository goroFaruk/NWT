package MainServices.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Šahin on 25.3.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String message;
    private String objekat;

    public Message(String message, String objekat){
        this.message=message;
        this.objekat=objekat;
    }
    public Message(){

    }

    public String getObjekat() {
        return objekat;
    }

    public String getMessage() {
        return message;
    }

    public void setObjekat(String objekat) {
        this.objekat = objekat;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
