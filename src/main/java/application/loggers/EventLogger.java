package application.loggers;

import application.beans.Event;

public interface EventLogger {
    void logEvent(Event event);

    public String getName();
}
