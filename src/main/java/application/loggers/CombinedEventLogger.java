package application.loggers;

import application.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;

@Component
public class CombinedEventLogger extends AbstractLogger {

    @Resource(name = "combinedLoggers")
    private Collection<EventLogger> loggers;

    @Override
    public void logEvent(Event event) {
        for (application.loggers.EventLogger eventLogger : loggers) {
            eventLogger.logEvent(event);
        }
    }

    public Collection<EventLogger> getLoggers() {
        return Collections.unmodifiableCollection(loggers);
    }

    @Value("#{'Combined ' + combinedEventLogger.loggers.![name].toString()}")
    @Override
    protected void setName(String name) {
        this.name = name;
    }
}
