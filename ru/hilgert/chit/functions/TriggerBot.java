package ru.hilgert.chit.functions;

import static ru.hilgert.chit.Chit.client;
import static ru.hilgert.chit.Chit.gdi;
import static ru.hilgert.chit.Chit.localPlayer;
import static ru.hilgert.chit.Chit.localPlayerTeam;
import static ru.hilgert.chit.Chit.tbEnabled;

import java.util.concurrent.ThreadLocalRandom;

import ru.hilgert.chit.Offsets;
import ru.hilgert.jhcore.JUtils;

public class TriggerBot extends Thread {

	@Override
	public void run() {
		while (true) {

			try {
				if (!tbEnabled) {
					Thread.sleep(1000);
					continue;
				} else {
					gdi.drawText(10, 60, "TriggerBot");
					Thread.sleep(5);
					if (JUtils.isMouseButtonDown(1)) continue;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			/*
			 * TriggerBot
			 */

			int targetEntityId = client.readInt(localPlayer + Offsets.oCrosshairId) - 1;

			if (targetEntityId >= 0 && targetEntityId < 64) {

				int entityBase = client.readInt((client.getAddress() + Offsets.oEntityList) + targetEntityId * 0x10);

				if (entityBase == 0) continue;

				int team = client.readInt(entityBase + Offsets.oTeam);

				if (team == localPlayerTeam) continue;

				JUtils.mouseLeftClick(ThreadLocalRandom.current().nextInt(30, 250));

				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
