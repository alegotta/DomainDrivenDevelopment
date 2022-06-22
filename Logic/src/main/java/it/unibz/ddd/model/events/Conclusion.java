package it.unibz.ddd.model.events;

public class Conclusion extends CourseEvent {
    private final boolean passed;

    public Conclusion(String id, boolean passed) {
        super(id);
        this.passed = passed;
    }

    public boolean isPassed() {
        return passed;
    }

    @Override
    public boolean isValidToHappenAfter(CourseEvent previousEvent) {
        return previousEvent instanceof Enrollment;
    }

    @Override
    public String relatedStatus() {
        return passed ? "PASSED" : "FAILED";
    }
}
