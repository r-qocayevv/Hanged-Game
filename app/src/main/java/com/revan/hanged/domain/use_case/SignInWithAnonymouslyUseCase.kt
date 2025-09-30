package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.repository.FirebaseRepository
import javax.inject.Inject

class SignInWithAnonymouslyUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke () {
        firebaseRepository.signInWithAnonymously()
    }
}