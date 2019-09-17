package application.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope("prototype")
public class Event {

    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);

    private int id;
    private String msg;

    @Value("#{new java.util.Date()}")
    private Date date;

    @Value("#{T(java.text.DateFormat).getTimeDateInstance()}")
    private DateFormat dateFormat;

    public Event() {
        this.id = AUTO_ID.getAndIncrement();
    }

    public Event(Date date, DateFormat dateFormat) {
        this();
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public static Boolean isDay(int start, int finish){
        LocalTime time = LocalTime.now();
        return time.getHour() > start && time.getHour() < finish;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", msg=" + msg + ", date="
                + (dateFormat != null ? dateFormat.format(date) : date) + "]";
    }

}
