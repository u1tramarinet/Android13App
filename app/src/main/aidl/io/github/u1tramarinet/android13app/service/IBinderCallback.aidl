// IBinderCallback.aidl
package io.github.u1tramarinet.android13app.service;

interface IBinderCallback {
    void onBinderConnected(IBinder binder);
    void onBinderDisconnected();
}