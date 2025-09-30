package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.repository.DataStorePreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsernameFromLocalUseCase @Inject constructor(
    private val dataStorePreferencesRepository: DataStorePreferencesRepository
) {

    operator fun invoke(): Flow<String?> {
        return dataStorePreferencesRepository.getUsername()
    }


}