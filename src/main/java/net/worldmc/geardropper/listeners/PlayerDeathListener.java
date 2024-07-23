package net.worldmc.geardropper.listeners;

import net.worldmc.geardropper.GearDropper;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerDeathListener implements Listener {
    private final GearDropper plugin;
    private final List<Material> gearMaterials;

    public PlayerDeathListener(GearDropper gearDropper) {
        this.plugin = gearDropper;
        this.gearMaterials = loadGearMaterialsFromConfig();
    }

    private List<Material> loadGearMaterialsFromConfig() {
        return plugin.getConfig().getStringList("gear").stream()
                .map(Material::valueOf)
                .collect(Collectors.toList());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Iterator<ItemStack> iterator = event.getDrops().iterator();
        while (iterator.hasNext()) {
            ItemStack item = iterator.next();
            if (item != null && !gearMaterials.contains(item.getType())) {
                event.getItemsToKeep().add(item);
                iterator.remove();
            }
        }
    }
}