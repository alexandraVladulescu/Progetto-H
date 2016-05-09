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
public class Address {

    private String addressName;
    private String houseNumber;
    private String localityName;
    private String informations; // esempio scala/citofono

    public Address() {
    }

    
    
    public Address(String localityName, String addressName, String houseNumeber) {
        this.addressName = addressName;
        this.houseNumber = houseNumeber;
        this.localityName = localityName;
        this.setInformations("Nessuna");

    }

    public Address(String mi, String città_del_Fumo, String string, String aK47) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public void setAddress(String address) {
        this.addressName = address;
    }

    public String getAddress() {
        return addressName;
    }

    public String getInformations() {
        return informations;
    }

    public void setInformations(String informations) {
        this.informations = informations;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }
    
    

    @Override
    public String toString() {
        return this.getAddress() + "\t"
                + this.houseNumber + "\t"
                + this.getLocalityName() + "\t" + "\n"
                + "INFORMAZIONI AGGIUNTIVE\n"
                + this.informations + "\n";

    }
    
    //Metodo per ritornare l'indirizzo sotto forma di un unica Stringa formattata
    //del tipo Via Luigi Giulotto, 28, Pavia, Nessuna
    public String printAdress(){
        return this.getAddress() + ", "
                + this.houseNumber + ", "
                + this.getLocalityName() + ", "
                + this.informations;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Address address = new Address();
        address.localityName = this.localityName;
        address.addressName = this.addressName;
        address.houseNumber = this.houseNumber;
        address.informations = this.informations;
        return address;
    }
    
    

}
