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
    private String telephone;
    private Address address;

    //Costruttore vuoto ci serve per il metodo clone
    public Client() {
    }

    
    
    public Client(String name, String surname, String telephone, Address address) {
        this.name = name;
        this.surname = surname;
        this.telephone=telephone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\t"
                + "\nSurname: " + this.getSurname() + "\t"
                + "\nAddress :" + this.getAddress() + "\n"
                + "\nTel." + this.getTelephone() + "\n";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Client client = new Client();
        client.name = this.name;
        client.surname = this.surname;
        client.telephone = this.telephone;
        client.address = (Address) this.getAddress().clone();
        return client;
    }
    
    

}
