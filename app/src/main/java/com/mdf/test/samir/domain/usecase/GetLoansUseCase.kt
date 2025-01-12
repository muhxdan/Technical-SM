package com.mdf.test.samir.domain.usecase

import com.mdf.test.samir.data.Resource
import com.mdf.test.samir.domain.model.Loan
import com.mdf.test.samir.domain.repository.LoanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoansUseCase @Inject constructor(private val loanRepository: LoanRepository) {

    /**
     * Executes the use case to fetch the list of loans.
     *
     * @return A Flow that emits a Resource containing the list of loans.
     * Resource handles loading, success, and error states.
     */
    operator fun invoke(): Flow<Resource<List<Loan>>> =
        loanRepository.getLoans()
}