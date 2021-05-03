# PUBG Mobile ESP External Mod Menu with login screen
PUBG Mobile ESP External Mod Menu project for PUBG Mobile 1.3.0. Support root and non-root (virtual), 32-bit injection. Works on both Android Studio and AIDE. Project fixed for better compatibility

# Fix aapt error in AIDE
When you first open this project in AIDE, you will meet a bunch of aapt erros, such as `Unknown entityR'`, `aapt: Original attribute defined here.` and many more... A manual fix is required.

- Open the file /app/build.gradle
- Uncomment this line `implementation fileTree(dir: "libs", include: ["*.jar"])`
- Comment out or remove this line `implementation 'com.github.QuadFlask:colorpicker:0.0.13'`
- Save the file
- Restart AIDE

Doing this steps will cause build errors in Android Studio. This project can only work one of IDE. Make sure to  warn everyone who are using this project

# Screenshots
![](https://i.imgur.com/FWlBLJW.png)

![](https://i.imgur.com/wMawN0X.png)

![](https://i.imgur.com/8I7FgKx.jpg)

![](https://i.imgur.com/UbijccX.jpg)

# Credits
- DESI ESP
- PUTRI - https://t.me/espgrup

# DISCLAIMER
**WE SHARE THIS PROJECT FOR EDUCATIONAL PURPOSES ONLY, TO LEARN HOW THIS PROJECT WORKS. WE DO NOT ENCOURAGE YOU TO MOD PUBG OR ANY TENCENT GAMES. DOING SO MAY END YOU UP IN LEGAL TROUBLE. YOU ARE ONLY RESPONSABLE FOR YOUR ACTIONS**

**WE WILL NEVER OFFER HELP AND SUPPORT WITH THIS PROJECT. DON'T ASK HOW TO USE IT AND/OR FIXING THE PROJECT, INSTEAD LEARN YOURSELF. IF YOU CAN'T, ASK SOMEONE FOR HELP OR ASK ON ANY MODDING COMMUNITY FORUM SITES.**

**DON'T SELL THE SOURCE, DON'T BUY FROM SOMEONE EITHER WHEN YOU CAN GET IT FOR FREE, DON'T GET SCAMMED, SAVE YOU MONEY. FUCK SOURCE SELLING!**