package com.joye.hk6domain.interactor;

import com.joye.basedomain.executor.PostExecutionThread;
import com.joye.basedomain.executor.ThreadExecutor;
import com.joye.hk6domain.constants.Hk6EnumHelp;
import com.joye.hk6domain.constants.Modular5;
import com.joye.hk6domain.entity.Hk6UiData;
import com.joye.hk6domain.repository.Hk6Repository;
import com.joye.hk6domain.vo.Modular5Vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func9;

/**
 * Created by Wx on 2016/9/25.
 * QQ:304622254;
 * remark:
 */

public class GetModular5UseCase extends GetHk6Data {
    public GetModular5UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Hk6Repository hk6Repository, String date) {
        super(threadExecutor, postExecutionThread, hk6Repository, date);
    }

    @Override
    protected Observable buildUseCaseObservable() {
       return getHistory().map(new Func1<List<Hk6UiData>, List<Modular5Vo>>() {

            @Override
            public List<Modular5Vo> call(List<Hk6UiData> hk6UiDatas) {
                List<Modular5Vo> list = new ArrayList<Modular5Vo>(hk6UiDatas.size());
                Collections.reverse(hk6UiDatas);
                int M0=0,M1=0,M2=0,M3=0,M4=0;
                Modular5Vo vo;
                for (Hk6UiData itemVo:hk6UiDatas){
                      vo = new Modular5Vo(itemVo);
                    if(Hk6EnumHelp.getModular5(vo.getOpenCode()).equals(Modular5.M0)){
                        M0=0;
                        M1++;
                        M2++;
                        M3++;
                        M4++;
                        vo.M0=M0;
                        vo.M1=M1;
                        vo.M2=M2;
                        vo.M3=M3;
                        vo.M4=M4;
                    }else if(Hk6EnumHelp.getModular5(vo.getOpenCode()).equals(Modular5.M1)){
                        M0++;
                        M1=0;
                        M2++;
                        M3++;
                        M4++;
                        vo.M0=M0;
                        vo.M1=M1;
                        vo.M2=M2;
                        vo.M3=M3;
                        vo.M4=M4;
                    }else if(Hk6EnumHelp.getModular5(vo.getOpenCode()).equals(Modular5.M2)){
                        M0++;
                        M1++;
                        M2=0;
                        M3++;
                        M4++;
                        vo.M0=M0;
                        vo.M1=M1;
                        vo.M2=M2;
                        vo.M3=M3;
                        vo.M4=M4;
                    }else if(Hk6EnumHelp.getModular5(vo.getOpenCode()).equals(Modular5.M3)){
                        M0++;
                        M1++;
                        M2++;
                        M3=0;
                        M4++;
                        vo.M0=M0;
                        vo.M1=M1;
                        vo.M2=M2;
                        vo.M3=M3;
                        vo.M4=M4;
                    }else if(Hk6EnumHelp.getModular5(vo.getOpenCode()).equals(Modular5.M4)){
                        M0++;
                        M1++;
                        M2++;
                        M3++;
                        M4=0;
                        vo.M0=M0;
                        vo.M1=M1;
                        vo.M2=M2;
                        vo.M3=M3;
                        vo.M4=M4;
                    }
                    list.add(vo);
                }
                Collections.reverse(list);
                return list;
            }
        });
    }
}
