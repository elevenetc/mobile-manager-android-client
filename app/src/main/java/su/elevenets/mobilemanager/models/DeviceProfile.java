package su.elevenets.mobilemanager.models;


import su.elevenets.mobilemanager.consts.Consts;

/**
 * Created by eleven on 28/08/2016.
 */
public class DeviceProfile {
	public final String platform = Consts.PLATFORM;
	public String deviceId;
	public String pushToken;
	public String manufacturer;
	public String model;
	public String osVersion;
	public String wifiSSID;
	public int screenWidth;
	public int screenHeight;
	public double screenSize;
	public boolean hasNfc;
	public boolean hasBluetooth;
	public boolean hasBluetoothLowEnergy;
	public boolean hasFingerprintScanner;
	public float batteryLevel;
	public String cpuArch;
	public int cpuCoreNum;
}