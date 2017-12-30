package com.example.hnc.wechat.tourling.listener;

import com.algebra.sdk.OnChannelListener;
import com.algebra.sdk.entity.Channel;
import com.algebra.sdk.entity.Contact;

import java.util.List;

/**
 * Created by mxf on 2017/6/28.
 */
public class ChannelListener implements OnChannelListener {

    private static final ChannelListener CHANNEL_LISTENER = new ChannelListener();

    public static ChannelListener getInstance() {
        return CHANNEL_LISTENER;
    }

    /**
     * 获取用户自创频道
     *
     * @param uid
     * @param channel
     * @param list
     */
    @Override
    public void onChannelListGet(int uid, Channel channel, List<Channel> list) {

    }

    /**
     * 获取成员列表
     *
     * @param userId
     * @param ctype
     * @param channelId
     * @param list
     */
    @Override
    public void onChannelMemberListGet(int userId, int ctype, int channelId, List<Contact> list) {

    }

    /**
     * 频道名字改变
     *
     * @param uid
     * @param ctype
     * @param cid
     * @param name
     */
    @Override
    public void onChannelNameChanged(int uid, int ctype, int cid, String name) {

    }

    @Override
    public void onChannelAdded(int userId, int ctype, int channelId, String channelName) {

    }

    @Override
    public void onChannelRemoved(int userId, int ctype, int channelId) {

    }

    /**
     * 频道成员增加
     *
     * @param ctype
     * @param cid
     * @param members
     */
    @Override
    public void onChannelMemberAdded(int ctype, int cid, List<Contact> members) {

    }

    public interface OnChannelMemberRemovedListener {
        void onChannelRemoved(int ctype, int cid, List<Integer> ids);
    }

    private OnChannelMemberRemovedListener mOnChannelMemberRemovedListener;

    public void setOnChannelMemberRemovedListener(OnChannelMemberRemovedListener listener) {
        this.mOnChannelMemberRemovedListener = listener;
    }

    /**
     * 频道成员减少
     *
     * @param ctype
     * @param cid
     * @param ids
     */
    @Override
    public void onChannelMemberRemoved(int ctype, int cid, List<Integer> ids) {
        if (mOnChannelMemberRemovedListener != null) {
            mOnChannelMemberRemovedListener.onChannelRemoved(ctype, cid, ids);
        }
    }

    public interface OnPubChannelCreateListener {
        void onCreated(int uid, int reason, int cid);
    }

    private OnPubChannelCreateListener mOnPubChannelCreateListener;

    public void setOnPubChannelCreateListener(OnPubChannelCreateListener listener) {
        this.mOnPubChannelCreateListener = listener;
    }

    /**
     * 频道创建结果
     *
     * @param uid
     * @param reason
     * @param cid
     */
    @Override
    public void onPubChannelCreate(int uid, int reason, int cid) {
        if (mOnPubChannelCreateListener != null) {
            mOnPubChannelCreateListener.onCreated(uid, reason, cid);
        }
    }

    public interface OnPubChannelSearchResultListener {
        void onSearchResult(int uid, List<Channel> list);
    }

    private OnPubChannelSearchResultListener mOnPubChannelSearchResultListener;

    public void setOnPubChannelSearchResultListener(OnPubChannelSearchResultListener listener) {
        this.mOnPubChannelSearchResultListener = listener;
    }

    /**
     * 频道搜索结果
     *
     * @param uid
     * @param list
     */
    @Override
    public void onPubChannelSearchResult(int uid, List<Channel> list) {
        if (mOnPubChannelSearchResultListener != null) {
            mOnPubChannelSearchResultListener.onSearchResult(uid, list);
        }
    }

    public interface OnPubChannelDeletedListener {
        void onDeleted(int uid, int reason);
    }

    private OnPubChannelDeletedListener mOnPubChannelDeletedListener;

    public void setOnPubChannelDeletedListener(OnPubChannelDeletedListener listener) {
        this.mOnPubChannelDeletedListener = listener;
    }

    @Override
    public void onPubChannelDeleted(int uid, int reason) {
        if (mOnPubChannelDeletedListener != null) {
            mOnPubChannelDeletedListener.onDeleted(uid, reason);
        }
    }

    public interface OnPubChannelFocusResultListener {
        void onChannelFocused(int uid, int reason);
    }

    private OnPubChannelFocusResultListener mOnPubChannelFocusResultListener;

    public void setOnPubChannelFocusResultListener(OnPubChannelFocusResultListener listener) {
        this.mOnPubChannelFocusResultListener = listener;
    }

    @Override
    public void onPubChannelFocusResult(int uid, int reason) {
        if (mOnPubChannelFocusResultListener != null) {
            mOnPubChannelFocusResultListener.onChannelFocused(uid, reason);
        }
    }

    public interface OnPubChannelUnFocusResultListener {
        void onChannelUnFocused(int uid, int reason);
    }

    private OnPubChannelUnFocusResultListener mOnPubChannelUnFocusResultListener;

    public void setOnPubChannelUnFocusResultListener(OnPubChannelUnFocusResultListener listener) {
        this.mOnPubChannelUnFocusResultListener = listener;
    }

    @Override
    public void onPubChannelUnfocusResult(int uid, int reason) {
        if (mOnPubChannelUnFocusResultListener != null) {
            mOnPubChannelUnFocusResultListener.onChannelUnFocused(uid, reason);
        }
    }

    @Override
    public void onPubChannelRenamed(int uid, int reason) {

    }


    /**************************以下官方文档中未提及*************************************/
    @Override
    public void onCallMeetingStarted(int i, int i1, int i2, List<Contact> list) {
    }

    @Override
    public void onCallMeetingStopped(int i, int i1) {

    }


    @Override
    public void onDefaultChannelSet(int userId, int chType, int defaultChId) {

    }

    @Override
    public void onAdverChannelsGet(int i, Channel channel, List<Channel> list) {

    }

}
