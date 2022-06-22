package it.unibz.ddd.model.events;

public class Enrollment extends CourseEvent {
    public Enrollment(String id) {
        super(id);
    }

    @Override
    public boolean isValidToHappenAfter(CourseEvent previousEvent) {
        boolean failedConclusion = (previousEvent instanceof Conclusion) && !((Conclusion)previousEvent).isPassed();

        return previousEvent==null || (previousEvent instanceof Withdraw) || failedConclusion;
    }

    @Override
    public String relatedStatus() {
        return "ENROLLED";
    }
}
