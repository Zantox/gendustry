/*
 * Copyright (c) bdew, 2013 - 2016
 * https://github.com/bdew/gendustry
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.gendustry.machines

import net.bdew.lib.capabilities.helpers.FluidHelper
import net.bdew.lib.data.DataSlotTankBase
import net.bdew.lib.data.base.TileDataSlotsTicking
import net.minecraft.util.EnumFacing

trait FluidPusher extends TileDataSlotsTicking {
  val tank: DataSlotTankBase
  serverTick.listen(() => {
    if (tank.getFluidAmount > 0) {
      for (dir <- EnumFacing.values(); handler <- FluidHelper.getFluidHandler(getWorldObject, getPos.offset(dir), dir.getOpposite)) {
        FluidHelper.pushFluid(tank, handler)
      }
    }
  })
}
