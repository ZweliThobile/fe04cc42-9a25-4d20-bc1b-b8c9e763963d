package com.glucode.about_you.engineers

import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class EngineersFragmentTest {


    //A test to check whether
    @Test
    fun testAscendingList() {
        var testList : List<Engineer> = listOf()
        testList = MockData.engineers
      assertTrue(isAscending(testList.sortedBy { it.quickStats.years }))
    }

    private fun isAscending(list:List<Engineer>):Boolean{
        for(i in 0 until list.size-1){
            if(list[i].quickStats.years > list[i + 1].quickStats.years){
                return false
            }
        }

        return  true
    }
}