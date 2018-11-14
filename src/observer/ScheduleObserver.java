package observer;

import ui.ScheduleItem;

public interface ScheduleObserver {
    void update (ScheduleItem item);
}
