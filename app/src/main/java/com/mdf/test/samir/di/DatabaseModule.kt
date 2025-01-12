package com.mdf.test.samir.di

import android.content.Context
import androidx.room.Room
import com.mdf.test.samir.data.datasource.local.database.AppDatabase
import com.mdf.test.samir.utils.Constants.DATABASE_NAME
import com.mdf.test.samir.utils.Constants.PASSPHRASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Passphrase for encrypting the database. The passphrase will converted into a byte array.
    private val passphrase = SQLiteDatabase.getBytes(PASSPHRASE.toCharArray())

    // SupportFactory is used to create the encrypted database.
    private val factory = SupportFactory(passphrase)

    /**
     * Provides the AppDatabase instance to be used throughout the app.
     *
     * @param context The application context to access the file system.
     * @return The AppDatabase instance.
     */
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()

    /**
     * Provides the LoanDao instance that interacts with the database.
     *
     * @param appDatabase The AppDatabase instance from which LoanDao is extracted.
     * @return The LoanDao instance.
     */
    @Singleton
    @Provides
    fun provideLoanDao(appDatabase: AppDatabase) = appDatabase.loanDao()
}
