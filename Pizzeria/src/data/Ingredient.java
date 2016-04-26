package data;

/**
 *
 * @author Markenos
 */
public class Ingredient {

    private String name;
    private double price;

    public Ingredient(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Ingredient(String name) {
        this.name = name;
        this.price = 0;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean equals(Ingredient ingredient) {
        return this.getName().equalsIgnoreCase(ingredient.getName());
    }

    public String toString() {
        return "\t" + this.getName() + "\n";
    }
}
