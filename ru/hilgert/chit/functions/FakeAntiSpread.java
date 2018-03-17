package ru.hilgert.chit.functions;

import static ru.hilgert.chit.Chit.client;
import static ru.hilgert.chit.Chit.fasEnabled;
import static ru.hilgert.chit.Chit.gdi;
import static ru.hilgert.chit.Chit.localPlayer;
import ru.hilgert.chit.Offsets;
import ru.hilgert.jhcore.JUtils;

public class FakeAntiSpread extends Thread {

	@Override
	public void run() {
		while (true) {
			try {
				if (!fasEnabled) {
					Thread.sleep(1000);
					continue;
				} else {
					gdi.drawText(10, 80, "FakeAntiSpread");
					if (!JUtils.isMouseButtonDown(1)) {
						Thread.sleep(10);
						continue;
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (client.readInt(client.getAddress() + Offsets.oForceAttack) == 5) {
				client.writeFloat(localPlayer + Offsets.oViewPunchAngle, 0);
				client.writeFloat(localPlayer + Offsets.oViewPunchAngle + 4, 0);
				client.writeFloat(localPlayer + Offsets.oViewPunchAngle + 8, 0);
				continue;
			}

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
