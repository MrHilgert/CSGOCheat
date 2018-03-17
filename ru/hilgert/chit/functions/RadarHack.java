package ru.hilgert.chit.functions;

import static ru.hilgert.chit.Chit.*;
import ru.hilgert.chit.Offsets;

public class RadarHack extends Thread {

	@Override
	public void run() {
		while (true) {
			try {
				if (!rhEnabled) {
					Thread.sleep(1000);
					continue;
				} else {
					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (int i = 0; i < 64; i++) {
				int entBase = client.readInt((client.getAddress() + Offsets.oEntityList) + i * 0x10);

				int entTeam = client.readInt(entBase + Offsets.oTeam);

				if (entTeam != localPlayerTeam) continue;

				boolean dormant = client.readBoolean(entBase + Offsets.oDormant);

				if (!dormant) {
					client.writeBoolean(entBase + Offsets.oSpotted, true);
				}

			}

		}
	}
}
