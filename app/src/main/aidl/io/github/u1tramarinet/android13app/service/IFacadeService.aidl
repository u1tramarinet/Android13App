// IFacadeService.aidl
package io.github.u1tramarinet.android13app.service;

import io.github.u1tramarinet.android13app.service.IBinderCallback;

interface IFacadeService {
    IBinder getRemoteBinder();
    void registerBinderCallback(IBinderCallback callback);
}