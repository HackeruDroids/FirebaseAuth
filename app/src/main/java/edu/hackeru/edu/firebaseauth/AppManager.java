package edu.hackeru.edu.firebaseauth;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;

/**
 * Application Object.
 */

public class AppManager extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //load the Boostrap (Font-Awesome) icons
        TypefaceProvider.registerDefaultIconSets();
    }
}
