package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.repository.FirebaseRepository
import com.revan.hanged.domain.model.Room
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke () : List<Room> {
        return firebaseRepository.getRooms()
    }
}