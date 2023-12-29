package com.amitav.springdemo.controller

import com.amitav.springdemo.model.Photo
import com.amitav.springdemo.service.PhotoService
import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
internal class PhotosController : ErrorController {

    @Autowired
    private val photoService: PhotoService = PhotoService()

    @RequestMapping("/")
    fun hello(): String {
        return "Hello World"
    }

    @GetMapping("/test")
    fun welcomeMessage() = "Welcome to my project"

    @GetMapping("/getPhotos")
    fun getPhotos() : MutableIterable<Photo> = photoService.getAllPhotos()

    @GetMapping("/getPhoto/{id}")
    fun getPhoto(@PathVariable id: String) : Photo = photoService.getPhotosById(id)

    @DeleteMapping("/deletePhoto/{id}")
    fun deletePhoto(@PathVariable id: String) = photoService.deletePhoto(id)

    @PostMapping("/createPhoto")
    fun createPhoto(@RequestBody @Validated photo: Photo): Photo {
        return photoService.createPhoto(photo)
    }

    @PostMapping("/uploadPhoto")
    fun uploadPhoto(@RequestPart("data") file: MultipartFile): Photo {
        return photoService.uploadPhoto(file)
    }

    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest): String {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)
        if (status != null) {
            val statusCode = Integer.valueOf(status.toString())
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404"
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500"
            }
        }
        return "error"
    }
}