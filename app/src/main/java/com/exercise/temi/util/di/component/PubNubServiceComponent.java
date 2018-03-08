package com.exercise.temi.util.di.component;

import com.exercise.temi.service.PubNubService;
import com.exercise.temi.util.di.module.PubNubModule;
import com.exercise.temi.util.di.scope.PubNubServiceScope;
import dagger.Component;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
@PubNubServiceScope
@Component(modules = {PubNubModule.class})
public interface PubNubServiceComponent {
    void inject(PubNubService service);
}
