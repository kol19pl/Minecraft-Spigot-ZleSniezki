package kol19pl.zlesniezki;


import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class LadowaczEnczantuw extends Enchantment {

    private final String name;
    private final int MaxLV;



    public LadowaczEnczantuw(String namespace, String name, int lvl){
        super(NamespacedKey.minecraft(namespace));
        this.name = name;
        this.MaxLV = lvl;

    }



    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxLevel() {
        return MaxLV;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return false;
    }

}
