package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.FirebaseRepository
import javax.inject.Inject

class SignInWithEmailUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke (email : String, password : String) : String {
        val userUid  = firebaseRepository.signInWithEmail(email = email, password = password)
        return userUid
    }
}