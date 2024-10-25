package be.kdg.prog6.family.domain;

import java.util.UUID;

public class FamilyMember {
    private final Person.PersonUUID personUUID;
    private final String name;

    public FamilyMember(Person.PersonUUID personUUID, String name) {
        this.personUUID = personUUID;
        this.name = name;
    }


    public static FamilyMember born(Person.PersonUUID personUUID, String name){
        return new FamilyMember(personUUID,name);
    }


    public Person.PersonUUID getPersonUUID() {
        return personUUID;
    }
}
