package app.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Event implements Runnable {
    private String title;

    public Event(String title) {
        this.title = title;
    };
};
