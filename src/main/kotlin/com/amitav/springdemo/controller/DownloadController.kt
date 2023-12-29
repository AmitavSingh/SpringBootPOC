package com.amitav.springdemo.controller

import com.amitav.springdemo.service.PhotoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
internal class DownloadController : ErrorController {

    @Autowired
    private val photoService: PhotoService = PhotoService()

    @GetMapping("/download/{id}")
    fun downloadPhoto(@PathVariable id: String): ResponseEntity<ByteArray> {
        val photo = photoService.getPhotosById(id)
        val headers = HttpHeaders()
        headers.contentType = MediaType.valueOf(photo.fileType ?: MediaType.IMAGE_JPEG.type)
        val contentDisposition = ContentDisposition
            .builder("attachment")
            .filename(photo.fileName)
            .build()
        headers.contentDisposition = contentDisposition
        return ResponseEntity(photo.fileData, headers, HttpStatus.OK)
    }
}