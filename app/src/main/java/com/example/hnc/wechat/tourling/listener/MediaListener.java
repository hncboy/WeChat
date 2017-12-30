package com.example.hnc.wechat.tourling.listener;

import android.util.Log;

import com.algebra.sdk.OnMediaListener;
import com.algebra.sdk.entity.HistoryRecord;


/**
 * Created by mxf on 2017/6/30.
 */
public class MediaListener implements OnMediaListener {

    private static final MediaListener MEDIA_LISTENER = new MediaListener();

    public static MediaListener getInstance() {
        return MEDIA_LISTENER;
    }


    public interface OnTalkListener {
        void onButtonPressed(int uid, int state);

        void onMediaReceiver(long l, int i, int i1, int i2, int i3);

        void onConfirm(int uid, int ctype, int sid, int tag, boolean enRed);

        void onDeny(int uid, int ctype, int sid);

        void onCatched(HistoryRecord historyRecord);

        void onRecorderMeter(int uid, int level);

        void onRelease(int i, int i1);

    }

    private OnTalkListener mOnTalkListener;

    public void setOnTalkListener(OnTalkListener listener) {
        this.mOnTalkListener = listener;
    }

    /**
     * @param selfUserId
     * @param type
     * @param sessionId
     */
    @Override
    public void onMediaInitializedEnd(int selfUserId, int type, int sessionId) {
//        Log.d(this, "onMediaInitializedEnd");
    }

    /**
     * 用户对讲流程
     * 1.用户按下按键 要开始
     * 6.调用这个方法
     *
     * @param uid
     * @param state
     */
    @Override
    public void onPttButtonPressed(int uid, int state) {
//        Log.d(this, "onPttButtonPressed");
        if (mOnTalkListener != null) {
            mOnTalkListener.onButtonPressed(uid, state);
        }
    }

    /**
     * 2. 暂时不知道什么情况
     * 5. 再次调用它
     *
     * @param l
     * @param i
     * @param i1
     * @param i2
     * @param i3
     */
    @Override
    public void onMediaReceiverReport(long l, int i, int i1, int i2, int i3) {
        if (mOnTalkListener != null) {
            mOnTalkListener.onMediaReceiver(l, i, i1, i2, i3);
        }
    }

    /**
     * 3.确认获得话语权
     *
     * @param uid
     * @param ctype
     * @param sid
     * @param tag
     * @param enRed
     */
    @Override
    public void onTalkRequestConfirm(int uid, int ctype, int sid, int tag, boolean enRed) {
        if (mOnTalkListener != null) {
            mOnTalkListener.onConfirm(uid, ctype, sid, tag, enRed);
        }
    }

    /**
     * 3.捕获新语音
     * 7.捕获新语音
     * x4.捕获新语音
     *
     * @param historyRecord
     */
    @Override
    public void onNewSpeakingCatched(HistoryRecord historyRecord) {
        if (mOnTalkListener != null) {
            mOnTalkListener.onCatched(historyRecord);
        }
    }

    /**
     * 4.麦克风音量
     *
     * @param uid
     * @param level
     */
    @Override
    public void onRecorderMeter(int uid, int level) {
        if (mOnTalkListener != null) {
            mOnTalkListener.onRecorderMeter(uid, level);
        }
    }

    /**
     * 8.释放了话语权
     *
     * @param i
     * @param i1
     */
    @Override
    public void onTalkReleaseConfirm(int i, int i1) {
//        Log.d(this, "onTalkReleaseConfirm");
        if (mOnTalkListener != null) {
            mOnTalkListener.onRelease(i, i1);
        }
    }

    /**
     * 收听流程
     * x1.首先调用了它
     *
     * @param l
     * @param i
     * @param i1
     * @param i2
     * @param i3
     */
    @Override
    public void onMediaSenderReport(long l, int i, int i1, int i2, int i3) {
//        Log.d(this, "onMediaSenderReport");
    }


    /**
     * x2.播放语音
     *
     * @param speaker
     * @param ctype
     * @param session
     * @param tag
     */
    @Override
    public void onStartPlaying(int speaker, int ctype, int session, int tag) {
//        Log.d(this, "onStartPlaying");
    }

    /**
     * x3.播放的音量
     *
     * @param uid
     * @param level
     */
    @Override
    public void onPlayerMeter(int uid, int level) {
//        Log.d(this, "onPlayerMeter");
    }

    /**
     * x5.说完了
     *
     * @param speaker
     * @param tag
     */
    @Override
    public void onThatoneSayOver(int speaker, int tag) {
//        Log.d(this, "onThatoneSayOver");
    }

    /**
     * 语音播放完毕
     *
     * @param tag
     */
    @Override
    public void onPlayStopped(int tag) {
//        Log.d(this, "onPlayStopped");
    }

    /**
     * 未获得话语权
     *
     * @param uid
     * @param ctype
     * @param sid
     */
    @Override
    public void onTalkRequestDeny(int uid, int ctype, int sid) {
//        Log.d(this, "onTalkRequestDeny");
        if (mOnTalkListener != null) {
            mOnTalkListener.onDeny(uid, ctype, sid);
        }
    }

    /**
     * 获得延时的话语权
     *
     * @param uid
     * @param ctype
     * @param sid
     */
    @Override
    public void onTalkRequestQueued(int uid, int ctype, int sid) {
//        Log.d(this, "onTalkRequestQueued");
    }

    /**
     * 发送媒体数据中断
     *
     * @param i
     * @param i1
     */
    @Override
    public void onTalkTransmitBroken(int i, int i1) {
//        Log.d(this, "onTalkTransmitBroken");
    }


    /**
     * 某人在频道中喊话
     *
     * @param speaker
     * @param ctype
     * @param sessionId
     * @param tag
     * @param dur10ms
     */
    @Override
    public void onSomeoneSpeaking(int speaker, int ctype, int sessionId, int tag, int dur10ms) {
//        Log.d(this, "onSomeoneSpeaking");

    }

    /**
     * 某人尝试
     *
     * @param userId
     * @param ctype
     * @param sessionId
     */
    @Override
    public void onSomeoneAttempt(int userId, int ctype, int sessionId) {
//        Log.d(this, "onSomeoneAttempt");
    }

    /**
     * 尝试结束了
     *
     * @param userId
     * @param ctype
     * @param sessionId
     */
    @Override
    public void onThatAttemptQuit(int userId, int ctype, int sessionId) {

    }

    @Override
    public void onPlayLastSpeaking(int i, int i1) {

    }

    @Override
    public void onPlayLastSpeakingEnd(int i) {

    }

    @Override
    public void onMediaSenderCutted(int i, int i1) {
//        Log.d(this, "onMediaSenderCutted");
    }


    @Override
    public void onBluetoothBatteryGet(int i) {

    }

    @Override
    public void onBluetoothConnect(int i) {

    }
}
