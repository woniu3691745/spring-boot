package com.lidl.sourcecode.spring_aop_proxy;

public class OfferImpl implements IOffer {
    public void postOffer() {
        System.out.println("post offer");
    }

    public void modifyOffer() {
        System.out.println("modify offer");
    }
}