package com.grupomedios.circulo_pro.android;

import com.grupomedios.circulo_pro.android.card.activity.SaludActivity;
import com.grupomedios.circulo_pro.android.card.fragment.CardFragment;
import com.grupomedios.circulo_pro.android.common.activity.BaseActivity;
import com.grupomedios.circulo_pro.android.common.activity.DesclubGeneralActivity;
import com.grupomedios.circulo_pro.android.discounts.activity.BranchesActivity;
import com.grupomedios.circulo_pro.android.discounts.activity.DiscountActivity;
import com.grupomedios.circulo_pro.android.discounts.activity.DiscountListActivity;
import com.grupomedios.circulo_pro.android.discounts.fragment.BranchesFragment;
import com.grupomedios.circulo_pro.android.discounts.fragment.DiscountListFragment;
import com.grupomedios.circulo_pro.android.home.activity.DesclubMainActivity;
import com.grupomedios.circulo_pro.android.home.activity.SplashActivity;
import com.grupomedios.circulo_pro.android.home.fragment.MainFragment;
import com.grupomedios.circulo_pro.android.map.activity.DesclubMapActivity;
import com.grupomedios.circulo_pro.android.map.fragment.DesclubMapFragment;
import com.grupomedios.circulo_pro.android.recommended.fragment.RecommendedFragment;
import com.grupomedios.circulo_pro.android.warranty.activity.RequestWarrantyActivity;
import com.grupomedios.circulo_pro.android.warranty.activity.WarrantyActivity;
import com.grupomedios.circulo_pro.util.MCXModule;

import dagger.Module;

/**
 * Created by jhon on 22/01/15.
 */
@Module(
        injects = {
                DesclubApplication.class,
                SplashActivity.class,
                DesclubGeneralActivity.class,
                BaseActivity.class,
                DesclubMainActivity.class,
                DiscountListActivity.class,
                DiscountActivity.class,
                SaludActivity.class,
                DesclubMapActivity.class,
                BranchesActivity.class,

                MainFragment.class,
                DesclubMapFragment.class,
                RecommendedFragment.class,
                CardFragment.class,
                DiscountListFragment.class,
                BranchesFragment.class,
                WarrantyActivity.class,
                RequestWarrantyActivity.class

        },
        includes = {
                MCXModule.class
        },
        overrides = true
)
public class DesclubModule {

}
