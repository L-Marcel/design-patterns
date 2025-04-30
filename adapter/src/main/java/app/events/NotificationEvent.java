package app.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationEvent extends Event {
    private String description;

    public NotificationEvent(String title, String description) {
        super(title);
        this.description = description;
    };

    @Override
    public void run() {
        System.out.println(this.getTitle() + " -> " + this.getDescription());
    };
};
