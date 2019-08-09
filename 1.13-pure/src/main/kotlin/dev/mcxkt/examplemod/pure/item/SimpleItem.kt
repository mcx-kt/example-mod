package dev.mcxkt.examplemod.pure.item

import net.minecraft.item.Item
import net.minecraft.item.ItemGroup

class SimpleItem : Item(Item.Properties().group(ItemGroup.BUILDING_BLOCKS)) {
    init {
        setRegistryName("example", "simple")
    }
}