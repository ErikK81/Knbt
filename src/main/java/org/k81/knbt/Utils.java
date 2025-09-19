package org.k81.knbt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class Utils {

    public static void SetTag(Player p, String key, String value) {
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType().isAir()) {
            p.sendMessage("§cVocê não está segurando nenhum item.");
            return;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        NamespacedKey nKey = new NamespacedKey("knbt", key.toLowerCase());
        meta.getPersistentDataContainer().set(nKey, PersistentDataType.STRING, value);
        item.setItemMeta(meta);

        p.sendMessage("§aTag salva §7(" + key + ": " + value + ")");
    }


    public static void GetTag(Player p, String key) {
        ItemStack item = p.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        String raw = meta.serialize().get(key).toString();
        p.sendMessage("§6--- Tag Information ---");
        p.sendMessage("§b" + key + "§7 = §f" + raw);
    }

    public static void listTag(Player p) {
        ItemStack item = p.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();

        if (item.getType().isAir()) {
            p.sendMessage("§eYou aren't holding an item!.");
            return;
        }
        if (meta == null) {
            p.sendMessage("§eThis item hasn't meta.");
            return;
        }

        p.sendMessage("§6--- Item Information ---");
        p.sendMessage("Material: §f" + item.getType());
        p.sendMessage("Amount: §f" + item.getAmount());
        p.sendMessage("CustomModelData: §f" + (meta.hasCustomModelData() ? meta.getCustomModelData() : "None"));

        p.sendMessage("§6--- ItemMeta ---");
        for (Map.Entry<String, Object> raw : meta.serialize().entrySet()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            if (raw.getKey().equals("custom-model-data")) continue;
            if (!raw.getKey().equals("PublicBukkitValues")) {
                p.sendMessage("§b" + gson.toJson(raw.getKey()) + "§7 = §f" + gson.toJson(raw.getValue()));
            } else {
                PersistentDataContainer pdc = meta.getPersistentDataContainer();

                pdc.getKeys().forEach(k -> {
                    String value = pdc.get(k, PersistentDataType.STRING);
                    if (value != null) {
                        p.sendMessage("§7BukkitValue: §e\"" +k.getNamespace() + ":" + k.getKey() + "\" = §f" + value);
                    }
                });
            }
        }
    }
}
