package YoutubeService.Models;

/**
 * Created by fare_ on 29.03.2017..
 */
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
