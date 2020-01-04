import org.terasology.math.ChunkMath;
import org.terasology.math.TeraMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.utilities.procedural.SimplexNoise;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizerPlugin;
import org.terasology.world.generation.facets.SeaLevelFacet;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.generator.plugin.RegisterPlugin;

@RegisterPlugin
public class MineralRasterizer implements WorldRasterizerPlugin {
    private Block goldOre;
    private Block diamondOre;
    private SimplexNoise noise;

    @Override
    public void initialize() {
        goldOre = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:GoldOre");
        diamondOre = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:DiamondOre");
        noise = new SimplexNoise(1010101);
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        for (Vector3i pos : chunkRegion.getRegion()) {
            float surfaceHeight = TeraMath.floorToInt(chunkRegion.getFacet(SurfaceHeightFacet.class).getWorld(pos.x, pos.z));
            // check to see if the surface is under the sea level and if we are dealing with something above the surface
            if (pos.y < surfaceHeight && noise.noise(pos.x,pos.y,pos.z)>0.75) {
                chunk.setBlock(ChunkMath.calcBlockPos(pos), goldOre);
            } else if (pos.y<surfaceHeight && noise.noise(pos.x,pos.y,pos.z)>0.3){
                chunk.setBlock(ChunkMath.calcBlockPos(pos), diamondOre);
            }
        }
    }
}