package com.sc.overhub

import com.sc.overhub.data.repository.ProfileRepositoryImpl
import org.junit.Assert
import org.junit.Test

class ProfileRepositoryTests {
    @Test
    fun battleTagMatch() {
        Assert.assertTrue(ProfileRepositoryImpl.validateTag("SuperCat#123"))

        Assert.assertFalse(ProfileRepositoryImpl.validateTag("SuperCat123"))
        Assert.assertFalse(ProfileRepositoryImpl.validateTag("#123"))
        Assert.assertFalse(ProfileRepositoryImpl.validateTag("SuperCat#123d"))
        Assert.assertFalse(ProfileRepositoryImpl.validateTag("SuperCat#"))
    }
}
