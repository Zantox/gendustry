/*
 * Copyright (c) bdew, 2013
 * https://github.com/bdew/gendustry
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/gendustry/master/MMPL-1.0.txt
 */

package net.bdew.gendustry.compat

import net.bdew.lib.Misc
import net.minecraft.item.{ItemStack, Item}
import net.bdew.gendustry.Gendustry
import net.bdew.gendustry.forestry.GeneSampleInfo
import net.minecraft.nbt.NBTTagCompound
import forestry.api.apiculture.EnumBeeChromosome
import forestry.api.genetics.AlleleManager

object ExtraBeesProxy {
  val ebLoaded = Misc.haveModVersion("ExtraBees@[1.6-pre15,)")
  val itemSerum =
    if (ebLoaded)
      try {
        Class.forName("binnie.extrabees.ExtraBees").getField("serum").get(null).asInstanceOf[Item]
      } catch {
        case e: Throwable =>
          Gendustry.logWarn("Failed to load ExtraBees serum item")
          e.printStackTrace()
          null
      }
    else
      null

  if (itemSerum != null) Gendustry.logInfo("ExtraBees serum item ID: ", itemSerum.itemID)

  def isSerum(stack: ItemStack) = if (itemSerum != null) stack.itemID == itemSerum.itemID else false

  def getSerumSample(stack: ItemStack): GeneSampleInfo = {
    if (!isSerum(stack)) return null
    val nbt: NBTTagCompound = stack.getTagCompound
    if (nbt.hasKey("chromosome") && nbt.hasKey("uid")) {
      var chromosome = nbt.getInteger("chromosome")
      val allele = nbt.getString("uid")
      if (chromosome >= EnumBeeChromosome.HUMIDITY.ordinal)
        chromosome = chromosome + 1
      val alleleObj = AlleleManager.alleleRegistry.getAllele(allele)
      return new GeneSampleInfo(AlleleManager.alleleRegistry.getSpeciesRoot("rootBees"), chromosome, alleleObj)
    }
    return null
  }
}
