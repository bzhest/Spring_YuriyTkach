package application.loggers;

import application.beans.Event;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

@Component
public class CombinedEventLogger implements EventLogger {

    @Resource(name = "combinedLoggers")
    private Collection<EventLogger> loggers;

    @Override
    public void logEvent(Event event) {
        for (application.loggers.EventLogger eventLogger : loggers) {
            eventLogger.logEvent(event);
        }
    }

}
