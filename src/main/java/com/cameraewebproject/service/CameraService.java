package com.cameraewebproject.service;

import com.cameraewebproject.io.entity.Camera;
import com.cameraewebproject.ui.model.request.CameraRequestModel;
import com.cameraewebproject.ui.model.response.OperationStatusModel;

import java.util.List;

public interface CameraService {

    Camera createCamera(CameraRequestModel camera);
   // Camera getCamera(long id);
//    List<Camera> getCameras(int page,int limit);
    List<Camera> getCameras();
    Camera updateCamera(long id,CameraRequestModel cameraRequestModel);
    void deleteCamera(long id);

}
