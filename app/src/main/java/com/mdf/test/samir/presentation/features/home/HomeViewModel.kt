package com.mdf.test.samir.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdf.test.samir.data.Resource
import com.mdf.test.samir.domain.model.Loan
import com.mdf.test.samir.domain.model.SortType
import com.mdf.test.samir.domain.usecase.GetLoansSortedUseCase
import com.mdf.test.samir.domain.usecase.GetLoansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLoansUseCase: GetLoansUseCase,
    private val getLoansSortedUseCase: GetLoansSortedUseCase
) : ViewModel() {
    private val _loanState = MutableStateFlow<Resource<List<Loan>>>(Resource.Loading())
    val loanState: StateFlow<Resource<List<Loan>>> = _loanState

    init {
        getLoans()
    }

    /**
     * Fetches all loans from the repository and updates the loan state.
     * The state is updated based on the result of the use case (loading, success, or error).
     */
    private fun getLoans() = viewModelScope.launch {
        getLoansUseCase().collect { resource ->
            _loanState.value = resource
        }
    }

    /**
     * Fetches sorted loans from the repository based on the provided sort type.
     * The state is updated based on the result of the use case (loading, success, or error).
     *
     * @param sortType The sorting criterion used to order the loans.
     */
    fun getLoansSorted(sortType: SortType) = viewModelScope.launch {
        getLoansSortedUseCase(sortType).collect { resource ->
            _loanState.value = resource
        }
    }
}


