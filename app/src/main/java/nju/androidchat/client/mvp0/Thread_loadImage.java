package nju.androidchat.client.mvp0;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Thread_loadImage extends Thread {
    private ImageView imageView;
    private String path;
    public Thread_loadImage(ImageView imageView,String path){
        this.imageView=imageView;
        this.path=path;
    }
    @Override
    public void run(){
        try {
            URL url = new URL(path);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            }
        } catch (Exception e) {

        }
    }
}
