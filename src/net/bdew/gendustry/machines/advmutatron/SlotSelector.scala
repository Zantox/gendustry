/*
 * Copyright (c) bdew, 2013 - 2016
 * https://github.com/bdew/gendustry
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.gendustry.machines.advmutatron

import net.bdew.lib.gui.SlotClickable
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.{ClickType, Slot}
import net.minecraft.item.ItemStack

class SlotSelector(inv: TileMutatronAdv, slot: Int, x: Int, y: Int) extends Slot(inv, slot, x, y) with SlotClickable {
  override def onClick(clickType: ClickType, button: Int, player: EntityPlayer): ItemStack = {
    if (button == 0 && clickType == ClickType.PICKUP && !inv.getWorld.isRemote && !inv.isWorking && getHasStack) {
      inv.setMutation(getSlotIndex)
    }
    return null
  }
  override def isItemValid(stack: ItemStack) = false
}
