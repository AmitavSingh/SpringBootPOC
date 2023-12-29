package com.amitav.springdemo.service

import com.amitav.springdemo.repository.PhotoRepository
import com.amitav.springdemo.model.Photo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class PhotoService {

    private lateinit var repository: PhotoRepository

    @Autowired
    fun init(repository: PhotoRepository){
        this.repository = repository
    }

    fun getAllPhotos(): MutableIterable<Photo> = repository.findAll()

    fun getPhotosById(id: String): Photo {
        return repository.findById(id).orElse(null)
    }

    fun deletePhoto(id: String) {
        repository.deleteById(id)
    }

    fun createPhoto(photo: Photo): Photo {
        return repository.save(photo)
    }

    fun uploadPhoto(file: MultipartFile): Photo {
        val photo = Photo(
            id = null,
            fileName = file.originalFilename ?: file.name,
            fileData = file.bytes,
            fileType = file.contentType
        )
        return repository.save(photo)
    }
}