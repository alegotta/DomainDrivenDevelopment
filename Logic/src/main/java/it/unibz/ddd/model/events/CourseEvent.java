package it.unibz.ddd.model.events;

public abstract class CourseEvent {
    private final String id;

    public CourseEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract boolean isValidToHappenAfter(CourseEvent previousEvent);
    public abstract String relatedStatus();
}
