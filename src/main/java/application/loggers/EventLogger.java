package application.loggers;

import application.beans.Event;

public interface EventLogger {
    public void logEvent(Event event);

    public String getName();
}
