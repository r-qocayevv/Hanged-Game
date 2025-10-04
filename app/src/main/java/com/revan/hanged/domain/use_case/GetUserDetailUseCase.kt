package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.repository.HangedRepository
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val hangedRepository: HangedRepository
) {
    suspend operator fun invoke(userId: String) = hangedRepository.getUserData(userId)

}