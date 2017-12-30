package com.example.hnc.wechat.tourling.manager;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.algebra.sdk.API;
import com.algebra.sdk.AccountApi;
import com.algebra.sdk.ChannelApi;
import com.algebra.sdk.DeviceApi;
import com.algebra.sdk.SessionApi;
import com.algebra.sdk.entity.Contact;

import com.example.hnc.wechat.tourling.listener.AccountListener;
import com.example.hnc.wechat.tourling.listener.ChannelListener;
import com.example.hnc.wechat.tourling.listener.MediaListener;
import com.example.hnc.wechat.tourling.listener.SessionListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by mxf on 2017/12/12.
 */

public class IntercomManager {

    private static final String TAG = "IntercomManager";
    private static final String DEFAULT_PASSWORD = "0xcafe520";

    private DeviceApi mDeviceApi;
    private SessionApi mSessionApi;
    private ChannelApi mChannelApi;
    private AccountApi mAccountApi;
    private Contact me;
    private final ExecutorService mExecutor;
    private int mInitCount = 0;
    public final ChannelManager channelManager = new ChannelManager();
    public final SessionManager sessionManager = new SessionManager();

    public IntercomManager() {
        mExecutor = Executors.newSingleThreadExecutor();
    }

    public void start(Context context) {
        boolean init = API.init(context);
//        Log.d(this, "init:" + init);
    }

    public void init(final Context context) {
        checkInitCount();
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                initSimpleApi();
                if (mAccountApi != null) {
                    setListeners();
                    if (mDeviceApi != null) {
                        String deviceID = API.getDeviceID(context);
                        mAccountApi.createVisitor(deviceID, "mxf");
                        getMe();
                    } else {
                        init(context);
                    }
                }
            }
        });
    }

    private void checkInitCount() {
        if (++mInitCount > 10) {
            throw new RuntimeException("初始化失败");
        }
    }

    private void initSimpleApi() {
        mAccountApi = API.getAccountApi();
        mDeviceApi = API.getDeviceApi();
        mSessionApi = API.getSessionApi();
        mChannelApi = API.getChannelApi();
    }

    private void setListeners() {
//        Log.d(this, "channelApi:" + mAccountApi);
//        Log.d(this, "deviceApi:" + mDeviceApi);
        mAccountApi.setOnAccountListener(AccountListener.getInstance());
        mSessionApi.setOnMediaListener(MediaListener.getInstance());
        mSessionApi.setOnSessionListener(SessionListener.getInstance());
        mChannelApi.setOnChannelListener(ChannelListener.getInstance());
    }

    private void getMe() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                me = mAccountApi.whoAmI();
                int count = 0;
                while (me == null) {
                    SystemClock.sleep(2000);
                    me = mAccountApi.whoAmI();
                    if (count++ > 10) {
                        break;
                    }
                }
//                Log.d(this, "selfId：" + me.id + " name：" + me.name);
            }
        });
    }

    public class ChannelManager {

        public void searchChannel(int uid, String name, ChannelListener.OnPubChannelSearchResultListener listener) {
            ChannelListener.getInstance().setOnPubChannelSearchResultListener(listener);
            mChannelApi.searchPublicChannel(uid, name);
        }

        public void createChannel(int selfId, String channelName, ChannelListener.OnPubChannelCreateListener listener) {
            ChannelListener.getInstance().setOnPubChannelCreateListener(listener);
            mChannelApi.createPublicChannel(selfId, channelName, DEFAULT_PASSWORD);
        }

        public void deleteChannel(int uid, int type, int channelId, ChannelListener.OnPubChannelDeletedListener listener) {
            ChannelListener.getInstance().setOnPubChannelDeletedListener(listener);
            mChannelApi.deletePublicChannel(uid, type, channelId);
        }

        public void focusChannel(int uid, int ctype, int cid, ChannelListener.OnPubChannelFocusResultListener listener) {
            ChannelListener.getInstance().setOnPubChannelFocusResultListener(listener);
            mChannelApi.focusPublicChannel(uid, ctype, cid, DEFAULT_PASSWORD);
        }

        public void unfocusChannel(int uid, int ctype, int channelId, ChannelListener.OnPubChannelUnFocusResultListener listener) {
            ChannelListener.getInstance().setOnPubChannelUnFocusResultListener(listener);
            mChannelApi.unfocusPublicChannel(uid, ctype, channelId);
        }

        public void addMemberRemovedListener(ChannelListener.OnChannelMemberRemovedListener listener) {
            ChannelListener.getInstance().setOnChannelMemberRemovedListener(listener);
        }
    }

    public class SessionManager {

        public void newSession(int selfId, int ctype, int cid, SessionListener.OnSessionEstablishedListener listener) {
            SessionListener.getInstance().setOnSessionEstablishedListener(listener);
            mSessionApi.sessionCall(selfId, ctype, cid);
        }

        public void exitSession(int selfId, int ctype, int cid, SessionListener.OnSessionReleasedListener listener) {
            SessionListener.getInstance().setOnSessionReleasedListener(listener);
            mSessionApi.sessionBye(selfId, ctype, cid);
        }

        public void newTalk(int selfId, int ctype, int cid, MediaListener.OnTalkListener listener) {
            MediaListener.getInstance().setOnTalkListener(listener);
            mSessionApi.talkRequest(selfId, ctype, cid);
        }


    }

}
