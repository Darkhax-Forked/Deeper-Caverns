package teamhollow.deepercaverns.block;

import net.minecraft.block.PaneBlock;
import net.minecraft.util.BlockRenderLayer;

public class CustomPaneBlock extends PaneBlock
{
	public CustomPaneBlock(Properties builder)
	{
		super(builder);
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}
}
