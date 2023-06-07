package me.kokodevs.kokoapi.utils;

import com.google.common.collect.Multimap;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * An items builder class.
 *
 * @author SadGhost
 * @since 1.0.0
 */
public final class ItemBuilder {
    @NotNull private final ItemStack itemStack;
    @NotNull private final ItemMeta meta;

    /**
     * Creates an item builder with a default amount of 1
     * from the material provided.
     *
     * @param material the item material.
     */
    public ItemBuilder(final @NotNull Material material) {
        this(material, 1);
    }

    /**
     * Creates an item builder with the provided amount
     * and the provided material.
     *
     * @param material the item material.
     * @param amount the amount of the item.
     */
    public ItemBuilder(final @NotNull Material material,
                       final int amount) {
        this(new ItemStack(material, amount));
    }

    /**
     * Creates an item builder with the provided
     * itemstack.
     *
     * @param itemStack the itemstack.
     */
    public ItemBuilder(final @NotNull ItemStack itemStack) {
        this.itemStack = itemStack;
        this.meta = Preconditions.checkNonNull(itemStack.getItemMeta());
    }

    /**
     * Returns an {@code ItemBuilder} instance with the
     * provided material.
     *
     * @param material the material.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> new")
    public static @NotNull ItemBuilder of(final @NotNull Material material) {
        return new ItemBuilder(material);
    }

    /**
     * Returns an {@code ItemBuilder} instance with the
     * provided material and amount.
     *
     * @param material the material.
     * @param amount the amount.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_, _ -> new")
    public static @NotNull ItemBuilder of(final @NotNull Material material, final int amount) {
        return new ItemBuilder(material, amount);
    }

    /**
     * Returns an {@code ItemBuilder} instance with the
     * provided itemstack.
     *
     * @param itemStack the itemstack.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> new")
    public static @NotNull ItemBuilder of(final @NotNull ItemStack itemStack) {
        return new ItemBuilder(itemStack);
    }

    /**
     * Sets the item's display name in the meta.
     *
     * @param displayName the new display name.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder setName(final @NotNull String displayName) {
        meta.setDisplayName(displayName);
        return this;
    }


    /**
     * Sets the item's damage in the meta.
     *
     * @param damage the new durability.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder setDamage(final int damage) {
        if (!(meta instanceof Damageable damageable))
            throw new IllegalStateException("Cannot set the damage of a non-damageable item.");

        damageable.setDamage(damage);
        return this;
    }


    /**
     * Sets the item's lore in the meta.
     *
     * @param lore the new lore.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder setLore(final @NotNull String @NotNull... lore) {
        final List<String> loreComponents = new ArrayList<>();
        for (int i = 0; i < lore.length; i++)
            loreComponents.set(i, lore[i]);

        meta.setLore(loreComponents);
        return this;
    }

    /**
     * Sets the item's lore in the meta.
     *
     * @param lore the new lore.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder setLore(final @NotNull List<String> lore) {
        final List<String> loreComponents = new ArrayList<>();
        for (int i = 0; i < lore.size(); i++)
            loreComponents.set(i, lore.get(i));

        meta.setLore(loreComponents);
        return this;
    }

    /**
     * Removes a lore line from the item's meta.
     *
     * @param line the lore line.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder removeLoreLine(final @NotNull String line) {
        final List<String> lore = meta.getLore();
        if (lore == null) return this;
        if (!lore.contains(line)) return this;

        lore.remove(line);
        meta.setLore(lore);
        return this;
    }

    /**
     * Removes a lore line from the item's meta.
     *
     * @param index the lore line index.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder removeLoreLine(final int index) {
        final List<String> lore = meta.getLore();
        if (lore == null) return this;
        if (index < 0 || index > lore.size()) return this;

        lore.remove(index);
        meta.setLore(lore);
        return this;
    }

    /**
     * Adds a lore line to the current lore
     *
     * @param line the new lore line.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder addLoreLine(final @NotNull String line) {
        final List<String> lore = meta.getLore();
        if (lore != null) {
            lore.add(line);
            meta.setLore(lore);
        }

        return this;
    }

    /**
     * Sets a lore line in the item's meta.
     *
     * @param line the new lore line.
     * @param pos the line index.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_, _ -> this")
    public @NotNull ItemBuilder setLoreLine(final @NotNull String line,
                                            final int pos) {
        final List<String> lore = meta.getLore();
        if (lore != null) {
            lore.set(pos, line);
            meta.setLore(lore);
        }
        return this;
    }

    /**
     * Sets the item's new model data in the item's meta.
     *
     * @param i the new custom model data.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder setCustomModelData(final int i) {
        meta.setCustomModelData(i);
        return this;
    }

    /**
     * Adds an enchantment to the item's metadata.
     *
     * @param enchant the enchantment.
     * @param level the enchantment level.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_, _ -> this")
    public @NotNull ItemBuilder addEnchant(final @NotNull Enchantment enchant,
                                           final int level) {
        meta.addEnchant(enchant, level, true);
        return this;
    }

    /**
     * Removes an enchantment from the item in the item's meta.
     *
     * @param enchant the enchantment.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder removeEnchant(final @NotNull Enchantment enchant) {
        meta.removeEnchant(enchant);
        return this;
    }

    /**
     * Adds item flags to the item's meta.
     *
     * @param itemFlags the item flags.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder addItemFlags(final @NotNull ItemFlag @NotNull... itemFlags) {
        meta.addItemFlags(itemFlags);
        return this;
    }

    /**
     * Removes item flags from the item's meta.
     *
     * @param itemFlags the item flags.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder removeItemFlags(@NotNull ItemFlag @NotNull... itemFlags) {
        meta.removeItemFlags(itemFlags);
        return this;
    }

    /**
     * Sets the unbreakable value of the item in the item's meta.
     *
     * @param unbreakable the value.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder setUnbreakable(final boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Adds an attribute modifier to the item in the item's meta.
     *
     * @param attribute the attribute.
     * @param attributeModifier the modifier.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_, _ -> this")
    public @NotNull ItemBuilder addAttributeModifier(final @NotNull Attribute attribute,
                                                     final @NotNull AttributeModifier attributeModifier) {
        meta.addAttributeModifier(attribute, attributeModifier);
        return this;
    }

    /**
     * Sets the item's attribute modifiers in the item's meta.
     *
     * @param attributeModifiers the attribute modifiers.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder setAttributeModifiers(final @Nullable Multimap<Attribute, AttributeModifier> attributeModifiers) {
        meta.setAttributeModifiers(attributeModifiers);
        return this;
    }

    /**
     * The attribute modifier to remove from the item's meta.
     *
     * @param attribute the attribute.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder removeAttributeModifier(final @NotNull Attribute attribute) {
        meta.removeAttributeModifier(attribute);
        return this;
    }

    /**
     * The equipment slot to remove modifiers from in
     * the item's meta.
     *
     * @param equipmentSlot the equipment slot.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder removeAttributeModifier(final @NotNull EquipmentSlot equipmentSlot) {
        meta.removeAttributeModifier(equipmentSlot);
        return this;
    }

    /**
     * The specific attribute modifier to remove from
     * the item's meta.
     *
     * @param attribute the attribute.
     * @param attributeModifier the attribute modifier.
     * @return the {@code ItemBuilder} instance.
     * @since 1.0.0
     */
    @Contract("_, _ -> this")
    public @NotNull ItemBuilder removeAttributeModifier(final @NotNull Attribute attribute,
                                                        final @NotNull AttributeModifier attributeModifier) {
        meta.removeAttributeModifier(attribute, attributeModifier);
        return this;
    }

    /**
     * Builds the item and returns it as an item stack.
     *
     * @return the item built as itemstack.
     * @since 1.0.0
     */
    public @NotNull ItemStack build() {
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
