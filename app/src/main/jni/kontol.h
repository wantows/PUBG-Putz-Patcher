#ifndef KONTOL_H
#define KONTOL_H

#include <jni.h>
#include <string>
#include <cstdlib>
#include <unistd.h>
#include <sys/mman.h>
#include <android/log.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <cerrno>
#include <sys/un.h>
#include <cstring>
#include <string>
#include <cmath>
#include "struct.h"




bool isPlayerBox = true;
bool isPlayerLine = true;
bool isPlayerDist = true;
bool isPlayerHealth = true;
bool isPlayerName = true;
bool isPlayerHead = true;
bool isr360Alert = true;
bool isSkelton = true;
bool isGrenadeWarning = true;
bool isEnemyWeapon= true;


#endif //KONTOL_H
