package com.joye.hk6data.repository.datasource;

import com.joye.hk6data.cache.impl.Hk6Cache;
import com.joye.hk6data.entity.Hk6DataSourceBean;
import com.joye.hk6data.entity.Hk6Entity;
import com.joye.hk6data.net.BaseApiConstants;
import com.joye.hk6data.net.Hk6RestApi;
import com.joye.hk6data.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by W,x (JoyeWang)
 * on 2016/9/20.
 * 304622254@qq.com
 * Remeark:
 */

public class CloudHk6DataStore implements Hk6DataStore {

    private  Hk6RestApi hk6RestApi;

    private Hk6Cache hk6Cache;

    public CloudHk6DataStore(Hk6RestApi hk6RestApi, Hk6Cache hk6Cache) {
        this.hk6RestApi = hk6RestApi;
        this.hk6Cache = hk6Cache;
    }

    @Override
    public Observable<List<Hk6Entity>> getHk6ListData(String date) {
        return hk6RestApi.getHk6ListData("AA048D6B58015BA5","hk6",date, BaseApiConstants.FORMAT_JSON).map(hk6DataSourceBean -> {
            List<Hk6Entity> retHk6Entitys=new ArrayList<Hk6Entity>();
            Hk6Entity item=new Hk6Entity();
            for (Hk6DataSourceBean.DataBean dataBean:hk6DataSourceBean.getData()){
                item=new Hk6Entity();
                item.setValueFormSource(dataBean);
                retHk6Entitys.add(item);
            }
            return retHk6Entitys;
        }).doOnNext(hk6EntityList -> {
            if(!CollectionUtils.isEmpty(hk6EntityList))
                hk6Cache.put(hk6EntityList,date.substring(0,4));
        });
    }
}
