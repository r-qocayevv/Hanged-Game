package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetUsernameFomFirestoreUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
) {

    suspend operator fun invoke (userUid : String) : String {
        return firebaseRepository.getUsernameFromFirestore(userUid = userUid)
    }

}