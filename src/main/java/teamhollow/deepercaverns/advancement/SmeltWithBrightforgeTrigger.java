package teamhollow.deepercaverns.advancement;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import teamhollow.deepercaverns.DeeperCaverns;

public class SmeltWithBrightforgeTrigger implements ICriterionTrigger<SmeltWithBrightforgeTrigger.Instance>
{
	public static final ResourceLocation ID = new ResourceLocation(DeeperCaverns.MODID, "smelt_with_brightforge");
	private final Map<PlayerAdvancements, SmeltWithBrightforgeTrigger.Listeners> listenerMap = Maps.newHashMap();

	@Override
	public ResourceLocation getId()
	{
		return ID;
	}

	@Override
	public void addListener(PlayerAdvancements playerAdvancements, Listener<SmeltWithBrightforgeTrigger.Instance> listener)
	{
		SmeltWithBrightforgeTrigger.Listeners listeners = listenerMap.computeIfAbsent(playerAdvancements, Listeners::new);
		listeners.add(listener);
	}

	@Override
	public void removeListener(PlayerAdvancements playerAdvancements, Listener<SmeltWithBrightforgeTrigger.Instance> listener)
	{
		SmeltWithBrightforgeTrigger.Listeners listeners = listenerMap.get(playerAdvancements);

		if(listeners != null)
		{
			listeners.remove(listener);

			if(listeners.isEmpty())
				listenerMap.remove(playerAdvancements);
		}
	}

	@Override
	public void removeAllListeners(PlayerAdvancements playerAdvancements)
	{
		listenerMap.remove(playerAdvancements);
	}

	@Override
	public SmeltWithBrightforgeTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
	{
		return new SmeltWithBrightforgeTrigger.Instance();
	}

	public void trigger(ServerPlayerEntity player)
	{
		SmeltWithBrightforgeTrigger.Listeners listeners = listenerMap.get(player.getAdvancements());

		if(listeners != null)
			listeners.trigger();
	}

	public static class Instance extends CriterionInstance
	{
		public Instance()
		{
			super(SmeltWithBrightforgeTrigger.ID);
		}
	}

	static class Listeners
	{
		private final PlayerAdvancements playerAdvancements;
		private final Set<Listener<SmeltWithBrightforgeTrigger.Instance>> listeners = Sets.newHashSet();

		public Listeners(PlayerAdvancements playerAdvancements)
		{
			this.playerAdvancements = playerAdvancements;
		}

		public boolean isEmpty()
		{
			return listeners.isEmpty();
		}

		public void add(Listener<SmeltWithBrightforgeTrigger.Instance> listener)
		{
			listeners.add(listener);
		}

		public void remove(Listener<SmeltWithBrightforgeTrigger.Instance> listener)
		{
			listeners.remove(listener);
		}

		public void trigger()
		{
			for(Listener<SmeltWithBrightforgeTrigger.Instance> listener : Lists.newArrayList(listeners))
			{
				listener.grantCriterion(playerAdvancements);
			}
		}
	}
}
