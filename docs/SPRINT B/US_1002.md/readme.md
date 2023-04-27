# US 1002

## 1. Context

It is required for the manager to be able to create courses

## 2. Requirements

-As a Manager, I want to create courses.

-Different editions of a course are to be considered different
courses (e.g., Intro-Math-Sem01, Intro-Math-Sem02). Only managers are able to execute
this functionality.

## 3. Analysis

![Domain Model Excerpt](Analysis/DM.svg)

## 4. Design

### 4.1. Realization

![Sequence Diagram](SD/SD.svg)

### 4.2. Class Diagram

![Class Diagram](CD/CD.svg)

### 4.3. Applied Patterns

### 4.4. Tests

**Test 1:** *Verifies that it is not possible to create an instance of the Example class with null values.*

```
@Test(expected = IllegalArgumentException.class)
public void ensureNullIsNotAllowed() {
	Example instance = new Example(null, null);
}
````

## 5. Implementation

*In this section the team should present, if necessary, some evidencies that the implementation is according to the design. It should also describe and explain other important artifacts necessary to fully understand the implementation like, for instance, configuration files.*

*It is also a best practice to include a listing (with a brief summary) of the major commits regarding this requirement.*

## 6. Integration/Demonstration

*In this section the team should describe the efforts realized in order to integrate this functionality with the other parts/components of the system*

*It is also important to explain any scripts or instructions required to execute an demonstrate this functionality*

## 7. Observations

*This section should be used to include any content that does not fit any of the previous sections.*

*The team should present here, for instance, a critical prespective on the developed work including the analysis of alternative solutioons or related works*

*The team should include in this section statements/references regarding third party works that were used in the development this work.* 