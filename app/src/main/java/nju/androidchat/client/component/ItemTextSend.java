package nju.androidchat.client.component;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StyleableRes;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import lombok.Setter;
import nju.androidchat.client.R;
import nju.androidchat.client.Utils;
import nju.androidchat.client.mvp0.MyImageView;
import nju.androidchat.client.mvp0.Thread_loadImage;

public class ItemTextSend extends LinearLayout implements View.OnLongClickListener {
    @StyleableRes
    int index0 = 0;

    private TextView textView;
    private Context context;
    private UUID messageId;
    @Setter
    private OnRecallMessageRequested onRecallMessageRequested;


    private MyImageView myImageView;
    private String value = "";

    public ItemTextSend(Context context, String text, UUID messageId, OnRecallMessageRequested onRecallMessageRequested) {
        super(context);
        this.context = context;
        inflate(context, R.layout.item_text_send, this);


        this.textView = findViewById(R.id.chat_item_content_text);
        this.myImageView = (MyImageView) findViewById(R.id.chat_item_content_img);
        this.messageId = messageId;
        this.onRecallMessageRequested = onRecallMessageRequested;

        this.setOnLongClickListener(this);


        this.value = text;
        if (text.startsWith("![]({") && text.endsWith("})")) {
            String imgUrl = text.substring(5, text.length() - 2);
            this.textView.setVisibility(INVISIBLE);
            this.myImageView.setVisibility(VISIBLE);
            myImageView.setImageURL(imgUrl);

        } else {
            this.myImageView.setVisibility(INVISIBLE);
            this.textView.setVisibility(VISIBLE);

            setText(text);
        }

    }

    public String getText() {
        return value;
//        return textView.getText().toString();
    }

    public void setText(String text) {
        this.value = text;
        textView.setText(text);
    }

    @Override
    public boolean onLongClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确定要撤回这条消息吗？")
                .setPositiveButton("是", (dialog, which) -> {
                    if (onRecallMessageRequested != null) {
                        onRecallMessageRequested.onRecallMessageRequested(this.messageId);
                    }
                })
                .setNegativeButton("否", ((dialog, which) -> {
                }))
                .create()
                .show();

        return true;


    }



}
