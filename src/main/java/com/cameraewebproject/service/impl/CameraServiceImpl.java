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
        System.out.println("Entered into createCamera method of service.");
        if (cameraRepository.findByName(camera.getName()) != null) {
            throw new CameraServiceException("record already exist");
        }
        Camera returnCamera = new Camera();
        BeanUtils.copyProperties(camera, returnCamera);

        returnCamera.setId(Gservice.generateSequence(returnCamera.SEQUENCE_NAME));
        cameraRepository.save(returnCamera);
        return returnCamera;

    }

    @Override
    public Camera getCamera(long id) {
        Camera returnCamera = cameraRepository.findById(id);

        if (returnCamera == null) {
            throw new CameraServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        } else
            return  returnCamera;
    }



    @Override
    public List<Camera> getCameras(int page, int limit) {

        if(page > 0) page--;

        PageRequest pageableRequest = PageRequest.of(page, limit);
        Page<Camera> usersPage = cameraRepository.findAll(pageableRequest);

        List<Camera> returnValue =usersPage.getContent();

        return returnValue;



    }

    @Override
    public Camera updateCamera(String id,CameraRequestModel cameraRequestModel){

        Optional<Camera> foundCamera = cameraRepository.findById(id);

        if( foundCamera == null) throw new CameraServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        foundCamera.get().setIp(cameraRequestModel.getIp());
        foundCamera.get().setModel(cameraRequestModel.getIp());
        foundCamera.get().setName(cameraRequestModel.getIp());
        foundCamera.get().setIp(cameraRequestModel.getIp());

        return foundCamera.get();
    }

    @Override
    public void deleteCamera(String id){
        Optional<Camera> returnCamera = cameraRepository.findById(id);
        if (returnCamera.isPresent()) {
            throw new CameraServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        cameraRepository.delete(returnCamera.get());
    }

    @Override
    public List<Camera> getCamerasByName(String name){
        return new ArrayList<>();
    }

}
