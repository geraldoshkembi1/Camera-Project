package com.cameraewebproject.ui.controller;


import com.cameraewebproject.exceptions.CameraServiceException;
import com.cameraewebproject.io.entity.Camera;
import com.cameraewebproject.service.CameraService;
import com.cameraewebproject.ui.model.request.CameraRequestModel;
import com.cameraewebproject.ui.model.response.OperationStatusModel;
import com.cameraewebproject.ui.model.response.RequestOperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cameras")  //http:localhost:8080/cameras
public class CameraController {

    @Autowired
    CameraService cameraService;


    @GetMapping(path = "/{id}")
    public Camera getCamera(@PathVariable(value = "id") long Cid) {
        Camera returnCamera = cameraService.getCamera(Cid);

        return returnCamera;
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Camera createCamera(@RequestBody CameraRequestModel cameraRequestModel) throws CameraServiceException {

        Camera createdCamera = cameraService.createCamera(cameraRequestModel);

        return createdCamera;
    }



    @PutMapping(path = "/{id}")
    public Camera updateCamera(@PathVariable String id, @RequestBody CameraRequestModel cameraRequestModel) {
        Camera updatedCamera = cameraService.updateCamera(id,cameraRequestModel);
        return updatedCamera;
    }



    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteCamera(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName("DELETE");
        cameraService.deleteCamera(id);
        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

        return returnValue;
    }



    @GetMapping
    public List<Camera> getCameras(@RequestParam(value="page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "10") int limit){
            List<Camera> returnCameras = cameraService.getCameras(page,limit);
                return returnCameras;

    }

    @GetMapping
    public List<Camera> getCamerasByName(@RequestParam String name){
        List<Camera> returnCameras = cameraService.getCamerasByName(name);
        return returnCameras;
    }
}
