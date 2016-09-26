package com.joye.hk6domain.interactor;

import com.joye.basedomain.executor.PostExecutionThread;
import com.joye.basedomain.executor.ThreadExecutor;
import com.joye.hk6domain.constants.NumberProperty;
import com.joye.hk6domain.entity.Hk6UiData;
import com.joye.hk6domain.repository.Hk6Repository;
import com.joye.hk6domain.vo.ChineseZodiacVo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by W,x (JoyeWang)
 * on 2016/9/24.
 * 304622254@qq.com
 * Remeark:
 */

public class GetDefaultNumberUseCase extends GetHk6Data {


    public GetDefaultNumberUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Hk6Repository hk6Repository, String date) {
        super(threadExecutor, postExecutionThread, hk6Repository, date);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return super.buildUseCaseObservable().map(new Func1<List<Hk6UiData>, List<ChineseZodiacVo>>() {

            @Override
            public List<ChineseZodiacVo> call(List<Hk6UiData> hk6UiDatas) {
                List<ChineseZodiacVo> list = new ArrayList<ChineseZodiacVo>(hk6UiDatas.size());
                Map<String,Integer> map=null;
                Collections.reverse(hk6UiDatas);
                //�������е�����
                for (Hk6UiData itemVo:hk6UiDatas){
                    ChineseZodiacVo vo = new ChineseZodiacVo(itemVo);
                    if(map==null){
                        map=vo.getMap();
                    }
                    for (NumberProperty number:NumberProperty.values()){
                        //�����һ�ڿ������������  �������
                        //��©����+1;
                        int value=map.get("item"+number.getValue());
                        map.put("item"+number.getValue(),vo.getOpenCode()!=number.getValue()?value+1:0);
                    }
                   /* //����49������
                    for (int i = 1; i < 50; i++) {
                        //�����һ�ڿ������������  �������
                        //��©����+1;
                        int value=map.get("item"+i);
                        map.put("item"+i,vo.getOpenCode()!=i?value+1:0);
                    }*/
                    vo.setMap(map);
                    list.add(vo);
                }
                return list;
            }
        });
    }
}