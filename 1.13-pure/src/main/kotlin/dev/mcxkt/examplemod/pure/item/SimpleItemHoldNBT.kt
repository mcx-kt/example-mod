package dev.mcxkt.examplemod.pure.item

import dev.mcxkt.examplemod.pure.ExampleMod
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUseContext
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.util.text.TextComponentString
import net.minecraft.world.World

class SimpleItemHoldNBT : Item(Item.Properties().group(ExampleMod.exampleGroup)) {
    init {
        setRegistryName("example", "hold_nbt")
    }

    override fun onItemRightClick(world: World, player: EntityPlayer, enumHand: EnumHand): ActionResult<ItemStack> {
        val item = player.getHeldItem(enumHand)
        val tag = item.tag
        if (tag != null) {
            player.sendMessage(TextComponentString("x: ${tag.getInt("x")}, y: ${tag.getInt("y")}, z: ${tag.getInt("z")}"))
        } else {
            player.sendMessage(TextComponentString("No data available"))
        }
        return ActionResult(EnumActionResult.SUCCESS, item)
    }

    override fun onItemUse(context: ItemUseContext): EnumActionResult {
        val item = context.item
        if (item.tag == null) {
            item.tag = NBTTagCompound()
        }
        val pos = context.pos
        item.tag?.run {
            putInt("x", pos.x)
            putInt("y", pos.y)
            putInt("z", pos.z)
        }
        return EnumActionResult.SUCCESS
    }
}