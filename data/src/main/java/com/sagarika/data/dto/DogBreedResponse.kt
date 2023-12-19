package com.sagarika.data.dto


data class DogBreedResponse(
    val status: String,
    val message: Map<String, List<String>>
) {
    companion object {
        const val SUCCESS_STATUS = "success"
    }
}
