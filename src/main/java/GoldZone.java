import org.terasology.math.TeraMath;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.generator.plugin.RegisterPlugin;
import org.terasology.world.zones.SingleBlockRasterizer;
import org.terasology.world.zones.ZonePlugin;

/*
 * Copyright 2018 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@RegisterPlugin
public class GoldZone extends ZonePlugin {
    public GoldZone() {
        super("GoldSoil", (x, y, z, region) ->
                y < TeraMath.floorToInt(region.getFacet(SurfaceHeightFacet.class).getWorld(x, z)));
        addProvider(new SurfaceProvider());
        addRasterizer(new SingleBlockRasterizer("CoreBlocks:GoldOre"));
    }
}