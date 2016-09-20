package com.joye.hk6domain.entity;

/**
 * Created by W,x (JoyeWang)
 * on 2016/9/20.
 * 304622254@qq.com
 * Remeark:
 */

public class Hk6UiData {
    /**
     * 期数
     */
    private String expect;
    /**
     * 平码
     */
    private String[]evictors;

    /**
     * 中奖号码
     */
    private String  winningNumber;

    /**
     * 开奖时间
     */
    private String opentime;

    /**
     * 开奖时间戳
     */
    private String opentimestamp;

    /**
     *
     * @return 开奖期数
     */
    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    /**
     *
     * @return 平码s
     */
    public String[] getEvictors() {
        return evictors;
    }

    public void setEvictors(String[] evictors) {
        this.evictors = evictors;
    }

    /**
     *
     * @return 中奖号码
     */
    public String getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(String winningNumber) {
        this.winningNumber = winningNumber;
    }

    /**
     *
     * @return 开奖时间
     */
    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    /**
     *
     * @return 开奖时间戳
     */
    public String getOpentimestamp() {
        return opentimestamp;
    }

    public void setOpentimestamp(String opentimestamp) {
        this.opentimestamp = opentimestamp;
    }
}
