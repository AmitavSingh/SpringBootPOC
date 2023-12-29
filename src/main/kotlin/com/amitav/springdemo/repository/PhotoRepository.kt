package com.amitav.springdemo.repository

import com.amitav.springdemo.model.Photo
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository

//interface PhotoRepository : CrudRepository<Photo, Int> {
//
//}


interface PhotoRepository : MongoRepository<Photo, String> {

}