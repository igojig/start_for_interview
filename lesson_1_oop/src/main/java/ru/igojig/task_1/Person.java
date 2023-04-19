package ru.igojig.task_1;

public class Person {

    public Person(){}

    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    // getters, setters, equals, hashCode

    public static class PersonBuilder {
        private final Person person;

        public PersonBuilder() {
            person = new Person();
        }

        public PersonBuilder setFirstName(String firstName) {
            this.person.firstName = firstName;
            return this;
        }

        public PersonBuilder setLastName(String lastName) {
            this.person.lastName = lastName;
            return this;
        }

        public PersonBuilder setMiddleName(String middleName) {
            this.person.middleName = middleName;
            return this;
        }

        public PersonBuilder setCountry(String country) {
            this.person.country = country;
            return this;
        }

        public PersonBuilder setAddress(String address) {
            this.person.address = address;
            return this;
        }

        public PersonBuilder setPhone(String phone) {
            this.person.phone = phone;
            return this;
        }


        public PersonBuilder setAge(int age) {
            this.person.age = age;
            return this;
        }

        public PersonBuilder setGender(String gender) {
            this.person.gender = gender;
            return this;
        }

        public Person build() {
            return this.person;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
