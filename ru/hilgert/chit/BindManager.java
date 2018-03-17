package ru.hilgert.chit;

import static ru.hilgert.chit.Chit.bhEnabled;
import static ru.hilgert.chit.Chit.fasEnabled;
import static ru.hilgert.chit.Chit.tbEnabled;
import static ru.hilgert.chit.Chit.whEnabled;
import static ru.hilgert.chit.Chit.rhEnabled;

import ru.hilgert.jhcore.JUtils;

public class BindManager extends Thread {

	@Override
	public void run() {
		while (true) {

			if (JUtils.getAsyncKeyState(0x70)) { // F1
				whEnabled = !whEnabled;
				System.out.println("Wallhack: " + whEnabled);
			} else if (JUtils.getAsyncKeyState(0x71)) { // F2
				tbEnabled = !tbEnabled;
				System.out.println("TriggerBot: " + tbEnabled);
			} else if (JUtils.getAsyncKeyState(0x72)) { // F3
				bhEnabled = !bhEnabled;
				System.out.println("Bunnyhop: " + bhEnabled);
			} else if (JUtils.getAsyncKeyState(0x73)) { // F3
				fasEnabled = !fasEnabled;
				System.out.println("FakeAntiSpread: " + fasEnabled);
			} else if (JUtils.getAsyncKeyState(0x74)) {
				rhEnabled = !rhEnabled;
				System.out.println("RadarHack: " + rhEnabled);
			} else if (JUtils.getAsyncKeyState(0x78)) { // F9
				whEnabled = false;
				tbEnabled = false;
				bhEnabled = false;
				fasEnabled = false;
				System.out.println("Bye!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
