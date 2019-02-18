
package com.fresh.manager.core;

import org.springframework.context.ApplicationContext;

public interface AppContextInitListener{
    void contextInitialized(ApplicationContext applicationContext);
}
