package com.tencent.qq;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.CompoundButton.*;
import java.io.*;
import com.tencent.qq.FloatLogo;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.lang.Process;
import java.nio.file.Paths;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import android.util.Log;
import android.widget.Button;
import android.os.HandlerThread;
import android.content.Intent;
import android.graphics.Point;
import android.view.WindowManager;

import android.view.Display;

import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity implements OnClickListener
{
    
	String gameName="com.tencent.ig";
	static int gameType=1;
	static int bit=1;
	
	  static {
        System.loadLibrary("Putri");
    }
    

    private boolean isDisplay = false;
    private boolean isMenuDis = false;
	//WindowManager.LayoutParams params;
	Context ctx;
	View menu;
	
	int mx,my;
    
public static String socket;
	public String MemHack;
    Process process;
	
	public String daemonPath;
	public String daemonPath64;

    public String daemonPathIND32;

	public String daemonPathIND64;
    
    private RadioButton radiobutton4;
    private RadioButton radiobutton6;
    private RadioButton radiobutton5;
	private RadioButton radiobutton3;
	
	
	static boolean isRoot = false;
	static boolean isNoroot = false;
	static boolean rootMod = false;

	static boolean is32 = false;
	static boolean is64 = false;
	static boolean bitMod = false;

	static boolean isIND = false;
	static boolean isInt = true;
	static boolean verMod = true;
	
	String indMEM;

	public boolean onESP = false;
	public boolean isDaemon = false;
	
	private RadioGroup radiogroup2,radiogroup3;
	
	public Button mbutton1,mbutton2,mbutton5,mbutton4,mbutton6,mbutton3;
	TextView telegram,stat,partners,aboutninja,logouch;
    TextView copyright,version;
    Button webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		CheckFloatViewPermission();
		initialization();
		
		
		copyright = findViewById(R.id.version_info);
        copyright.setText("Powered By PutriMod | Apk Version 1.5");
        
		ctx = this;
		
	
		if(!isConfigExist())
		{
			Init();
		}

        radiogroup2 = (RadioGroup) findViewById(R.id.radioMod);
		radiogroup3 = (RadioGroup) findViewById(R.id.radioBit);

		mbutton1=(Button)findViewById(R.id.b01);
        mbutton2=(Button)findViewById(R.id.b02);
        

        mbutton1.setOnClickListener(this);
		mbutton2.setOnClickListener(this);
       
            


		radiobutton3 = (RadioButton) findViewById(R.id.radiobutton3);
		radiobutton4 = (RadioButton) findViewById(R.id.radiobutton4);
		radiobutton5 = (RadioButton) findViewById(R.id.radiobutton5);
        telegram = findViewById(R.id.telegram);
        
        telegram.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://t.me/putrimod");
            }
        });

              Button kr = (Button)findViewById(R.id.kr);
                     kr.setOnClickListener(new OnClickListener(){
                        @Override
				public void onClick(View p1) {
			        RunCPP("/kr.sh");
					Toast toast=Toast.makeText(getApplicationContext(),"Succesfuly ✨",Toast.LENGTH_SHORT);  
					toast.show();  
				}
		});
		
		
              Button gl = (Button)findViewById(R.id.gl);
                     gl.setOnClickListener(new OnClickListener(){
                        @Override
				public void onClick(View p1) {
			        RunCPP("/gl.sh");
					Toast toast=Toast.makeText(getApplicationContext(),"Succesfuly ✨",Toast.LENGTH_SHORT);  
					toast.show();  
				}
		});
        Button Clear = (Button)findViewById(R.id.Clear);
                     Clear.setOnClickListener(new OnClickListener(){
                        @Override
				public void onClick(View p1) {
			        RunCPP("/Clear.sh");
					Toast toast=Toast.makeText(getApplicationContext(),"Succesfuly ✨",Toast.LENGTH_SHORT);  
					toast.show();  
				}
		});
    
	

        final String[] vers = {"Global", "Korea", "Vietnam", "Taiwan"};
        final Spinner spin = findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vers);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (spin.getSelectedItem().toString().equals("Taiwan")){
                        gameName = "com.rekoo.pubgm";
                        gameType=4;
                    }
                    else if (spin.getSelectedItem().toString().equals("Korea")){
                        gameName = "com.pubg.krmobile";
                        gameType=2;
                    }
                    else if (spin.getSelectedItem().toString().equals("Vietnam")){
                        gameName = "com.vng.pubgmobile";
                        gameType=3;
                    }
                    else{
                        gameName = "com.tencent.ig";
                        gameType=1;
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        
        	


	

			
		radiogroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() 
			{
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					// checkedId is the RadioButton selected
					switch (checkedId) {
						case R.id.radiobutton3:
                            isRoot = true;
							ExecuteElf("su -c");
							isNoroot = false;

							rootMod = true;

							break;

						case R.id.radiobutton4:
                            isNoroot = true;
							isRoot = false;

							rootMod = true;

							break;
					}
				}
			});

		radiogroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() 
			{
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					// checkedId is the RadioButton selected
					switch (checkedId) {
						case R.id.radiobutton5:
                            is32 = true;
							is64 = false;

							bitMod = true;

							break;

						
					}
				}
			});
			
		ExecuteElf("su -c");
		
		loadAssets();	
		loadAssets64();

		socket = daemonPath;
		
    }
        
	private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

	public void onClick(View v)
	{
	switch (v.getId()) 
		{ 
		case R.id.b01: 


				if (isDisplay == false && isMenuDis == false) 
				{ 
				   
					if(!rootMod || !verMod || !bitMod)
					{
						Toast.makeText(MainActivity.this,"Please Select Settings First !!",Toast.LENGTH_LONG).show();
					}


					else if (isDisplay == false && isMenuDis == false) 
					{ 


						if(isNoroot)
						{
							if(is32)
							{
								if(isIND)
								{
									socket = daemonPathIND32;
									//				MemHack = daemonPathMEM;
								}
								else
								{
									socket = daemonPath;
									//			MemHack = daemonPathMEM;
								}
							}
							else if(is64)
							{

								if(isIND)
								{
									//Toast.makeText(MainActivity.this,"64bit IND version is currently not supported !!",Toast.LENGTH_LONG).show();
									socket = daemonPathIND64;
									//				MemHack = daemonPath64;

								}
								else
								{
									//			MemHack = daemonPathMEM;
									socket = daemonPath64;
								}

							}
						}

						if(isRoot)
						{
							if(is32)
							{
								if(isIND)
								{
									socket = "su -c " + daemonPathIND32;
									//				MemHack = "su -c " + daemonPathMEM;
								}
								else
								{
									socket = "su -c " + daemonPath;
									//			MemHack = "su -c " + daemonPathMEM;
								}
							}
							else if(is64)
							{

								if(isIND)
								{
									//Toast.makeText(MainActivity.this,"64bit IND version is currently not supported !!",Toast.LENGTH_LONG).show();
									socket = "su -c " + daemonPathIND64;
									//	MemHack = "su -c " + daemonPath64;

								}
								else
								{
									socket = "su -c " + daemonPath64;
									//		MemHack = "su -c " + daemonPath64;
								}

							}
						}


						startPatcher(); 
						startService(new Intent(this, Overlay.class));

						// ShowFloatWindow();   


						isDisplay = true;
						

						//startDaemon();
						
						isDaemon = true;
						//Toast.makeText(MainActivity.this,socket,Toast.LENGTH_LONG).show();


					} 

					else  
					{ 

						Toast.makeText(MainActivity.this,"Already Started !!",Toast.LENGTH_LONG).show();

					} 
					
				
				// FloatButton();   
					//  startPatcher(); 
				} 

				else  
				{ 
				
				
			    } 

				break;

				case R.id.b02:

				if(isDisplay == true)
				{
					
					isDisplay = false;
					isMenuDis = false;
					isDaemon = false;
					
					//process.destroy();
					//Stop();
					stopService(new Intent(this, FloatLogo.class));
					stopService(new Intent(this, Overlay.class));

					

				}
				break;
				
			}
		}
    private boolean MvAssets(String outPath, String fileName)
    {
        File file = new File(outPath);
        if (!file.exists())
        {
            if (!file.mkdirs())
            {
                Log.e("--Method--", "copyAssetsSingleFile: cannot create directory.");
                return false;
            }
        }
        try
        {
            InputStream inputStream = getAssets().open(fileName);
            File outFile = new File(file, fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = inputStream.read(buffer)))
            {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            inputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

	
        
    private void initialization(){
        MvAssets(getFilesDir() + "/","gl.sh");
        MvAssets(getFilesDir() + "/","Clear.sh");
        MvAssets(getFilesDir() + "/","kr.sh");
        Handler Handler = new Handler();
        Handler.postDelayed(new Runnable() {
                @Override
                public void run() {}}, 6000);
    }

    
	
	public void CheckFloatViewPermission()
	{
		if (!Settings.canDrawOverlays(this))
		{
			Toast.makeText(this,"Enable Floating Permission ",Toast.LENGTH_LONG).show();
			startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
		}
	}
	
	private boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                    if (FloatLogo.class.getName().equals(service.service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }
	
	
	
	
	void startPatcher() {
        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 123);
        } else {
            startFloater();
        }
    }

    private void startFloater() {
        if (!isServiceRunning()) {
                startService(new Intent(this, FloatLogo.class));
        } else {
            Toast.makeText(this, "Service Already Running!", Toast.LENGTH_SHORT).show();
        }
    }
	
		private void ExecuteElf(String shell)
	{
	String s=shell;

	try
		{
		Runtime.getRuntime().exec(s,null,null);
		}
		catch (Exception e)
			{
		e.printStackTrace();
		}
		}
        
        public void RunCPP(String path)
            {
                try
                    {
                        ExecuteElf("chmod 777 "+getFilesDir()+path);
                        ExecuteElf(getFilesDir()+path);
                        ExecuteElf("su -c chmod 777 "+getFilesDir()+path);
                        ExecuteElf("su -c "+getFilesDir()+path);
                    }
                catch(Exception e){
                    }
            }

		
	public void Shell(String str) {

	DataOutputStream dataOutputStream = null;
	try {
		process = Runtime.getRuntime().exec(str);
        } catch (IOException e) {
		e.printStackTrace();
            process = null;
        }
			if (process != null) {
            dataOutputStream = new DataOutputStream(process.getOutputStream());
        }
        try {
            dataOutputStream.flush();
        } catch (IOException e2) {
		e2.printStackTrace();
			}
        try {
            process.waitFor();
        } catch (InterruptedException e3) {
		e3.printStackTrace();
			}
		}
		
	public void startDaemon(){
        new Thread()  
		{
			public void run()
			{
				Shell(socket);

		    }
        }.start();
    }
	
	public void loadAssets()
	{
		String filepath =Environment.getExternalStorageDirectory()+"/Android/data/.tyb";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filepath);
			byte[] buffer = "DO NOT DELETE".getBytes();
			fos.write(buffer, 0, buffer.length);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		String pathf = getFilesDir().toString()+"/sock";
		 try
		 {	
		 OutputStream myOutput = new FileOutputStream(pathf);
		 byte[] buffer = new byte[1024];
		 int length;
		 InputStream myInput = getAssets().open("sock");
		 while ((length = myInput.read(buffer)) > 0) {
		 myOutput.write(buffer, 0, length);
		 }
		 myInput.close();
		 myOutput.flush();
		 myOutput.close();

		 }

		 catch (IOException e)
		 { 
		 }


		daemonPath = getFilesDir().toString()+"/sock";


		try{
			Runtime.getRuntime().exec("chmod 777 "+daemonPath);
		}
		catch (IOException e)
		{ 
		}

	}
		
	public void loadAssets64()
	{

		String pathf = getFilesDir().toString()+"/sock64";
		 try
		 {	
		 OutputStream myOutput = new FileOutputStream(pathf);
		 byte[] buffer = new byte[1024];
		 int length;
		 InputStream myInput = getAssets().open("sock64");
		 while ((length = myInput.read(buffer)) > 0) {
		 myOutput.write(buffer, 0, length);
		 }
		 myInput.close();
		 myOutput.flush();
		 myOutput.close();

		 }

		 catch (IOException e)
		 { 
		 }


		daemonPath64 = getFilesDir().toString()+"/sock64";


		try{
			Runtime.getRuntime().exec("chmod 777 "+daemonPath64);
		}
		catch (IOException e)
		{ 
		}

	}
	
	void Init(){
        SharedPreferences sp=getApplicationContext().getSharedPreferences("espValue", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed= sp.edit();
        ed.putInt("fps",30);
        ed.putBoolean("Box",true);
        ed.putBoolean("Line",true);
        ed.putBoolean("Distance",false);
        ed.putBoolean("Health",false);
        ed.putBoolean("Name",false);
        ed.putBoolean("Back Mark",false);
        ed.putBoolean("Skelton",false);
        ed.putBoolean("Grenade Warning",false);
        ed.putBoolean("Enemy Weapon",false);  
        ed.putBoolean("Cross",true);     
        ed.apply();
    }
    boolean isConfigExist(){
        SharedPreferences sp=getApplicationContext().getSharedPreferences("espValue", Context.MODE_PRIVATE);
        return sp.contains("fps");
    }

}

		





	
