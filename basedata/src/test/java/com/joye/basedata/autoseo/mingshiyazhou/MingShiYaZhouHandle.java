package com.joye.basedata.autoseo.mingshiyazhou;

import com.joye.basedata.autoseo.ExtralResourceWriteDelegate;

import org.junit.Test;

/**
 * Created by joye on 2017/4/4.
 */

public class MingShiYaZhouHandle {

    @Test
    public void testHandleKeysExcel()  {
        try {
            /**
             * 关键字生成
             * AAA:千亿国际
             * AAA+AAAB+Random
             * 龙8娱乐_龙8娱乐客户端城_龙8娱乐手机下载
             */
            ExtralResourceWriteDelegate.handleKeysExcel("/Users/joye/search/word//明仕亚洲/明仕亚洲关键字整理.xlsx","/Users/joye/search/word/明仕亚洲/create","明仕亚洲组合");
            /**
             * 域名词+关键字生成
             * long8520龙8娱乐官网_long8520龙8娱乐官网下载_long8520龙8娱乐老虎机
             */
            ExtralResourceWriteDelegate.handleDomainKeys("/Users/joye/search/word/明仕亚洲/明仕亚洲关键字整理.xlsx","/Users/joye/search/word/明仕亚洲/明仕亚洲域词.xlsx","/Users/joye/search/word/明仕亚洲/create","明仕亚洲域加关键字组合");
            /**
             * 域名加关键字 组合成关键字
             * long8877龙8娱乐官网_龙8娱乐官网_龙8娱乐官网
             */
            ExtralResourceWriteDelegate.handleKeysExcel("/Users/joye/search/word/明仕亚洲/明仕亚洲关键字整理.xlsx","/Users/joye/search/word/明仕亚洲/明仕亚洲域词.xlsx","/Users/joye/search/word/明仕亚洲/create","明仕亚洲域加关键字作为组合");
            /**
             * 域名词+关键字生成
             * long8.cc龙8娱乐备用网址_www.long8.cc.com_龙8娱乐老虎机手机版
             */
            ExtralResourceWriteDelegate.handleWWWDomainKeys("/Users/joye/search/word/明仕亚洲/明仕亚洲关键字整理.xlsx","/Users/joye/search/word/明仕亚洲/明仕亚洲域词.xlsx","/Users/joye/search/word/明仕亚洲/create","明仕亚洲关键词+www域组合");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}