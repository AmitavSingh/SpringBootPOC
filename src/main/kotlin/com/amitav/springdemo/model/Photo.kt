package com.amitav.springdemo.model


import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType


/**
 * Model class for MongoDb
 * use @Document
 */
@Document
data class Photo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : String?,
    val fileName: String,
    val fileType: String?,
    val fileData: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Photo

        if (id != other.id) return false
        if (fileName != other.fileName) return false
        if (fileType != other.fileType) return false
        return fileData.contentEquals(other.fileData)
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + fileName.hashCode()
        result = 31 * result + (fileType?.hashCode() ?: 0)
        result = 31 * result + fileData.contentHashCode()
        return result
    }
}


/**
 * Model class for h2 In-memory Database
 * use @Entity, @Table
 */


//@Entity
//@Table("MYPHOTOS")
//data class Photo(
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id : Long = 0,
//    val fileName: String,
//    val fileType: String?,
//    val fileData: ByteArray
//) {
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as Photo
//
//        if (fileName != other.fileName) return false
//        if (fileType != other.fileType) return false
//        return fileData.contentEquals(other.fileData)
//    }
//
//    override fun hashCode(): Int {
//        var result = fileName.hashCode()
//        result = 31 * result + (fileType?.hashCode() ?: 0)
//        result = 31 * result + fileData.contentHashCode()
//        return result
//    }
//}