package com.example.hnc.wechat.tourling.listener;

import com.algebra.sdk.OnSessionListener;
import com.algebra.sdk.entity.Contact;

import java.util.List;

/**
 * Created by mxf on 2017/6/30.
 */
public class SessionListener implements OnSessionListener {

    private static final SessionListener SESSION_LISTENER = new SessionListener();

    public static SessionListener getInstance() {
        return SESSION_LISTENER;
    }

    public interface OnSessionEstablishedListener {
        void onEstablished(int selfUserId, int type, int sessionId);
    }

    private OnSessionEstablishedListener mOnSessionEstablishedListener;

    public void setOnSessionEstablishedListener(OnSessionEstablishedListener listener) {
        this.mOnSessionEstablishedListener = listener;
    }

    /**
     * 会话连接成功
     *
     * @param selfUserId
     * @param type
     * @param sessionId
     */
    @Override
    public void onSessionEstablished(int selfUserId, int type, int sessionId) {
        if (mOnSessionEstablishedListener != null) {
            mOnSessionEstablishedListener.onEstablished(selfUserId, type, sessionId);
        }
    }

    public interface OnSessionReleasedListener {
        void onReleased(int selfUserId, int sessionType, int sessionId);
    }

    private OnSessionReleasedListener mOnSessionReleasedListener;

    public void setOnSessionReleasedListener(OnSessionReleasedListener listener) {
        this.mOnSessionReleasedListener = listener;
    }

    /**
     * @param selfUserId
     * @param sessionType
     * @param sessionId
     */
    @Override
    public void onSessionReleased(int selfUserId, int sessionType, int sessionId) {
        if (mOnSessionReleasedListener != null) {
            mOnSessionReleasedListener.onReleased(selfUserId, sessionType, sessionId);
        }
    }

    /**
     * 会话创建成功
     *
     * @param selfUserId
     * @param type
     * @param sessionId
     * @param initiator  会话发起人
     */
    @Override
    public void onSessionGet(int selfUserId, int type, int sessionId, int initiator) {

    }

    /**
     * 会话成员增加
     *
     * @param ctype
     * @param sid
     * @param members
     */
    @Override
    public void onSessionPresenceAdded(int ctype, int sid, List<Contact> members) {

    }

    /**
     * 频道成员移除
     *
     * @param ctype
     * @param sid
     * @param ids
     */
    @Override
    public void onSessionPresenceRemoved(int ctype, int sid, List<Integer> ids) {

    }

    @Override
    public void onDialogEstablished(int i, int i1, int i2, List<Integer> list) {

    }

    @Override
    public void onDialogLeaved(int i, int i1) {

    }

    @Override
    public void onDialogPresenceAdded(int i, int i1, List<Integer> list) {

    }

    @Override
    public void onDialogPresenceRemoved(int i, int i1, List<Integer> list) {

    }
}
