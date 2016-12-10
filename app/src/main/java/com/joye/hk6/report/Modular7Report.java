package com.joye.hk6.report;

import com.joye.hk6.view.IPieChartCallback;
import com.joye.hk6domain.vo.Modular7Vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func7;
import rx.schedulers.Schedulers;

import static com.joye.hk6.util.demarcations.Modular7Demarcations.M0;
import static com.joye.hk6.util.demarcations.Modular7Demarcations.M1;
import static com.joye.hk6.util.demarcations.Modular7Demarcations.M2;
import static com.joye.hk6.util.demarcations.Modular7Demarcations.M3;
import static com.joye.hk6.util.demarcations.Modular7Demarcations.M4;
import static com.joye.hk6.util.demarcations.Modular7Demarcations.M5;
import static com.joye.hk6.util.demarcations.Modular7Demarcations.M6;

/**
 * Created by W,x (JoyeWang)
 * on 2016/11/5.
 * 304622254@qq.com
 * Remeark:合数报表统计
 */

public class Modular7Report extends BaseReport{
    /**
     * 总数据
     */
    private final List<Modular7Vo> colorVos;

    public static final int MIN_VALUE=15;

    public Modular7Report(List<Modular7Vo> chineseZodiacVos) {
        this.colorVos = chineseZodiacVos;
    }

    @Override
    public void BubbleSort(final IPieChartCallback callback){
        Observable<Map<Integer, Integer>> M0 = Observable.just(colorVos).flatMap(new Func1<List<Modular7Vo>, Observable<Map<Integer, Integer>>>() {
            @Override
            public Observable<Map<Integer, Integer>> call(List<Modular7Vo> colorVos) {
                List<Statistics> blueDatas =new ArrayList<>();
                final Map<Integer,Integer>  blueMap=new TreeMap<>();
                for (int i = 0; i < colorVos.size(); i++) {
                    if(colorVos.get(i).M0<MIN_VALUE){
                        continue;
                    }
                    int blueNum = 0;
                    if (blueMap.get(colorVos.get(i).M0) != null) {
                        blueNum = blueMap.get(colorVos.get(i).M0);
                    }
                    blueNum++;
                    blueMap.put(colorVos.get(i).M0, blueNum);
                }
                for (Integer integer : blueMap.keySet()) {
                    Statistics item = new Statistics("M0", integer, blueMap.get(integer));
                    blueDatas.add(item);
                }
                Map<Integer, Integer> retBlueMap = mBubbleSort(blueDatas);
                return Observable.just(retBlueMap);
            }
        }).subscribeOn(Schedulers.newThread());

        Observable<Map<Integer, Integer>> M1 = Observable.just(colorVos).flatMap(new Func1<List<Modular7Vo>, Observable<Map<Integer, Integer>>>() {
            @Override
            public Observable<Map<Integer, Integer>> call(List<Modular7Vo> colorVos) {
                List<Statistics> blueDatas =new ArrayList<>();
                final Map<Integer,Integer>  blueMap=new TreeMap<>();
                for (int i = 0; i < colorVos.size(); i++) {
                    if(colorVos.get(i).M1<MIN_VALUE){
                        continue;
                    }
                    int blueNum = 0;
                    if (blueMap.get(colorVos.get(i).M1) != null) {
                        blueNum = blueMap.get(colorVos.get(i).M1);
                    }
                    blueNum++;
                    blueMap.put(colorVos.get(i).M1, blueNum);
                }
                for (Integer integer : blueMap.keySet()) {
                    Statistics item = new Statistics("M1", integer, blueMap.get(integer));
                    blueDatas.add(item);
                }
                Map<Integer, Integer> retBlueMap = mBubbleSort(blueDatas);
                return Observable.just(retBlueMap);
            }
        }).subscribeOn(Schedulers.newThread());
        Observable<Map<Integer, Integer>> M2 = Observable.just(colorVos).flatMap(new Func1<List<Modular7Vo>, Observable<Map<Integer, Integer>>>() {
            @Override
            public Observable<Map<Integer, Integer>> call(List<Modular7Vo> colorVos) {
                List<Statistics> blueDatas =new ArrayList<>();
                final Map<Integer,Integer>  blueMap=new TreeMap<>();
                for (int i = 0; i < colorVos.size(); i++) {
                    if(colorVos.get(i).M2<MIN_VALUE){
                        continue;
                    }
                    int blueNum = 0;
                    if (blueMap.get(colorVos.get(i).M2) != null) {
                        blueNum = blueMap.get(colorVos.get(i).M2);
                    }
                    blueNum++;
                    blueMap.put(colorVos.get(i).M2, blueNum);
                }
                for (Integer integer : blueMap.keySet()) {
                    Statistics item = new Statistics("M2", integer, blueMap.get(integer));
                    blueDatas.add(item);
                }
                Map<Integer, Integer> retBlueMap = mBubbleSort(blueDatas);
                return Observable.just(retBlueMap);
            }
        }).subscribeOn(Schedulers.newThread());

        Observable<Map<Integer, Integer>> M3 = Observable.just(colorVos).flatMap(new Func1<List<Modular7Vo>, Observable<Map<Integer, Integer>>>() {
            @Override
            public Observable<Map<Integer, Integer>> call(List<Modular7Vo> colorVos) {
                List<Statistics> blueDatas =new ArrayList<>();
                final Map<Integer,Integer>  blueMap=new TreeMap<>();
                for (int i = 0; i < colorVos.size(); i++) {
                    if(colorVos.get(i).M3<MIN_VALUE){
                        continue;
                    }
                    int blueNum = 0;
                    if (blueMap.get(colorVos.get(i).M3) != null) {
                        blueNum = blueMap.get(colorVos.get(i).M3);
                    }
                    blueNum++;
                    blueMap.put(colorVos.get(i).M3, blueNum);
                }
                for (Integer integer : blueMap.keySet()) {
                    Statistics item = new Statistics("M3", integer, blueMap.get(integer));
                    blueDatas.add(item);
                }
                Map<Integer, Integer> retBlueMap = mBubbleSort(blueDatas);
                return Observable.just(retBlueMap);
            }
        }).subscribeOn(Schedulers.newThread());
        Observable<Map<Integer, Integer>> M4 = Observable.just(colorVos).flatMap(new Func1<List<Modular7Vo>, Observable<Map<Integer, Integer>>>() {
            @Override
            public Observable<Map<Integer, Integer>> call(List<Modular7Vo> colorVos) {
                List<Statistics> blueDatas =new ArrayList<>();
                final Map<Integer,Integer>  blueMap=new TreeMap<>();
                for (int i = 0; i < colorVos.size(); i++) {
                    if(colorVos.get(i).M4<MIN_VALUE){
                        continue;
                    }
                    int blueNum = 0;
                    if (blueMap.get(colorVos.get(i).M4) != null) {
                        blueNum = blueMap.get(colorVos.get(i).M4);
                    }
                    blueNum++;
                    blueMap.put(colorVos.get(i).M4, blueNum);
                }
                for (Integer integer : blueMap.keySet()) {
                    Statistics item = new Statistics("M4", integer, blueMap.get(integer));
                    blueDatas.add(item);
                }
                Map<Integer, Integer> retBlueMap = mBubbleSort(blueDatas);
                return Observable.just(retBlueMap);
            }
        }).subscribeOn(Schedulers.newThread());
        Observable<Map<Integer, Integer>> M5 = Observable.just(colorVos).flatMap(new Func1<List<Modular7Vo>, Observable<Map<Integer, Integer>>>() {
            @Override
            public Observable<Map<Integer, Integer>> call(List<Modular7Vo> colorVos) {
                List<Statistics> blueDatas =new ArrayList<>();
                final Map<Integer,Integer>  blueMap=new TreeMap<>();
                for (int i = 0; i < colorVos.size(); i++) {
                    if(colorVos.get(i).M5<MIN_VALUE){
                        continue;
                    }
                    int blueNum = 0;
                    if (blueMap.get(colorVos.get(i).M5) != null) {
                        blueNum = blueMap.get(colorVos.get(i).M5);
                    }
                    blueNum++;
                    blueMap.put(colorVos.get(i).M5, blueNum);
                }
                for (Integer integer : blueMap.keySet()) {
                    Statistics item = new Statistics("M5", integer, blueMap.get(integer));
                    blueDatas.add(item);
                }
                Map<Integer, Integer> retBlueMap = mBubbleSort(blueDatas);
                return Observable.just(retBlueMap);
            }
        }).subscribeOn(Schedulers.newThread());
        Observable<Map<Integer, Integer>> M6 = Observable.just(colorVos).flatMap(new Func1<List<Modular7Vo>, Observable<Map<Integer, Integer>>>() {
            @Override
            public Observable<Map<Integer, Integer>> call(List<Modular7Vo> colorVos) {
                List<Statistics> blueDatas =new ArrayList<>();
                final Map<Integer,Integer>  blueMap=new TreeMap<>();
                for (int i = 0; i < colorVos.size(); i++) {
                    if(colorVos.get(i).M6<MIN_VALUE){
                        continue;
                    }
                    int blueNum = 0;
                    if (blueMap.get(colorVos.get(i).M6) != null) {
                        blueNum = blueMap.get(colorVos.get(i).M6);
                    }
                    blueNum++;
                    blueMap.put(colorVos.get(i).M6, blueNum);
                }
                for (Integer integer : blueMap.keySet()) {
                    Statistics item = new Statistics("M6", integer, blueMap.get(integer));
                    blueDatas.add(item);
                }
                Map<Integer, Integer> retBlueMap = mBubbleSort(blueDatas);
                return Observable.just(retBlueMap);
            }
        }).subscribeOn(Schedulers.newThread());
        Observable.zip(M0,M1,M2,M3,M4,M5,M6, new Func7<Map<Integer,Integer>,Map<Integer,Integer>,Map<Integer,Integer>, Map<Integer,Integer>, Map<Integer,Integer>,Map<Integer,Integer>,Map<Integer,Integer>,Void>() {


            @Override
            public Void call(Map<Integer, Integer> map, Map<Integer, Integer> map2, Map<Integer, Integer> map3,Map<Integer, Integer> map4,Map<Integer, Integer> map5,Map<Integer, Integer> map6,Map<Integer,Integer>map7) {
                if(callback!=null) {
                    ArrayList<PieChartImpl> datas=new ArrayList<PieChartImpl>();
                    datas.add(genPieChartImpl(map,"模7余0"));
                    datas.add(genPieChartImpl(map2,"模7余1"));
                    datas.add(genPieChartImpl(map3,"模7余2"));
                    datas.add(genPieChartImpl(map4,"模7余3"));
                    datas.add(genPieChartImpl(map5,"模7余4"));
                    datas.add(genPieChartImpl(map6,"模7余5"));
                    datas.add(genPieChartImpl(map6,"模7余6"));
                    callback.callback(datas);
                }
                return null;
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    @Override
    public void Demarcations(final IPieChartCallback callback) {
        Observable.zip(M0(colorVos),M1(colorVos),M2(colorVos),M3(colorVos),M4(colorVos),M5(colorVos),M6(colorVos), new Func7<Map<Integer,Integer>,Map<Integer,Integer>,Map<Integer,Integer>, Map<Integer,Integer>, Map<Integer,Integer>,Map<Integer,Integer>,Map<Integer,Integer>,Void>() {


            @Override
            public Void call(Map<Integer, Integer> map, Map<Integer, Integer> map2, Map<Integer, Integer> map3,Map<Integer, Integer> map4,Map<Integer, Integer> map5,Map<Integer, Integer> map6,Map<Integer,Integer>map7) {
                if(callback!=null) {
                    ArrayList<PieChartImpl> datas=new ArrayList<PieChartImpl>();
                    datas.add(demarcationPieChartImpl(map,"模7余0"));
                    datas.add(demarcationPieChartImpl(map2,"模7余1"));
                    datas.add(demarcationPieChartImpl(map3,"模7余2"));
                    datas.add(demarcationPieChartImpl(map4,"模7余3"));
                    datas.add(demarcationPieChartImpl(map5,"模7余4"));
                    datas.add(demarcationPieChartImpl(map6,"模7余5"));
                    datas.add(demarcationPieChartImpl(map6,"模7余6"));
                    callback.demarcationCallBack(datas);
                }
                return null;
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }
}
