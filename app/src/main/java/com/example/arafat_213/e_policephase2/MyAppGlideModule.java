package com.example.arafat_213.e_policephase2;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.storage.StorageReference;


import java.io.InputStream;

@GlideModule
public class MyAppGlideModule extends AppGlideModule {

//    @Override
//    public void registerComponents(Context context, Glide glide, Registry registry) {
//        // Register FirebaseImageLoader to handle StorageReference
//        registry.append(StorageReference.class, InputStream.class,
//                new FirebaseImageLoader.Factory());
//    }


    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
//        super.applyOptions(context, builder);
        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888));
    }
}
