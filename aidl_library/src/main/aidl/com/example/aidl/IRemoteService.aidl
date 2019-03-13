// IRemoteService.aidl
package com.example.aidl;

// Declare any non-default types here with import statements
import com.example.aidl.IParticipateCallback;

interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,double aDouble, String aString);

    int someOperate(int a, int b);

    void join(IBinder token, String name);
    void leave(IBinder token);

    List<String> getParticipators();

    void registerParticipateCallback(IParticipateCallback cb);
    void unregisterParticipateCallback(IParticipateCallback cb);
}
