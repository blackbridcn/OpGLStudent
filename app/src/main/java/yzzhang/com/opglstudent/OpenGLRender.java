package yzzhang.com.opglstudent;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by yzzhang on 2018/1/11.
 */

public class OpenGLRender implements GLSurfaceView.Renderer {

    private Square mSquare;

    //画面创建--》在这里我们主要进行一些初始化工作，比如对透视进行修正、设置清屏所用颜色等
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // 黑色背景
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        // 启用阴影平滑（不是必须的）
        gl.glShadeModel(GL10.GL_SMOOTH);
        // 设置深度缓存
        gl.glClearDepthf(1.0f);
        // 启用深度测试
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // 所作深度测试的类型
        gl.glDepthFunc(GL10.GL_LEQUAL);
        // 对透视进行修正
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        mSquare = new Square();
    }

    // 画面改变--->当设备水平或者垂直变化时调用此方法，设置新的显示比例
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        // 设置画面的大小
        gl.glViewport(0, 0, width, height);
        // 设置投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // 重置投影矩阵
        gl.glLoadIdentity();
        // 设置画面比例
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
        // 选择模型观察矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // 重置模型观察矩阵
        gl.glLoadIdentity();
    }

    //画面绘制---》绘制当前画面
    @Override
    public void onDrawFrame(GL10 gl) {
        // 清除屏幕和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // 在屏幕中移动四个单位
       // gl.glTranslatex(0, 0, 0);
        mSquare.draw(gl);
        //重置当前的模型观察矩阵
        gl.glLoadIdentity();
    }
}
