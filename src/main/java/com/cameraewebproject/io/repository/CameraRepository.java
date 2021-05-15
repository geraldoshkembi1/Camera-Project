package com.cameraewebproject.io.repository;

import com.cameraewebproject.io.entity.Camera;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface  CameraRepository extends MongoRepository<Camera,String> {
     Camera findByModel(String model);
     Camera findByIp(String ip);
     Camera findById(long id);

}
