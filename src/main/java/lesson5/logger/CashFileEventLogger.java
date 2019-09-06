package lesson5.logger;

import lesson5.beans.Event;

import java.util.ArrayList;
import java.util.List;

public class CashFileEventLogger extends FileEventLogger {

    private int cashSize;
    private List<Event> cache;

    public CashFileEventLogger(String fileName, int cashSize) {
        super(fileName);
        this.cashSize = cashSize;
        this.cache = new ArrayList<Event>(cashSize);
    }

    private void writeEventsFromCache() {
        cache.stream().forEach(super::logEvent);
    }

    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cashSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }
}
