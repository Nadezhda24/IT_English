package com.ntdvv.it_english;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }

    public String makeServiceCall(String reqUrl) {
        URL url;
        String response = "";
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(reqUrl);

            urlConnection = (HttpURLConnection) url
                    .openConnection();
                urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:221.0) Gecko/20100101 Firefox/31.0");
            urlConnection.connect();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                response = response + current;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return response;
    }

    public void urlToBitmap (String img, Context context){
        final Bitmap[] myBitmap = new Bitmap[1];
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    java.net.URL url = new java.net.URL(img);
                    HttpURLConnection connection = (HttpURLConnection) url
                            .openConnection();
                    connection.setDoInput(true);
                    connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:221.0) Gecko/20100101 Firefox/31.0");
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    myBitmap[0] = BitmapFactory.decodeStream(input);

                    ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
                    File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                    directory.mkdirs();
                    File path = new File(directory,img);
                    path.createNewFile();
                    FileOutputStream out = new FileOutputStream(path);
                    myBitmap[0].compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {

        }
    }

    public static Bitmap openImage(String img, Context context){
        ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File path = new File(directory,img);
        Bitmap bmp=BitmapFactory.decodeFile(path.getPath());
        return bmp;
    }

}
