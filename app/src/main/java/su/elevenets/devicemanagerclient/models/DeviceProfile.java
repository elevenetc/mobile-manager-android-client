package su.elevenets.devicemanagerclient.models;

/**
 * Created by eleven on 28/08/2016.
 */
public class DeviceProfile {
    public String deviceId;
    public String pushToken;
    public String manufacturer;
    public String model;
    public String osVersion;
    public String wifiSSID;
    public int screenWidth;
    public int screenHeight;
    public boolean hasNfc;
    public boolean hasBluetooth;
    public boolean hasBluetoothLowEnergy;
    public boolean hasFingerprintScanner;
    public long lat;
    public long lon;
    public float batteryLevel;

}