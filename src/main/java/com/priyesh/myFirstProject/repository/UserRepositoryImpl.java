package com.priyesh.myFirstProject.repository;

import com.priyesh.myFirstProject.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.schema.JsonSchemaObject;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<UserEntity> getUserForSA(){
        Query query = new Query();

        /// this is the example how to write query
//        query.addCriteria(Criteria.where("userName").is("priyesh"));

        /// to use regex to check email is valid or not
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

        ///  because I have written two Criteria the default condition between then will be and [&]
        ///  the .ne() means not equal null and .ne() ""
//        query.addCriteria(Criteria.where("email").exists(true));
//        query.addCriteria(Criteria.where("email").ne(null).ne(""));
//        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

        ///  if I want to use or || condition between the condition then I have to do this
        ///  create a Criteria object and then use the orOperator which take two Criteria which is separated by [,]
//        Criteria criteria = new Criteria();
//        query.addCriteria(
//                criteria.orOperator(
//                        Criteria.where("email").exists(true) ,
//                        Criteria.where("sentimentAnalysis").is(true)
//                ));

        ///  if I am playing wit arrays then
//        query.addCriteria(Criteria.where("roles").nin("USER","ADMIN")); // this is nin mean notIn
//        query.addCriteria(Criteria.where("roles").in("USER","ADMIN")); // this is in mean In /// search in roles array

        ///  if I want to play with type // for eg. get all user which has the type boolean
//        query.addCriteria(Criteria.where("sentimentAnalysis").type(JsonSchemaObject.Type.BsonType.BOOLEAN));


        List<UserEntity> userEntities = mongoTemplate.find(query, UserEntity.class);
        return userEntities;
    }
}
