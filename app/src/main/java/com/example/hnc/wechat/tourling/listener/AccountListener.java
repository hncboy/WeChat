package com.example.hnc.wechat.tourling.listener;

import com.algebra.sdk.OnAccountListener;
import com.algebra.sdk.entity.Contact;
import com.algebra.sdk.entity.UserProfile;

import java.util.List;

/**
 * Created by mxf on 2017/6/30.
 */
public class AccountListener implements OnAccountListener {
    private static final AccountListener ACCOUNT_LISTENER = new AccountListener();

    public static AccountListener getInstance() {
        return ACCOUNT_LISTENER;
    }

    /**
     * 登陆回调
     *
     * @param uid
     * @param result
     * @param userProfile
     */
    @Override
    public void onLogin(int uid, int result, UserProfile userProfile) {
//        Log.d(this, "onLogin");
    }

    /**
     * 创建用户回调
     *
     * @param uid
     * @param result
     * @param account
     */
    @Override
    public void onCreateUser(int uid, int result, String account) {
//        Log.d(this, "onCreateUser uid :" + uid + " account: " + account);
    }

    /**
     * 登出
     */
    @Override
    public void onLogout() {

    }

    /**
     * 设置昵称
     *
     * @param uid
     */
    @Override
    public void onSetNickName(int uid) {}


    @Override
    public void onChangePassWord(int uid, boolean successful) {  }

    /******************************下面的官方文档没写*********************************/


    @Override
    @Deprecated //官方文档没写 不管了
    public void onAskUnbind(int i) {

    }

    /**
     * 绑定手机 请求验证码 用不到
     *
     * @param userId
     * @param state
     * @param phoneNo
     */
    @Override
    @Deprecated
    public void onAuthRequestReply(int userId, int state, String phoneNo) {

    }

    @Override
    public void onAuthBindingReply(int i, int i1, String s) {

    }

    @Override
    public void onAuthRequestPassReply(int i, int i1, String s) {

    }

    @Override
    public void onAuthResetPassReply(int i, int i1) {

    }

    @Override
    public void onFriendsSectionGet(int i, int i1, int i2, int i3, List<Contact> list) {

    }

    @Override
    public void onFriendStatusUpdate(int i, int i1, int i2) {

    }

    @Override
    public void onShakeScreenAck(int i, int i1, int i2) {

    }

    @Override
    public void onShakeScreenReceived(int i, String s, String s1) {

    }

    @Override
    public void onSetStatusReturn(int i, boolean b) {

    }

    @Override
    public void onHearbeatLost(int i, int i1) {

    }

    @Override
    public void onKickedOut(int i, int i1) {

    }

    @Override
    public void onSelfStateChange(int i, int i1) {

    }

    @Override
    public void onSelfLocationAvailable(int i, Double aDouble, Double aDouble1, Double aDouble2) {

    }

    @Override
    public void onSelfLocationReported(int i) {

    }

    @Override
    public void onUserLocationNotify(int i, String s, Double aDouble, Double aDouble1) {

    }

    @Override
    public void onLogger(int i, String s) {

    }

    @Override
    public void onSmsRequestReply(int i) {

    }
}
