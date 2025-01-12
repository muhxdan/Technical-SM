package com.mdf.test.samir.di

import com.mdf.test.samir.data.repository.LoanRepositoryImpl
import com.mdf.test.samir.domain.repository.LoanRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    /**
     * Binds the implementation of LoanRepository to be injected wherever LoanRepository is required.
     * It allows Hilt to know that whenever LoanRepository is needed, the LoanRepositoryImpl will be provided.
     *
     * @param loanRepositoryImpl The implementation of the LoanRepository interface.
     * @return LoanRepository implementation that is injected wherever needed.
     */
    @Binds
    abstract fun provideLoanRepository(loanRepositoryImpl: LoanRepositoryImpl): LoanRepository

}