package nju.androidchat.client.mvp0;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nju.androidchat.client.ClientMessage;

@AllArgsConstructor
public class Mvp0TalkPresenter implements Mvp0Contract.Presenter {

    private Mvp0Contract.Model mvp0TalkModel;
    private Mvp0Contract.View iMvp0TalkView;

    @Override
    public void loadImage(String path, ImageView imageView) {
        Thread_loadImage th=new Thread_loadImage(imageView,path);
        th.start();
    }


    @Getter
    private List<ClientMessage> clientMessages;

    @Override
    public void sendMessage(String content) {
        ClientMessage clientMessage = mvp0TalkModel.sendInformation(content);
        refreshMessageList(clientMessage);
    }

    @Override
    public void receiveMessage(ClientMessage clientMessage) {
        refreshMessageList(clientMessage);
    }

    @Override
    public String getUsername() {
        return mvp0TalkModel.getUsername();
    }

    private void refreshMessageList(ClientMessage clientMessage) {
        clientMessages.add(clientMessage);
        iMvp0TalkView.showMessageList(clientMessages);
    }

    //撤回消息，Mvp0暂不实现
    @Override
    public void recallMessage(int index0) {

    }

    @Override
    public void start() {

    }

}
