package com.mdf.test.samir.domain.usecase

import com.mdf.test.samir.data.Resource
import com.mdf.test.samir.domain.model.Loan
import com.mdf.test.samir.domain.model.SortType
import com.mdf.test.samir.domain.repository.LoanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoansSortedUseCase @Inject constructor(private val loanRepository: LoanRepository) {

    /**
     * Executes the use case to fetch a sorted list of loans.
     *
     * @param sortType Specifies the sorting order (e.g., by term, risk, etc.).
     * @return A Flow that emits a Resource containing the sorted list of loans.
     * Resource handles loading, success, and error states.
     */
    operator fun invoke(sortType: SortType): Flow<Resource<List<Loan>>> =
        loanRepository.getLoansSorted(sortType)
}