package app.tasks;

import app.items.Item;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StorageTask implements Task {
    private Item item;

    @Override
    public void execute() {
        System.out.println(
            this.item.getTitle() + " foi armazenado(a)!"
        );
    };
};
