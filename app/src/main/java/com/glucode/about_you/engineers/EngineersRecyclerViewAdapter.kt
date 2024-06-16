package com.glucode.about_you.engineers

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ItemEngineerBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.service.LocalRoom
import android.util.Base64

class EngineersRecyclerViewAdapter(
    private var engineers: List<Engineer>,
    private val context: Context?,
    private val onClick: (Engineer) -> Unit,

) : RecyclerView.Adapter<EngineersRecyclerViewAdapter.EngineerViewHolder>() {

    override fun getItemCount() = engineers.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EngineerViewHolder {
        return EngineerViewHolder(ItemEngineerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EngineerViewHolder, position: Int) {
        holder.bind(engineers[position], onClick)
    }

    inner class EngineerViewHolder(private val binding: ItemEngineerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(engineer: Engineer, onClick: (Engineer) -> Unit) {
            binding.name.text = engineer.name
            binding.role.text = engineer.role
            binding.root.setOnClickListener {
                onClick(engineer)
            }
            //TODO - set profile picture
            val localDB:LocalRoom = LocalRoom(context)
            val profileImage :String? = localDB.getImage(engineer)
           var num : Int = profileImage!!.length

            if(profileImage!=null&&num>1){
                val myByte = Base64.decode(profileImage,Base64.DEFAULT)
                val myBitMap = BitmapFactory.decodeByteArray(myByte,0,myByte.size)
                binding.profileImage.setImageBitmap(myBitMap)
            }else{
                binding.profileImage.setImageDrawable(context?.getDrawable(R.drawable.baseline_person_24))
            }

        }
    }
}