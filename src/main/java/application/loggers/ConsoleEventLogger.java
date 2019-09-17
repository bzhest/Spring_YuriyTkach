package application.loggers;

import application.beans.Event;
import org.springframework.stereotype.Component;

public class ConsoleEventLogger extends AbstractLogger {

    @Override
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }

}