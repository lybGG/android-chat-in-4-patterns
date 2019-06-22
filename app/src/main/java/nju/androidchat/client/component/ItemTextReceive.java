package nju.androidchat.client.component;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StyleableRes;

import java.io.IOException;
import java.util.UUID;

import nju.androidchat.client.R;
import nju.androidchat.client.Utils;
import nju.androidchat.client.mvp0.MyImageView;
import nju.androidchat.client.mvp0.Thread_loadImage;

public class ItemTextReceive extends LinearLayout {


    @StyleableRes
    int index0 = 0;

    private TextView textView;

    private Context context;
    private UUID messageId;
    private OnRecallMessageRequested onRecallMessageRequested;


    private MyImageView myImageView;
    private String value = "";

    public ItemTextReceive(Context context, String text, UUID messageId) {
        super(context);
        this.context = context;
        inflate(context, R.layout.item_text_receive, this);
        this.messageId = messageId;

        this.textView = findViewById(R.id.chat_item_content_text);

        this.myImageView =  (MyImageView) (findViewById(R.id.chat_item_content_img));
        this.value = text;

        if (text.startsWith("![]({") && text.endsWith("})")) {
            String imgUrl = text.substring(5, text.length() - 2);
            this.textView.setVisibility(INVISIBLE);
            this.myImageView.setVisibility(VISIBLE);
            myImageView.setImageURL(imgUrl);
        } else {
            this.textView.setVisibility(VISIBLE);
            this.myImageView.setVisibility(INVISIBLE);
            setText(text);
        }


    }

    public void init(Context context) {

    }

    public String getText() {
        return value;
//        return textView.getText().toString();
    }

    public void setText(String text) {
        this.value=text;
        textView.setText(text);
    }


}
