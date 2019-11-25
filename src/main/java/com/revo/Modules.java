package com.revo;

import com.google.inject.Module;
import com.revo.account.AccountModule;
import com.revo.core.CoreModule;
import com.revo.core.persistence.CorePersistenceModule;
import com.revo.core.web.CoreWebModule;
import com.revo.transfer.TransferModule;

class Modules {

    Module[] modules() {
        return new Module[] {
                new CoreModule(),
                new CoreWebModule(),
                new CorePersistenceModule(),
                new AccountModule(),
                new TransferModule()
        };
    }

}
