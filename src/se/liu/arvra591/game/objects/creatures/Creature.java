package se.liu.arvra591.game.objects.creatures;

import se.liu.arvra591.game.objects.AbstractObject;
import se.liu.arvra591.game.objects.containers.CreatureInventory;
import se.liu.arvra591.game.objects.items.Equipable;
import se.liu.arvra591.game.objects.items.Item;

/**
 * A class representing all creatures, all creatures have some basic stats as well as health and level
 * All creatures such as {@link Player} and {@link Npc} inherit from this class
 */
public abstract class Creature extends AbstractObject
{
    protected int currentHealth;
    protected Equipable equippedItem = null;
    protected int currentEnergy;
    protected CreatureStats stats;

    protected CreatureInventory inventory;

    protected Creature(String name, String description, int currentHealth, int currentEnergy, CreatureStats stats, CreatureInventory inventory){

	super(name, description);
	this.currentHealth = currentHealth;
        this.currentEnergy = currentEnergy;
        this.stats = stats;
        this.inventory = inventory;
    }

    /**
     * @param item is the item that the creature will equip
     */
    public void equipItem(Equipable item){
        equippedItem = item;
    }


    public Item getEquippedItem(){
        return equippedItem;
    }
    /**
     * Prints the creature
     */
    @Override
    public void printObject(){
        super.printObject();
        System.out.println("Health: " + getCurrentHealth());
        stats.printStats();
    }

    /**
     * @return Returns the current health of the creature
     */
    public int getCurrentHealth(){
        return currentHealth;
    }

    /**
     * @param number is the amount of health the creature will gain
     */
    public void increaseHealth(String number){
        try {
            int amount = Integer.parseInt(number);
            currentHealth += amount;
            int maxHealth = stats.getMaxHealth();
            if(currentHealth > maxHealth){
                currentHealth = maxHealth;
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("wrong formatting, third word must be integer");
        }
    }

    /**
     * @param number is the amount of energy the creature will gain
     */
    public void addEnergy(String number){
        stats = getStats();
        int maxEnergy = stats.getMaxEnergy();
        try {
            int amount = Integer.parseInt(number);
            currentEnergy += amount;
            if (currentEnergy > maxEnergy) {
                currentEnergy = maxEnergy;
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("wrong formatting, third word must be integer");
        }
    }

    /**
     * @param number is the amount of attack the creature will gain
     */
    public void increaseAttack(String number){
        try {
            int amount = Integer.parseInt(number);
            stats.increaseAttack(amount);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("wrong formatting, third word must be integer");
        }
    }

    /**
     * @param damage is the amount of damage the creature will take
     */
    public void takeDamage(int damage){
        currentHealth -= damage;
    }

    /**
     * @return Returns true if the creature is alive, false if it is dead
     */
    public boolean isAlive(){
        if (currentHealth <= 0) {
            return false;
        }
        return true;
    }

    /**
     * @return Returns the inventory of the creature
     */
    public CreatureInventory getInventory(){
        return inventory;
    }


    /**
     * @param number is the amount of defense the creature will gain
     */
    public void increaseDefense(String number){
        try {
            int amount = Integer.parseInt(number);
            stats.increaseDefense(amount);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("wrong formatting, third word must be integer");
        }
    }

    /**
     * @return Returns the current energy of the player
     */
    public int getCurrentEnergy(){
        return currentEnergy;
    }

    /**
     * @param item is the item that the creature picks up
     */
    public boolean pickUpItem(Item item){
        return inventory.addObject(item);
    }
    /**
     * @return Returns the stats of the creature
     */
    public CreatureStats getStats() {
        if (equippedItem == null) {
            return stats;
        }
        return stats.calculateStats(equippedItem.getStats());
    }
}
