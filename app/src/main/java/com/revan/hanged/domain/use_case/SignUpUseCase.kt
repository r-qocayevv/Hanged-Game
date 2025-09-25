package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.FirebaseRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke (username : String, email : String, password : String) : String {
        val userUid = firebaseRepository.signUp(email,password)
        return userUid
    }

}