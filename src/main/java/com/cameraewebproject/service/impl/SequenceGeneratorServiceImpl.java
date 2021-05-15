package com.cameraewebproject.service.impl;

import com.cameraewebproject.io.entity.DatabaseSequence;
import com.cameraewebproject.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {

    private MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorServiceImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }



    @Override
    public long generateSequence(String seqName) {

        Query query = new Query((Criteria.where("id").is(seqName)));
        Update update = new Update().inc("seq",1);
        DatabaseSequence counter = mongoOperations.findAndModify(query,
                update,options().returnNew(true).upsert(true),DatabaseSequence.class);

        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }
}
