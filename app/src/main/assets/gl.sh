echo "Current date:"
date
GUEST="/data/data/com.tencent.ig/shared_prefs/device_id.xml"
kill com.tencent.ig
rm -rf $GUEST
echo "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
<map>
    <string name=\"random\"></string>
    <string name=\"install\"></string>
    <string name=\"uuid\">$RANDOM$RANDOM-$RANDOM-$RANDOM-$RANDOM-$RANDOM$RANDOM$RANDOM</string>
</map>" > $GUEST
rm -rf /data/data/com.tencent.ig/databases
rm -rf /data/media/0/Android/data/com.tencent.ig/files/login-identifier.txt
rm -rf /data/media/0/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Intermediate/SaveGames/JKGuestRegisterCnt.json
echo "Succes"
