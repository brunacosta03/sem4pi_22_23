# US 1001 - Register User

## 1. Context

In Sprint B client wants us to develop a feature for our System. He wants that a Manager to be able to register Teachers and Students, as well as Managers.

## 2. Requirements

As Manager, I want to be able to register Teachers and Students, as well as Managers

## 3. Analysis
Information in System Specification

	All users should be identified in the system by their email. Each user should also provide its full name and short name. In order to be authenticated by the system the users must also provide a password.

	Managers - Managers (they can be also named as Administrators) manage all the users of the system

	Teacher - "A Teacher is characterized by his/her name, date of birth, tax payer number and an acronym inputed by the administrator (e.g., "AALB")"

	Student - A student is characterized by his/her name, date of birth, tax payer number and a mechanographic number assigned automatically by the system based on the year of registration and a sequential number, e.g., "202300001".

This is an excerpt of our domain Model, it provides the clear idea of how the User should be identified according to the information in System Specification.

![Domain Model Excerpt](Analysis/DomainModelExcerpt.svg)

So all users should have:
- Email
- Full Name
- Short Name
- Password
- Birth Date
- Tax Payer Number

Teachers should have Acronym

Students should have Mechanographic Number

## 4. Design

### 4.1. Realization

#### 4.1.1. Sequence Diagram Register User

![Register User SD](SD/RegisterUser-SD.svg)

### 4.2. Class Diagram Register User

![a class diagram](CD/RegisterUser-CD.svg)

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