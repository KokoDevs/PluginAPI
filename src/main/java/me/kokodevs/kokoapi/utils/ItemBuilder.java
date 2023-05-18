package me.kokodevs.kokoapi.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    private Material material;
    private String name;
    private List<String> lore;
    private boolean unbreakable;
    private boolean glowing;

    private int customModelData;

    private int amount;

    public ItemBuilder(Material material) {
        this.material = material;
        this.amount = 1;
        this.unbreakable = false;
        this.glowing = false;
        this.customModelData = 0;
    }

    public ItemBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public ItemBuilder setGlowing(boolean glowing) {
        this.glowing = glowing;
        return this;
    }

    public ItemBuilder setCustomModelData(int customModelData) {
        this.customModelData = customModelData;
        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(unbreakable);
        if(glowing){
            item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 0);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if (name != null) meta.setDisplayName(name);
        if (lore != null) meta.setLore(lore);
        meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);
        return item;
    }
}
