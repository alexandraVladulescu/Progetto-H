/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Francesco
 */
public class Client {

    private String name;
    private String surname;
    private Address address;

    public Client(String name, String surname, Address address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Address getIndirizzo() {
        return address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\t"
                + "Surname: " + this.getSurname() + "\t"
                + "Address " + this.getAddress() + "\n";
    }

}
