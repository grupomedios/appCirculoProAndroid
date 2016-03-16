package com.grupomedios.circulo_pro.util;

import com.grupomedios.circulo_pro.api.facade.DiscountFacade;
import com.grupomedios.circulo_pro.util.security.UserHelper;

import dagger.Module;

@Module(injects = {
        DiscountFacade.class,

        UserHelper.class

})
public class MCXModule {

}
