package com.glucode.about_you.about

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.glucode.about_you.about.views.ProfileView
import com.glucode.about_you.about.views.QuestionCardView
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.mockdata.MockData
import com.glucode.about_you.service.LocalRoom
import java.io.ByteArrayOutputStream
import java.io.InputStream

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpQuestions()
        setUpProfile()
    }

    private fun setUpQuestions() {
        val engineerName = arguments?.getString("name")
        val engineer = MockData.engineers.first { it.name == engineerName }

        engineer.questions.forEach { question ->
            val questionView = QuestionCardView(requireContext())
            questionView.title = question.questionText
            questionView.answers = question.answerOptions
            questionView.selection = question.answer.index


            binding.container.addView(questionView)
        }
        //  binding.profileContainer.
    }

    private fun setUpProfile() {
        val engineerName = arguments?.getString("name")
        val engineer = MockData.engineers.first { it.name == engineerName }

        val profileView = ProfileView(requireContext(), this)
        profileView.engineerName = engineer.name
        profileView.engineerJobRole = engineer.role
        profileView.engineerNumberOfCoffees = engineer.quickStats.coffees
        profileView.engineerNumberOfYears = engineer.quickStats.years
        profileView.engineerNumberOfBugs = engineer.quickStats.bugs

        profileView.setUpProfileImage()
        profileView.setProfileImageListener { r1 ->
            profileView.engineerProfileImage = r1
            var localDB: LocalRoom = LocalRoom(requireContext())
            localDB.saveImage(engineer, uriToBase64(context, r1))

        }

        binding.profileContainer.addView(profileView)
    }

    fun uriToBase64(context: Context?, uri: Uri?): String? {
        var inputStream: InputStream? = null
        try {
            inputStream = uri?.let { context?.contentResolver?.openInputStream(it) }
            val byteArrayOutputStream = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var len: Int
            while (inputStream?.read(buffer).also { len = it!! } != -1) {
                byteArrayOutputStream.write(buffer, 0, len)
            }
            val data = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(data, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                inputStream?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return null
    }

}