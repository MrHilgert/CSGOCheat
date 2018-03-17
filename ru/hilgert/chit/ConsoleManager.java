package ru.hilgert.chit;

import static ru.hilgert.chit.Chit.bhEnabled;
import static ru.hilgert.chit.Chit.fasEnabled;
import static ru.hilgert.chit.Chit.rhEnabled;
import static ru.hilgert.chit.Chit.tbEnabled;
import static ru.hilgert.chit.Chit.whEnabled;

import java.util.Scanner;

public class ConsoleManager extends Thread {

	private Scanner scan;

	public ConsoleManager() {
		scan = new Scanner(System.in);
	}

	@Override
	public void run() {
		while (scan.hasNext()) {
			String cmd = scan.nextLine();

			if (cmd.equalsIgnoreCase("wh")) {
				whEnabled = !whEnabled;
				System.out.println("Wallhack: " + whEnabled);
			} else if (cmd.equalsIgnoreCase("tb")) {
				tbEnabled = !tbEnabled;
				System.out.println("TriggerBot: " + tbEnabled);
			} else if (cmd.equalsIgnoreCase("bh")) {
				bhEnabled = !bhEnabled;
				System.out.println("BunnyHop: " + bhEnabled);
			} else if (cmd.equalsIgnoreCase("fas")) {
				fasEnabled = !fasEnabled;
				System.out.println("FakeAntiSpread: " + fasEnabled);
			} else if (cmd.equalsIgnoreCase("rh")) {
				rhEnabled = !rhEnabled;
				System.out.println("RadarHack: " + rhEnabled);
			}

			if (cmd.equalsIgnoreCase("exit")) {
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
				scan.close();
				System.exit(0);
			}

		}
	}
}
