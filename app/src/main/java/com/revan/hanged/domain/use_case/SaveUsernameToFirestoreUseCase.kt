package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.repository.FirebaseRepository
import javax.inject.Inject

class SaveUsernameToFirestoreUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke (username : String, userUid : String,email : String) {
        firebaseRepository.saveUsernameToFirestore(username = username, userUid = userUid, email = email)
    }

}