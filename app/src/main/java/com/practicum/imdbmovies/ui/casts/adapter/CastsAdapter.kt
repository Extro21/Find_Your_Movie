package com.practicum.imdbmovies.ui.casts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.practicum.imdbmovies.R
import com.practicum.imdbmovies.databinding.CastItemBinding
import com.practicum.imdbmovies.domain.models.CastsModel

class CastsAdapter :
    ListAdapter<CastsModel, CastsAdapter.CastsHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastsHolder {
        return CastsHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CastsHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CastsHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CastItemBinding.bind(view)

        fun bind(cast: CastsModel) = with(binding) {
            val cornerSize = itemView.resources.getDimensionPixelSize(R.dimen.corners_photo_cast)
            Glide.with(itemView)
                .load(cast.photo)
                .centerCrop()
                .placeholder(R.drawable.icon_cast_default)
                .transform(RoundedCorners(cornerSize))
                .into(photoActor)

            nameValue.text = cast.name
            descriptionValue.text = cast.description
            professionValue.text = cast.profession
        }

        companion object {
            fun create(parent: ViewGroup): CastsHolder {
                return CastsHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.cast_item, parent, false)
                )
            }
        }

    }

    class ItemComparator : DiffUtil.ItemCallback<CastsModel>() {
        override fun areItemsTheSame(oldItem: CastsModel, newItem: CastsModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CastsModel, newItem: CastsModel): Boolean {
            return oldItem == newItem
        }
    }


}