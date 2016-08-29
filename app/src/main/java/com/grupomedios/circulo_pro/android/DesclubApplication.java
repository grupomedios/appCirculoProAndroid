package com.grupomedios.circulo_pro.android;

import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.grupomedios.circulo_pro.util.MCXApplication;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by jhoncruz on 27/05/15.
 */
public class DesclubApplication extends MCXApplication {

    private static DesclubApplication mInstance;
    private static Context mAppContext;
    public final String TAG = "DesclubApplication";

    public static DesclubApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    public void setAppContext(Context mAppContext) {
        this.mAppContext = mAppContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        this.injectSelf();

        mInstance = this;
        this.setAppContext(getApplicationContext());

        ImageLoader.getInstance()
                .init(ImageLoaderConfiguration.createDefault(this));

        initCalligraphy();

    }

    @Override
    public void buildDaggerModules(List<Object> modules) {
        modules.add(new DesclubModule());
    }

    private void initCalligraphy(){
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
