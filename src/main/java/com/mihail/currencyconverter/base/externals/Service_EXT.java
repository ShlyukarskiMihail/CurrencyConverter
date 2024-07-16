package com.mihail.currencyconverter.base.externals;

import reactor.core.publisher.Mono;

public interface Service_EXT {
    Mono<?> callExternalService(String parameter);
}
