package ru.hilgert.chit.functions;

import static ru.hilgert.chit.Chit.client;
import static ru.hilgert.chit.Chit.gdi;
import static ru.hilgert.chit.Chit.localPlayer;
import static ru.hilgert.chit.Chit.localPlayerTeam;
import static ru.hilgert.chit.Chit.whEnabled;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

import ru.hilgert.chit.CColor;
import ru.hilgert.chit.Offsets;

public class WallHack extends Thread {

	public static int glowObject = 0x0;

	public static HashSet<Integer> bases = new HashSet<>();

	public static final HashMap<Integer, CColor> colors = new HashMap<Integer, CColor>();

	public static long lastWhUpdate = 0L;

	public WallHack() {}

	@Override
	public void run() {
		while (true) {
			try {
				if (!whEnabled) {
					Thread.sleep(1000);
					localPlayerTeam = client.readInt(localPlayer + Offsets.oTeam);
					continue;
				} else {
					
					gdi.drawText(10, 20, "Wallhack");
					
					if (System.currentTimeMillis() - lastWhUpdate > 500) {
						lastWhUpdate = System.currentTimeMillis();
						bases.clear();

						glowObject = client.readInt(client.getAddress() + Offsets.oGlowObject);

						for (int i = 0; i < 64; i++) {
							int entityBase = client.readInt((client.getAddress() + Offsets.oEntityList) + i * 0x10);

							if (entityBase == 0) continue;

							int team = client.readInt(entityBase + Offsets.oTeam);
							boolean dormant = client.readBoolean(entityBase + Offsets.oDormant);

							if (!dormant && team != localPlayerTeam && !bases.contains(entityBase)) {
								if (!colors.containsKey(entityBase)) colors.put(entityBase, randomColor());
								bases.add(entityBase);
							}

						}
					}

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			/*
			 * WallHack
			 */

			for (Integer base : bases) {
				int glowIndex = client.readInt(base + Offsets.oGlowIndex) * 0x38;
				glow(glowObject + glowIndex, colors.get(base));
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private CColor randomColor() {
		return new CColor(ThreadLocalRandom.current().nextFloat(), ThreadLocalRandom.current().nextFloat(), ThreadLocalRandom.current().nextFloat(), 1F);
	}

	public static void glow(int base, CColor c) {
		client.writeFloat(base + 0x4, c.getR());
		client.writeFloat(base + 0x8, c.getG());
		client.writeFloat(base + 0xC, c.getB());
		client.writeFloat(base + 0x10, c.getA());
		client.writeBoolean(base + 0x24, true);
		client.writeBoolean(base + 0x25, false);
	}
}
