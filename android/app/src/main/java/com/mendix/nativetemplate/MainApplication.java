package com.mendix.nativetemplate;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.facebook.react.PackageList;
import com.facebook.react.ReactPackage;
import com.mendix.mendixnative.MendixReactApplication;
import com.mendix.mendixnative.react.splash.MendixSplashScreenPresenter;
import com.microsoft.codepush.react.BuildConfig;
import com.microsoft.codepush.react.CodePush;

import org.devio.rn.splashscreen.SplashScreen;
import org.devio.rn.splashscreen.SplashScreenReactPackage;

import java.util.List;

import com.reactnativedrivekitcore.DriveKitCoreModule;
import com.reactnativedrivekittripanalysis.DriveKitTripAnalysisModule;
import com.reactnativedrivekittripanalysis.RNHeadlessJSNotification;
import com.reactnativedrivekittripanalysis.RNTripNotification;

public class MainApplication extends MendixReactApplication {
    @Override
    public boolean getUseDeveloperSupport() {
        return false;
    }

    @Override
    public List<ReactPackage> getPackages() {
        List<ReactPackage> packages = new PackageList(this).getPackages();
        packages.addAll(new MendixPackageList(this).getPackages());

        // Packages that cannot be autolinked yet can be added manually here, for example:
        // packages.add(new MyReactNativePackage());
        packages.add(new CodePush(getCodePushKey(), getApplicationContext(), BuildConfig.DEBUG));
        packages.add(new SplashScreenReactPackage());

        return packages;
    }

    @Override
    public MendixSplashScreenPresenter createSplashScreenPresenter() {
        return new MendixSplashScreenPresenter() {
            @Override
            public void show(@NonNull Activity activity) {
                hide(activity);
                SplashScreen.show(activity, true);
            }

            @Override
            public void hide(@NonNull Activity activity) {
                SplashScreen.hide(activity);
            }
        };
    }

       @Override
  public void onCreate() {
    super.onCreate();
    DriveKitCoreModule.Companion.initialize(this); 

    final RNTripNotification tripNotification = new RNTripNotification("Boubyan Takaful", "Start a Trip", R.drawable.common_google_signin_btn_icon_dark);
    final RNHeadlessJSNotification headlessJSNotification = new RNHeadlessJSNotification("Notification title", "Notification description");
    DriveKitTripAnalysisModule.Companion.initialize(tripNotification, headlessJSNotification);

  }
}
