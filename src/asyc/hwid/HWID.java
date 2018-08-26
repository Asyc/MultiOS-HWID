package asyc.hwid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import asyc.hwid.exception.UnsupportedOSException;

public class HWID {

	public static String getHWID() throws IOException, UnsupportedOSException, NoSuchAlgorithmException {
		String hwid = "";
		if (OSUtil.getCurrentOS().equals(OS.WINDOWS)) {
			hwid = runCommand("wmic baseboard get serialNumber");
		} else if (OSUtil.getCurrentOS().equals(OS.MAC)) {
			String result = runCommand("system_profiler SPHardwareDataType | awk '/Serial/ {print $4}'");
			hwid = result.substring(result.indexOf("Hardware UUID: ") + 15);
		} else if (OSUtil.getCurrentOS().equals(OS.UNIX)) {
			String cpu = runCommand("lshw -c cpu");
			cpu = cpu.substring(cpu.indexOf("product: ") + 9, cpu.indexOf("vendor: ") - 7);
			String graphics = runCommand("lshw -c display");
			graphics = graphics.substring(graphics.indexOf("product: ") + 9, graphics.indexOf("vendor: ") - 7);
			String networkSerialNumber = runCommand("lshw -c network");
			networkSerialNumber = networkSerialNumber.substring(networkSerialNumber.indexOf("serial: ") + 8,
					networkSerialNumber.indexOf("width: ") - 7);
			hwid = cpu + " " + graphics + " " + networkSerialNumber;
		} else if (OSUtil.getCurrentOS().equals(OS.UNKNOWN)) {
			throw new UnsupportedOSException();
		}
		if (hwid.isEmpty() || hwid.equals(" ")) {
			throw new NullPointerException();
		} else {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(hwid.getBytes());
			byte[] bytes = digest.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : bytes) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return sb.toString();
		}
	}

	private static String runCommand(String command) throws IOException {
		String result = "";
		Process p = Runtime.getRuntime().exec(command);
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;

		while ((line = reader.readLine()) != null) {
			result += line;
		}
		reader.close();
		if (result.equalsIgnoreCase(" ") || result.isEmpty()) {
			throw new IOException();
		} else {
			return result;
		}
	}
}
