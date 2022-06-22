package it.unibz.ddd.model;

import it.unibz.ddd.model.events.CourseEvent;
import it.unibz.ddd.service.RepositoryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//Entity
public class Student {
    private final String id;
    private final String name;
    private final List<PhoneContact> contacts;
    private List<CourseEvent> events;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.contacts = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public void addContact(PhoneContact contact) {
        if (!contacts.contains(contact))
            contacts.add(contact);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PhoneContact> getContacts() {
        return contacts;
    }

    public void addEvent(CourseEvent event) {
        CourseEvent previousEvent = lastEventFor(event.getId());
        if (event.isValidToHappenAfter(previousEvent))
            getEvents().add(event);
        else
            throw new RuntimeException("Invalid event");
    }

    public List<CourseEvent> getEvents() {
        loadEvents();
        return events;
    }

    private void loadEvents() {
        if (events.isEmpty())
            events = RepositoryFactory.fromStudent().getEventsByStudent(id);
    }

    public Set<String> getCourses() {
        return getEvents().parallelStream()
                .map(CourseEvent::getId)
                .collect(Collectors.toSet());
    }

    public String getStatusForCourse(String id) {
        return lastEventFor(id).relatedStatus();
    }

    public CourseEvent lastEventFor(String courseId) {
        return getEvents().parallelStream()
                .filter(event -> courseId.equals(event.getId()))
                .reduce((first, second) -> second)
                .orElse(null);
    }
}
