package com.example.seasalonapp.data.model.response.branchsalon

data class BranchSalonResponse(
    val meta: Meta,
    val data: BranchData
)

data class Meta(
    val code: Int,
    val status: String,
    val message: String
)

data class BranchData(
    val branch_salon: List<BranchSalon>
)

data class BranchSalon(
    val id: Int,
    val branch_name: String,
    val branch_address: String,
    val picturePath: String,
    val opening_time: String,
    val closing_time: String,
    val created_at: String,
    val updated_at: String,
    val services: String
)
