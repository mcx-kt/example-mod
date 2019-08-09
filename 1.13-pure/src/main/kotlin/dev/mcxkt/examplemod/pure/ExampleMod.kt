package dev.mcxkt.examplemod.pure

import dev.mcxkt.examplemod.pure.item.SimpleItem
import net.minecraft.block.Block
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.minecraftforge.fml.event.server.FMLServerStartingEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import org.apache.logging.log4j.LogManager

@Mod("example")
class ExampleMod {
    companion object {
        val logger = LogManager.getLogger(ExampleMod::class.java)

        val instance by lazy { ModList.get().getModObjectById<ExampleMod>("example").get() }

        val exampleGroup = object : ItemGroup("example") {
            override fun createIcon() = ItemStack(Items.DIAMOND)
        }
    }

    init {
        FMLJavaModLoadingContext.get().modEventBus.apply {
            addListener<FMLCommonSetupEvent> { setup(it) }
            addListener<FMLClientSetupEvent> { clientSetup(it) }
            addListener<FMLDedicatedServerSetupEvent> { logger.info("Hello from server setup") }
            MinecraftForge.EVENT_BUS.register(this@ExampleMod)
        }
    }

    private fun setup(event: FMLCommonSetupEvent) {
        logger.info("Hello from Pre-init")
    }

    private fun clientSetup(event: FMLClientSetupEvent) {
        logger.info("Hello from client setup")
    }

    @Suppress("unused")
    @SubscribeEvent
    fun onServerStarting(event: FMLServerStartingEvent) {
        logger.info("Hello from server starting")
    }

    @Suppress("unused")
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    class RegistryEvents {
        companion object {
            @SubscribeEvent
            @JvmStatic
            fun onBlockRegistry(event: RegistryEvent.Register<Block>) {
            }

            @SubscribeEvent
            @JvmStatic
            fun onItemRegistry(event: RegistryEvent.Register<Item>) {
                event.registry.register(SimpleItem())
            }
        }
    }
}