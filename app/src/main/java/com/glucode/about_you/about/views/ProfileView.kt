package com.glucode.about_you.about.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater.from
import androidx.cardview.widget.CardView
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ProfileViewDesignBinding

class ProfileView @JvmOverloads  constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): CardView(context, attrs, defStyleAttr){
    private val binding: ProfileViewDesignBinding =
        ProfileViewDesignBinding.inflate( from(context),this,true)



    init {
radius = resources.getDimension(R.dimen.corner_radius_normal);
    }
    var engineerName: String? = null
        set(name) {
            field = name
            binding.engineerName.text = name
        }

    var engineerJobRole: String? = null
        set(role) {
            field = role
            binding.engineerJobTitle.text = role
        }

    var engineerNumberOfYears: Int? = null
        set(years) {
            field = years
            binding.numYears.text = years.toString()
        }


    var engineerNumberOfCoffees: Int? = null
        set(coffees) {
            field = coffees
            binding.numCoffees.text = coffees.toString()
        }


    var engineerNumberOfBugs: Int? = null
        set(bugs) {
            field = bugs
            binding.numBugs.text = bugs.toString()
        }


}