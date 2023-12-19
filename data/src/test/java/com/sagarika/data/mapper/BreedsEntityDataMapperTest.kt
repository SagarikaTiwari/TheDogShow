package com.sagarika.data.mapper

import com.sagarika.data.dto.BreedListDTO
import org.junit.Assert.*
import org.junit.Test

class BreedsEntityDataMapperTest {

    private companion object {
        private lateinit var messageEntityDataMapper: MessageEntityDataMapper
        private lateinit var breedsEntityDataMapper: BreedsEntityDataMapper
        val message = Message(
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            listOf("kelpie", "shepherd"), listOf("indian"), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList()
        )
        val breedListDTO = BreedListDTO(message, "success")

        val messageModel = MessageModel(
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            listOf("kelpie", "shepherd"), listOf("indian"), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList()
        )

        val breedsModel = BreedsModel(messageModel, "success")
    }

    @Test
    fun `when mapper function is called then it maps breedListDTO to breedListModel`() {

        messageEntityDataMapper = MessageEntityDataMapper()
        breedsEntityDataMapper = BreedsEntityDataMapper(messageEntityDataMapper)
        val breedListRes = breedsEntityDataMapper.mapBreedsEntityToBreeds(breedListDTO)

        assert(breedListRes.status == "success")
        assert(breedListRes.messageModel.australian[0]=="kelpie")
    }

}