package com.grupomedios.circulo_pro.api.facade;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.grupomedios.circulo_pro.android.BuildConfig;
import com.grupomedios.circulo_pro.android.R;
import com.grupomedios.circulo_pro.api.representations.DiscountRepresentation;
import com.grupomedios.circulo_pro.api.representations.NearByDiscountRepresentation;
import com.grupomedios.circulo_pro.util.network.volley.GsonRequest;
import com.grupomedios.circulo_pro.util.security.UserHelper;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jhoncruz on 28/05/15.
 */
public class DiscountFacade {

    private final String TAG = "DiscountFacade";

    @Inject
    UserHelper userHelper;

    @Inject
    public DiscountFacade() {
    }

    /**
     *
     * @param context
     * @param successListener
     * @param errorListener
     * @param params
     * @return
     */
    public GsonRequest<NearByDiscountRepresentation[]> getAllDiscounts(Context context,
                                                                 Response.Listener<NearByDiscountRepresentation[]> successListener,
                                                                 Response.ErrorListener errorListener,
                                                                 List<NameValuePair> params) {

        String url = BuildConfig.BASE_URL + context.getString(R.string.endpoint_discounts_nearby);
        url = url + "?" + URLEncodedUtils.format(params, "UTF-8");
        Log.d(TAG, url);
        GsonRequest<NearByDiscountRepresentation[]> jsObjRequest = new GsonRequest<NearByDiscountRepresentation[]>(
                userHelper, Request.Method.GET, url, null,
                NearByDiscountRepresentation[].class,
                successListener,
                errorListener);

        return jsObjRequest;
    }

    /**
     *
     * @param context
     * @param successListener
     * @param errorListener
     * @param params
     * @return
     */
    public GsonRequest<DiscountRepresentation[]> getAllRecommendedDiscounts(Context context,
                                                                       Response.Listener<DiscountRepresentation[]> successListener,
                                                                       Response.ErrorListener errorListener,
                                                                       List<NameValuePair> params) {

        String url = BuildConfig.BASE_URL + context.getString(R.string.endpoint_discounts_recommended);
        url = url + "?" + URLEncodedUtils.format(params, "UTF-8");
        Log.d(TAG, url);
        GsonRequest<DiscountRepresentation[]> jsObjRequest = new GsonRequest<DiscountRepresentation[]>(
                userHelper, Request.Method.GET, url, null,
                DiscountRepresentation[].class,
                successListener,
                errorListener);

        return jsObjRequest;
    }

}
