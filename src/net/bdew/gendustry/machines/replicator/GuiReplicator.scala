/*
 * Copyright (c) bdew, 2013 - 2014
 * https://github.com/bdew/gendustry
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/gendustry/master/MMPL-1.0.txt
 */

package net.bdew.gendustry.machines.replicator

import net.bdew.gendustry.Gendustry
import net.bdew.gendustry.gui.{HintIcons, WidgetPowerCustom, WidgetProgressBarNEI, Textures}
import net.minecraft.entity.player.EntityPlayer
import net.bdew.lib.gui.{Texture, Color, Rect, BaseScreen}
import net.bdew.lib.gui.widgets.{WidgetLabel, WidgetFluidGauge}
import net.bdew.lib.Misc

class GuiReplicator(val te: TileReplicator, player: EntityPlayer) extends BaseScreen(new ContainerReplicator(te, player), 176, 166) {
  val background = Texture(Gendustry.modId, "textures/gui/replicator.png", rect)

  override def initGui() {
    super.initGui()
    widgets.add(new WidgetProgressBarNEI(new Rect(89, 41, 40, 15), Textures.greenProgress(40), te.progress, "Replicator"))
    widgets.add(new WidgetPowerCustom(new Rect(8, 19, 16, 58), Textures.powerFill, te.power))
    widgets.add(new WidgetFluidGauge(new Rect(32, 19, 16, 58), Textures.tankOverlay, te.dnaTank))
    widgets.add(new WidgetFluidGauge(new Rect(56, 19, 16, 58), Textures.tankOverlay, te.proteinTank))
    widgets.add(new WidgetLabel(Misc.toLocal("tile.gendustry.replicator.name"), 8, 6, Color.darkgray))

    inventorySlots.getSlotFromInventory(te, te.slots.inTemplate).setBackgroundIcon(HintIcons.template)
  }
}