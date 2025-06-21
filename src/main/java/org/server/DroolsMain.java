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
            System.err.println(" kSession is null!");
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

        System.out.println(" Starting Drools rule engine...");
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
