package org.k81.knbt;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class Utils {

    // Define NBT de acordo com o tipo do valor
    public static void SetNbt(Player p, String tagName, Object value) {
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType().isAir()) return;

        NBTItem nbt = new NBTItem(item);

        switch (value) {
            case String str -> nbt.setString(tagName, str);
            case Integer i -> nbt.setInteger(tagName, i);
            case Double d -> nbt.setDouble(tagName, d);
            case Boolean b -> nbt.setBoolean(tagName, b);
            default -> throw new IllegalArgumentException("Tipo não suportado: " + value.getClass());
        }

        p.getInventory().setItemInMainHand(nbt.getItem());
    }

    // Recupera um NBT
    public static Object GetNbt(Player p, String tagName) {
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType().isAir()) return null;

        NBTItem nbt = new NBTItem(item);
        if (!nbt.hasKey(tagName)) return null;

        try { return nbt.getString(tagName); } catch (Exception ignored) {}
        try { return nbt.getInteger(tagName); } catch (Exception ignored) {}
        try { return nbt.getDouble(tagName); } catch (Exception ignored) {}
        try { return nbt.getBoolean(tagName); } catch (Exception ignored) {}

        return null;
    }

    // Remove uma tag NBT do item
    public static void RemoveNbt(Player p, String tagName) {
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType().isAir()) return;

        NBTItem nbt = new NBTItem(item);

        if (nbt.hasKey(tagName)) {
            nbt.removeKey(tagName);
            p.getInventory().setItemInMainHand(nbt.getItem());
        }
    }

    // Lista todas as tags NBT do item
    public static Set<String> ListNbt(Player p) {
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType().isAir()) return Set.of();

        NBTItem nbt = new NBTItem(item);

        return nbt.getKeys(); // Retorna todas as chaves em um Set<String>
    }
}
