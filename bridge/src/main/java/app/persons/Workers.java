package app.persons;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Workers {
    private List<Worker> workers;

    public Workers() {
        this.workers = new LinkedList<Worker>();
    };

    public void work() {
        for(Worker worker : this.workers) {
            worker.work();
            System.out.println("");
        };
    };

    public void add(Worker worker) {
        this.workers.add(worker);
    };
};
