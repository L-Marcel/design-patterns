package app.tasks;

import java.util.List;

import app.persons.Worker;
import app.persons.Workers;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ManagementTask implements Task {
    private Workers workers;

    @Override
    public void execute() {
        List<Worker> workers = this.workers.getWorkers();
        for(int i = 0; i < workers.size(); i++) {
            Worker worker = workers.get(i);
            if(worker.getTask().isEmpty()) {
                System.out.println(
                    worker.getName() + " foi demitido(a)!"
                );
                workers.remove(i);
                i--;
            };
        };
    };
};
