package application;

import application.beans.Client;
import application.beans.Event;
import application.beans.EventType;
import application.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import application.spring.AppConfig;
import application.spring.LoggerConfig;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class App {

    @Autowired
    private Client client;

    @Value("#{T(application.beans.Event).isDay(8,17) ? "
            + "cacheFileEventLogger : consoleEventLogger }")
    private EventLogger defaultLogger;

    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;

    @Value("#{'Hello user ' + "
            + "( systemProperties['os.name'].contains('Windows') ? "
            + "systemEnvironment['USERNAME'] : systemEnvironment['USER'] ) + "
            + "'. Default logger is ' + app.defaultLogger.name }")
    private String startupMessage;

    public App() {
    }

    public App(Client client, EventLogger defaultLogger,
               Map<EventType, EventLogger> loggersMap) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggersMap;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class, LoggerConfig.class);
        ctx.scan("application");
        ctx.refresh();

        App app = (App) ctx.getBean("app");

        Client client = ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        ctx.close();
    }

    private void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }

    public EventLogger getDefaultLogger() {
        return defaultLogger;
    }

}
