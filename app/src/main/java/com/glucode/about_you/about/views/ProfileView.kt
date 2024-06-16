package com.glucode.about_you.about.views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.AttributeSet
import android.view.LayoutInflater.from
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ProfileViewDesignBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.service.LocalRoom

class ProfileView @JvmOverloads

constructor(
    context: Context,
    fragment: Fragment,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    engineer: Engineer? = null
 ) : CardView(context, attrs, defStyleAttr) {
    private val binding: ProfileViewDesignBinding =
        ProfileViewDesignBinding.inflate(from(context), this, true)

    var imageResultListener: ((Uri?) -> Unit)? = null
    val photoChooserLauncher: ActivityResultLauncher<Intent>

    init {
        radius = resources.getDimension(R.dimen.corner_radius_normal);

        photoChooserLauncher =
            fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    handleIntentResult(data)
                }
            }
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

     var engineerProfileImage: Uri? = null
         set(image) {
             field = image
             var local:LocalRoom = LocalRoom(context)
             binding.profileImage.setImageURI(image)
         }

     fun setUpProfileImage() {
        binding.profileImage.setOnClickListener(OnClickListener {

            val profilePhoto =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

            photoChooserLauncher.launch(profilePhoto)

        })
    }

    fun setProfileImageListener(listener: (Uri?) -> Unit) {
        imageResultListener = listener
    }


    fun handleIntentResult(data: Intent?) {
        val imageUri: Uri? = data?.data
        binding.profileImage.setImageURI(imageUri)
        imageResultListener?.invoke(imageUri)
    }

 }










