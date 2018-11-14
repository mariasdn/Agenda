package observer;

import ui.ScheduleItem;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<ScheduleObserver> observers = new ArrayList();

    public void addObserver (ScheduleObserver scheduleObserver) {
        if (! observers.contains(scheduleObserver)) {
            observers.add(scheduleObserver);
        }
    }

    public void notifyObservers (ScheduleItem item) {
        for (ScheduleObserver observer : observers) {
            observer.update(item);
        }
    }
}
