package com.lidl.sourcecode.spring_aop_proxy.jdk;

import com.lidl.sourcecode.spring_aop_proxy.IOffer;
import com.lidl.sourcecode.spring_aop_proxy.OfferImpl;

public class TestJDKProxy {

    public static void main(String[] args) {
        IOffer offer = (IOffer) new ProxyFactory().bind(new OfferImpl());
        offer.postOffer();
        offer.modifyOffer();
    }

}
