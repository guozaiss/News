package com.guozaiss.news.common.utils.http;

import com.guozaiss.news.Constants;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by guozaiss on 16/3/30.
 */
public class DataUtils {
    static class RetrofitInstanceHolder {
        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.topBase)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static DataService getDataService() {
        return RetrofitInstanceHolder.retrofit.create(DataService.class);
    }

//    public static Subscription getEntity(Handler handler, int what) {
//        return getDataService().getEntity()
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(Schedulers.newThread())
//                .flatMap(new Func1<Entity, Observable<Entity.HotPhoneBooksBean>>() {
//                    @Override
//                    public Observable<Entity.HotPhoneBooksBean> call(Entity entity) {
//                        return Observable.from(entity.getHot_phone_books().toArray(new Entity.HotPhoneBooksBean[entity.getHot_phone_books().size()]));
//                    }
//                })
//                .flatMap(new Func1<Entity.HotPhoneBooksBean, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(Entity.HotPhoneBooksBean hotPhoneBooksBean) {
//                        return Observable.just(hotPhoneBooksBean.getId());
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//
//                    }
//                });
////        ..enqueue(new CustomerCallBack<Entity>(handler, what));
//    }

//    public static Call<Temp> getTemp(String controller, String action, Handler handler, int what) {
//        Call<Temp> temp = getDataService().getTemp(controller, action);
//        temp.enqueue(new CustomerCallBack<Temp>(handler, what));
//        return temp;
//    }
//
//    static class CustomerCallBack<T> implements Callback<T> {
//        private Handler handler;
//        private int what;
//
//        public CustomerCallBack(Handler handler, int what) {
//            this.handler = handler;
//            this.what = what;
//        }
//
//        @Override
//        public void onResponse(Response<T> response, Retrofit retrofit) {
//            try {
//                String s = response.headers().get("Link");
//                T body = response.body();
//                Message message = handler.obtainMessage();
//                message.obj = body;
//                message.what = what;
//                handler.sendMessage(message);
//            } catch (Exception e) {
//                Log.e("DataUtils-->onResponse", "估计数据异常l，仔细排查一下b。。");
//            }
//        }
//
//        @Override
//        public void onFailure(Throwable t) {
//            try {
//                Message message = handler.obtainMessage();
//                message.obj = t.getMessage();
//                message.what = what;
//                handler.sendMessage(message);
//            } catch (Exception e) {
//                Log.e("DataUtils-->onFailure", "ERROR--ERROR--ERROR");
//            }
//        }
//    }
}
