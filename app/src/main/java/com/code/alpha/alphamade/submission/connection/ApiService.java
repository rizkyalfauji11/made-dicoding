package com.code.alpha.alphamade.submission.connection;

import android.content.Context;

public class ApiService {
    public static MainServices getServices(Context context) {
        return RetrofitClient
                .getClient(context)
                .create(MainServices.class);
    }
}
