// IRemoteService.aidl
package com.yi.jetpackDemo.service;

// Declare any non-default types here with import statements

interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);


    void registerClient(com.yi.jetpackDemo.service.IClient client);

    void saveUser(in com.yi.jetpackDemo.service.User user);
    void saveUser2(in Bundle bundle);

}