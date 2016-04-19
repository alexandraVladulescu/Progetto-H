package data;

/**
 *
 * @author Markenos
 */
public class Ingredient {
    private String name;
    private String price;

    public Ingredient(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
    
    
}
