package lesson1;

/*Application troubles -

   hard to modify -> data inside code
   scale is impossible -> Logger is created in single instance
   hard to test - unit test for App.logEvent() method indirectly tests ConsoleEventLogger.logEvent*/
public class App {
    private Client client;
    private ConsoleEventLogger consoleEventLogger;

    private void logEvent(String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
        consoleEventLogger.logEvent(message);
    }

    public static void main(String[] args) {
        App app = new App();
        app.client = new Client("1", "John Smith");
        app.consoleEventLogger = new ConsoleEventLogger();

        app.logEvent("Some event for user 1");
    }


}
