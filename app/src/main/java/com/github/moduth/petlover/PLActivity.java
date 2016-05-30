package com.github.moduth.petlover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.moduth.petlover.internal.di.components.ApplicationComponent;
import com.github.moduth.petlover.internal.di.modules.ActivityModule;

/**
 * @author markzhai
 * @version 1.0.0
 */
public abstract class PLActivity extends AppCompatActivity {

    protected static final String TAG = "PLActivity";
    private ActivityModule mActivityModule;


    String xx;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        if (mActivityModule == null) {
            mActivityModule = new ActivityModule(this);
        }
        return mActivityModule;
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((PLApplication) getApplication()).getApplicationComponent();
    }
}
