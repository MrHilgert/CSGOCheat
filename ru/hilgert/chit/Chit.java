package ru.hilgert.chit;

import java.awt.Color;

import ru.hilgert.chit.functions.BunnyHop;
import ru.hilgert.chit.functions.FakeAntiSpread;
import ru.hilgert.chit.functions.RadarHack;
import ru.hilgert.chit.functions.TriggerBot;
import ru.hilgert.chit.functions.WallHack;
import ru.hilgert.jhcore.JGDI;
import ru.hilgert.jhcore.JHCore;
import ru.hilgert.jhcore.JModule;
import ru.hilgert.jhcore.JProcess;
import ru.hilgert.jhcore.JUtils;

public class Chit {

	public static JModule client;

	public static int localPlayer = 0xFFFFFFFF;
	public static int localPlayerTeam = 0x0;

	public static boolean bhEnabled = false;
	public static boolean tbEnabled = false;
	public static boolean whEnabled = false;
	public static boolean fasEnabled = false;
	public static boolean rhEnabled = false;

	public static JGDI gdi;

	public static void main(String[] args) {

		System.out.println("Waiting process");
		JProcess process = JHCore.openProcess("csgo.exe");
		System.out.println("Process opened");

		while ((client = process.getModule("client.dll")).getAddress() == 0xFFFFFFFF);
		System.out.println("client.dll address = " + JUtils.toHex(client.getAddress()));

		localPlayer = client.readInt(client.getAddress() + Offsets.oLocalPlayer);
		localPlayerTeam = client.readInt(localPlayer + Offsets.oTeam);

		System.out.println();
		System.out.println("[F1/wh]		Toggle	WallHack");
		System.out.println("[F2/tb]		Toggle	TriggerBot");
		System.out.println("[F3/bh]		Toggle	BunnyHop");
		System.out.println("[F4/fas]	Toggle	FakeAntiSpread");
		System.out.println("[F5/rh]		Toggle	RadarHack");
		System.out.println("[F9/exit]	Exit");
		System.out.println();

		System.out.println(localPlayer);
		System.out.println(localPlayerTeam == 2 ? "Terrorist" : "Counter-Terrorist");

		System.out.println("WPN " + getActiveWeaponId());

		if (localPlayer == 0xFFFFFFFF) {
			System.out.println("Failed to read localPlayer( = 0xFFFFFFFF)");
			System.exit(0);
		}
		
		gdi = process.getGDI();

		gdi.setTextColor(Color.yellow);
		gdi.setBackColor(0, 0, 0);

		new BindManager().start();
		new ConsoleManager().start();

		new RadarHack().start();
		new BunnyHop().start();
		new FakeAntiSpread().start();
		new TriggerBot().start();
		new WallHack().start();

	}

	public static byte getActiveWeaponId() {
		int activeWeapon = client.readInt(localPlayer + Offsets.oActiveWeapon);
		int entNum = activeWeapon & 0xFFF;

		int weaponId = client.readInt(client.getAddress() + Offsets.oEntityList + (entNum - 1) * 0x10);

		byte myWeaponId = client.readByte(weaponId + Offsets.oWeaponId);

		return myWeaponId;
	}

}
