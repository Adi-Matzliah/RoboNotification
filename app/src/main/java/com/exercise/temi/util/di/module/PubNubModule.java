package com.exercise.temi.util.di.module;

import com.exercise.temi.util.di.scope.PubNubServiceScope;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.enums.PNLogVerbosity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
@Module
public class PubNubModule {

    private final String pubKey;
    private final String subKey;

    public PubNubModule(String pubKey, String subKey){
        this.pubKey = pubKey;
        this.subKey = subKey;
    }

    @Provides
    @PubNubServiceScope
    PNConfiguration providePNConfiguration(){
       return new PNConfiguration();
    }

/*    @Provides
    @PubNubServiceScope
    PubNubService.LocalBinder provideBinder(){
        // Binder given to clients
        return new PubNubService.LocalBinder();
    }*/

    @Provides
    @PubNubServiceScope
    PubNub providePubNub(PNConfiguration pnConfiguration){
        pnConfiguration.setSubscribeKey(subKey);
        pnConfiguration.setPublishKey(pubKey);
        pnConfiguration.setSecure(false);
        pnConfiguration.setLogVerbosity(PNLogVerbosity.BODY);

        return new PubNub(pnConfiguration);
    }

}
