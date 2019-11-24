package com.revo;

import com.google.inject.Module;
import com.revo.core.CoreModule;
import com.revo.core.persistence.CorePersistanceModule;
import com.revo.core.web.CoreWebModule;

class Modules {

    Module[] modules() {
        return new Module[] {
                new CoreModule(),
                new CoreWebModule(),
                new CorePersistanceModule(),
        };
    }

}
