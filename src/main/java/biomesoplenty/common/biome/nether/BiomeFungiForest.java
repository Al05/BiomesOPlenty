/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.nether;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorBigMushroom;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeFungiForest extends BOPHellBiome
{
    
    public BiomeFungiForest()
    {
        super("fungi_forest", new PropsBuilder("Fungi Forest").withGuiColour(0xA93C3E).withTemperature(2.0F).withRainfall(0.0F).withRainDisabled());

        this.addWeight(BOPClimates.HELL, 15);

        // shrooms
        IBlockPosQuery surfaceBlocks = BlockQuery.buildOr().states(this.topBlock).create();
        this.addGenerator("glowshroom", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(1.25F).scatterYMethod(ScatterYMethod.NETHER_SURFACE).with(BlockBOPMushroom.MushroomType.GLOWSHROOM).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        this.addGenerator("flat_mushroom", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).scatterYMethod(ScatterYMethod.NETHER_SURFACE).with(BlockBOPMushroom.MushroomType.FLAT_MUSHROOM).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        this.addGenerator("toadstools", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).scatterYMethod(ScatterYMethod.NETHER_SURFACE).with(BlockBOPMushroom.MushroomType.TOADSTOOL).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(5.0F).scatterYMethod(ScatterYMethod.NETHER_SURFACE).with(Blocks.RED_MUSHROOM.getDefaultState()).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(2.0F).scatterYMethod(ScatterYMethod.NETHER_SURFACE).with(Blocks.BROWN_MUSHROOM.getDefaultState()).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        this.addGenerator("big_brown_mushroom", GeneratorStage.BIG_SHROOM,(new GeneratorBigMushroom.Builder()).amountPerChunk(2.0F).mushroomType(GeneratorBigMushroom.BigMushroomType.BROWN).scatterYMethod(ScatterYMethod.NETHER_SURFACE).placeOn(surfaceBlocks).create());
        this.addGenerator("big_red_mushroom", GeneratorStage.BIG_SHROOM,(new GeneratorBigMushroom.Builder()).amountPerChunk(15.0F).mushroomType(GeneratorBigMushroom.BigMushroomType.RED).scatterYMethod(ScatterYMethod.NETHER_SURFACE).minHeight(5).maxHeight(15).placeOn(surfaceBlocks).create());
    }
    
    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.NETHER_HIVES)) {this.removeGenerator("hive");}
        
        if (!settings.isEnabled(GeneratorType.MUSHROOMS)) {this.removeGenerator("glowshrooms"); this.removeGenerator("toadstools"); this.removeGenerator("flat_mushroom"); this.removeGenerator("blue_milk_caps"); this.removeGenerator("portobellos");}
    }
}
