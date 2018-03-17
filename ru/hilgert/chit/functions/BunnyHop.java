package ru.hilgert.chit.functions;

import static ru.hilgert.chit.Chit.bhEnabled;
import static ru.hilgert.chit.Chit.client;
import static ru.hilgert.chit.Chit.gdi;
import static ru.hilgert.chit.Chit.localPlayer;
import ru.hilgert.chit.Offsets;
import ru.hilgert.jhcore.JUtils;

public class BunnyHop extends Thread {

	@Override
	public void run() {
		while (true) {
			
			try {
				if (!bhEnabled) {
					Thread.sleep(1000);
					continue;
				} else {
					gdi.drawText(10, 40, "BunnyHop");
					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (JUtils.isMouseButtonDown(0x04)) {
				int flag = client.readInt(localPlayer + Offsets.oFlags);

				if (flag == 257 || flag == 263) {

					client.writeInt(client.getAddress() + Offsets.oForceJump, 6);

					JUtils.pressUpKey(0x20);

					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
