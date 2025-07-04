# Assignment 5 – Object-Oriented Programming 2

**Drools Rule Engine – Custom Layer Implementation**

**Semester B • Second Year • B.Sc. in Computer Science**

---

## Overview

This assignment demonstrates the creation of a rule-based system using the Drools rule engine, focusing on layering custom business logic using declarative rules.

---

## Sample Output

```
 Starting Drools rule engine...
Rule triggered: age > 18 (adult)
Rule triggered: age > 18 (adult)
Rule triggered: age <= 18 (minor)
Rule triggered: age <= 18 (minor)
Rule triggered: name is John
Rule triggered: name is not John
Rule triggered: name is not John
Rule triggered: name is not John
Alice is in kindergarten age
Tiny is an baby
------------
Name: John
Age: 25
Is adult? true
Is named John? true
------------
Name: Alice
Age: 5
Is adult? false
Is named John? false
------------
Name: Tiny
Age: 2
Is adult? false
Is named John? false
------------
Name: Sarah
Age: 72
Is adult? true
Is named John? false
```

---

## Structure

The project includes:

* Java classes defining domain logic (`Person` class)
* Drools rules file (`rules.drl`) defining logic based on attributes
* Maven project setup with appropriate dependencies
* Execution of multiple rules on several domain objects

---

## Technologies

* Java 17+
* Drools 7.74.0.Final
* Maven

---

## Rule Logic (rules.drl)

```java
package org.example.drools;
import org.server.Person;

rule "Adult age"
    when
        $p : Person(age > 18)
    then
        System.out.println("Rule triggered: age > 18 (adult)");
        modify($p) { setAdult(true) }
end

rule "Minor age"
    when
        $p : Person(age <= 18)
    then
        System.out.println("Rule triggered: age <= 18 (minor)");
        modify($p) { setAdult(false) }
end

rule "Name is John"
    when
        $p : Person(getName() == "John")
    then
        System.out.println("Rule triggered: name is John");
        modify($p) { setIsName(true) }
end

rule "Name is not John"
    when
        $p : Person(getName() != "John")
    then
        System.out.println("Rule triggered: name is not John");
        modify($p) { setIsName(false) }
end

rule "Kindergarten age"
    when
        $p : Person(age > 3 && age < 6)
    then
        System.out.println($p.getName() + " is in kindergarten age");
end

rule "School age"
    when
        $p : Person(age >= 6 && age <= 18)
    then
        System.out.println($p.getName() + " is in school age");
end

rule "Infant age"
    when
        $p : Person(age <= 3)
    then
        System.out.println($p.getName() + " is an infant");
end
```

---

## Domain Class: `Person.java`

```java
package org.server;

public class Person {
    private String name;
    private int age;
    private boolean isAdult;
    private boolean isName;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.isAdult = false;
        this.isName = false;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public boolean isAdult() { return isAdult; }
    public void setAdult(boolean isAdult) { this.isAdult = isAdult; }
    public boolean getIsName() { return isName; }
    public void setIsName(boolean isName) { this.isName = isName; }
}
```

---

## Runner Class: `DroolsMain.java`

```java
package org.server;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsMain {
    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        KieSession kSession = kc.newKieSession("ksession-rules");

        if (kSession == null) {
            System.err.println("kSession is null!");
            return;
        }

        Person john = new Person("John", 25);
        Person alice = new Person("Alice", 5);
        Person baby = new Person("Tiny", 2);
        Person older = new Person("Sarah", 72);

        kSession.insert(john);
        kSession.insert(alice);
        kSession.insert(baby);
        kSession.insert(older);

        System.out.println("Starting Drools rule engine...");
        kSession.fireAllRules();
        kSession.dispose();

        printPersonStatus(john);
        printPersonStatus(alice);
        printPersonStatus(baby);
        printPersonStatus(older);
    }

    private static void printPersonStatus(Person p) {
        System.out.println("------------");
        System.out.println("Name: " + p.getName());
        System.out.println("Age: " + p.getAge());
        System.out.println("Is adult? " + p.isAdult());
        System.out.println("Is named John? " + p.getIsName());
    }
}
```

---

## Maven Setup (pom.xml excerpt)

```xml
<dependencies>
    <dependency>
        <groupId>org.kie</groupId>
        <artifactId>kie-api</artifactId>
        <version>7.74.0.Final</version>
    </dependency>
    <dependency>
        <groupId>org.drools</groupId>
        <artifactId>drools-core</artifactId>
        <version>7.74.0.Final</version>
    </dependency>
    <dependency>
        <groupId>org.drools</groupId>
        <artifactId>drools-compiler</artifactId>
        <version>7.74.0.Final</version>
    </dependency>
    <dependency>
        <groupId>org.kie</groupId>
        <artifactId>kie-ci</artifactId>
        <version>7.74.0.Final</version>
    </dependency>
</dependencies>
```

---

## Team Member

| Name           | Contribution                          |
| -------------- | ------------------------------------- |
| Yakov Ben-Hamo | Full rule creation, design, debugging |

---

## Notes

* The rules are triggered declaratively using conditions defined in `.drl`.
* Drools uses the Working Memory (WM) to hold facts (like `Person` objects), matches them to conditions (LHS), and executes actions (RHS).
* Each `modify(...)` tells Drools to update the WM so that other rules can re-evaluate.
* The system is modular and ready to be extended with more rule sets.
#   A s s i g m e n t 5 _ D r o o l s 
 
 
