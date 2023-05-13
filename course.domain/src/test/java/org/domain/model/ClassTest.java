package org.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClassTest {

    private final String courseCode = "MAT-1";

    private final String classTitle = "Class Title";

    private final String classRecurrence = "MONDAY";

    private final String classStartTime = "10:00";

    private final String classEndTime = "12:00";

    private final Integer classDuration = 120;

    @Test
    public void testCreateValidClass() {

        ClassFactory classFactory = new ClassFactory();
        Class aClass = classFactory.createClass(courseCode, classTitle, classRecurrence, classStartTime, classDuration, classEndTime);

        assertNotNull(aClass);
        assertEquals(classTitle, aClass.title().value());
        assertEquals(classRecurrence, aClass.recurrence().dayOfWeek().toString());
        assertEquals(classStartTime, aClass.startTime().valueStartTime());
        assertEquals(classEndTime, aClass.endTime().valueEndTime());
        assertEquals(classDuration, aClass.duration());
    }

    @Test
    public void testCreateInvalidClassWithNullClassTitle() {

        ClassFactory classFactory = new ClassFactory();
        assertThrows(NullPointerException.class,
                () -> classFactory.createClass(courseCode, null, classRecurrence, classStartTime, classDuration, classEndTime));
    }

    @Test
    public void testCreateInvalidClassWithNullClassRecurrence() {

        ClassFactory classFactory = new ClassFactory();
        assertThrows(NullPointerException.class,
                () -> classFactory.createClass(courseCode, classTitle, null, classStartTime, classDuration, classEndTime));
    }

    @Test
    public void testCreateInvalidClassWithNullClassStartTime() {

        ClassFactory classFactory = new ClassFactory();
        assertThrows(NullPointerException.class,
                () -> classFactory.createClass(courseCode, classTitle, classRecurrence, null, classDuration, classEndTime));
    }

    @Test
    public void testCreateInvalidClassWithNullClassDuration() {

        ClassFactory classFactory = new ClassFactory();
        assertThrows(NullPointerException.class,
                () -> classFactory.createClass(courseCode, classTitle, classRecurrence, classStartTime, null, classEndTime));
    }

    @Test
    public void testCreateInvalidClassWithNullClassEndTime() {

        ClassFactory classFactory = new ClassFactory();
        assertThrows(NullPointerException.class,
                () -> classFactory.createClass(courseCode, classTitle, classRecurrence, classStartTime, classDuration, null));
    }

    @Test
    public void testCreateInvalidClassWithInvalidClassStartTime() {

        ClassFactory classFactory = new ClassFactory();
        assertThrows(IllegalArgumentException.class,
                () -> classFactory.createClass(courseCode, classTitle, classRecurrence, "05:00", classDuration, classEndTime));
    }

    @Test
    public void testCreateInvalidClassWithInvalidClassEndTime() {

        ClassFactory classFactory = new ClassFactory();
        assertThrows(IllegalArgumentException.class,
                () -> classFactory.createClass(courseCode, classTitle, classRecurrence, classStartTime, classDuration, "05:00"));
    }

    @Test
    public void testCreateInvalidClassWithInvalidClassStartTimeAndEndTime() {

        ClassFactory classFactory = new ClassFactory();
        assertThrows(IllegalArgumentException.class,
                () -> classFactory.createClass(courseCode, classTitle, classRecurrence, "05:00", classDuration, "05:00"));
    }

    @Test
    public void testCreateInvalidClassWithInvalidClassStartTimeAndDuration() {

        ClassFactory classFactory = new ClassFactory();
        assertThrows(IllegalArgumentException.class,
                () -> classFactory.createClass(courseCode, classTitle, classRecurrence, "05:00", 1000, classEndTime));
    }

    @Test
    public void testCreateInvalidClassWithInvalidClassDurationAndEndTime() {

        ClassFactory classFactory = new ClassFactory();
        assertThrows(IllegalArgumentException.class,
                () -> classFactory.createClass(courseCode, classTitle, classRecurrence, classStartTime, 1000, "05:00"));
    }

    @Test
    public void testCreateInvalidClassWithInvalidClassStartTimeAndDurationAndEndTime() {

        ClassFactory classFactory = new ClassFactory();
        assertThrows(IllegalArgumentException.class,
                () -> classFactory.createClass(courseCode, classTitle, classRecurrence, "05:00", 1000, "05:00"));
    }

    @Test
    public void testCompareTwoEqualClasses() {

        ClassFactory classFactory = new ClassFactory();
        Class aClass = classFactory.createClass(courseCode, classTitle, classRecurrence, classStartTime, classDuration, classEndTime);
        Class anotherClass = classFactory.createClass(courseCode, classTitle, classRecurrence, classStartTime, classDuration, classEndTime);

        assertEquals(aClass, anotherClass);
    }

    @Test
    public void testCompareTwoDifferentClasses() {

        ClassFactory classFactory = new ClassFactory();
        Class aClass = classFactory.createClass(courseCode, classTitle, classRecurrence, classStartTime, classDuration, classEndTime);
        Class anotherClass = classFactory.createClass(courseCode, classTitle, classRecurrence, classStartTime, classDuration, "13:00");

        assertNotEquals(aClass, anotherClass);
    }

    @Test
    public void testCompareClassWithNull() {

        ClassFactory classFactory = new ClassFactory();
        Class aClass = classFactory.createClass(courseCode, classTitle, classRecurrence, classStartTime, classDuration, classEndTime);

        assertNotEquals(aClass, null);
    }

    @Test
    public void testCompareClassWithDifferentClass() {

        ClassFactory classFactory = new ClassFactory();
        Class aClass = classFactory.createClass(courseCode, classTitle, classRecurrence, classStartTime, classDuration, classEndTime);

        assertNotEquals(aClass, new Object());
    }
}