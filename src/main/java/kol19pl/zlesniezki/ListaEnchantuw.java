package kol19pl.zlesniezki;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.bukkit.Bukkit.getLogger;

public class ListaEnchantuw {

    public static final Enchantment SNIEZKOWAKLONTFA = new LadowaczEnczantuw("snieznaklontfa","Snieznaklontfa", 1);

//sprawć czy został jusz zarejestrowany
    public static void register(){
         boolean zarejestrowany = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(SNIEZKOWAKLONTFA);

         if (!zarejestrowany)
             registerEnchantment(SNIEZKOWAKLONTFA);

    }


//Zarejestruj
    public static void registerEnchantment(Enchantment enchantment){
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null,true);
            Enchantment.registerEnchantment(enchantment);

        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if (registered)
        {
            getLogger().info("enchant"+ enchantment + "dodany");
        }
    }


}
