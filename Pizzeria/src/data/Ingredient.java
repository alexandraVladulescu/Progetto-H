package data;

/**
 *
 * @author Markenos
 */
public class Ingredient implements Cloneable {

    private String name;
    private double price;

    public Ingredient(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Ingredient in = new Ingredient(this.name, this.price);
        return in;
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

    public boolean equals(Object o) {
        Ingredient ingredient = (Ingredient) o;
        return this.getName().equalsIgnoreCase(ingredient.getName());
    }

    public String toString() {
        return "\t" + this.getName() + "\n";
    }
}
