package dev.mcxkt.examplemod.pure

import dev.mcxkt.examplemod.pure.item.SimpleItem
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
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
    }

    init {
        FMLJavaModLoadingContext.get().modEventBus.apply {
            addListener(this@ExampleMod::setup)
            addListener(this@ExampleMod::clientSetup)
            addListener(this@ExampleMod::serverSetup)
            MinecraftForge.EVENT_BUS.register(this)
        }
    }

    private fun setup(event: FMLCommonSetupEvent) {
        logger.info("Hello from Pre-init")
    }

    private fun clientSetup(event: FMLClientSetupEvent) {
        logger.info("Hello from client setup")
    }

    private fun serverSetup(event: FMLDedicatedServerSetupEvent) {
        logger.info("Hello from server setup")
    }

    @SubscribeEvent
    fun onServerStarting(event: FMLServerStartingEvent) {
        logger.info("Hello from server starting")
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    class RegistryEvents {
        @SubscribeEvent
        fun onBlockRegistry(event: RegistryEvent.Register<Block>) {
        }

        @SubscribeEvent
        fun onItemRegistry(event: RegistryEvent.Register<Item>) {
            event.registry.register(SimpleItem())
        }
    }
}