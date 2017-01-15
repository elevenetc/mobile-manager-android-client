# Mobile Manager client
Client for [Mobile Manager](https://github.com/elevenetc/mobile-manager)
# Build
1. Add endpoint of Mobile Manager to `local.properties`:
```
endpoint=https://yourdomain.org:6666
```
2. Create and add `google-services.json` file to `./app` with enabled Google Cloud Messages( [Firebase Messages](https://support.google.com/firebase/answer/7015592) )
3. Build apk
```
./gradlew assembleDebug
```
# Install and run
Connect device and execute command(for devices with Android <6.0 remove `-g` param):
```bash
adb install -g ./app/build/outputs/apk/app-debug.apk;
adb shell am start -n su.elevenets.mobilemanager/.MainActivity;
adb shell am broadcast -a su.elevenets.mobilemanager.intent.Bind --es command "bind"

```
It installs apk, starts activity and sends broadcast message which binds client with your server.
# Licence
[MIT](https://opensource.org/licenses/MIT)
