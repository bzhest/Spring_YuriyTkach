package lesson5.logger;

import lesson5.beans.Event;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event){
        System.out.println(event.toString());;
    }
}
