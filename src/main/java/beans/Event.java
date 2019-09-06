package beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {

    private static final AtomicInteger AUTI_ID = new AtomicInteger(0);
    private int id;
    private String message;
    private Date date;
    private DateFormat df;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + df.format(date) +
                ", df=" + df +
                '}';
    }

    public Event(Date date, DateFormat df) {
        this.id = AUTI_ID.getAndIncrement();
        this.date = date;
        this.df = df;
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

}
