package be.kdg.prog6.family.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


public class Person {

    private String name;

    private LocalDate deceasedDate;

    private PersonUUID sso;



    public record PersonUUID(UUID uuid) { }



    private Person spouse;

    public static Person born(String name) {
        Person person = new Person(name, new PersonUUID(UUID.randomUUID()));
        return person;
    }

    private Person(String name, PersonUUID sso) {
        this.name = name;
        this.sso = sso;
    }

    public String getName() {
        return name;
    }

    public PersonUUID getSso() {
        return sso;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void marry(Person person) {
        //cant be married if someone is deceased
        if (person.isDeceased() || this.isDeceased()) {
            System.out.println("Not possible!");
            //if you are already married, but not to this person or the other person is already married but not to me
        } else if ((this.isMarried() && !this.isMarriedTo(person)) || (person.isMarried() && !person.isMarriedTo(this))){
            System.out.println("Naughty naughty!");
        } else if (person.isMarriedTo(this)){
            this.spouse = person;
            System.out.println("all good");
        } else {
            this.spouse = person;
            spouse.marry(this);
        }
    }

    public boolean isMarried(){
        return getSpouse() != null;
    }

    public boolean isMarriedTo(Person person){
        return this.isMarried() && this.getSpouse().equals(person);
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sso=" + sso +
                ", deceasedDate=" + deceasedDate +
                ", spouse=" + (spouse != null ? spouse.name :"" ) +
                '}';
    }

    public void decease(){
        this.deceasedDate = LocalDate.now();
        this.spouse.spouse = null;
        this.spouse = null;
    }

    public boolean isDeceased(){
        return deceasedDate != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(sso, person.sso) && Objects.equals(deceasedDate, person.deceasedDate) && Objects.equals(spouse, person.spouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sso, deceasedDate, spouse);
    }



    public static void main(String[] args) {
        Person jeffrey = Person.born("jeffrey");
        Person marie = Person.born("marie");
        Person someoneElse = Person.born("someoneElse");

        jeffrey.marry(marie);

        System.out.println(jeffrey);
        System.out.println(marie);
        System.out.println("***");

        //renew vows!
        jeffrey.marry(marie);
        System.out.println(jeffrey);
        System.out.println(marie);
        System.out.println("***");

        jeffrey.marry(someoneElse);
        System.out.println(jeffrey);
        System.out.println(marie);
        System.out.println("***");
        jeffrey.decease();

        System.out.println(jeffrey);
        System.out.println(marie);


    }


}
