import beans.*;
import logger.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*Specific logger is specified in spring.xml in:
<bean id="app" class="App">
<constructor-arg ref="client" />
<constructor-arg ref="eventLogger" />
</bean>
to change on FileEventLogger we can use:
<bean id="app" class="App">
<constructor-arg ref="client" />
<constructor-arg ref="fileEventLogger" />
</bean>

*/
public class App {
    private Client client;

    private EventLogger eventLogger;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event event = (Event) ctx.getBean("event");
        app.logEvent(event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 2");

        ctx.close();
    }

    public App(Client client, EventLogger eventLogger) {
        super();
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMessage(message);
        eventLogger.logEvent(event);
    }


}
