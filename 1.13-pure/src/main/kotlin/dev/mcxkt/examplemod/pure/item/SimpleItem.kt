package dev.mcxkt.examplemod.pure.item

import dev.mcxkt.examplemod.pure.ExampleMod
import net.minecraft.item.Item

class SimpleItem : Item(Item.Properties().group(ExampleMod.exampleGroup)) {
    init {
        setRegistryName("example", "simple")
    }
}