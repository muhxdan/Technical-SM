package com.mdf.test.samir.data.repository

import com.mdf.test.samir.data.Resource
import com.mdf.test.samir.data.datasource.local.LocalDataSource
import com.mdf.test.samir.data.datasource.remote.RemoteDataSource
import com.mdf.test.samir.data.datasource.remote.network.ApiResponse
import com.mdf.test.samir.data.mapper.LoanMapper
import com.mdf.test.samir.domain.model.Loan
import com.mdf.test.samir.domain.model.SortType
import com.mdf.test.samir.domain.repository.LoanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoanRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val mapper: LoanMapper,
) : LoanRepository {

    /**
     * Fetches the list of loans, prioritizing the local database.
     * If the local database is null or empty, the data will be fetched from the remote API,
     * saved to the database, and then returned.
     *
     * @return A Flow that emits the loan data as a Resource, indicating loading, success, or error states.
     */
    override fun getLoans(): Flow<Resource<List<Loan>>> = flow {
        emit(Resource.Loading())

        val localLoans = localDataSource.getLoans().firstOrNull()

        if (localLoans.isNullOrEmpty()) {
            when (val apiResponse = remoteDataSource.getLoans().first()) {
                is ApiResponse.Success -> {
                    localDataSource.insertLoans(apiResponse.data.map { mapper.mapFromResponse(it) })
                    emit(Resource.Success(apiResponse.data.map { mapper.fromResponseToDomain(it) }))
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }

                ApiResponse.Empty -> {
                    emit(Resource.Success(emptyList()))
                }
            }

        } else {
            emit(Resource.Success(localLoans.map { mapper.mapFromEntity(it) }))
        }
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unknown error occurred"))
    }


    /**
     * Fetches a sorted list of loans from the local database.
     *
     * @param sortType The sorting criterion to determine the order of the loans.
     * @return A Flow that emits the sorted loan data as a Resource.
     */
    override fun getLoansSorted(sortType: SortType): Flow<Resource<List<Loan>>> =
        localDataSource.getLoansSorted(sortType.name).map { entities ->
            Resource.Success(entities.map { mapper.mapFromEntity(it) })
        }
}

