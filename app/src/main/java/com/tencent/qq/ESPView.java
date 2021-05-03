package com.tencent.qq;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.icu.text.UFormat;
import android.os.SystemClock;
import android.os.health.HealthStats;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.tencent.qq.Overlay.getConfig;

public class ESPView extends View implements Runnable {
    Paint mStrokePaint;
    Paint mFilledPaint;
    Paint mTextPaint;
    Thread mThread;
    Paint mStrokePaint2;
    Paint mFilledPaint2;
    Paint mTextPaint2;
    Paint mTextPaint3;
    Paint p;
    Bitmap bitmap,out,bitmap2,out2;
    int FPS = 60;
    private  static int Pos;
    private  static int Size;
    private  static int Strokebox;
    private  static int Strokeskel;
    private  static int Strokeline;
    private static int ColorBox,ColorSkel,ColorHead,ColorLine,ColorAlert,ColorAlertText,bgname,bgdistance,bgEnWeapon,bgid,textname,textdistance,textenweapon;
    private  static int itemSize,itemPosition;
    static long sleepTime;
    Date time;
    SimpleDateFormat formatter;
    SimpleDateFormat formatter2;
    private int    mFPS = 0;
    private int    mFPSCounter = 0;
    private long   mFPSTime = 0;
    private Paint mTextPaint4;

    public static void ChangeFps(int fps){
        sleepTime =1000/(20+fps);
    }
    public static void ChangePosition(int position){
        Pos = position;
    }

    public static void ChangeSize(int size){
        Size = size;
    }

    public static void ChangeitemPosition(int itemposition){
        itemPosition = itemposition;
    }

    public static void ChangeitemSize(int itemsize){
        itemSize = itemsize;
    }

    public static void ChangeStrokeBox(int strokebox){ Strokebox = strokebox; }
    public static void ChangeStrokeSkel(int strokeskel){
        Strokeskel = strokeskel;
    }
    public static void ChangeStrokeLine(int strokeline){
        Strokeline = strokeline;
    }

    public static void ChangeColorBox(int colorBox){
        ColorBox = colorBox;
    }
    public static void ChangeColorSkel(int colorSkel){
        ColorSkel = colorSkel;
    }
    public static void ChangeColorHead(int colorHead){
        ColorHead = colorHead;
    }
    public static void ChangeColorLine(int colorLine){
        ColorLine = colorLine;
    }
    public static void ChangeColorAlert(int colorAlert){
        ColorAlert = colorAlert;
    }
    public static void ChangeColorAlertText(int colorAlertText){
        ColorAlertText = colorAlertText;
    }

    public static void ChangeBgName(int BGName){
        bgname = BGName;
    }
    public static void ChangeBgId(int BGId){
        bgid = BGId;
    }
    public static void ChangeBgDist(int BGDist){
        bgdistance = BGDist;
    }
    public static void ChangeBgEnWeapon(int BGEnWeapon){
        bgEnWeapon = BGEnWeapon;
    }

    public static void ChangeTextName(int TextName){
        textname = TextName;
    }
    public static void ChangeTextDist(int TextDist){
        textdistance = TextDist;
    }
    public static void ChangeTextEnWeapon(int TextEnWeapon){
        textenweapon = TextEnWeapon;
    }
    Bitmap[] OTHER = new Bitmap[5];
    private static final int[] OTH_NAME = {
            R.drawable.ic_clear_noob,
            R.drawable.ic_clear_pro,
            R.drawable.ic_warn_noob,
            R.drawable.ic_warn_pro,
            R.drawable.ic_grenade_warning
    };
    public ESPView(Context context) {
        super(context, null, 0);
        InitializePaints();
        setFocusableInTouchMode(false);
        setBackgroundColor(Color.TRANSPARENT);
        time = new Date();
        formatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        formatter2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        sleepTime = 1000 / FPS;
        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas != null && getVisibility() == VISIBLE) {
            ClearCanvas(canvas);
            time.setTime(System.currentTimeMillis());
            if (getHeight() == 720 && getWidth() == 1280) {
                DrawText(canvas, 255, 255, 0, 0, "                              " + formatter2.format(time) + "  " + formatter.format(time), 55, 45, 28);
            }
            else {
                DrawText(canvas, 255, 255, 0, 0, "                              " + formatter2.format(time) + "  " + formatter.format(time), 55, 45, 28);
            }
            Overlay.DrawOn(this, canvas);
        }
    }


    @Override
    public void run() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        while (mThread.isAlive() && !mThread.isInterrupted()) {
            try {
                long t1 = System.currentTimeMillis();
                postInvalidate();
                long td = System.currentTimeMillis() - t1;

                Thread.sleep(Math.max(Math.min(0, sleepTime - td), sleepTime));
            } catch (Exception e) {
                Thread.currentThread().interrupt();//preserve the message
                return;
                //System.out.println("----------------\n"+e.toString()+"\n----------------------");
            }
        }
    }

    public void InitializePaints() {
        mStrokePaint = new Paint();
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setColor(Color.rgb(0, 0, 0));

        mFilledPaint = new Paint();
        mFilledPaint.setStyle(Paint.Style.FILL);
        mFilledPaint.setAntiAlias(true);
        mFilledPaint.setColor(Color.rgb(0, 0, 0));

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.rgb(0, 0, 0));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setStrokeWidth(1.1f);
        mFilledPaint2 = new Paint();
        mFilledPaint2.setStyle(Paint.Style.FILL);
        mFilledPaint2.setAntiAlias(true);
        mFilledPaint2.setColor(Color.rgb(0, 0, 0));

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.rgb(0, 0, 0));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setStrokeWidth(1.1f);

        mTextPaint2 = new Paint();
        mTextPaint2.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint2.setAntiAlias(true);
        mTextPaint2.setColor(Color.rgb(0, 0, 0));
        mTextPaint2.setTextAlign(Paint.Align.CENTER);
        mTextPaint2.setStrokeWidth(1.1f);

        mTextPaint3 = new Paint();
        mTextPaint3.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint3.setAntiAlias(true);
        mTextPaint3.setColor(Color.rgb(0, 0, 0));
        mTextPaint3.setTextAlign(Paint.Align.CENTER);
        mTextPaint3.setStrokeWidth(1.1f);

        mTextPaint4 = new Paint();
        mTextPaint4.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint4.setAntiAlias(true);
        mTextPaint4.setColor(Color.rgb(0, 0, 0));
        mTextPaint4.setTextAlign(Paint.Align.CENTER);
        mTextPaint4.setStrokeWidth(1.1f);

        p=new Paint();
        final int bitmap_count_oth = OTHER.length;
        for(int i = 0 ; i < bitmap_count_oth ; i++)
        {
            OTHER[i] = BitmapFactory.decodeResource(getResources(), OTH_NAME[i]);
            if(i == 4){
                OTHER[i] = scale(OTHER[i],500,400);
            }
            else{
                OTHER[i] = scale(OTHER[i],80,80);
            }
        }
    }

    public void ClearCanvas(Canvas cvs) {
        cvs.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }
    public void Drawcount(Canvas canvas) {
        DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-80,50,160,40);
        DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-72,50,144,40);
        DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-64,50,128,40);
        DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-60,50,120,40);
        DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-56,50,112,40);
        DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-52,50,104,40);
        DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-48,50,96,40);
        DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-44,50,88,40);}

    //Draw Line
    public void DrawLine(Canvas cvs, int a, int r, int g, int b, float lineWidth, float fromX, float fromY, float toX, float toY) {
        mStrokePaint.setColor(Color.rgb(r, g, b));
        mStrokePaint.setAlpha(a);
        mStrokePaint.setStrokeWidth(Strokeline);
        mStrokePaint.setStrokeWidth(lineWidth);
        cvs.drawLine(fromX, fromY, toX, toY, mStrokePaint);
    }
    //It's brsd
    public void DrawRect(Canvas cvs, int a, int r, int g, int b, float stroke, float x, float y, float width, float height) {
        mStrokePaint.setStrokeWidth(stroke);
        mStrokePaint.setColor(Color.rgb(r, g, b));
        mStrokePaint.setAlpha(a);
        cvs.drawRect(x, y,  width,  height, mStrokePaint);
    }
    //fill
    public void DrawFilledRect(Canvas cvs, int a, int r, int g, int b, float x, float y, float width, float height) {
        mFilledPaint.setColor(Color.rgb(r, g, b));
        mFilledPaint.setAlpha(a);
        cvs.drawRect(x, y, width, height, mFilledPaint);
    }
    //enemy count icon
    public void DrawOTH(Canvas cvs, int image_number, float X, float Y) {

        cvs.drawBitmap(OTHER[image_number], X, Y, p);

    }
    public void DebugText(String s) {
        System.out.println(s);
    }
    //Esp View All Text Style
    public void DrawText(Canvas cvs, int a, int r, int g, int b, String txt, float posX, float posY, float size) {
        mTextPaint.setARGB(a,r, g, b);
        mTextPaint.setTextSize(size);
        cvs.drawText(txt, posX, posY, mTextPaint);
    }
    //Enemy Weapon
    public void DrawWeapon(Canvas cvs, int a, int r, int g, int b, int id,int ammo, float posX, float posY, float size) {
        mTextPaint.setARGB(a,r, g, b);
        mTextPaint.setTextSize(size);
        String wname=getWeapon(id);
        if(wname!=null)
            cvs.drawText(wname+" > "+ammo, posX, posY, mTextPaint);
    }
    //Draw Name Enemy, Items, Vehicles
    public void DrawName(Canvas cvs, int a, int r, int g, int b, String nametxt,int teamid, float posX, float posY, float size) {
        String[] namesp = nametxt.split(":");
        char[] nameint = new char[namesp.length];
        for (int i = 0; i < namesp.length; i++)
            nameint[i] = (char) Integer.parseInt(namesp[i]);
        String realname = new String(nameint);
        mTextPaint.setTextSize(18+Size);
        mTextPaint.setARGB(255, 255, 255, 255);
        cvs.drawText("" + realname, posX, posY - Pos, mTextPaint);
        cvs.drawText("" + teamid, posX - 84, posY - Pos, mTextPaint3);
        mTextPaint3.setTextSize(18+Size);
        mTextPaint3.setARGB(255, 255, 0, 0);

    }

    public void DrawSkeleton(Canvas cvs, int a, int r, int g, int b, float lineWidth, float fromX, float fromY, float toX, float toY) {
        mStrokePaint.setColor(Color.rgb(r, g, b));
        mStrokePaint.setAlpha(a);
        mStrokePaint.setStrokeWidth(Strokeskel);
        mStrokePaint.setARGB(255, 255, 0, 0);
        cvs.drawLine(fromX, fromY, toX, toY, mStrokePaint);
    }
    //All Round sht
    public void DrawCircle(Canvas cvs, int a, int r, int g, int b, float stroke, float posX, float posY, float radius) {
        mStrokePaint.setColor(Color.rgb(r, g, b));
        mStrokePaint.setAlpha(a);
        mStrokePaint.setStrokeWidth(stroke);
        cvs.drawCircle(posX, posY, radius, mStrokePaint);
    }

    //All Round Fill
    public void DrawFilledCircle(Canvas cvs, int a, int r, int g, int b, float posX, float posY, float radius) {
        mFilledPaint.setColor(Color.rgb(r, g, b));
        mFilledPaint.setAlpha(a);
        cvs.drawCircle(posX, posY, radius, mFilledPaint);
    }

    public void DrawTextName(Canvas cvs, int a, int r, int g, int b, String txt, float posX, float posY, float size) {
        mTextPaint.setARGB(a,r, g, b);
        mTextPaint.setTextSize(size);
        if (SystemClock.uptimeMillis() - mFPSTime > 1000) {
            mFPSTime = SystemClock.uptimeMillis();
            mFPS = mFPSCounter;
            mFPSCounter = 0;
        } else {
            mFPSCounter++;
        }
        String s = "" + mFPS;
        cvs.drawText("Fps :   "+s, posX-885, posY+940, mTextPaint);
    }



    //Item With Menually customizeable Icon materials
    public void DrawItems(Canvas cvs, String itemName,float distance, float posX, float posY, float size) {
        String realItemName = getItemName(itemName);
        mTextPaint.setTextSize(17+itemSize);
        if(realItemName!=null && !realItemName.equals(""))
            if (true && getConfig("Icon")) {
                if (realItemName == "AUG"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aug);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Groza") {
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.groza);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "M762"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m762);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "SCAR-L"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scar_l);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "M416"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m416);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "M16A-4"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m16a4);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Mk47 Mutant"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mk47);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "G36C"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.g36c);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "QBZ"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qbz);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "AKM"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.akm);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "TommyGun"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tommy);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Vector"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vector);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "DP28"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dp);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "AWM"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.awm);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "QBU"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qbu);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "SLR"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slr);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "SKS"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sks);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Mini14"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mini14);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "M24"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m24);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Kar98k"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kar98k);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Mk14"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mk14);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "S12K"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s12k);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "DBS"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dbs);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "S1897"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s1897);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Pan"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pan);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Painkiller"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pain_killer);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Adrenaline"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.injection);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Energy Drink"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.energy_drink);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "FirstAidKit"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.first_aid);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Bandage"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bandage);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Molotov"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.molotov);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Flare Gun"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flare_gun);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "AirDrop"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.air_drop);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "DropPlane"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.plane);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "GasCan"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gas_can);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "8x"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s8x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "2x"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s2x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Red Dot"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.reddot);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "3X"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s3x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Hollow Sight"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hollow);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "6x"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s6x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "4x"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s4x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Canted Sight"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.canted);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "45ACP"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammocp);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "9mm"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo9mm);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "12 Guage"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo12uage);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "7.62"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo762);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "5.56"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo556);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Arrow"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aarrow);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "300Magnum"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo300magnum);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Bag lvl 3"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bag3);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Bag lvl 2"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bag2);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Bag lvl 1"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bag1);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Vest lvl 3"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vest3);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Vest lvl 2"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vest2);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Vest lvl 1"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vest1);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Helmet lvl 3"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.helmet3);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Helmet lvl 2"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.helmet2);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Helmet lvl 1"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.helmet1);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }
                //bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m416);
                //out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                //cvs.drawBitmap(
                //        out, // Bitmap
                //        posX - 50, // Left
                //        posY - 70, // Top
                //        null // Paint
                //);
            }else if (true && getConfig("IconDistanceName")){
                if (realItemName == "AUG"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aug);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    mTextPaint.setARGB(255, 0, 255, 0);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Groza") {
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.groza);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "M762"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m762);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "SCAR-L"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scar_l);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "M416"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m416);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    mTextPaint.setARGB(255, 0, 255, 0);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "M16A-4"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m16a4);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Mk47 Mutant"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mk47);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "G36C"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.g36c);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "QBZ"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qbz);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    mTextPaint.setARGB(255, 0, 255, 0);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "AKM"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.akm);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "TommyGun"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tommy);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Vector"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vector);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "DP28"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dp);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "AWM"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.awm);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "QBU"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qbu);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "SLR"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slr);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "SKS"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sks);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Mini14"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mini14);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "M24"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m24);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Kar98k"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kar98k);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Mk14"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mk14);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "S12K"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s12k);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "DBS"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dbs);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "S1897"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s1897);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Pan"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pan);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Painkiller"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pain_killer);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Adrenaline"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.injection);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Energy Drink"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.energy_drink);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "FirstAidKit"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.first_aid);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Bandage"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bandage);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Molotov"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.molotov);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Flare Gun"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flare_gun);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "AirDrop"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.air_drop);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "DropPlane"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.plane);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "GasCan"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gas_can);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "8x"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s8x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "2x"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s2x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Red Dot"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.reddot);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "3X"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s3x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Hollow Sight"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hollow);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "6x"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s6x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "4x"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s4x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Canted Sight"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.canted);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "45ACP"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammocp);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "9mm"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo9mm);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "12 Guage"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo12uage);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "7.62"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo762);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "5.56"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo556);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Arrow"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aarrow);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "300Magnum"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo300magnum);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Bag lvl 3"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bag3);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Bag lvl 2"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bag2);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Bag lvl 1"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bag1);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Vest lvl 3"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vest3);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Vest lvl 2"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vest2);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Vest lvl 1"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vest1);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Helmet lvl 3"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.helmet3);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Helmet lvl 2"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.helmet2);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Helmet lvl 1"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.helmet1);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }
                //bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m416);
                //out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                //cvs.drawBitmap(
                //        out, // Bitmap
                //        posX - 50, // Left
                //        posY - 70, // Top
                //        null // Paint
                //);
                cvs.drawText(realItemName + " (" + Math.round(distance) + "m)", posX, posY - itemPosition, mTextPaint);
            }else if (true && getConfig("IconDistance")){
                if (realItemName == "AUG"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aug);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    mTextPaint.setARGB(255, 0, 255, 0);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Groza") {
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.groza);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "M762"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m762);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "SCAR-L"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scar_l);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "M416"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m416);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    mTextPaint.setARGB(255, 0, 255, 0);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "M16A-4"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m16a4);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Mk47 Mutant"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mk47);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "G36C"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.g36c);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "QBZ"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qbz);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "AKM"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.akm);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "TommyGun"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tommy);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Vector"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vector);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "DP28"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dp);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "AWM"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.awm);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "QBU"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qbu);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "SLR"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slr);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "SKS"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sks);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Mini14"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mini14);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "M24"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m24);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Kar98k"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kar98k);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Mk14"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mk14);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "S12K"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s12k);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "DBS"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dbs);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "S1897"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s1897);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Pan"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pan);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Painkiller"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pain_killer);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Adrenaline"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.injection);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Energy Drink"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.energy_drink);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "FirstAidKit"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.first_aid);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Bandage"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bandage);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Molotov"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.molotov);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Flare Gun"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flare_gun);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "AirDrop"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.air_drop);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "DropPlane"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.plane);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "GasCan"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gas_can);
                    out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 50, // Left
                            posY - 70 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "8x"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s8x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "2x"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s2x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Red Dot"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.reddot);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "3X"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s3x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Hollow Sight"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hollow);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "6x"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s6x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "4x"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s4x);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Canted Sight"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.canted);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "45ACP"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammocp);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "9mm"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo9mm);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "12 Guage"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo12uage);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "7.62"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo762);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "5.56"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo556);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Arrow"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aarrow);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "300Magnum"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ammo300magnum);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 30, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Bag lvl 3"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bag3);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Bag lvl 2"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bag2);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Bag lvl 1"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bag1);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Vest lvl 3"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vest3);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Vest lvl 2"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vest2);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Vest lvl 1"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vest1);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Helmet lvl 3"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.helmet3);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Helmet lvl 2"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.helmet2);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realItemName == "Helmet lvl 1"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.helmet1);
                    out = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 40, // Left
                            posY - 85 -  itemPosition, // Top
                            null // Paint
                    );
                }
                //bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m416);
                //out = Bitmap.createScaledBitmap(bitmap, 100, 50, false);
                //cvs.drawBitmap(
                //        out, // Bitmap
                //        posX - 50, // Left
                //        posY - 70, // Top
                //        null // Paint
                //);
                cvs.drawText("(" + Math.round(distance) + "m)", posX, posY - itemPosition, mTextPaint);
            }else{
                cvs.drawText(realItemName + " (" + Math.round(distance) + "m)", posX, posY - itemPosition, mTextPaint);
            }
    }
    public void DrawVehicles(Canvas cvs, String itemName,float distance, float posX, float posY, float size) {
        String realVehicleName = getVehicleName(itemName);
        //mTextPaint.setAlpha(150);
        mTextPaint.setTextSize(15+itemSize);
        if(realVehicleName!=null && !realVehicleName.equals(""))
            if (true && getConfig("Icon")) {
                if (realVehicleName == "Buggy"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.buggy);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "UAZ"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.uaz);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Bike"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.motocycle);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Dacia"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dacia);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Jet"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aquarail);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Boat"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.big_boat);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "MiniBus"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mini_bus);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Mirado"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mirado);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Snowbike"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.snowbike);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Snowmobile"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.snow_mobile);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Tuk"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tuktuk);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Pickup"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pickup);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "BRDM"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.brdm);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "LadaNiva"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lada_niva);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }
                //bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m416);
                //out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                //cvs.drawBitmap(
                //        out, // Bitmap
                //        posX - 20, // Left
                //        posY - 80, // Top
                //        null // Paint
                //);
            }else if (true && getConfig("IconDistanceName")){
                if (realVehicleName == "Buggy"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.buggy);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "UAZ"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.uaz);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Bike"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.motocycle);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Dacia"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dacia);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Jet"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aquarail);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Boat"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.big_boat);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "MiniBus"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mini_bus);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Mirado"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mirado);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Snowbike"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.snowbike);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Snowmobile"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.snow_mobile);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Tuk"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tuktuk);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Pickup"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pickup);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "BRDM"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.brdm);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "LadaNiva"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lada_niva);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }
                //bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m416);
                //out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                //cvs.drawBitmap(
                //        out, // Bitmap
                //        posX - 20, // Left
                //        posY - 80, // Top
                //        null // Paint
                //);
                cvs.drawText(realVehicleName + " (" + Math.round(distance) + "m)", posX, posY - itemPosition, mTextPaint);
            }else if (true && getConfig("IconDistance")){
                if (realVehicleName == "Buggy"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.buggy);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "UAZ"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.uaz);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Bike"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.motocycle);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Dacia"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dacia);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Jet"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aquarail);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Boat"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.big_boat);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "MiniBus"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mini_bus);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Mirado"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mirado);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Snowbike"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.snowbike);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Snowmobile"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.snow_mobile);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Tuk"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tuktuk);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "Pickup"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pickup);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "BRDM"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.brdm);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }else if (realVehicleName == "LadaNiva"){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lada_niva);
                    out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                    cvs.drawBitmap(
                            out, // Bitmap
                            posX - 20, // Left
                            posY - 80 -  itemPosition, // Top
                            null // Paint
                    );
                }
                //bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m416);
                //out = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
                //cvs.drawBitmap(
                //        out, // Bitmap
                //        posX - 20, // Left
                //        posY - 80, // Top
                //        null // Paint
                //);
                cvs.drawText("(" + Math.round(distance) + "m)", posX, posY - itemPosition, mTextPaint);
            }else{
                cvs.drawText(realVehicleName + " (" + Math.round(distance) + "m)", posX, posY - itemPosition, mTextPaint);
            }
        //cvs.drawText(realVehicleName+": "+Math.round(distance)+"m", posX, posY, mTextPaint);
    }
    public void DrawHead(Canvas cvs, int a, int r, int g, int b, float posX, float posY, float radius) {
        mFilledPaint.setColor(Color.rgb(r, g, b));
        mFilledPaint.setAlpha(a);
        mFilledPaint.setColor(ColorHead);
        cvs.drawCircle(posX, posY, radius, mFilledPaint);
    }

    private String getItemName(String s) {
        //Scopes
        if (s.contains("MZJ_8X")&& getConfig("8x")) { mTextPaint.setARGB(255, 247, 99, 245);
            return "8x";
        }

        if (s.contains("MZJ_2X") && getConfig("2x")) { mTextPaint.setARGB(255, 230, 172, 226);
            return "2x";
        }

        if (s.contains("MZJ_HD") && getConfig("Red Dot")) { mTextPaint.setARGB(255, 230, 172, 226);
            return "Red Dot";
        }

        if (s.contains("MZJ_3X") && getConfig("3x")) { mTextPaint.setARGB(255, 247, 99, 245);
            return "3X";
        }

        if (s.contains("MZJ_QX") && getConfig("Hollow")) { mTextPaint.setARGB(255, 153, 75, 152);
            return "Hollow Sight";
        }

        if (s.contains("MZJ_6X") && getConfig("6x")) { mTextPaint.setARGB(255, 247, 99, 245);
            return "6x";
        }

        if (s.contains("MZJ_4X") && getConfig("4x")) { mTextPaint.setARGB(255, 247, 99, 245);
            return "4x";
        }

        if (s.contains("MZJ_SideRMR") && getConfig("Canted")) { mTextPaint.setARGB(255, 153, 165, 0);
            return "Canted Sight";
        }


        //AR and smg
        if (s.contains("AUG") && getConfig("AUG")) { mTextPaint.setARGB(255, 0, 255, 0);
            return "AUG";
        }

        if (s.contains("M762") && getConfig("M762")) { mTextPaint.setARGB(255, 153, 165, 0);
            return "M762";
        }

        if (s.contains("SCAR") && getConfig("SCAR-L")) { mTextPaint.setARGB(255, 0, 255, 0);
            return "SCAR-L";
        }

        if (s.contains("M416") && getConfig("M416")) { mTextPaint.setARGB(255, 0, 255, 0);
            return "M416";
        }

        if (s.contains("M16A4") && getConfig("M16A4")) { mTextPaint.setARGB(255, 0, 255, 0);
            return "M16A-4";
        }

        if (s.contains("Mk47") && getConfig("Mk47 Mutant")) { mTextPaint.setARGB(255, 247, 99, 245);
            return "Mk47 Mutant";
        }

        if (s.contains("G36") && getConfig("G36C")) { mTextPaint.setARGB(255, 116, 227, 123);
            return "G36C";
        }

        if (s.contains("QBZ") && getConfig("QBZ")) { mTextPaint.setARGB(255, 0, 255, 0);
            return "QBZ";
        }

        if (s.contains("AKM") && getConfig("AKM")) { mTextPaint.setARGB(255, 153, 165, 0);
            return "AKM";
        }

        if (s.contains("Groza") && getConfig("Groza")) { mTextPaint.setARGB(255, 153, 165, 0);
            return "Groza";
        }

        if (s.contains("PP19") && getConfig("Bizon")) { mTextPaint.setARGB(255, 255, 246, 0);
            return "Bizon";
        }

        if (s.contains("TommyGun") && getConfig("TommyGun")) { mTextPaint.setARGB(255, 0, 0, 207);
            return "TommyGun";
        }

        if (s.contains("MP5K") && getConfig("MP5K")) { mTextPaint.setARGB(255, 0, 0, 207);
            return "MP5K";
        }

        if (s.contains("UMP9") && getConfig("UMP")) { mTextPaint.setARGB(255, 0, 0, 207);
            return "UMP";
        }

        if (s.contains("Vector") && getConfig("Vector")) { mTextPaint.setARGB(255, 233, 0, 207);
            return "Vector";
        }

        if (s.contains("MachineGun_Uzi") && getConfig("Uzi")) { mTextPaint.setARGB(255, 233, 0, 207);
            return "Uzi";
        }

        if (s.contains("DP28") && getConfig("DP28")) { mTextPaint.setARGB(255, 255, 165, 0);
            return "DP28";
        }

        if (s.contains("M249") && getConfig("M249")) { mTextPaint.setARGB(255, 0, 255, 0);
            return "M249";
        }

        //snipers

        if (s.contains("AWM") && getConfig("AWM")) { mTextPaint.setARGB(255, 153, 165, 0);
            return "AWM";
        }

        if (s.contains("QBU") && getConfig("QBU")) { mTextPaint.setARGB(255, 207, 207, 207);
            return "QBU";
        }

        if (s.contains("SLR") && getConfig("SLR")) { mTextPaint.setARGB(255, 43, 26, 28);
            return "SLR";
        }

        if (s.contains("SKS") && getConfig("SKS")) { mTextPaint.setARGB(255, 153, 165, 0);
            return "SKS";
        }

        if (s.contains("Mini14") && getConfig("Mini14")) { mTextPaint.setARGB(255, 0, 255, 0);
            return "Mini14";
        }

        if (s.contains("M24") && getConfig("M24")) { mTextPaint.setARGB(255, 153, 165, 0);
            return "M24";
        }

        if (s.contains("Kar98k") && getConfig("Kar98k")) { mTextPaint.setARGB(255, 153, 165, 0);
            return "Kar98k";
        }

        if (s.contains("VSS") && getConfig("VSS")) { mTextPaint.setARGB(255, 255, 43, 0);
            return "VSS";
        }

        if (s.contains("Win94") && getConfig("Win94")) { mTextPaint.setARGB(255, 207, 207, 207);
            return "Win94";
        }

        if (s.contains("Mk14") && getConfig("Mk14")) { mTextPaint.setARGB(255, 153, 0, 0);
            return "Mk14";
        }

//shotguns and hand weapons
        if (s.contains("S12K") && getConfig("S12K")) { mTextPaint.setARGB(255, 153, 109, 109);
            return "S12K";
        }

        if (s.contains("DBS") && getConfig("DBS")) { mTextPaint.setARGB(255, 153, 109, 109);
            return "DBS";
        }

        if (s.contains("S686") && getConfig("S686")) { mTextPaint.setARGB(255, 153, 109, 109);
            return "S686";
        }

        if (s.contains("S1897") && getConfig("S1897")) { mTextPaint.setARGB(255, 153, 109, 109);
            return "S1897";
        }

        if (s.contains("Sickle") && getConfig("Sickle")) { mTextPaint.setARGB(255, 102, 74, 74);
            return "Sickle";
        }

        if (s.contains("Machete") && getConfig("Machete")) { mTextPaint.setARGB(255, 102, 74, 74);
            return "Machete";
        }

        if (s.contains("Cowbar") && getConfig("Cowbar")) { mTextPaint.setARGB(255, 102, 74, 74);
            return "Cowbar";
        }

        if (s.contains("CrossBow") && getConfig("CrossBow")) { mTextPaint.setARGB(255, 102, 74, 74);
            return "CrossBow";
        }

        if (s.contains("Pan") && getConfig("Pan")) { mTextPaint.setARGB(255, 102, 74, 74);
            return "Pan";
        }

        //pistols

        if (s.contains("SawedOff") && getConfig("SawedOff")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "SawedOff";
        }

        if (s.contains("R1895") && getConfig("R1895")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "R1895";
        }

        if (s.contains("Vz61") && getConfig("Vz61")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "Vz61";
        }

        if (s.contains("P92") && getConfig("P92")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "P92";
        }

        if (s.contains("P18C") && getConfig("P18C")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "P18C";
        }

        if (s.contains("R45") && getConfig("R45")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "R45";
        }

        if (s.contains("P1911") && getConfig("P1911")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "P1911";
        }

        if (s.contains("DesertEagle") && getConfig("Desert Eagle")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "DesertEagle";
        }


        //Ammo
        if (s.contains("Ammo_762mm") && getConfig("7.62")) { mTextPaint.setARGB(255, 92, 36, 28);
            return "7.62";
        }

        if (s.contains("Ammo_45AC") && getConfig("45ACP")) { mTextPaint.setColor(Color.LTGRAY);
            return "45ACP";
        }

        if (s.contains("Ammo_556mm") && getConfig("5.56")) { mTextPaint.setColor(Color.GREEN);
            return "5.56";
        }

        if (s.contains("Ammo_9mm") && getConfig("9mm")) { mTextPaint.setColor(Color.YELLOW);
            return "9mm";
        }

        if (s.contains("Ammo_300Magnum") && getConfig("300Magnum")) { mTextPaint.setColor(Color.BLACK);
            return "300Magnum";
        }

        if (s.contains("Ammo_12Guage") && getConfig("12 Guage")) { mTextPaint.setARGB(255, 156, 91, 81);
            return "12 Guage";
        }

        if (s.contains("Ammo_Bolt") && getConfig("Arrow")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "Arrow";
        }

        //bag helmet vest
        if (s.contains("Bag_Lv3") && getConfig("Bag L 3")) { mTextPaint.setARGB(255, 36, 83, 255);
            return "Bag lvl 3";
        }

        if (s.contains("Bag_Lv1")  && getConfig("Bag L 1")) { mTextPaint.setARGB(255, 127, 154, 250);
            return "Bag lvl 1";
        }

        if (s.contains("Bag_Lv2") && getConfig("Bag L 2")) { mTextPaint.setARGB(255, 77, 115, 255);
            return "Bag lvl 2";
        }

        if (s.contains("Armor_Lv2") && getConfig("Vest L 2")) { mTextPaint.setARGB(255, 77, 115, 255);
            return "Vest lvl 2";
        }


        if (s.contains("Armor_Lv1") && getConfig("Vest L 1")) { mTextPaint.setARGB(255, 127, 154, 250);
            return "Vest lvl 1";
        }


        if (s.contains("Armor_Lv3") && getConfig("Vest L 3")) { mTextPaint.setARGB(255, 36, 83, 255);
            return "Vest lvl 3";
        }


        if (s.contains("Helmet_Lv2") && getConfig("Helmet 2")) { mTextPaint.setARGB(255, 77, 115, 255);
            return "Helmet lvl 2";
        }

        if (s.contains("Helmet_Lv1") && getConfig("Helmet 1")) { mTextPaint.setARGB(255, 127, 154, 250);
            return "Helmet lvl 1";
        }

        if (s.contains("Helmet_Lv3") && getConfig("Helmet 3")) { mTextPaint.setARGB(255, 36, 83, 255);
            return "Helmet lvl 3";
        }

        //Healthkits
        if (s.contains("Pills") && getConfig("PainKiller")) { mTextPaint.setARGB(255, 227, 91, 54);
            return "Painkiller";
        }

        if (s.contains("Injection") && getConfig("Adrenaline")) { mTextPaint.setARGB(255,204, 193, 190);
            return "Adrenaline";
        }

        if (s.contains("Drink") && getConfig("Energy Drink")) { mTextPaint.setARGB(255, 54, 175, 227);
            return "Energy Drink";
        }

        if (s.contains("Firstaid") && getConfig("FirstAidKit")) { mTextPaint.setARGB(255, 194, 188, 109);
            return "FirstAidKit";
        }

        if (s.contains("Bandage") && getConfig("Bandage")) { mTextPaint.setARGB(255, 43, 189, 48);
            return "Bandage";
        }

        if (s.contains("FirstAidbox") && getConfig("Medkit")) { mTextPaint.setARGB(255, 0, 171, 6);
            return "Medkit";
        }

        //Throwables
        if (s.contains("Grenade_Stun") && getConfig("Stung")) { mTextPaint.setARGB(255,204, 193, 190);
            return "Stung";
        }

        if (s.contains("Grenade_Shoulei") && getConfig("Grenade")) { mTextPaint.setARGB(255, 2, 77, 4);
            return "Grenade";
        }

        if (s.contains("Grenade_Smoke") && getConfig("Smoke")) { mTextPaint.setColor(Color.WHITE);
            return "Smoke";
        }

        if (s.contains("Grenade_Burn") && getConfig("Molotov")) { mTextPaint.setARGB(255, 230, 175, 64);
            return "Molotov";
        }


        //others
        if (s.contains("Large_FlashHider") && getConfig("Flash Hider Ar")) { mTextPaint.setARGB(255, 255, 213, 130);
            return "Flash Hider Ar";
        }

        if (s.contains("QK_Large_C") && getConfig("Ar Compensator")) { mTextPaint.setARGB(255, 255, 213, 130);
            return "Ar Compensator";
        }

        if (s.contains("Mid_FlashHider") && getConfig("Flash Hider SMG")) { mTextPaint.setARGB(255, 255, 213, 130);
            return "Flash Hider SMG";
        }

        if (s.contains("QT_A_") && getConfig("Tactical Stock")) { mTextPaint.setARGB(255, 158, 222, 195);
            return "Tactical Stock";
        }

        if (s.contains("DuckBill") && getConfig("Duckbill")) { mTextPaint.setARGB(255, 158, 222, 195);
            return "DuckBill";
        }

        if (s.contains("Sniper_FlashHider") && getConfig("Flash Hider Snp")) { mTextPaint.setARGB(255, 158, 222, 195);
            return "Flash Hider Sniper";
        }

        if (s.contains("Mid_Suppressor") && getConfig("Suppressor SMG")) { mTextPaint.setARGB(255, 158, 222, 195);
            return "Suppressor SMG";
        }

        if (s.contains("HalfGrip") && getConfig("Half Grip")) { mTextPaint.setARGB(255, 155, 189, 222);
            return "Half Grip";
        }


        if (s.contains("Choke") && getConfig("Choke")) { mTextPaint.setARGB(255, 155, 189, 222);
            return "Choke";
        }

        if (s.contains("QT_UZI") && getConfig("Stock Micro UZI")) { mTextPaint.setARGB(255, 155, 189, 222);
            return "Stock Micro UZI";
        }

        if (s.contains("QK_Sniper") && getConfig("SniperCompensator")) { mTextPaint.setARGB(255, 60, 127, 194);
            return "Sniper Compensator";
        }

        if (s.contains("Sniper_Suppressor") && getConfig("Sup Sniper")) { mTextPaint.setARGB(255, 60, 127, 194);
            return "Suppressor Sniper";
        }

        if (s.contains("Large_Suppressor") && getConfig("Suppressor Ar")) { mTextPaint.setARGB(255, 60, 127, 194);
            return "Suppressor Ar";
        }


        if (s.contains("Sniper_EQ_") && getConfig("Ex.Qd.Sniper")) { mTextPaint.setARGB(255, 193, 140, 222);
            return "Ex.Qd.Sniper";
        }

        if (s.contains("Mid_Q_") && getConfig("Qd.SMG")) { mTextPaint.setARGB(255, 193, 163, 209);
            return "Qd.SMG";
        }

        if (s.contains("Mid_E_") && getConfig("Ex.SMG")) { mTextPaint.setARGB(255, 193, 163, 209);
            return "Ex.SMG";
        }

        if (s.contains("Sniper_Q_") && getConfig("Qd.Sniper")) { mTextPaint.setARGB(255, 193, 163, 209);
            return "Qd.Sniper";
        }

        if (s.contains("Sniper_E_") && getConfig("Ex.Sniper")) { mTextPaint.setARGB(255, 193, 163, 209);
            return "Ex.Sniper";
        }

        if (s.contains("Large_E_") && getConfig("Ex.Ar")) { mTextPaint.setARGB(255, 193, 163, 209);
            return "Ex.Ar";
        }

        if (s.contains("Large_EQ_") && getConfig("Ex.Qd.Ar")) { mTextPaint.setARGB(255, 193, 140, 222);
            return "Ex.Qd.Ar";
        }

        if (s.contains("Large_Q_") && getConfig("Qd.Ar")) { mTextPaint.setARGB(255, 193, 163, 209);
            return "Qd.Ar";
        }

        if (s.contains("Mid_EQ_") && getConfig("Ex.Qd.SMG")) { mTextPaint.setARGB(255, 193, 140, 222);
            return "Ex.Qd.SMG";
        }

        if (s.contains("Crossbow_Q") && getConfig("Quiver CrossBow")) { mTextPaint.setARGB(255, 148, 121, 163);
            return "Quiver CrossBow";
        }

        if (s.contains("ZDD_Sniper") && getConfig("Bullet Loop")) { mTextPaint.setARGB(255, 148, 121, 163);
            return "Bullet Loop";
        }


        if (s.contains("ThumbGrip") && getConfig("Thumb Grip")) { mTextPaint.setARGB(255, 148, 121, 163);
            return "Thumb Grip";
        }

        if (s.contains("Lasersight") && getConfig("Laser Sight")) { mTextPaint.setARGB(255, 148, 121, 163);
            return "Laser Sight";
        }

        if (s.contains("Angled") && getConfig("Angled Grip")) { mTextPaint.setARGB(255, 219, 219, 219);
            return "Angled Grip";
        }

        if (s.contains("LightGrip") && getConfig("Light Grip")) { mTextPaint.setARGB(255, 219, 219, 219);
            return "Light Grip";
        }

        if (s.contains("Vertical") && getConfig("Vertical Grip")) { mTextPaint.setARGB(255, 219, 219, 219);
            return "Vertical Grip";
        }

        if (s.contains("GasCan") && getConfig("Gas Can")) { mTextPaint.setARGB(255, 255, 143, 203);
            return "Gas Can";
        }

        if (s.contains("Mid_Compensator") && getConfig("Compensator SMG")) { mTextPaint.setARGB(255, 219, 219, 219);
            return "Compensator SMG";
        }


        //special
        if (s.contains("Flare") && getConfig("Flare Gun")) { mTextPaint.setARGB(255, 242, 63, 159);
            return "Flare Gun";
        }

        if (s.contains("Ghillie") && getConfig("Ghillie Suit")) { mTextPaint.setARGB(255, 139, 247, 67);
            return "Ghillie Suit";
        }
        if (s.contains("CheekPad") && getConfig("CheekPad")) { mTextPaint.setARGB(255, 112, 55, 55);
            return "CheekPad";
        }
        if ( s.contains("PickUpListWrapperActor") && getConfig("Crate")) { mTextPaint.setARGB(255, 132, 201, 66);
            return "Crate";
        }
        if ((s.contains("AirDropPlane") )&& getConfig("DropPlane")) { mTextPaint.setARGB(255, 224, 177, 224);
            return "DropPlane";
        }
        if ((s.contains("AirDrop")  )&& getConfig("AirDrop")) { mTextPaint.setARGB(255, 255, 10, 255);
            return "AirDrop";
        }
        //return s;
        return null;

    }
    private String getWeapon(int id){
        if(id==101006)
            return "AUG";

        if(id==101008)
            return "M762" ;

        if(id==101003)
            return "SCAR-L";

        if(id==101004)
            return "M416";

        if(id==101002)
            return "M16A-4";

        if(id==101009)
            return "Mk47 Mutant";

        if(id==101010)
            return "G36C";

        if(id==101007)
            return "QBZ";

        if(id==101001)
            return "AKM";

        if(id==101005)
            return "Groza";

        if(id==102005)
            return "Bizon";

        if(id==102004)
            return "TommyGun";

        if(id==102007)
            return "MP5K";

        if(id==102002)
            return "UMP";

        if(id==102003)
            return "Vector";

        if(id==102001)
            return "Uzi";

        if(id==105002)
            return "DP28";

        if(id==105001)
            return "M249";

        //snipers

        if(id==103003)
            return "AWM";

        if(id==103010)
            return "QBU";

        if(id==103009)
            return "SLR";

        if(id==103004)
            return "SKS";

        if(id==103006)
            return "Mini14";

        if(id==103002)
            return "M24";

        if(id==103001)
            return "Kar98k";

        if(id==103005)
            return "VSS";

        if(id==103008)
            return "Win94";

        if(id==103007)
            return "Mk14";

//shotguns and hand weapons
        if(id==104003)
            return "S12K";

        if(id==104004)
            return "DBS";

        if(id==104001)
            return "S686";

        if(id==104002)
            return "S1897";

        if(id==108003)
            return "Sickle";

        if(id==108001)
            return "Machete";

        if(id==108002)
            return "Crowbar";

        if(id==107001)
            return "CrossBow";

        if(id==108004)
            return "Pan";

        //pistols

        if(id==106006)
            return "SawedOff";

        if(id==106003)
            return "R1895";

        if(id==106008)
            return "Vz61";

        if(id==106001)
            return "P92";

        if(id==106004)
            return "P18C";

        if(id==106005)
            return "R45";

        if(id==106002)
            return "P1911";

        if(id==106010)
            return "DesertEagle";

        return null;

    }
    private String getVehicleName(String s){
        if(s.contains("Buggy") && getConfig("Buggy")) {  mTextPaint.setARGB(255, 255, 11, 12);
            return "Buggy";
        }

        if(s.contains("UAZ") && getConfig("UAZ")) {  mTextPaint.setARGB(255, 0, 255, 255);
            return "UAZ";
        }


        if(s.contains("MotorcycleC") && getConfig("Trike") ) {
            mTextPaint.setARGB(255, 0, 0, 255);
            return "Trike";
        }

        if(s.contains("Motorcycle") && getConfig("Bike")) {  mTextPaint.setARGB(255, 255, 255, 90);
            return "Bike";
        }

        if(s.contains("Dacia") && getConfig("Dacia")) {  mTextPaint.setARGB(255, 10, 255, 255);
            return "Dacia";
        }

        if(s.contains("AquaRail") && getConfig("Jet")) {  mTextPaint.setARGB(255, 28, 255, 165);
            return "Jet";
        }

        if(s.contains("PG117") && getConfig("Boat")) {  mTextPaint.setARGB(255, 165, 255, 110);
            return "Boat";
        }

        if(s.contains("MiniBus") && getConfig("Bus")) {  mTextPaint.setARGB(255, 100, 155, 255);
            return "Bus";
        }

        if(s.contains("Mirado") && getConfig("Mirado")) {  mTextPaint.setARGB(255, 90, 255, 120);
            return "Mirado";
        }

        if(s.contains("Scooter") && getConfig("Scooter")) {  mTextPaint.setARGB(255, 0, 255, 255);
            return "Scooter";
        }

        if(s.contains("Rony") && getConfig("Rony")) {  mTextPaint.setARGB(255, 10, 130, 255);
            return "Rony";
        }

        if(s.contains("Snowbike") && getConfig("Snowbike")) {  mTextPaint.setARGB(255, 0, 0, 0);
            return "Snowbike";
        }

        if(s.contains("Snowmobile") && getConfig("Snowmobile")) {  mTextPaint.setARGB(255, 0, 125, 120);
            return "Snowmobile";
        }

        if(s.contains("Tuk") && getConfig("Tempo")) {  mTextPaint.setARGB(255, 60, 111, 70);
            return "Tempo";
        }

        if(s.contains("PickUp") && getConfig("Truck")) {  mTextPaint.setARGB(255, 40, 60, 255);
            return "Truck";
        }

        if(s.contains("BRDM") && getConfig("BRDM")) {  mTextPaint.setARGB(255, 255, 10, 222);
            return "BRDM";
        }

        if(s.contains("LadaNiva") && getConfig("LadaNiva")) {  mTextPaint.setARGB(255, 222, 167, 198);
            return "LadaNiva";
        }

        if(s.contains("Bigfoot") && getConfig("Monster Truck")) {  mTextPaint.setARGB(255, 0, 189, 255);
            return "Monster Truck";
        }

        return "";
    }

    public static Bitmap scale(Bitmap bitmap, int maxWidth, int maxHeight) {
        // Determine the constrained dimension, which determines both dimensions.
        int width;
        int height;
        float widthRatio = (float)bitmap.getWidth() / maxWidth;
        float heightRatio = (float)bitmap.getHeight() / maxHeight;
        // Width constrained.
        if (widthRatio >= heightRatio) {
            width = maxWidth;
            height = (int)(((float)width / bitmap.getWidth()) * bitmap.getHeight());
        }
        // Height constrained.
        else {
            height = maxHeight;
            width = (int)(((float)height / bitmap.getHeight()) * bitmap.getWidth());
        }
        Bitmap scaledBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        float ratioX = (float)width / bitmap.getWidth();
        float ratioY = (float)height / bitmap.getHeight();
        float middleX = width / 2.0f;
        float middleY = height / 2.0f;
        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));
        return scaledBitmap;
    }
}
