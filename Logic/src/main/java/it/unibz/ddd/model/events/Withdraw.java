package it.unibz.ddd.model.events;

public class Withdraw extends CourseEvent {
    public Withdraw(String id) {
        super(id);
    }

    @Override
    public boolean isValidToHappenAfter(CourseEvent previousEvent) {
        return previousEvent instanceof Enrollment;
    }

    @Override
    public String relatedStatus() {
        return "WITHDRAW";
    }
}
