package lesson4.beans;

import java.text.DateFormat;
import java.util.Date;

public class Event {
    private int id;
    private String message;
    private Date date;
    private DateFormat df;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", df=" + df +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }
}
