package emotiWar.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "items")
public class ItemEntity extends BaseEntity{
    protected String name;
    protected int strength;
    protected int armor;
    protected int price;

    public ItemEntity() {
    }

    public  ItemEntity(String name, int strength, int armor, int price) {
        this.name = name;
        this.strength = strength;
        this.armor = armor;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
