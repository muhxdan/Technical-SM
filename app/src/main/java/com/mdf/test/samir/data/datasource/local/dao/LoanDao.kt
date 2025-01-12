package com.mdf.test.samir.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mdf.test.samir.data.datasource.local.entity.LoanEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LoanDao {

    /**
     * Retrieves all loans from the database.
     *
     * @return A Flow emitting a list of all LoanEntity objects.
     */
    @Query("SELECT * FROM loans ORDER BY created_at ASC")
    fun getLoans(): Flow<List<LoanEntity>>

    /**
     * Inserts a list of loans into the database, replacing existing records in case of conflict.
     *
     * @param loans The list of LoanEntity objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoans(loans: List<LoanEntity>)

    /**
     * Retrieves all loans from the database sorted dynamically based on the provided sort type.
     * Sorting can be based on term, risk rating, or creation date, with ascending or descending order.
     *
     * @param sortType A string indicating the sort criteria (e.g., 'TERM_ASC', 'TERM_DESC', 'RISK_RATING_ASC', 'RISK_RATING_DESC').
     * @return A Flow emitting a list of sorted LoanEntity objects.
     */
    @Query(
        """
    SELECT * FROM loans 
    ORDER BY 
    CASE 
        WHEN :sortType = 'TERM_ASC' THEN term 
        WHEN :sortType = 'RISK_RATING_ASC' THEN risk_rating 
        ELSE created_at 
    END ASC,
    CASE 
        WHEN :sortType = 'TERM_DESC' THEN term 
        WHEN :sortType = 'RISK_RATING_DESC' THEN risk_rating 
        ELSE created_at 
    END DESC
    """
    )
    fun getLoansSorted(sortType: String): Flow<List<LoanEntity>>

}
