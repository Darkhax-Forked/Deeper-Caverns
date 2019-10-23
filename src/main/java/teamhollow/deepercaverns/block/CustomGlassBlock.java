package teamhollow.deepercaverns.block;

import net.minecraft.block.GlassBlock;
import net.minecraft.util.BlockRenderLayer;

public class CustomGlassBlock extends GlassBlock
{
	public CustomGlassBlock(Properties builder)
	{
		super(builder);
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}
}
