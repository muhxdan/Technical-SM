package com.mdf.test.samir.data.mapper

import com.mdf.test.samir.data.datasource.local.entity.BorrowerEntity
import com.mdf.test.samir.data.datasource.local.entity.CollateralEntity
import com.mdf.test.samir.data.datasource.local.entity.DocumentEntity
import com.mdf.test.samir.data.datasource.local.entity.InstallmentEntity
import com.mdf.test.samir.data.datasource.local.entity.LoanEntity
import com.mdf.test.samir.data.datasource.local.entity.RepaymentScheduleEntity
import com.mdf.test.samir.data.datasource.remote.response.LoanResponse
import com.mdf.test.samir.domain.model.Borrower
import com.mdf.test.samir.domain.model.Collateral
import com.mdf.test.samir.domain.model.Document
import com.mdf.test.samir.domain.model.Installment
import com.mdf.test.samir.domain.model.Loan
import com.mdf.test.samir.domain.model.RepaymentSchedule
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoanMapper @Inject constructor() {
    fun mapFromEntity(entity: LoanEntity): Loan {
        return Loan(
            id = entity.id,
            amount = entity.amount,
            term = entity.term,
            purpose = entity.purpose,
            interestRate = entity.interestRate,
            riskRating = entity.riskRating,
            borrower = Borrower(
                entity.borrower.id,
                entity.borrower.name,
                entity.borrower.email,
                entity.borrower.creditScore
            ),
            collateral = Collateral(entity.collateral.type, entity.collateral.value),
            repaymentSchedule = RepaymentSchedule(entity.repaymentSchedule.installments.map {
                Installment(
                    it.amountDue, it.dueDate
                )
            }),
            documents = entity.documents?.map { Document(it.type, it.url) },
            createdAt = entity.createdAt
        )
    }

    fun mapFromResponse(response: LoanResponse): LoanEntity {
        return LoanEntity(
            id = response.id,
            interestRate = response.interestRate as Double,
            amount = response.amount,
            purpose = response.purpose,
            documents = response.documents?.map { DocumentEntity(it.type, it.url) },
            borrower = BorrowerEntity(
                response.borrowerResponse.creditScore,
                response.borrowerResponse.name,
                response.borrowerResponse.id,
                response.borrowerResponse.email
            ),
            term = response.term,
            collateral = CollateralEntity(
                response.collateralResponse.type, response.collateralResponse.value
            ),
            repaymentSchedule = RepaymentScheduleEntity(response.repaymentScheduleResponse.installments.map {
                InstallmentEntity(
                    it.amountDue, it.dueDate
                )
            }),
            riskRating = response.riskRating,
        )
    }

    fun fromResponseToDomain(response: LoanResponse): Loan {
        return Loan(
            id = response.id,
            interestRate = response.interestRate as Double,
            amount = response.amount,
            purpose = response.purpose,
            documents = response.documents?.map { Document(it.type, it.url) },
            borrower = Borrower(
                response.borrowerResponse.id,
                response.borrowerResponse.name,
                response.borrowerResponse.email,
                response.borrowerResponse.creditScore
            ),
            term = response.term,
            collateral = Collateral(
                response.collateralResponse.type, response.collateralResponse.value
            ),
            repaymentSchedule = RepaymentSchedule(response.repaymentScheduleResponse.installments.map {
                Installment(
                    it.amountDue, it.dueDate
                )
            }),
            riskRating = response.riskRating,
            createdAt = System.currentTimeMillis()
        )
    }
}
