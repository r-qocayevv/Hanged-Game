package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.FirebaseRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    operator fun invoke () {
        firebaseRepository.logOut()
    }

}