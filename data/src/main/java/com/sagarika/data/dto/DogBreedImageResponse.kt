package com.sagarika.data.dto

data class DogBreedImageResponse(
    val message: List<String>,
    val status: String
) {
    companion object {
        const val SUCCESS_STATUS = "success"
    }
}