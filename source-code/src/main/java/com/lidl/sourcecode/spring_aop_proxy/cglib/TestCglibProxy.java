package com.lidl.sourcecode.spring_aop_proxy.cglib;

import com.lidl.sourcecode.spring_aop_proxy.IOffer;
import com.lidl.sourcecode.spring_aop_proxy.OfferImpl;

public class TestCglibProxy {

    public static void main(String[] args) {
        IOffer offer = (IOffer) new CglibProxyFactory().bind(new OfferImpl());
        offer.postOffer();
        offer.modifyOffer();
    }
}
