package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.repository.DataStorePreferencesRepository
import javax.inject.Inject

class SaveLoginStateUseCase @Inject constructor(
    private val dataStorePreferencesRepository: DataStorePreferencesRepository
) {
    suspend operator fun invoke(isLoggedIn: Boolean) {
        dataStorePreferencesRepository.saveLoginState(isLoggedIn)
    }
}