package yzzhang.com.opglstudent;

import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏设置
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.activity_main);

        final ActivityManager activityManager=(ActivityManager)getSystemService(ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo=activityManager.getDeviceConfigurationInfo();
        boolean supportsEs2=configurationInfo.reqGlEsVersion>=0x2000;
        Log.e("TAG", "onCreate: ---------------------- supportsEs2 :"+supportsEs2 );
        if(supportsEs2){
            GLSurfaceView view = new GLSurfaceView(this);
            view.setRenderer(new OpenGLRender());
            setContentView(view);
        }
    }
}
