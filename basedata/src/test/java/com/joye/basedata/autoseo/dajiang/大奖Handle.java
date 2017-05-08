package com.joye.basedata.autoseo.dajiang;

import com.joye.basedata.autoseo.ExtralResourceWriteDelegate;

import org.junit.Test;

/**
 * Created by joye on 2017/4/4.
 */

public class 大奖Handle {

    @Test
    public void testHandleKeysExcel()  {
        try {
            /**
             * 关键字生成
             * AAA:大奖娱乐
             * AAA+AAAB+Random
             * 龙8娱乐_龙8娱乐客户端城_龙8娱乐手机下载
             */
            ExtralResourceWriteDelegate.handleKeysExcel("/Users/joye/search/word/大奖娱乐/大奖娱乐关键词.xlsx","/Users/joye/search/word/大奖娱乐/create","1.大奖娱乐关键词组合");
            /**
             * 域名词+关键字生成
             * long8520龙8娱乐娱乐_long8520龙8娱乐娱乐下载_long8520龙8娱乐娱乐
             */
            ExtralResourceWriteDelegate.handleDomainKeys("/Users/joye/search/word/大奖娱乐/大奖娱乐关键词.xlsx","/Users/joye/search/word/大奖娱乐/大奖娱乐域词.xlsx","/Users/joye/search/word/大奖娱乐/create","4.大奖娱乐关键词域加关键字组合");
            /**
             * 域名加关键字 组合成关键字
             * long8877龙8娱乐娱乐_龙8娱乐娱乐_龙8娱乐娱乐
             */
            ExtralResourceWriteDelegate.handleKeysExcel("/Users/joye/search/word/大奖娱乐/大奖娱乐关键词.xlsx","/Users/joye/search/word/大奖娱乐/大奖娱乐域词.xlsx","/Users/joye/search/word/大奖娱乐/create","3.大奖娱乐关键词域加关键字作为组合");
            /**
             * 域名词+关键字生成
             * long8.cc龙8娱乐备用网址_www.long8.cc.com_龙8娱乐娱乐手机版
             */
            ExtralResourceWriteDelegate.handleWWWDomainKeys("/Users/joye/search/word/大奖娱乐/大奖娱乐关键词.xlsx","/Users/joye/search/word/大奖娱乐/大奖娱乐域词.xlsx","/Users/joye/search/word/大奖娱乐/create","5.大奖娱乐关键词域关键词+www域组合");


            /**
             * 域名词+关键字生成
             * 龙8娱乐备用网址_www.long8.cc.com_龙8娱乐娱乐手机版
             */
            ExtralResourceWriteDelegate.handleWWWDomainKeysOnlyKeys("/Users/joye/search/word/大奖娱乐/大奖娱乐关键词.xlsx","/Users/joye/search/word/大奖娱乐/大奖娱乐域词.xlsx","/Users/joye/search/word/大奖娱乐/create","2.大奖娱乐关键词关键词+www域组合");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
