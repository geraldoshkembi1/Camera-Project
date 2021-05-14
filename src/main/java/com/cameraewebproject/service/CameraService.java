package com.cameraewebproject.service;

import com.cameraewebproject.io.entity.Camera;
import com.cameraewebproject.ui.model.request.CameraRequestModel;
import com.cameraewebproject.ui.model.response.OperationStatusModel;

import java.util.List;

public interface CameraService {

    Camera createCamera(CameraRequestModel camera);
    Camera getCamera(String id);
    List<Camera> getCameras(int page,int limit);
    Camera updateCamera(String id,CameraRequestModel cameraRequestModel);
    void deleteCamera(String id);
}
