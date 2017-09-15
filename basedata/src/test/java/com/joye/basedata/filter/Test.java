package com.joye.basedata.filter;

import com.alibaba.dcm.DnsCacheManipulator;
import com.joye.basedata.autoseo.ExcelReaderHelper;
import com.joye.basedata.autoseo.ExcelWriterHelper;
import com.joye.basedata.autoseo.UploadRowResourceEntity;
import com.joye.basedata.combination.CombinationDelegate;
import com.joye.basedata.crawler4j.MyCrawler;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joye on 2017/4/20.
 */

public class Test {
/**
 *
 */
    /**
     * 1.读取Excel 加入到hosts
     * <p>
     * 2.开始检查
     * <p>
     * 3.获取得到title为11的 <或者认为title 跟上传的不一样的 ></或者认为title>
     * 获取到成功的<title>跟上传的一样</title>
     * <p>
     * 4.加入到失败集合里面去
     * <p>
     * 5.获取到IP 和 老域名
     * <p>
     * 6.重新生成一份新的可以提交的
     */
    String fileName="2017-09-14_待重新上传_待重新上传_待重新上传";

    String filePath = "/Users/joye/Search/combination/joye/"+fileName+".xlsx";
    String newPath = "/Users/joye/Search/combination/joye/";//重新提交的excel路径
    String newFileName =fileName+"_待重新上传";
    @org.junit.Test
    public void testWriteFailedUpload() throws IOException {
        //获取上传的列表
        List<UploadRowResourceEntity> upLoadRows = CombinationDelegate.getUploadRowResourceEntitys(filePath, 0);

        List<ReplaceDomainEntity> replaceDomainEntityList = getReplaceDomainentitys(filePath, 2);

//        List<OtherInfoEntity> otherInfoEntityList=getOtherInfoEntitys(filePath);

        //所有的
        List<ItemEntity> result = getAllItemEntity(filePath, 1);


        //重新上传的列表
        List<UploadRowResourceEntity> reUploadRows = new ArrayList<>();
//        int failed[] = new int[]{25,93,143,146,163,188,214,219,229,236,254};
        int failed[] = new int[]{7};
        System.out.println(failed.length);
        List<String> ips = new ArrayList<>();
        for (int index :
                failed) {
            reUploadRows.add(upLoadRows.get(index - 2));
            ips.add(result.get(index-2).getIp());
        }
        List<OtherInfoEntity> writeInfoEntitys = new ArrayList<>();
        for (int index :
                failed) {
//            writeInfoEntitys.add(otherInfoEntityList.get(index - 2));
        }

        List<ReplaceDomainEntity> antherReplaceDomainEntitys = new ArrayList<>();
        for (int i = reUploadRows.size() - 1; i < replaceDomainEntityList.size(); i++) {
            antherReplaceDomainEntitys.add(replaceDomainEntityList.get(i));
        }
        writeAgainUpload(reUploadRows, replaceDomainEntityList, writeInfoEntitys, ips, antherReplaceDomainEntitys);
    }

    @org.junit.Test
    public void testDNS() throws IOException {
        //批量检查上站失败的domain...
        DnsCacheManipulator.clearDnsCache();
        DnsCacheManipulator.clearDnsCache();

        //获取上传的列表
        List<UploadRowResourceEntity> upLoadRows = CombinationDelegate.getUploadRowResourceEntitys(filePath, 0);
        //获取其他的
        List<OtherInfoEntity> otherInfoEntityList = getOtherInfoEntitys(filePath);
        //成功的
        List<ItemEntity> successItem = new ArrayList<>();
        //失败的
        List<ItemEntity> failedItem = new ArrayList<>();
        //所有的
        List<ItemEntity> result = getAllItemEntity(filePath, 1);

        //重新上传的列表
        List<UploadRowResourceEntity> reUploadRows = new ArrayList<>();
        List<ReplaceDomainEntity> replaceDomainEntityList = getReplaceDomainentitys(filePath, 2);

        //如果所有的数量与上站的数量不一样
        if (result.size() != upLoadRows.size()) {
            System.out.println(result.size() + "~~~" + upLoadRows.size());
            Validate.isTrue(false);
        }
        //检查对象站是否一一对应
        for (int i = 0; i < upLoadRows.size(); i++) {
            if (!upLoadRows.get(i).getOldDomainStr().equals(result.get(i).getPrefixDomain())) {
                //上传的老域名
//                System.out.println(upLoadRows.get(i).getOldDomainStr());

//                System.out.println(result.get(i).getPrefixDomain());

//                System.out.println(i);
                Validate.isTrue(false);
            }
        }

        //设置hosts
        for (ItemEntity item : result) {
            DnsCacheManipulator.setDnsCache(item.getDomain(), item.getIp());
            DnsCacheManipulator.setDnsCache(item.getPrefixDomain(), item.getIp());
        }


        //遍历
        for (int i = 0; i < result.size(); i++) {

            httpURLConGet(result.get(i), upLoadRows.get(i), new CallBack() {
                @Override
                public void failedItem(ItemEntity itemEntity) {
                    System.out.println("add failed");
                    failedItem.add(itemEntity);
                }

                @Override
                public void successItem(ItemEntity item) {
                    System.out.println("add success");
                    successItem.add(item);
                }
            });
        }
        List<String> ips = new ArrayList<>();
        List<OtherInfoEntity> writeOtherinfoEntity = new ArrayList<>();
        UploadRowResourceEntity uploadRowResourceEntity;
        //遍历失败的,准备构建重新上传的列表
        for (int i = 0; i < upLoadRows.size(); i++) {
            uploadRowResourceEntity = upLoadRows.get(i);
            for (int j = 0; j < failedItem.size(); j++) {
                //如果失败的域名等于老域名,证明这行上传失败
                if (failedItem.get(j).getPrefixDomain().equals(uploadRowResourceEntity.getOldDomainStr())) {
                    ips.add(failedItem.get(j).getIp());
                    reUploadRows.add(uploadRowResourceEntity);
//                    writeOtherinfoEntity.add(otherInfoEntityList.get(i));
                    break;
                }
            }
        }
        List<OtherInfoEntity> newOtherInfoEntitys = new ArrayList<>();
        otherInfoEntityList.removeAll(writeOtherinfoEntity);
        for (OtherInfoEntity item :
                otherInfoEntityList) {
            newOtherInfoEntitys.add(item);
        }
        List<ReplaceDomainEntity> antherReplaceDomainEntitys = new ArrayList<>();
        for (int i = reUploadRows.size() - 1; i < replaceDomainEntityList.size(); i++) {
            antherReplaceDomainEntitys.add(replaceDomainEntityList.get(i));
        }
        writeAgainUpload(reUploadRows, replaceDomainEntityList, newOtherInfoEntitys, ips, antherReplaceDomainEntitys);

    }


    @org.junit.Test
    public void testCheck() throws Exception {
//        Workbook book=ExcelReaderHelper.getWorkBookByPath( "/Users/joye/Search/combination/kevin/2017-05-12.xlsx");
//        List<String> domains=ExcelReaderHelper.getAllCellsByRowsIndex(0,book);
//        for(int i=0;i<domains.size()/100;i++){
//            httpURLConGet(result.get(i), upLoadRows.get(i), new CallBack() {
//                @Override
//                public void failedItem(ItemEntity itemEntity) {
//                    failedItem.add(itemEntity);
//                }
//
//                @Override
//                public void successItem(ItemEntity item) {
//                    successItem.add(item);
//                }
//            });
//        }
    }

    /**
     * 获取要替换的对象站
     *
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static List<OtherInfoEntity> getOtherInfoEntitys(String filePath) throws FileNotFoundException {
        Workbook workBook = ExcelReaderHelper.getWorkBookByPath(filePath);
        List<OtherInfoEntity> allResult = new ArrayList<>();
        OtherInfoEntity entity;
        Sheet sheet = workBook.getSheetAt(3);
        int i = 0;
        for (Row r : sheet) {
            if (i == 0) {
                i++;
                continue;
            }
            entity = new OtherInfoEntity();
            entity.setRow(r);
            allResult.add(entity);
        }
        return allResult;
    }

    /**
     * 重新生成一份上站程序
     *
     * @param reUploadRows            上传的数据
     * @param replaceDomainEntityList 下一次替换的数据
     * @param otherInfoEntityList     外链数据
     * @param ips                     ips
     * @throws IOException
     */
    public void writeAgainUpload(List<UploadRowResourceEntity> reUploadRows, List<ReplaceDomainEntity> replaceDomainEntityList,
                                 List<OtherInfoEntity> otherInfoEntityList, List<String> ips, List<ReplaceDomainEntity> antherRepalceEntityLists) throws IOException {
        //获取需要重新上传的
        if (writeAginUploadData(reUploadRows, replaceDomainEntityList, otherInfoEntityList)) return;
        ExcelWriterHelper.WriteAginUploadData(reUploadRows, newPath, newFileName, ips, antherRepalceEntityLists, otherInfoEntityList);

    }

    /**
     * 写入重新上传的
     *
     * @param reUploadRows
     * @param replaceDomainEntityList
     * @throws IOException
     */
    private void writeAgainUpload(List<UploadRowResourceEntity> reUploadRows, List<ReplaceDomainEntity> replaceDomainEntityList,
                                  List<OtherInfoEntity> otherInfoEntityList) throws IOException {
        //获取需要重新上传的
        if (writeAginUploadData(reUploadRows, replaceDomainEntityList, otherInfoEntityList)) return;
        ExcelWriterHelper.WriteExtralRerouse(reUploadRows, newPath, newFileName);

    }

    /**
     * @param reUploadRows
     * @param replaceDomainEntityList
     * @param otherInfoEntityList
     * @return
     */
    private boolean writeAginUploadData(List<UploadRowResourceEntity> reUploadRows, List<ReplaceDomainEntity> replaceDomainEntityList, List<OtherInfoEntity> otherInfoEntityList) {
        //标题
        String title = "";
        //关键字
        String[] keys;
        //需要替换的关键字
        String[] replaceKeys;
        //重新构建新的上传站列表,替换下
        for (int i = 0; i < reUploadRows.size(); i++) {
            //获取需要上传失败的
            UploadRowResourceEntity item = reUploadRows.get(i);
            title = item.getTitle();
            ReplaceDomainEntity replaceDomainEntity = replaceDomainEntityList.get(i);
//            OtherInfoEntity otherInfoEntity=otherInfoEntityList.get(i);
            replaceKeys = replaceDomainEntity.getkeys();
            item.setObjectDomain(replaceDomainEntity.getDomain());
            item.setChartsetStr(replaceDomainEntity.getCharsert());
            try {
                //            电影[to]qy014千亿国际[or]综艺[to]千亿国际[or]动漫[to]千亿国际娱乐平台
                String[] keystrArray = new String[6];
                String keystr_or_to = item.getReplaceKeyStr();
//            System.out.println(keystr_or_to);
                int indexTo = keystr_or_to.indexOf("[to]");
                int indexOr = keystr_or_to.indexOf("[or]");
                //第一个关键字
                keystrArray[0] = keystr_or_to.substring(indexTo + 4, indexOr);
                keystr_or_to = keystr_or_to.substring(keystr_or_to.indexOf("[or]") + 4, keystr_or_to.length());
                System.out.println(keystrArray[0]);
                //第二个关键字
                keystrArray[1] = keystr_or_to.substring(keystr_or_to.indexOf("[to]") + 4, keystr_or_to.indexOf("[or]"));
                keystr_or_to = keystr_or_to.substring(keystr_or_to.indexOf("[or]") + 4, keystr_or_to.length());
                System.out.println(keystrArray[1]);
                //第三个关键字
                keystrArray[2] = keystr_or_to.substring(keystr_or_to.indexOf("[to]") + 4, keystr_or_to.indexOf("[or]"));
                keystr_or_to = keystr_or_to.substring(keystr_or_to.indexOf("[or]") + 4, keystr_or_to.length());
                System.out.println(keystrArray[2]);
                keystrArray[3] = keystr_or_to.substring(keystr_or_to.indexOf("[to]") + 4, keystr_or_to.indexOf("[or]"));
                keystr_or_to = keystr_or_to.substring(keystr_or_to.indexOf("[or]") + 4, keystr_or_to.length());
                System.out.println(keystrArray[3]);
                keystrArray[4] = keystr_or_to.substring(keystr_or_to.indexOf("[to]") + 4, keystr_or_to.indexOf("[or]"));
                keystr_or_to = keystr_or_to.substring(keystr_or_to.indexOf("[to]") + 4, keystr_or_to.indexOf("[or]"));
                System.out.println(keystrArray[4]);


                String needReplaceKeystr = replaceKeys[0] + "[to]" + keystrArray[0] + "[or]" +
                        replaceKeys[1] + "[to]" + keystrArray[1] + "[or]" +
                        replaceKeys[2] + "[to]" + keystrArray[2] + "[or]" +
                        replaceKeys[3] + "[to]" + keystrArray[3] + "[or]" +
                        replaceKeys[4] + "[to]" + keystrArray[4];
                //去掉 手机代理跳转
                needReplaceKeystr = needReplaceKeystr + "[or]mobile-agent[to]" + keystrArray[0];

                //头部加个链接
                needReplaceKeystr = needReplaceKeystr + "[or]</head>[to]" +
                        "<meta name=\"keywords\"  content=\"" + keystrArray[0] + "，" + keystrArray[3] + "，" + keystrArray[4] + "\">" +
                        "<meta  name=\"Author\" content=\"" + keystrArray[0] + "\">" +
                        //加入手机适配以及Google
                        "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"/>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no\">" +
                        "</head>";
//                //引入上面和下面
//                needReplaceKeystr= needReplaceKeystr
//                        +"[or]</body>[to]" +
//                            "<ul style='text-align:left'>"+
//                                "<li>" +
//                                    "<span style=\"display:inline-block;width:160px;height:30px;text-align:center;border:#000 1px dotted;font-family:Arial,Helvetica,sans-serif;font-size:11px;background-color:#FFFFFF;\"><strong style=\"display:block;padding:0px;margin:0px;\"></strong><a href=\"http://www.submitexpress.com/\" title=\"SubmitExpress.com\" style=\"font-family:Arial,Helvetica,sans-serif;font-size:11px;\">SubmitExpress.com</a></span>"+
//                                "</li>"+
//                            "</ul>"+
//                        "</body>";
                item.setReplaceKeyStr(needReplaceKeystr);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(i+2);
            }
        }
        if (reUploadRows.size() == 0) {
            System.out.println("没有需要重新上传的");
            return true;
        }
        return false;
    }


    /**
     * 获取一批批量替换的备用
     *
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    private List<ReplaceDomainEntity> getReplaceDomainentitys(String filePath, int sheetindex) throws FileNotFoundException {
        Workbook workBook = ExcelReaderHelper.getWorkBookByPath(filePath);
        List<ReplaceDomainEntity> allResult = new ArrayList<>();
        ReplaceDomainEntity entity;
        Sheet sheet = workBook.getSheetAt(sheetindex);
        int i = 0;
        for (Row r : sheet) {
            if (i == 0) {
                i++;
                continue;
            }
            entity = new ReplaceDomainEntity();
            entity.setRow(r);
            allResult.add(entity);
        }
        return allResult;

    }


    private List<ItemEntity> getAllItemEntity(String filePath, int sheetIndex) throws FileNotFoundException {
        Workbook workBook = ExcelReaderHelper.getWorkBookByPath(filePath);
        List<ItemEntity> allResult = new ArrayList<>();
        ItemEntity entity;
        Sheet sheet = workBook.getSheetAt(sheetIndex);
        for (Row r : sheet) {
            entity = new ItemEntity();
            for (Cell cell : r) {
                String value = cell.getStringCellValue();
                if (cell.getColumnIndex() == 0) {
                    entity.setPrefixDomain(value);
                } else if (cell.getColumnIndex() == 2) {
                    entity.setIp(value);
                } else if (cell.getColumnIndex() == 6) {
                    entity.setDomain(value);
                } else if (cell.getColumnIndex() == 10) {
                    entity.setHttpStr(value);
                }
            }
            allResult.add(entity);
        }
        return allResult;
    }

    @org.junit.Test
    public void testA() {
        HttpGet get = null;
        HttpClient httpClient = HttpClients.custom().build();
        HttpResponse response = null;
        try {
            String url = "http://www.sh6158.com/";

            get = new HttpGet(url);
            response = httpClient.execute(get);
            System.out.println(response.getStatusLine().getStatusCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testB() throws Exception {
        String[] keystrArray = new String[3];
        String keystr_or_to = "发现[to]伟德国际[or]农业[to]伟德国际娱乐1946[or]生活[to]伟德娱乐客户端[or]</head>[to]</head><h1><a href='http://www.hffbsh.com'>伟德国际</a></h1>[or]</body>[to]</body><a href='http://www.hffbsh.com'><strong>伟德国际娱乐1946</strong></a><a href='http://www.hffbsh.com'><strong>伟德娱乐客户端</strong></a>";
        int indexTo = keystr_or_to.indexOf("[to]");
        int indexOr = keystr_or_to.indexOf("[or]");
        keystrArray[0] = keystr_or_to.substring(indexTo + 4, indexOr);
        keystr_or_to = keystr_or_to.substring(keystr_or_to.indexOf("[or]") + 4, keystr_or_to.length());
        keystrArray[1] = keystr_or_to.substring(keystr_or_to.indexOf("[to]") + 4, keystr_or_to.indexOf("[or]"));
        keystr_or_to = keystr_or_to.substring(keystr_or_to.indexOf("[or]") + 4, keystr_or_to.length());
        keystrArray[2] = keystr_or_to.substring(keystr_or_to.indexOf("[to]") + 4, keystr_or_to.indexOf("[or]"));
        for (String str :
                keystrArray) {
            System.out.println(str);
        }
    }

    @org.junit.Test
    public void testC() throws Exception {
        String keystr_or_to = "首页[to]钱柜娱乐官网[or]家装体验[to]钱柜娱乐官网[or]装修服务[to]钱柜娱乐官网[or]公司特色[to]钱柜娱乐官网登录[or]关于我们[to]钱柜娱乐手机版官方网";
        String[] keystrArray = new String[6];
//            System.out.println(keystr_or_to);
        int indexTo = keystr_or_to.indexOf("[to]");
        int indexOr = keystr_or_to.indexOf("[or]");
        //第一个关键字
        keystrArray[0] = keystr_or_to.substring(indexTo + 4, indexOr);
        keystr_or_to = keystr_or_to.substring(keystr_or_to.indexOf("[or]") + 4, keystr_or_to.length());
        //第二个关键字
        keystrArray[1] = keystr_or_to.substring(keystr_or_to.indexOf("[to]") + 4, keystr_or_to.indexOf("[or]"));
        keystr_or_to = keystr_or_to.substring(keystr_or_to.indexOf("[or]") + 4, keystr_or_to.length());
        //第三个关键字
        keystrArray[2] = keystr_or_to.substring(keystr_or_to.indexOf("[to]") + 4, keystr_or_to.indexOf("[or]"));
        keystr_or_to = keystr_or_to.substring(keystr_or_to.indexOf("[or]") + 4, keystr_or_to.length());

        keystrArray[3] = keystr_or_to.substring(keystr_or_to.indexOf("[to]") + 4, keystr_or_to.indexOf("[or]"));
        keystr_or_to = keystr_or_to.substring(keystr_or_to.indexOf("[or]") + 4, keystr_or_to.length());
        keystrArray[4] = keystr_or_to.substring(keystr_or_to.indexOf("[to]") + 4, keystr_or_to.length());
        keystr_or_to = keystr_or_to.substring(keystr_or_to.indexOf("[to]") + 4, keystr_or_to.length());
        keystrArray[5] = keystr_or_to.substring(0, keystr_or_to.length());
        for (String str :
                keystrArray) {
            System.out.println(str);
        }

        String description = ",拥有完整且庞大的游戏资讯体系和优质的社区服务,客观真实的反应玩家的声音,是广大玩家喜爱的游戏平台";
        String[] array_small = description.split(",");
        String[] array_big = description.split(",");
        //如果按,分割
        if (array_big.length > array_small.length) {
            description = description.replaceAll(",", ",");
            for (int i = 0; i < array_big.length; i++) {
                if (StringUtils.isBlank(array_big[i])) {
                    continue;
                }
                if (i == 3) {
                    break;
                }
                description = description.replace(array_big[i], keystrArray[i] + array_big[i]);
            }
        } else {
            description = description.replaceAll(",", ",");
            for (int i = 0; i < array_small.length; i++) {
                if (StringUtils.isBlank(array_small[i])) {
                    continue;
                }
                if (i == 3) {
                    break;
                }
                System.out.println(description);
                description = description.replace(array_small[i], keystrArray[i] + array_small[i]);
            }
            System.out.println(description);
        }

    }

    interface CallBack {
        void failedItem(ItemEntity itemEntity);

        void successItem(ItemEntity item);
    }


    /**
     * 使用java.net.HttpURLConnection类的【GET】的方式登录
     */
    public static String httpURLConGet(ItemEntity itemEntity, UploadRowResourceEntity uploadRow, CallBack callBack) {
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(itemEntity.getHttpStr().replaceAll("\"", "") + "/");//"?"后面的内容都属于请求头中的内容,服务器获取到请求信息后通过"&"分离出数据
            httpURLConnection = (HttpURLConnection) url.openConnection();//打开指定URL的连接
            httpURLConnection.setRequestMethod("GET"); // get或者post必须大写
            httpURLConnection.setConnectTimeout(5000); // 连接的超时时间,非必须
            httpURLConnection.setReadTimeout(5000); // 读数据的超时时间,非必须
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)");//非必须
            System.out.println(httpURLConnection.getURL().toString());
            int responseCode = httpURLConnection.getResponseCode();//获取响应码
            System.out.println(httpURLConnection.getURL().toString());
            if (httpURLConnection.getURL().toString().contains("baidu.com")) {
                callBack.failedItem(itemEntity);
                return "";
            }
            if (responseCode == 200) {
                InputStream is = httpURLConnection.getInputStream();
                String result;
                try {
                    result = getStringFromInputStream(is);
//                    System.out.println(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    callBack.failedItem(itemEntity);
                    return "失败";
                }
                if (result.contains("<title>11")) {
                    callBack.failedItem(itemEntity);
                } else if (result.contains(uploadRow.getTitle())) {
                    callBack.successItem(itemEntity);
                    System.out.println(result);

                } else {
//                    System.out.println("unknow error");
//                    callBack.failedItem(itemEntity);
                }
                return result;
            } else if (responseCode == 403 || responseCode == 404) {
                System.out.println("403 404 error");
                callBack.failedItem(itemEntity);
                return "失败";
            } else {
                System.out.println(responseCode);
                callBack.failedItem(itemEntity);

                return "失败";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            callBack.failedItem(itemEntity);
            return "MalformedURLException";
        } catch (ProtocolException e) {
            callBack.failedItem(itemEntity);
            e.printStackTrace();
            return "ProtocolException";
        } catch (IOException e) {
            callBack.failedItem(itemEntity);
            e.printStackTrace();
            return "IOException";
        } finally {
            if (httpURLConnection != null) httpURLConnection.disconnect(); // 关闭连接
        }
    }

    ;

    /**
     * 从流中读取数据,返回字节数组
     */
    public static byte[] getBytesFromInputStream(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// 字节数组输出流(内存输出流)：可以捕获内存缓冲区的数据,转换成字节数组
        byte[] buffer = new byte[1024];
        int len = -1;
        try {
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            is.close();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将输入流转换成字符串信息
     */
    public static String getStringFromInputStream(InputStream is) {
        byte[] bytes = getBytesFromInputStream(is);
        String temp = new String(bytes);//默认为UTF8编码
        if (temp.contains("utf-8")) return temp;
        else if (temp.contains("gb2312") || temp.contains("gbk")) {
            try {
                return new String(bytes, "gbk");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    /**
     * 打开指定URL的URLConnection对象,获取一个InputStream
     */
    public static InputStream getInputStreamFromUrl(String url) {
        try {
            URLConnection conn = new URL(url).openConnection();
            conn.setConnectTimeout(3 * 1000);
            conn.setReadTimeout(3 * 1000);
            return conn.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
