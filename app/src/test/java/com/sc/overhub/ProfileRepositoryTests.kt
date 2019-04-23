package com.sc.overhub

import com.sc.overhub.repository.ProfileRepository
import org.junit.Assert
import org.junit.Test

class ProfileRepositoryTests {
    @Test
    fun battleTagMatch() {
        Assert.assertTrue(ProfileRepository.validateTag("SuperCat#123"))

        Assert.assertFalse(ProfileRepository.validateTag("SuperCat123"))
        Assert.assertFalse(ProfileRepository.validateTag("#123"))
        Assert.assertFalse(ProfileRepository.validateTag("SuperCat#123d"))
        Assert.assertFalse(ProfileRepository.validateTag("SuperCat#"))
    }
}
