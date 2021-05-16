package com.cameraewebproject.service.impl;

import com.cameraewebproject.exceptions.CameraServiceException;
import com.cameraewebproject.io.entity.Camera;
import com.cameraewebproject.io.repository.CameraRepository;
import com.cameraewebproject.service.CameraService;
import com.cameraewebproject.ui.model.request.CameraRequestModel;
import com.cameraewebproject.ui.model.response.ErrorMessages;
import com.cameraewebproject.ui.model.response.OperationStatusModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CameraServiceImpl implements CameraService {

    @Autowired
    CameraRepository cameraRepository;

    @Autowired
    SequenceGeneratorServiceImpl Gservice;


    @Override
    public Camera createCamera(CameraRequestModel camera) {

//        if (cameraRepository.findByIp(camera.getIp()) != null) {
//            throw new CameraServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
//        }

        Camera returnCamera = new Camera();
        BeanUtils.copyProperties(camera, returnCamera);

        returnCamera.setId(Gservice.generateSequence(returnCamera.SEQUENCE_NAME));
        cameraRepository.save(returnCamera);
        return returnCamera;

    }

//    @Override
//    public Camera getCamera(long id) {
//        Camera returnCamera = cameraRepository.findById(id);
//
//        if (returnCamera == null) {
//            throw new CameraServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
//        } else
//            return  returnCamera;
//    }



//    @Override
//    public List<Camera> getCameras(int page, int limit) {
//
//        if(page > 0) page--;
//
//        PageRequest pageableRequest = PageRequest.of(page, limit);
//        Page<Camera> cameraPage = cameraRepository.findAll(pageableRequest);
//        System.out.println(cameraPage.getTotalElements());
//        List<Camera> returnValue =cameraPage.getContent();
//
//        return returnValue;
//
//    }

    @Override
    public List<Camera> getCameras() {


        List<Camera> returnValue =cameraRepository.findAll();;

        return returnValue;

    }

    @Override
    public Camera updateCamera(long id,CameraRequestModel cameraRequestModel){

        Camera foundCamera = cameraRepository.findById(id);
//        if (cameraRepository.findByIp(foundCamera.getIp()) != null) {
//            throw new CameraServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
//        }

        if( foundCamera == null){ throw new CameraServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());}

        foundCamera.setName(cameraRequestModel.getName());
        foundCamera.setResolutions(cameraRequestModel.getResolutions());
        foundCamera.setModel(cameraRequestModel.getModel());
        cameraRepository.save(foundCamera);

        return foundCamera;
    }

    @Override
    public void deleteCamera(long id){
        Camera returnCamera = cameraRepository.findById(id);
        if (returnCamera == null) {
            throw new CameraServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        cameraRepository.delete(returnCamera);
    }


}
