package emotiWar.model.entity;

import javax.persistence.*;

@Entity
@Table (name = "emoticons")
public class EmotiEntity extends BaseEntity{

    private int strength;
    private int armor;
    private int baseStrength;
    private int baseArmor;
    private int wins;
    private int loses;
    private int coins;
    private int coinsToCollect;
    private int battles;

    @ManyToOne
    private ItemEntity weapon;
    @ManyToOne
    private ItemEntity shield;
    @ManyToOne
    private ItemEntity head;
    @ManyToOne
    private ItemEntity boots;
    @ManyToOne
    private ItemEntity trinkets;

    public int getBattles() {
        return battles;
    }

    public void setBattles(int battles) {
        this.battles = battles;
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

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public ItemEntity getWeapon() {
        return weapon;
    }

    public void setWeapon(ItemEntity weapon) {
        this.weapon = weapon;
    }

    public ItemEntity getShield() {
        return shield;
    }

    public void setShield(ItemEntity shield) {
        this.shield = shield;
    }

    public ItemEntity getHead() {
        return head;
    }

    public void setHead(ItemEntity head) {
        this.head = head;
    }

    public ItemEntity getBoots() {
        return boots;
    }

    public void setBoots(ItemEntity boots) {
        this.boots = boots;
    }

    public ItemEntity getTrinkets() {
        return trinkets;
    }

    public void setTrinkets(ItemEntity trinkets) {
        this.trinkets = trinkets;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public void setBaseStrength(int baseStrength) {
        this.baseStrength = baseStrength;
    }

    public int getBaseArmor() {
        return baseArmor;
    }

    public void setBaseArmor(int baseArmor) {
        this.baseArmor = baseArmor;
    }

    public void updateStrength() {
        this.strength = baseStrength + head.strength + weapon.strength +
                shield.strength + trinkets.strength + boots.strength;
    }

    public void updateArmor() {
        this.armor = baseArmor + head.armor + weapon.armor +
                shield.armor + trinkets.armor + boots.armor;
    }

    public int getCoinsToCollect() {
        return coinsToCollect;
    }

    public void setCoinsToCollect(int coinsToCollect) {
        this.coinsToCollect = coinsToCollect;
    }
}
