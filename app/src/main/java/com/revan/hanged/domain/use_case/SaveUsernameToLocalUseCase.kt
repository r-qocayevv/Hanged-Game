package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.repository.DataStorePreferencesRepository
import javax.inject.Inject

class SaveUsernameToLocalUseCase @Inject constructor (
    private val dataStorePreferencesRepository: DataStorePreferencesRepository
) {

    suspend operator fun invoke(username : String) {
        dataStorePreferencesRepository.saveUsername(username = username)
    }

}